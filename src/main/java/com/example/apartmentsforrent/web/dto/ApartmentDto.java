package com.example.apartmentsforrent.web.dto;

public class ApartmentDto {
    private ApartmentDetailsDto apartmentDetails;
    private ApartmentDescriptionDto apartmentDescription;
    private OwnerDto owner;

    public ApartmentDto(ApartmentDetailsDto apartmentDetails, ApartmentDescriptionDto apartmentDescription, OwnerDto owner) {
        this.apartmentDetails = apartmentDetails;
        this.apartmentDescription = apartmentDescription;
        this.owner = owner;
    }

    public ApartmentDto() {
    }

    public ApartmentDetailsDto getApartmentDetails() {
        return apartmentDetails;
    }

    public void setApartmentDetails(ApartmentDetailsDto apartmentDetails) {
        this.apartmentDetails = apartmentDetails;
    }

    public ApartmentDescriptionDto getApartmentDescription() {
        return apartmentDescription;
    }

    public void setApartmentDescription(ApartmentDescriptionDto apartmentDescription) {
        this.apartmentDescription = apartmentDescription;
    }

    public OwnerDto getOwner() {
        return owner;
    }

    public void setOwner(OwnerDto owner) {
        this.owner = owner;
    }
}
