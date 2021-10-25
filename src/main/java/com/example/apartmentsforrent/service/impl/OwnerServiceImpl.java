package com.example.apartmentsforrent.service.impl;

import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.persistence.repository.OwnerRepository;
import com.example.apartmentsforrent.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void saveOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public Optional<Owner> getOwnerById(long id) {
        return ownerRepository.findById(id);
    }

    @Override
    public Optional<Owner> getOwnerByEmail(String email) {
        return ownerRepository.getOwnerByEmail(email);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return getOwnerByEmail(email).isPresent();
    }
}
