package ru.job4j.accident.service;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeMem;

import java.util.Collection;

@Repository
public class AccidentTypeService {

    private AccidentTypeMem store;

    public AccidentTypeService(AccidentTypeMem store) {
        this.store = store;
    }

    public Collection<AccidentType> getTypes() {
        return store.getTypes();
    }

    public AccidentType findByIdType(int id) {
        return store.findByIdType(id);
    }
}
