package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;

@Service
public class AccidentService {

    private AccidentMem store;

    public AccidentService(AccidentMem store) {
        this.store = store;
    }

    public void addAccidents() {
        store.addAccidents();
    }

    public Collection<Accident> getAccidents() {
        return store.getAccidents();
    }

    public void addAccident(Accident accident) {
        store.addAccident(accident);
    }
}
