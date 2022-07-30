package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger count = new AtomicInteger();

    public AccidentMem() {
        Accident one = new Accident(1, "Ivan", "Accident one", "Moscow");
        one.setAccidentType(new AccidentType(1, "Две машины"));
        one.addRule(new Rule(1, "Статья 1"));
        accidents.put(count.incrementAndGet(), one);
    }

    public Collection<Accident> getAccidents() {
        return accidents.values();
    }

    public void addAccident(Accident accident) {
        accident.setId(count.incrementAndGet());
        accidents.put(accident.getId(), accident);
    }

    public Accident findByIdAccident(int id) {
        return accidents.get(id);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
}
