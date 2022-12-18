package ru.job4j.accident.repository.data;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;
import ru.job4j.accident.repository.data.crud.CrudRuleData;

import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("SpringData")
public class RuleData implements RuleRepository {
    private final CrudRuleData crudRuleData;

    public RuleData(CrudRuleData crudRuleData) {
        this.crudRuleData = crudRuleData;
    }

    @Override
    public Collection<Rule> findAll() {
        return (Collection<Rule>) crudRuleData.findAll();
    }

    @Override
    public Optional<Rule> findByIdRule(int id) {
        return crudRuleData.findById(id);
    }
}
