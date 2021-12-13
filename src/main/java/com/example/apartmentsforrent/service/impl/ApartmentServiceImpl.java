package com.example.apartmentsforrent.service.impl;

import com.example.apartmentsforrent.persistence.dao.ApartmentDao;
import com.example.apartmentsforrent.persistence.model.Apartment;
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

    private final ApartmentDao apartmentRepository;

    @Autowired
    public ApartmentServiceImpl(ApartmentDao apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public Apartment create(Apartment apartment) {
        return apartmentRepository.create(apartment);
    }

    @Override
    public Apartment update(Apartment apartment) {
        if (!existsById(apartment.getId())) {
            throw new IllegalArgumentException(String.format("Apartment with id %s does not exist", apartment.getId()));
        }
        apartmentRepository.update(apartment);
        return apartment;
    }

    @Override
    public void delete(Apartment apartment) {
        Long apartmentId = apartment.getId();
        if (!existsById(apartmentId)) {
            throw new IllegalArgumentException(String.format("Apartment with id %s does not exist", apartmentId));
        }
        apartmentRepository.delete(apartment);
    }

    @Override
    public Optional<Apartment> findById(Long id) {
        return apartmentRepository.read(id);
    }

    @Override
    public boolean existsById(Long id) {
        return apartmentRepository.read(id).isPresent();
    }

    @Override
    public List<Apartment> findAll() {
        return new ArrayList<>(apartmentRepository.findAll());
    }

    @Override
    public List<Apartment> findAll(int page, int size) {
        return apartmentRepository.findAll(page, size);
    }

    @Override
    public List<Apartment> getAllWithFiltering(int page, int size, BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom,
                                               Integer quantityOfRoomsTo, Float areaFrom, Float areaTo, Integer floorFrom,
                                               Integer floorTo, Year yearOfBuildFrom, Year yearOfBuildTo) {
        return apartmentRepository.getAllWithFiltering(page, size, priceFrom, priceTo, quantityOfRoomsFrom, quantityOfRoomsTo, areaFrom,
                areaTo, floorFrom, floorTo, yearOfBuildFrom, yearOfBuildTo);
    }
}
