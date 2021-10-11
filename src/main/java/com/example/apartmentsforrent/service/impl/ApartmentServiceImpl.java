package com.example.apartmentsforrent.service.impl;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.persistence.repository.ApartmentRepository;
import com.example.apartmentsforrent.service.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Year;
import java.util.ArrayList;
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
    public Apartment create(ApartmentDetails apartmentDetails, ApartmentDescription apartmentDescription, Owner owner) {
        Apartment apartment = new Apartment(apartmentDetails, apartmentDescription, owner);
        return apartmentRepository.save(apartment);
    }

    @Override
    public Apartment update(Apartment apartment) {
        if(!apartmentRepository.existsById(apartment.getId())) {
            throw new IllegalArgumentException(String.format("Apartment with id %s does not exist", apartment.getId()));
        }
        return apartmentRepository.save(apartment);
    }

    @Override
    public void deleteById(Long id) {
        if (!apartmentRepository.existsById(id)) {
            throw new IllegalArgumentException(String.format("Apartment with id %s does not exist", id));
        }
        apartmentRepository.deleteById(id);
    }

    @Override
    public Optional<Apartment> findById(Long id) {
        return apartmentRepository.findById(id);
    }

    @Override
    public List<Apartment> findAll() {
        List<Apartment> apartments = new ArrayList<>();
        apartmentRepository.findAll().forEach(apartments::add);
        return apartments;
    }

    @Override
    public List<Apartment> search(BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom,
                                  Integer quantityOfRoomsTo, Float areaFrom, Float areaTo, Integer floorFrom,
                                  Integer floorTo, Year yearOfBuildFrom, Year yearOfBuildTo) {
        return apartmentRepository.search(priceFrom, priceTo, quantityOfRoomsFrom, quantityOfRoomsTo, areaFrom,
                areaTo, floorFrom, floorTo, yearOfBuildFrom, yearOfBuildTo);
    }
}
