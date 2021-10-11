package com.example.apartmentsforrent.web.converter;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.web.dto.ApartmentDto;

public class ApartmentDtoConverter {
    public Apartment convertToApartment(ApartmentDto apartmentDto) {
        return new Apartment.Builder()
                .setApartmentDetails(apartmentDto.getApartmentDetails())
                .setApartmentDescription(apartmentDto.getApartmentDescription())
                .setOwner(apartmentDto.getOwner())
                .build();
    }
}
