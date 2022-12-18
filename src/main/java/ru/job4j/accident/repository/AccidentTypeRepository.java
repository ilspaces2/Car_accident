package ru.job4j.accident.repository;

import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeRepository {

    Collection<AccidentType> findAll();

    Optional<AccidentType> findById(int id);
}
