package ru.job4j.accident.repository.data.crud;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Authority;

import java.util.Optional;

public interface CrudAuthorityData extends CrudRepository<Authority, Integer> {

    Optional<Authority> findByAuthority(String authority);
}