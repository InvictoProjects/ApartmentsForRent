package com.example.apartmentsforrent.service;

import com.example.apartmentsforrent.persistence.model.Owner;

import java.util.Optional;

public interface OwnerService {
    void saveOwner(Owner owner);
    Optional<Owner> getOwnerById(long id);
    Optional<Owner> getOwnerByEmail(String email);
    boolean isEmailTaken(String email);
}