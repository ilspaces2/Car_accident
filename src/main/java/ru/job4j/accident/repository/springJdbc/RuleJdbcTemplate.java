package ru.job4j.accident.repository.springJdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("SpringJDBC")
public class RuleJdbcTemplate implements RuleRepository {

    private final JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Rule> findAll() {
        return jdbc.query("select * from rule",
                (resultSet, row) -> makeRule(resultSet));
    }

    public Optional<Rule> findByIdRule(int id) {
        return Optional.ofNullable(
                jdbc.queryForObject("select * from rule where id= ?",
                        (resultSet, row) -> makeRule(resultSet), id));
    }

    private Rule makeRule(ResultSet resultSet) throws SQLException {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("id"));
        rule.setName(resultSet.getString("name"));
        return rule;
    }
}
