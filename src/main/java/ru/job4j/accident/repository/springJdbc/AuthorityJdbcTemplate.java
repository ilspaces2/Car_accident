package ru.job4j.accident.repository.springJdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.AuthorityRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
@Profile("SpringJDBC")
public class AuthorityJdbcTemplate implements AuthorityRepository {

    private final JdbcTemplate jdbc;

    public AuthorityJdbcTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Optional<Authority> findByAuthority(String authority) {
        return Optional.ofNullable(
                jdbc.queryForObject("select * from authorities where authority= ?",
                        (resultSet, row) -> makeAuthority(resultSet), authority)
        );
    }

    @Override
    public Optional<Authority> findById(int id) {
        return Optional.ofNullable(
                jdbc.queryForObject("select * from authorities where id= ?",
                        (resultSet, row) -> makeAuthority(resultSet), id)
        );
    }

    private Authority makeAuthority(ResultSet resultSet) throws SQLException {
        Authority authority = new Authority();
        authority.setId(resultSet.getInt("id"));
        authority.setAuthority(resultSet.getString("authority"));
        return authority;
    }
}
