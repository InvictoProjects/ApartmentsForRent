package com.example.apartmentsforrent.persistence.model;

import java.math.BigDecimal;
import java.time.Year;

public class ApartmentDetails {
    private long id;
    private long apartmentId;
    private String address;
    private Year buildYear;
    private BigDecimal price;
    private int floor;
    private int area;
    private int quantityOfRooms;

    public ApartmentDetails() {
    }

    public ApartmentDetails(long apartmentId, String address, Year buildYear, BigDecimal price, int floor, int area, int quantityOfRooms) {
        this.apartmentId = apartmentId;
        this.address = address;
        this.buildYear = buildYear;
        this.price = price;
        this.floor = floor;
        this.area = area;
        this.quantityOfRooms = quantityOfRooms;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
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

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getQuantityOfRooms() {
        return quantityOfRooms;
    }

    public void setQuantityOfRooms(int quantityOfRooms) {
        this.quantityOfRooms = quantityOfRooms;
    }
}
