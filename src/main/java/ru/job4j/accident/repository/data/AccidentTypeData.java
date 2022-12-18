package ru.job4j.accident.repository.data;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;
import ru.job4j.accident.repository.data.crud.CrudAccidentTypeData;

import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("SpringData")
public class AccidentTypeData implements AccidentTypeRepository {

    private final CrudAccidentTypeData crudAccidentTypeData;

    public AccidentTypeData(CrudAccidentTypeData crudAccidentTypeData) {
        this.crudAccidentTypeData = crudAccidentTypeData;
    }

    @Override
    public Collection<AccidentType> findAll() {
        return (Collection<AccidentType>) crudAccidentTypeData.findAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return crudAccidentTypeData.findById(id);
    }
}