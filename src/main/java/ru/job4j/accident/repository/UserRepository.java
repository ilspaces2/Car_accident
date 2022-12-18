package ru.job4j.accident.repository;

import ru.job4j.accident.model.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByUsername(String username);

    void save(User user);
}
