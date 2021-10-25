package com.example.apartmentsforrent.persistence.repository;

import com.example.apartmentsforrent.persistence.model.Owner;

import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Optional<Owner> getOwnerByEmail(String email);
}
