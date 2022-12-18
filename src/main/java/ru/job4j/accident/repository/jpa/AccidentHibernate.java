package ru.job4j.accident.repository.jpa;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("Hibernate")
public class AccidentHibernate implements Store, AccidentRepository {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public void save(Accident accident) {
        if (accident.getId() == 0) {
            tx(session -> session.save(accident), sf);
        } else {
            tx(session -> {
                session.createQuery("update Accident set name=:nName, text=:nText, address=:nAddress where id=:nId")
                        .setParameter("nName", accident.getName())
                        .setParameter("nText", accident.getText())
                        .setParameter("nAddress", accident.getAddress())
                        .setParameter("nId", accident.getId())
                        .executeUpdate();
                return accident;
            }, sf);
        }
    }

    @Override
    public Collection<Accident> findAll() {
        return tx(session ->
                session.createQuery("select distinct a from Accident a join fetch a.accidentType join fetch a.rules")
                        .list(), sf);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return Optional.ofNullable(
                (Accident) tx(session ->
                        session.createQuery("from Accident a join fetch a.accidentType join fetch a.rules where a.id=:nId")
                                .setParameter("nId", id)
                                .uniqueResult(), sf)
        );
    }
}
