package com.example.apartmentsforrent.persistence.model;

public class ApartmentDescription {
    private Long id;
    private String condition;
    private String buildingType;
    private String additionalInfo;

    public ApartmentDescription() {
    }

    public static class Builder {
        private Long id;
        private String condition;
        private String buildingType;
        private String additionalInfo;

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setCondition(String condition) {
            this.condition = condition;
            return this;
        }

        public Builder setBuildingType(String buildingType) {
            this.buildingType = buildingType;
            return this;
        }

        public Builder setAdditionalInfo(String additionalInfo) {
            this.additionalInfo = additionalInfo;
            return this;
        }

        public ApartmentDescription build() {
            return new ApartmentDescription(this);
        }
    }

    private ApartmentDescription(Builder builder) {
        this.id = builder.id;
        this.additionalInfo = builder.additionalInfo;
        this.buildingType = builder.buildingType;
        this.condition = builder.condition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
