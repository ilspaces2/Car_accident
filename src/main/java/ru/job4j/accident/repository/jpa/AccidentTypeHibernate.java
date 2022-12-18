package ru.job4j.accident.repository.jpa;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("Hibernate")
public class AccidentTypeHibernate implements Store, AccidentTypeRepository {

    private final SessionFactory sf;

    public AccidentTypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Collection<AccidentType> findAll() {
        return tx(session ->
                session.createQuery("from AccidentType").list(), sf);
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(
                (AccidentType) tx(session ->
                        session.createQuery("from AccidentType where id=:nId")
                                .setParameter("nId", id)
                                .uniqueResult(), sf)
        );
    }
}
