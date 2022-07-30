package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcTemplate;

import java.util.Collection;

@Service
public class AccidentService {

    private AccidentJdbcTemplate store;

    public AccidentService(AccidentJdbcTemplate store) {
        this.store = store;
    }

    public Collection<Accident> getAccidents() {
        return store.getAccidents();
    }

    public void addAccident(Accident accident) {
        store.addAccident(accident);
    }

    public Accident findByIdAccident(int id) {
        return store.findByIdAccident(id);
    }

    public void update(Accident accident) {
        store.update(accident);
    }
}
