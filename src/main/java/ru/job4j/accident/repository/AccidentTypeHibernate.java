package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;

/*
@Repository
 */
public class AccidentTypeHibernate implements Store {

    private final SessionFactory sf;

    public AccidentTypeHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Collection<AccidentType> getTypes() {
        return tx(session ->
                session.createQuery("from AccidentType").list(), sf);
    }

    public AccidentType findByIdType(int id) {
        return (AccidentType) tx(session ->
                session.createQuery("from AccidentType where id=:nId")
                        .setParameter("nId", id)
                        .uniqueResult(), sf);
    }
}
