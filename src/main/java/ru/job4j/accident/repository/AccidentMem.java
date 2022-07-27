package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.Map;

@Repository
public class AccidentMem {

    private Map<Integer, Accident> accidents;

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
}
