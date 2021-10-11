package com.example.apartmentsforrent.web.dto;

import com.example.apartmentsforrent.persistence.model.ApartmentDescription;
import com.example.apartmentsforrent.persistence.model.ApartmentDetails;
import com.example.apartmentsforrent.persistence.model.Owner;

public class ApartmentDto {
    private ApartmentDetails apartmentDetails;
    private ApartmentDescription apartmentDescription;
    private Owner owner;

    public ApartmentDto(ApartmentDetails apartmentDetails, ApartmentDescription apartmentDescription, Owner owner) {
        this.apartmentDetails = apartmentDetails;
        this.apartmentDescription = apartmentDescription;
        this.owner = owner;
    }

    public ApartmentDto() {
    }

    public ApartmentDetails getApartmentDetails() {
        return apartmentDetails;
    }

    public void setApartmentDetails(ApartmentDetails apartmentDetails) {
        this.apartmentDetails = apartmentDetails;
    }

    public ApartmentDescription getApartmentDescription() {
        return apartmentDescription;
    }

    public void setApartmentDescription(ApartmentDescription apartmentDescription) {
        this.apartmentDescription = apartmentDescription;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }
}
