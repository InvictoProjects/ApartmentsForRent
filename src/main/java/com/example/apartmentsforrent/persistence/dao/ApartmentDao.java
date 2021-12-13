package com.example.apartmentsforrent.persistence.dao;

import com.example.apartmentsforrent.persistence.model.Apartment;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;

public interface ApartmentDao extends CrudDao<Apartment, Long> {

    List<Apartment> findAll();

    List<Apartment> findAll(int page, int size);

    List<Apartment> getAllWithFiltering(int page, int size, BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom,
                                        Integer quantityOfRoomsTo, Float areaFrom, Float areaTo, Integer floorFrom, Integer floorTo,
                                        Year yearOfBuildFrom, Year yearOfBuildTo);
}
