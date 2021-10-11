package com.example.apartmentsforrent.persistence.repository.impl;

import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.persistence.repository.OwnerRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class OwnerRepositoryImpl implements OwnerRepository {
    private final Map<Long, Owner> db = new HashMap<>();
    private long index;

    @PostConstruct
    public void populateDb() {
        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setName("Michael");
        owner1.setSurname("Jackson");
        owner1.setEmail("jackson@gmail.com");
        owner1.setPhoneNumber("380991853345");
        owner1.setPasswordHash("jdiJdflJSdfnG");

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setName("Johny");
        owner2.setSurname("Depp");
        owner2.setEmail("depp@gmail.com");
        owner2.setPhoneNumber("380991852245");
        owner2.setPasswordHash("jdiGdNORERTnG");

        db.put(owner1.getId(), owner1);
        db.put(owner2.getId(), owner2);
        index = 3;
    }

    @Override
    public Optional<Owner> getOwnerByEmail(String email) {
        return db.values().stream().filter(owner -> owner.getEmail().equals(email)).findFirst();
    }

    @Override
    public Owner save(Owner owner) {
        if (db.containsKey(owner.getId())) {
            db.replace(owner.getId(), owner);
        } else {
            db.put(index++, owner);
        }
        return owner;
    }

    @Override
    public Optional<Owner> findById(Long id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return Optional.ofNullable(db.get(id)).isPresent();
    }

    @Override
    public Iterable<Owner> findAll() {
        return db.values();
    }

    @Override
    public void deleteById(Long id) {
        db.remove(id);
    }
}
