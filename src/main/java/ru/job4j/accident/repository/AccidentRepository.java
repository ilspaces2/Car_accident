package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Optional;

public interface AccidentRepository {

    void save(Accident accident);

    Collection<Accident> findAll();

    Optional<Accident> findById(int id);
}
