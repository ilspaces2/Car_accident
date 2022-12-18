package ru.job4j.accident.repository.data.crud;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.AccidentType;

public interface CrudAccidentTypeData extends CrudRepository<AccidentType, Integer> {
}