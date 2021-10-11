package com.example.apartmentsforrent.web.converter;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.web.dto.ApartmentDto;

public class ApartmentConverter {
    public ApartmentDto convertToApartmentDto(Apartment apartment) {
        return new ApartmentDto(apartment.getApartmentDetails(),
                apartment.getApartmentDescription(), apartment.getOwner());
    }
}
