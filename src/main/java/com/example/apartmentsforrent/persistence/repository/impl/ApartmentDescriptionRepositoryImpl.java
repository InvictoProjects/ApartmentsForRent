package com.example.apartmentsforrent.persistence.repository.impl;

import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.repository.ApartmentDescriptionRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ApartmentDescriptionRepositoryImpl implements ApartmentDescriptionRepository {
    private final Map<Long, ApartmentDescription> databaseMap = new HashMap<>();
    private long index = 0;

    @Override
    public ApartmentDescription save(ApartmentDescription apartmentDescription) {
        if (apartmentDescription.getId() != null) {
            databaseMap.put(apartmentDescription.getId(), apartmentDescription);
        } else {
            apartmentDescription.setId(index);
            databaseMap.put(index, apartmentDescription);
            index++;
        }
        return apartmentDescription;
    }

    @Override
    public Optional<ApartmentDescription> findById(Long id) {
        return Optional.of(databaseMap.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return databaseMap.containsKey(id);
    }

    @Override
    public Iterable<ApartmentDescription> findAll() {
        return databaseMap.values();
    }

    @Override
    public void delete(ApartmentDescription apartmentDescription) {
        databaseMap.remove(apartmentDescription.getId());
    }
}
