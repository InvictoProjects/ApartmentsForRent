package com.example.apartmentsforrent.persistence.repository.impl;

import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.persistence.repository.ApartmentDetailsRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class ApartmentDetailsRepositoryImpl implements ApartmentDetailsRepository {
    private final Map<Long, ApartmentDetails> databaseMap = new HashMap<>();
    private long index = 0;

    @Override
    public ApartmentDetails save(ApartmentDetails apartmentDetails) {
        if (apartmentDetails.getId() != null) {
            databaseMap.put(apartmentDetails.getId(), apartmentDetails);
        } else {
            apartmentDetails.setId(index);
            databaseMap.put(index, apartmentDetails);
            index++;
        }
        return apartmentDetails;
    }

    @Override
    public Optional<ApartmentDetails> findById(Long id) {
        return Optional.of(databaseMap.get(id));
    }

    @Override
    public boolean existsById(Long id) {
        return databaseMap.containsKey(id);
    }

    @Override
    public Iterable<ApartmentDetails> findAll() {
        return databaseMap.values();
    }

    @Override
    public void delete(ApartmentDetails apartmentDetails) {
        databaseMap.remove(apartmentDetails.getId());
    }
}
