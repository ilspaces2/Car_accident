package ru.job4j.accident.repository.jpa;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.Collection;
import java.util.Optional;

@Repository
@Profile("Hibernate")
public class RuleHibernate implements Store, RuleRepository {

    private final SessionFactory sf;

    public RuleHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public Collection<Rule> findAll() {
        return tx(session ->
                session.createQuery("from Rule").list(), sf);
    }

    public Optional<Rule> findByIdRule(int id) {
        return Optional.ofNullable(
                (Rule) tx(session -> session.createQuery("from Rule where id=:nId")
                        .setParameter("nId", id)
                        .uniqueResult(), sf)
        );
    }
}
