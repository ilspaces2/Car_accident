package ru.job4j.accident.repository.springJdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("SpringJDBC")
public class AccidentTypeJdbcTemplate implements AccidentTypeRepository {

    private final JdbcTemplate jdbc;

    public AccidentTypeJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Collection<AccidentType> findAll() {
        return jdbc.query("select * from type",
                (resultSet, row) -> makeType(resultSet));
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(
                jdbc.queryForObject("select * from type where id= ?",
                        (resultSet, row) -> makeType(resultSet), id)
        );
    }

    private AccidentType makeType(ResultSet resultSet) throws SQLException {
        AccidentType type = new AccidentType();
        type.setId(resultSet.getInt("id"));
        type.setName(resultSet.getString("name"));
        return type;
    }
}
