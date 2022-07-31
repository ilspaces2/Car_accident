package ru.job4j.accident.service;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleData;

import java.util.Collection;

@Repository
public class RuleService {

    private RuleData store;

    public RuleService(RuleData store) {
        this.store = store;
    }

    public Collection<Rule> getRules() {
        return (Collection<Rule>) store.findAll();
    }

    public Rule findByIdRule(int id) {
        return store.findById(id).get();
    }
}
