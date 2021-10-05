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
