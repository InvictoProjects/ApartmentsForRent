package com.example.apartmentsforrent.service;

import com.example.apartmentsforrent.persistence.model.Apartment;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface ApartmentService {
    Apartment create(Apartment apartment);

    Apartment update(Apartment apartment);

    void delete(Apartment apartment);

    Optional<Apartment> findById(Long id);

    boolean existsById(Long id);

    List<Apartment> findAll();

    List<Apartment> findAll(int page, int size);

    List<Apartment> getAllWithFiltering(int page, int size, BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom,
                                        Integer quantityOfRoomsTo, Float areaFrom, Float areaTo, Integer floorFrom, Integer floorTo,
                                        Year yearOfBuildFrom, Year yearOfBuildTo);
}
