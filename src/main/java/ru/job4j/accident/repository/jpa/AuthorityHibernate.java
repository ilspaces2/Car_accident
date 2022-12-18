package ru.job4j.accident.repository.jpa;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.AuthorityRepository;

import java.util.Optional;

@Repository
@Profile("Hibernate")
public class AuthorityHibernate implements Store, AuthorityRepository {

    private final SessionFactory sf;

    public AuthorityHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Optional<Authority> findByAuthority(String authority) {
        return Optional.ofNullable(
                (Authority) tx(session -> session.createQuery(
                                "from Authority where authority=:authority")
                        .setParameter("authority", authority)
                        .uniqueResult(), sf)
        );
    }

    @Override
    public Optional<Authority> findById(int id) {
        return Optional.ofNullable(
                (Authority) tx(session -> session.createQuery(
                                "from Authority where id=:id")
                        .setParameter("id", id)
                        .uniqueResult(), sf)
        );
    }
}
