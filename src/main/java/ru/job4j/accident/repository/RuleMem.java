package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RuleMem {

    private Map<Integer, Rule> rules = new HashMap();

    public RuleMem() {
        rules.put(1, new Rule(1, "Статья 1"));
        rules.put(2, new Rule(2, "Статья 2"));
        rules.put(3, new Rule(3, "Статья 3"));
    }

    public Collection<Rule> getRules() {
        return rules.values();
    }

    public Rule findByIdRule(int id) {
        return rules.get(id);
    }
}
