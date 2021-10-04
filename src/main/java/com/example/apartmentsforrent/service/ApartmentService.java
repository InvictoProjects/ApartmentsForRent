package com.example.apartmentsforrent.service;

import com.example.apartmentsforrent.persistence.model.Apartment;

public interface ApartmentService {

    boolean deleteApartment(Apartment apartment);

    Apartment findById(String id);
}
