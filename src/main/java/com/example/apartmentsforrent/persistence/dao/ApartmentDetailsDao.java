package com.example.apartmentsforrent.persistence.dao;

import com.example.apartmentsforrent.persistence.model.ApartmentDetails;

import java.math.BigDecimal;
import java.time.Year;
import java.util.List;
import java.util.Optional;

public interface ApartmentDetailsDao extends CrudDao<ApartmentDetails, Long> {

    List<ApartmentDetails> findAll();

    List<ApartmentDetails> findAll(int page, int size);

    List<ApartmentDetails> getAllWithFiltering(int page, int size, BigDecimal priceFrom, BigDecimal priceTo, Integer quantityOfRoomsFrom,
                                        Integer quantityOfRoomsTo, Float areaFrom, Float areaTo, Integer floorFrom, Integer floorTo,
                                        Year yearOfBuildFrom, Year yearOfBuildTo);

    Optional<ApartmentDetails> findByAddress(String address);

}
