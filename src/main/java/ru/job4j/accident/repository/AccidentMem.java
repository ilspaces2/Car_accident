package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> accidents;
    private AtomicInteger count = new AtomicInteger();

    public AccidentMem(Map<Integer, Accident> accidents) {
        this.accidents = accidents;
    }

    public void addAccidents() {
        accidents.put(1, new Accident(1, "Ivan", "Accident one", "Moscow"));
        accidents.put(2, new Accident(2, "Boris", "Accident two", "Kazan"));
        accidents.put(3, new Accident(3, "Anna", "Accident three", "Orel"));
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
        accidents.put(accident.getId(), accident);
    }
}
