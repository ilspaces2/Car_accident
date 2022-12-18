package ru.job4j.accident.repository.data;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Authority;
import ru.job4j.accident.repository.AuthorityRepository;
import ru.job4j.accident.repository.data.crud.CrudAuthorityData;

import java.util.Optional;

@Repository
@Profile("SpringData")
public class AuthorityData implements AuthorityRepository {

    private final CrudAuthorityData crudAuthorityData;

    public AuthorityData(CrudAuthorityData crudAuthorityData) {
        this.crudAuthorityData = crudAuthorityData;
    }

    @Override
    public Optional<Authority> findByAuthority(String authority) {
        return crudAuthorityData.findByAuthority(authority);
    }

    @Override
    public Optional<Authority> findById(int id) {
        return crudAuthorityData.findById(id);
    }
}
