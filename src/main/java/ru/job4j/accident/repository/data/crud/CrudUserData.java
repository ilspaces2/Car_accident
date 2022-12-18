package ru.job4j.accident.repository.data.crud;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.User;

import java.util.Optional;

public interface CrudUserData extends CrudRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
}