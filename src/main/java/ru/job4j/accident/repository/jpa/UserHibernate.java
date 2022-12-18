package ru.job4j.accident.repository.jpa;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.User;
import ru.job4j.accident.repository.UserRepository;

import java.util.Optional;

@Repository
@Profile("Hibernate")
public class UserHibernate implements Store, UserRepository {

    private final SessionFactory sf;

    public UserHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable((User) tx(session -> session.createQuery(
                        "from User u join fetch u.authority where u.username=:username")
                .setParameter("username", username)
                .uniqueResult(), sf));
    }

    @Override
    public void save(User user) {
        tx(session -> session.save(user), sf);
    }
}