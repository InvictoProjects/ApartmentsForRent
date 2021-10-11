package com.example.apartmentsforrent.persistence.model;

public class Apartment {
    private Long id;
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

    public static class Builder {
        private Long id;
        private ApartmentDetails apartmentDetails;
        private ApartmentDescription apartmentDescription;
        private Owner owner;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setApartmentDetails(ApartmentDetails apartmentDetails) {
            this.apartmentDetails = apartmentDetails;
            return this;
        }

        public Builder setApartmentDescription(ApartmentDescription apartmentDescription) {
            this.apartmentDescription = apartmentDescription;
            return this;
        }

        public Builder setOwner(Owner owner) {
            this.owner = owner;
            return this;
        }

        public Apartment build() {
            return new Apartment(this);
        }
    }

    private Apartment(Builder builder) {
        this.id = builder.id;
        this.apartmentDetails = builder.apartmentDetails;
        this.apartmentDescription = builder.apartmentDescription;
        this.owner = builder.owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
