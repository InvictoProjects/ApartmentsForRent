package com.example.apartmentsforrent.persistence.dao;

import com.example.apartmentsforrent.persistence.model.Apartment;

import java.util.Optional;

public interface ApartmentDao extends CrudDao<Apartment, Long> {
    Optional<Apartment> findByDetailsId(Long id);
}
