package com.example.apartmentsforrent.service.impl;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.repository.impl.ApartmentRepositoryImpl;
import com.example.apartmentsforrent.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepositoryImpl apartmentRepository;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepositoryImpl apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public boolean deleteApartment(Apartment apartment) {
        return apartmentRepository.delete(apartment);
    }

    @Override
    public Optional<Apartment> findById(long id) {
        return apartmentRepository.findById(id);
    }
}
