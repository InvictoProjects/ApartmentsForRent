package com.example.apartmentsforrent.web.converter;

import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.web.dto.ApartmentDetailsDto;

public class ApartmentDetailsDtoConverter {
    public ApartmentDetails convertToApartmentDetails(ApartmentDetailsDto apartmentDetailsDto) {
        return new ApartmentDetails.Builder()
                .setAddress(apartmentDetailsDto.getAddress())
                .setBuildYear(apartmentDetailsDto.getBuildYear())
                .setPrice(apartmentDetailsDto.getPrice())
                .setArea(apartmentDetailsDto.getArea())
                .setFloor(apartmentDetailsDto.getFloor())
                .setQuantityOfRooms(apartmentDetailsDto.getQuantityOfRooms())
                .build();
    }
}
