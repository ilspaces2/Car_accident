package ru.job4j.accident.service;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeData;

import java.util.Collection;

@Repository
public class AccidentTypeService {

    private AccidentTypeData store;

    public AccidentTypeService(AccidentTypeData store) {
        this.store = store;
    }

    public Collection<AccidentType> getTypes() {
        return (Collection<AccidentType>) store.findAll();
    }

    public AccidentType findByIdType(int id) {
        return store.findById(id).get();
    }
}
