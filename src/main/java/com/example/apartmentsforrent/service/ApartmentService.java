package com.example.apartmentsforrent.service;

import com.example.apartmentsforrent.persistence.model.Apartment;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface ApartmentService {

    boolean deleteById(long apartment);

    Optional<Apartment> findById(long id);

    List<Apartment> search(BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom, Integer quantityOfRoomsTo,
                           Float areaFrom, Float areaTo, Integer floorFrom, Integer floorTo, Year yearOfBuildFrom,
                           Year yearOfBuildTo);
}
