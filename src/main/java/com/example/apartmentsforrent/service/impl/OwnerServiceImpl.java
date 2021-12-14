package com.example.apartmentsforrent.service.impl;

import com.example.apartmentsforrent.persistence.dao.OwnerDao;
import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService {
    private final OwnerDao ownerDao;

    public OwnerServiceImpl(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    @Override
    public void saveOwner(Owner owner) {
        ownerDao.create(owner);
    }

    @Override
    public Optional<Owner> getOwnerById(long id) {
        return ownerDao.read(id);
    }

    @Override
    public Optional<Owner> getOwnerByEmail(String email) {
        return ownerDao.findByEmail(email);
    }

    @Override
    public boolean isEmailTaken(String email) {
        return getOwnerByEmail(email).isPresent();
    }
}
