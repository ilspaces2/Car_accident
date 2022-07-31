package ru.job4j.accident.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.job4j.accident.model.AccidentType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/*
@Repository
 */
public class AccidentTypeJdbcTemplate {

    private JdbcTemplate jdbc;

    public AccidentTypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Collection<AccidentType> getTypes() {
        return jdbc.query("select id, name from type",
                (resultSet, row) -> makeType(resultSet));
    }

    public AccidentType findByIdType(int id) {
        return jdbc.queryForObject("select id, name from type where id= ?",
                (resultSet, row) -> makeType(resultSet), id);
    }

    private AccidentType makeType(ResultSet resultSet) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(resultSet.getInt("id"));
        type.setName(resultSet.getString("name"));
        return type;
    }
}
