package ru.job4j.accident.repository.springJdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("SpringJDBC")
public class AccidentJdbcTemplate implements AccidentRepository {

    private final JdbcTemplate jdbc;
    private final AccidentTypeJdbcTemplate type;

    public AccidentJdbcTemplate(JdbcTemplate jdbc, AccidentTypeJdbcTemplate type) {
        this.jdbc = jdbc;
        this.type = type;
    }

    @Override
    public void save(Accident accident) {
        if (accident.getId() == 0) {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbc.update(connect -> {
                PreparedStatement ps = connect.prepareStatement(
                        "insert into accident (name, text, address, type_id) values (?,?,?,?)",
                        new String[]{"id"});
                ps.setString(1, accident.getName());
                ps.setString(2, accident.getText());
                ps.setString(3, accident.getAddress());
                ps.setInt(4, accident.getAccidentType().getId());
                return ps;
            }, keyHolder);
            List<Rule> rules = accident.getRules().stream().toList();
            jdbc.batchUpdate("insert into accident_rule (accident_id, rule_id) values (?,?)", rules, rules.size(),
                    (resultSet, rule) -> {
                        resultSet.setInt(1, (int) keyHolder.getKey());
                        resultSet.setInt(2, rule.getId());
                    }
            );
        } else {
            jdbc.update("update accident set name=?, text=?, address=? where id=?",
                    accident.getName(),
                    accident.getText(),
                    accident.getAddress(),
                    accident.getId());
        }
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbc.query("select * from accident",
                (resultSet, row) -> makeAccident(resultSet));
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(
                jdbc.queryForObject("select * from accident where id= ?",
                        (resultSet, row) -> makeAccident(resultSet), id)
        );
    }

    private void fillRules(Accident accident) {
        jdbc.query("select id,name from rule join accident_rule on rule.id=rule_id and accident_id=?",
                (resultSet, row) -> {
                    Rule rule = new Rule();
                    rule.setId(resultSet.getInt("id"));
                    rule.setName(resultSet.getString("name"));
                    accident.addRule(rule);
                    return rule;
                }, accident.getId());
    }

    private Accident makeAccident(ResultSet resultSet) throws SQLException {
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("id"));
        accident.setName(resultSet.getString("name"));
        accident.setText(resultSet.getString("text"));
        accident.setAddress(resultSet.getString("address"));
        accident.setAccidentType(
                type.findById(resultSet.getInt("type_id")).orElse(null));
        fillRules(accident);
        return accident;
    }
}
