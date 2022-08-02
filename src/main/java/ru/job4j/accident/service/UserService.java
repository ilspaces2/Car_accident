package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository store;

    public UserService(UserRepository store) {
        this.store = store;
    }

    public Optional<User> findByUsername(String username) {
        return store.findByUsername(username);
    }

    public User save(User user) {
        return store.save(user);
    }
}
