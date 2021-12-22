package com.example.apartmentsforrent.persistence.dao;

import com.example.apartmentsforrent.persistence.model.Owner;

import java.util.Optional;

public interface OwnerDao extends CrudDao<Owner, Long> {
    Optional<Owner> findByEmail(String email);
}
