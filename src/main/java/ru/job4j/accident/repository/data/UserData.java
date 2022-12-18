package ru.job4j.accident.repository.data;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;
import ru.job4j.accident.repository.data.crud.CrudUserData;

import java.util.Optional;

@Repository
@Profile("SpringData")
public class UserData implements UserRepository {

    private final CrudUserData crudUserData;

    public UserData(CrudUserData crudUserData) {
        this.crudUserData = crudUserData;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return crudUserData.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        crudUserData.save(user);
    }
}