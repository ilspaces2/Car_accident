package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository
public class AccidentJdbcTemplate {

    private JdbcTemplate jdbc;
    private AccidentTypeJdbcTemplate type;

    public AccidentJdbcTemplate(JdbcTemplate jdbc, AccidentTypeJdbcTemplate type) {
        this.jdbc = jdbc;
        this.type = type;
    }

    public void addAccident(Accident accident) {
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
    }

    public Collection<Accident> getAccidents() {
        return jdbc.query("select id,name,text,address,type_id from accident",
                (resultSet, row) -> makeAccident(resultSet));
    }

    public Accident findByIdAccident(int id) {
        return jdbc.queryForObject("select id,name,text,address,type_id from accident where id= ?",
                (resultSet, row) -> makeAccident(resultSet), id);
    }

    public void update(Accident accident) {
        jdbc.update("update accident set name=?, text=?, address=? where id=?",
                accident.getName(),
                accident.getText(),
                accident.getAddress(),
                accident.getId());
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
        accident.setAccidentType(type.findByIdType(resultSet.getInt("type_id")));
        fillRules(accident);
        return accident;
    }
}
