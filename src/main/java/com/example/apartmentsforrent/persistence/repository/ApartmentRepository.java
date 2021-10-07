package com.example.apartmentsforrent.persistence.repository;

import com.example.apartmentsforrent.persistence.model.Apartment;

import java.util.Optional;

public interface ApartmentRepository {
    Optional<Apartment> save(Apartment apartment);

    Optional<Apartment> findById(long id);

    boolean delete(Apartment apartment);

}
