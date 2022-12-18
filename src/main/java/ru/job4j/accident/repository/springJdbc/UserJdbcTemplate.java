package ru.job4j.accident.repository.springJdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("SpringJDBC")
public class UserJdbcTemplate implements UserRepository {

    private final AuthorityJdbcTemplate authority;
    private final JdbcTemplate jdbc;

    public UserJdbcTemplate(AuthorityJdbcTemplate authority, JdbcTemplate jdbc) {
        this.authority = authority;
        this.jdbc = jdbc;
    }

    /**
     * Если использовать queryForObject, то ожидается результат 1 и если запись не
     * находится (получаем 0 ), то вылетает исключение EmptyResultDataAccessException.
     * <p>
     * Там где уверен что пустых результатов не будет то можно оставить.
     * <p>
     * Если еще допилить REST то тогда нужно все доработать.
     * <p>
     * Некоторые варианты решения это обработать его и вернуть то, что нужно или получать список
     * элементов и уже выполнять проверки с возвратом нужного результата.
     */
    @Override
    public Optional<User> findUserByUsername(String username) {
        List<User> userList = jdbc.query("select * from users where username= ?",
                (resultSet, row) -> makeUser(resultSet), username);
        return Optional.ofNullable(DataAccessUtils.singleResult(userList));
    }

    @Override
    public void save(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(connect -> {
            PreparedStatement ps = connect.prepareStatement(
                    "insert into users (username, password, enabled, authority_id) values (?,?,?,?)",
                    new String[]{"id"});
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setBoolean(3, user.isEnabled());
            ps.setInt(4, user.getAuthority().getId());
            return ps;
        }, keyHolder);
    }

    private User makeUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUsername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEnabled(resultSet.getBoolean("enabled"));
        user.setAuthority(
                authority.findById(resultSet.getInt("authority_id")).orElse(null));
        return user;
    }
}