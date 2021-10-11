package com.example.apartmentsforrent.persistence.repository;

import com.example.apartmentsforrent.persistence.model.Owner;

import java.util.Optional;

public interface OwnerRepository {
    void saveOwner(Owner owner);
    Optional<Owner> getOwnerById(long id);
    Optional<Owner> getOwnerByEmail(String email);
}
