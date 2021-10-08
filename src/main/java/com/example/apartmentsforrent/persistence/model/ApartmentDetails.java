package com.example.apartmentsforrent.persistence.model;

import java.math.BigDecimal;
import java.time.Year;

public class ApartmentDetails {
    private long id;
    private String address;
    private Year buildYear;
    private BigDecimal price;
    private int floor;
    private float area;
    private int quantityOfRooms;

    public ApartmentDetails() {
    }

    public ApartmentDetails(String address, Year buildYear, BigDecimal price, int floor, float area, int quantityOfRooms) {
        this.address = address;
        this.buildYear = buildYear;
        this.price = price;
        this.floor = floor;
        this.area = area;
        this.quantityOfRooms = quantityOfRooms;
    }

    public static class Builder {
        private long id;
        private String address;
        private Year buildYear;
        private BigDecimal price;
        private int floor;
        private float area;
        private int quantityOfRooms;

        public Builder setId(long id) {
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
