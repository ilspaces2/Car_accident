package ru.job4j.accident.repository;

import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Optional;

public interface RuleRepository {

    Collection<Rule> findAll();

    Optional<Rule> findByIdRule(int id);
}
