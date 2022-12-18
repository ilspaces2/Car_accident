package ru.job4j.accident.repository.data;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;
import ru.job4j.accident.repository.data.crud.CrudAccidentData;

import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("SpringData")
public class AccidentData implements AccidentRepository {

    private final CrudAccidentData crudAccidentData;

    public AccidentData(CrudAccidentData crudAccidentData) {
        this.crudAccidentData = crudAccidentData;
    }

    @Override
    public void save(Accident accident) {
        crudAccidentData.save(accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return crudAccidentData.findAll();
    }

    @Override
    public Optional<Accident> findById(int id) {
        return crudAccidentData.findById(id);
    }
}
