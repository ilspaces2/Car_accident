package ru.job4j.accident.service;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeMem;

import java.util.List;

@Repository
public class AccidentTypeService {

    private AccidentTypeMem store;

    public AccidentTypeService(AccidentTypeMem store) {
        this.store = store;
    }

    public List<AccidentType> getTypes() {
        return store.getTypes();
    }

    public AccidentType findByIdType(int id) {
        return store.findByIdType(id);
    }
}
