package ru.job4j.accident.repository;

import org.hibernate.SessionFactory;
import ru.job4j.accident.model.Rule;

import java.util.Collection;

/*
@Repository
 */
public class RuleHibernate implements Store {

    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Collection<Rule> getRules() {
        return tx(session ->
                session.createQuery("from Rule").list(), sf);
    }

    public Rule findByIdRule(int id) {
        return (Rule) tx(session ->
                session.createQuery("from Rule where id=:nId")
                        .setParameter("nId", id)
                        .uniqueResult(), sf);
    }
}
