package ru.job4j.accident.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.Collection;

@Service
public class AccidentService {

    private final static Logger log = LoggerFactory.getLogger(AccidentService.class);


    private final AccidentRepository store;

    public AccidentService(AccidentRepository store) {
        this.store = store;
    }

    public Collection<Accident> getAccidents() {
        return store.findAll();
    }

    public void addAccident(Accident accident) {
        store.save(accident);
        log.info("Created accident: {}", accident);
    }

    public Accident findByIdAccident(int id) {
        return store.findById(id).orElseThrow();
    }

    public void update(Accident accident) {
        store.save(accident);
        log.info("Updated accident: {}", accident);
    }
}
