package com.example.apartmentsforrent.persistence.repository;

import com.example.apartmentsforrent.persistence.model.Apartment;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface ApartmentRepository {

    Optional<Apartment> save(Apartment apartment);

    Optional<Apartment> findById(long id);

    boolean delete(Apartment apartment);

    List<Apartment> search(BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom, Integer quantityOfRoomsTo,
                           Float areaFrom, Float areaTo, Integer floorFrom, Integer floorTo, Year yearOfBuildFrom,
                           Year yearOfBuildTo);

}