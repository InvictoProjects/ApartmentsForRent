package com.example.apartmentsforrent.persistence.model;

import java.math.BigDecimal;
import java.time.Year;

public class ApartmentDetails {
    private Long id;
    private String address;
    private Year buildYear;
    private BigDecimal price;
    private int floor;
    private float area;
    private int quantityOfRooms;

    public ApartmentDetails() {
    }

    public static class Builder {
        private Long id;
        private String address;
        private Year buildYear;
        private BigDecimal price;
        private int floor;
        private float area;
        private int quantityOfRooms;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setBuildYear(Year buildYear) {
            this.buildYear = buildYear;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            this.price = price;
            return this;
        }

        public Builder setFloor(int floor) {
            this.floor = floor;
            return this;
        }

        public Builder setArea(float area) {
            this.area = area;
            return this;
        }

        public Builder setQuantityOfRooms(int quantityOfRooms) {
            this.quantityOfRooms = quantityOfRooms;
            return this;
        }

        public ApartmentDetails build() {
            return new ApartmentDetails(this);
        }
    }

    private ApartmentDetails(Builder builder) {
        this.id = builder.id;
        this.address = builder.address;
        this.buildYear = builder.buildYear;
        this.price = builder.price;
        this.floor = builder.floor;
        this.area = builder.area;
        this.quantityOfRooms = builder.quantityOfRooms;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Year getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Year buildYear) {
        this.buildYear = buildYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public int getQuantityOfRooms() {
        return quantityOfRooms;
    }

    public void setQuantityOfRooms(int quantityOfRooms) {
        this.quantityOfRooms = quantityOfRooms;
    }
}
