package com.example.apartmentsforrent.service.impl;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.repository.ApartmentRepository;
import com.example.apartmentsforrent.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public boolean deleteApartment(Apartment apartment) {
        return apartmentRepository.delete(apartment);
    }

    @Override
    public Apartment findById(String id) {
        return apartmentRepository.findById(id);
    }
}
