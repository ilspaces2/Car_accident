package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RuleMem {

    private List<Rule> rules;

    public RuleMem() {
        rules = new ArrayList<>();
        rules.add(new Rule(1, "Статья 1"));
        rules.add(new Rule(2, "Статья 2"));
        rules.add(new Rule(3, "Статья 3"));
    }

    public List<Rule> getRules() {
        return List.copyOf(rules);
    }

    public Rule findByIdRule(int id) {
        return rules.get(id - 1);
    }
}
