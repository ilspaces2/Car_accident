package ru.job4j.accident.repository.data.crud;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accident.model.Rule;

public interface CrudRuleData extends CrudRepository<Rule, Integer> {
}