package com.example.apartmentsforrent.web.dto;

import com.example.apartmentsforrent.persistence.model.BuildingType;

public class ApartmentDescriptionDto {
    private String condition;
    private BuildingType buildingType;
    private String additionalInfo;

    public ApartmentDescriptionDto(String condition, BuildingType buildingType, String additionalInfo) {
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

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(BuildingType buildingType) {
        this.buildingType = buildingType;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
