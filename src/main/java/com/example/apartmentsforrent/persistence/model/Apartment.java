package com.example.apartmentsforrent.persistence.model;

public class Apartment {
    private Long id;
    private Long apartmentDetailsId;
    private ApartmentDetails apartmentDetails;
    private Long apartmentDescriptionId;
    private ApartmentDescription apartmentDescription;
    private Long ownerId;
    private Owner owner;

    public Apartment() {
    }

    public static class Builder {
        private Long id;
        private Long apartmentDetailsId;
        private ApartmentDetails apartmentDetails;
        private Long apartmentDescriptionId;
        private ApartmentDescription apartmentDescription;
        private Long ownerId;
        private Owner owner;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setApartmentDetailsId(Long apartmentDetailsId) {
            this.apartmentDetailsId = apartmentDetailsId;
            return this;
        }

        public Builder setApartmentDescriptionId(Long apartmentDescriptionId) {
            this.apartmentDescriptionId = apartmentDescriptionId;
            return this;
        }

        public Builder setOwnerId(Long ownerId) {
            this.ownerId = ownerId;
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
        this.apartmentDetailsId = builder.apartmentDetailsId;
        this.apartmentDescriptionId = builder.apartmentDescriptionId;
        this.ownerId = builder.ownerId;
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

    public Long getApartmentDetailsId() {
        return apartmentDetailsId;
    }

    public void setApartmentDetailsId(Long apartmentDetailsId) {
        this.apartmentDetailsId = apartmentDetailsId;
    }

    public Long getApartmentDescriptionId() {
        return apartmentDescriptionId;
    }

    public void setApartmentDescriptionId(Long apartmentDescriptionId) {
        this.apartmentDescriptionId = apartmentDescriptionId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
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
