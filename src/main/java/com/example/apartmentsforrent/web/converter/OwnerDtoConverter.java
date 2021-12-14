package com.example.apartmentsforrent.web.converter;

import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.web.dto.OwnerDto;

public class OwnerDtoConverter {

    public Owner convertToOwner(OwnerDto ownerDto) {
        return new Owner.Builder()
                .setEmail(ownerDto.getEmail())
                .setName(ownerDto.getName())
                .setSurname(ownerDto.getSurname())
                .setPhoneNumber(ownerDto.getPhoneNumber())
                .setPasswordHash("password")
                .build();
    }
}
