package com.example.apartmentsforrent.web.converter;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.web.dto.ApartmentDescriptionDto;
import com.example.apartmentsforrent.web.dto.ApartmentDetailsDto;
import com.example.apartmentsforrent.web.dto.ApartmentDto;
import com.example.apartmentsforrent.web.dto.OwnerDto;

public class ApartmentConverter {
    public ApartmentDto convertToApartmentDto(Apartment apartment) {
        ApartmentDetails details = apartment.getApartmentDetails();
        ApartmentDetailsDto apartmentDetailsDto;
        if (details == null) apartmentDetailsDto = null;
        else apartmentDetailsDto = new ApartmentDetailsDto(details.getAddress(),
                details.getBuildYear(),
                details.getPrice(),
                details.getFloor(),
                details.getArea(),
                details.getQuantityOfRooms());
        ApartmentDescription description = apartment.getApartmentDescription();
        ApartmentDescriptionDto apartmentDescriptionDto;
        if (description == null) apartmentDescriptionDto = null;
        else apartmentDescriptionDto = new ApartmentDescriptionDto(description.getCondition(),
                description.getBuildingType(),
                description.getAdditionalInfo());
        Owner owner = apartment.getOwner();
        OwnerDto ownerDto;
        if (owner == null) ownerDto = null;
        else ownerDto = new OwnerDto(owner.getName(),
                owner.getSurname(),
                owner.getEmail(),
                owner.getPhoneNumber(),
                owner.getPasswordHash());
        return new ApartmentDto(apartmentDetailsDto, apartmentDescriptionDto, ownerDto);
    }
}
