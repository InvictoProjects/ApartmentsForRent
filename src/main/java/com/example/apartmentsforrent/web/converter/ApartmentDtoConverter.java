package com.example.apartmentsforrent.web.converter;

import com.example.apartmentsforrent.persistence.model.Apartment;
import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.persistence.model.Owner;
import com.example.apartmentsforrent.web.dto.ApartmentDescriptionDto;
import com.example.apartmentsforrent.web.dto.ApartmentDetailsDto;
import com.example.apartmentsforrent.web.dto.ApartmentDto;
import com.example.apartmentsforrent.web.dto.OwnerDto;

public class ApartmentDtoConverter {
    public Apartment convertToApartment(ApartmentDto apartmentDto) {
        Apartment.Builder builder = new Apartment.Builder();

        ApartmentDetailsDto apartmentDetailsDto = apartmentDto.getApartmentDetails();
        if (apartmentDetailsDto == null) builder.setApartmentDetails(null);
        else builder.setApartmentDetails(new ApartmentDetails.Builder()
                .setAddress(apartmentDetailsDto.getAddress())
                .setBuildYear(apartmentDetailsDto.getBuildYear())
                .setPrice(apartmentDetailsDto.getPrice())
                .setArea(apartmentDetailsDto.getArea())
                .setFloor(apartmentDetailsDto.getFloor())
                .setQuantityOfRooms(apartmentDetailsDto.getQuantityOfRooms())
                .build());

        ApartmentDescriptionDto apartmentDescriptionDto = apartmentDto.getApartmentDescription();
        if (apartmentDescriptionDto == null) builder.setApartmentDescription(null);
        else builder.setApartmentDescription(new ApartmentDescription.Builder()
                .setCondition(apartmentDescriptionDto.getCondition())
                .setBuildingType(apartmentDescriptionDto.getBuildingType())
                .setAdditionalInfo(apartmentDescriptionDto.getAdditionalInfo())
                .build());

        OwnerDto ownerDto = apartmentDto.getOwner();
        if (ownerDto == null) builder.setOwner(null);
        else builder.setOwner(new Owner.Builder()
                .setEmail(ownerDto.getEmail())
                .setName(ownerDto.getName())
                .setSurname(ownerDto.getSurname())
                .setPhoneNumber(ownerDto.getPhoneNumber())
                .setPasswordHash("password")
                .build());

        return builder.build();
    }
}
