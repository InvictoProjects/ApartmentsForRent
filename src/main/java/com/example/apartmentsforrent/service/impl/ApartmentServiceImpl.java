package com.example.apartmentsforrent.service.impl;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.repository.ApartmentRepository;
import com.example.apartmentsforrent.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Optional;

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
    public Optional<Apartment> findById(long id) {
        return apartmentRepository.findById(id);
    }

    @Override
    public List<Apartment> search(BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom, Integer quantityOfRoomsTo, Float areaFrom, Float areaTo, Integer floorFrom, Integer floorTo, Year yearOfBuildFrom, Year yearOfBuildTo) {
        return apartmentRepository.search(priceFrom, priceTo, quantityOfRoomsFrom, quantityOfRoomsTo, areaFrom,
                areaTo, floorFrom, floorTo, yearOfBuildFrom, yearOfBuildTo);
    }
}
