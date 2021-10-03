package com.example.apartmentsforrent.persistence.model;

public class ApartmentDescription {
    private long id;
    private long apartmentId;
    private String condition;
    private String buildingType;
    private String additionalInfo;

    public ApartmentDescription(long apartmentId, String condition, String buildingType, String additionalInfo) {
        this.apartmentId = apartmentId;
        this.condition = condition;
        this.buildingType = buildingType;
        this.additionalInfo = additionalInfo;
    }

    public ApartmentDescription() {
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

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
