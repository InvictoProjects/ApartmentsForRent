package com.example.apartmentsforrent.service;

import com.example.apartmentsforrent.persistence.model.Apartment;

import java.util.Optional;

public interface ApartmentService {

    boolean deleteApartment(Apartment apartment);

    Optional<Apartment> findById(long id);
}
