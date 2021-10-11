package com.example.apartmentsforrent.web.converter;

import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.web.dto.ApartmentDescriptionDto;

public class ApartmentDescriptionDtoConverter {
    public ApartmentDescription convertToApartmentDescription(ApartmentDescriptionDto apartmentDescriptionDto) {
        return new ApartmentDescription.Builder()
                .setCondition(apartmentDescriptionDto.getCondition())
                .setBuildingType(apartmentDescriptionDto.getBuildingType())
                .setAdditionalInfo(apartmentDescriptionDto.getAdditionalInfo())
                .build();
    }
}
