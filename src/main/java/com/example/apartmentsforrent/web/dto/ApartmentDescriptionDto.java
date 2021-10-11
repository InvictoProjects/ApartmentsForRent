package com.example.apartmentsforrent.web.dto;

public class ApartmentDescriptionDto {
    private String condition;
    private String buildingType;
    private String additionalInfo;

    public ApartmentDescriptionDto(String condition, String buildingType, String additionalInfo) {
        this.condition = condition;
        this.buildingType = buildingType;
        this.additionalInfo = additionalInfo;
    }

    public ApartmentDescriptionDto() {
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
