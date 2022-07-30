package ru.job4j.accident.service;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleJdbcTemplate;

import java.util.Collection;

@Repository
public class RuleService {

    private RuleJdbcTemplate store;

    public RuleService(RuleJdbcTemplate store) {
        this.store = store;
    }

    public Collection<Rule> getRules() {
        return store.getRules();
    }

    public Rule findByIdRule(int id) {
        return store.findByIdRule(id);
    }
}
