package ru.job4j.accident.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.accident.exception.UsernameAlreadyExistsException;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.UserRepository;

@Service
public class UserService {

    private final static Logger log = LoggerFactory.getLogger(UserService.class);

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final Authority defaultRole;


    public UserService(PasswordEncoder encoder, AuthorityRepository authorityRepository, UserRepository userRepository) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.defaultRole = authorityRepository.findByAuthority("ROLE_USER").orElse(null);
    }

    public void save(User user) {
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(
                    String.format("User with name : %s already exists", user.getUsername()));
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(defaultRole);
        userRepository.save(user);
        log.info("Created user: {}", user.getUsername());
    }
}
