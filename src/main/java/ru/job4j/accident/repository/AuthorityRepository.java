package ru.job4j.accident.repository;

import ru.job4j.accident.model.Authority;

import java.util.Optional;

public interface AuthorityRepository {

    Optional<Authority> findByAuthority(String authority);

    Optional<Authority> findById(int id);
}
