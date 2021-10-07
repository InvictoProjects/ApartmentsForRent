package com.example.apartmentsforrent.service;

import com.example.apartmentsforrent.persistence.model.Apartment;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

public interface ApartmentService {

    List<Apartment> search(BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom, Integer quantityOfRoomsTo,
                           Float areaFrom, Float areaTo, Integer floorFrom, Integer floorTo, Year yearOfBuildFrom,
                           Year yearOfBuildTo);

}
