package com.example.apartmentsforrent.web.converter;

import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.web.dto.OwnerDto;

public class OwnerDtoConverter {

    public Owner convertToOwner(OwnerDto ownerDto) {
        Owner owner = new Owner();
        owner.setEmail(ownerDto.getEmail());
        owner.setName(ownerDto.getName());
        owner.setSurname(ownerDto.getSurname());
        owner.setPhoneNumber(ownerDto.getPhoneNumber());
        owner.setPasswordHash(ownerDto.getPassword());

        return owner;
    }
}
