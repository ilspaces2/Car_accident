package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class AccidentTypeMem {

    private Map<Integer, AccidentType> accidentType = new HashMap<>();

    public AccidentTypeMem() {
        accidentType.put(1, new AccidentType(1, "Две машины"));
        accidentType.put(2, new AccidentType(2, "Машина и человек"));
        accidentType.put(3, new AccidentType(3, "Машина и велосипед"));
    }

    public Collection<AccidentType> getTypes() {
        return accidentType.values();
    }

    public AccidentType findByIdType(int id) {
        return accidentType.get(id);
    }
}
