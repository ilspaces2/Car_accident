package ru.job4j.accident.service;

import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentData;

import java.util.Collection;

@Service
public class AccidentService {

    private AccidentData store;

    public AccidentService(AccidentData store) {
        this.store = store;
    }

    public Collection<Accident> getAccidents() {
        return  store.findAll();
    }

    public void addAccident(Accident accident) {
        store.save(accident);
    }

    public Accident findByIdAccident(int id) {
        return store.findById(id);
    }

    public void update(Accident accident) {
        store.save(accident);
    }
}
