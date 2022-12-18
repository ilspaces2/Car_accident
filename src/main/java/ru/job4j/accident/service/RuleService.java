package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.Collection;

@Service
public class RuleService {

    private final RuleRepository store;

    public RuleService(RuleRepository store) {
        this.store = store;
    }

    public Collection<Rule> getRules() {
        return store.findAll();
    }

    public Rule findByIdRule(int id) {
        return store.findByIdRule(id).orElseThrow();
    }
}
