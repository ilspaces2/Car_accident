package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import ru.job4j.accident.model.Accident;

import java.util.Collection;

/*
@Repository
 */
public class AccidentHibernate implements Store {

    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void addAccident(Accident accident) {
        tx(session -> session.save(accident), sf);
    }

    public Collection<Accident> getAccidents() {
        return tx(session -> session.createQuery(
                        "select distinct a from Accident a join fetch a.accidentType join fetch a.rules")
                .list(), sf);
    }

    public Accident findByIdAccident(int id) {
        return (Accident) tx(session -> session.createQuery(
                        "from Accident a join fetch a.accidentType join fetch a.rules where a.id=:nId")
                .setParameter("nId", id)
                .uniqueResult(), sf);
    }

    public void update(Accident accident) {
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
