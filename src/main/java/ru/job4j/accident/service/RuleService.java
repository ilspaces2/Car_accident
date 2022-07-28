package ru.job4j.accident.service;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;

import java.util.List;

@Repository
public class RuleService {

    private RuleMem store;

    public RuleService(RuleMem store) {
        this.store = store;
    }

    public List<Rule> getRules() {
        return store.getRules();
    }

    public Rule findByIdRule(int id) {
        return store.findByIdRule(id);
    }
}
