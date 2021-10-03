package com.example.apartmentsforrent.persistence.model;

public class Apartment {
    private long id;
    private ApartmentDetails apartmentDetails;
    private ApartmentDescription apartmentDescription;
    private Owner owner;

    public Apartment(ApartmentDetails apartmentDetails, ApartmentDescription apartmentDescription, Owner owner) {
        this.apartmentDetails = apartmentDetails;
        this.apartmentDescription = apartmentDescription;
        this.owner = owner;
    }

    public Apartment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
