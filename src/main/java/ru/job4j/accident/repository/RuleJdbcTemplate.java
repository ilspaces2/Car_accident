package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

@Repository
public class RuleJdbcTemplate {

    private JdbcTemplate jdbc;

    public RuleJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<Rule> getRules() {
        return jdbc.query("select id, name from rule",
                (resultSet, row) -> makeRule(resultSet));
    }

    public Rule findByIdRule(int id) {
        return jdbc.queryForObject("select id, name from rule where id= ?",
                (resultSet, row) -> makeRule(resultSet), id);
    }

    private Rule makeRule(ResultSet resultSet) throws SQLException {
        Rule rule = new Rule();
        rule.setId(resultSet.getInt("id"));
        rule.setName(resultSet.getString("name"));
        return rule;
    }
}
