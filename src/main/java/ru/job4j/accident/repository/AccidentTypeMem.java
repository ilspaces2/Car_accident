package ru.job4j.accident.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccidentTypeMem {

    private List<AccidentType> accidentType;

    public AccidentTypeMem() {
        accidentType = new ArrayList<>();
        accidentType.add(new AccidentType(1, "Две машины"));
        accidentType.add(new AccidentType(2, "Машина и человек"));
        accidentType.add(new AccidentType(3, "Машина и велосипед"));
    }

    public List<AccidentType> getTypes() {
        return List.copyOf(accidentType);
    }

    public AccidentType findByIdType(int id) {
        return accidentType.get(id - 1);
    }
}
