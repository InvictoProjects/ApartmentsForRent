package com.example.apartmentsforrent.persistence.model;

public enum BuildingType {
    BRICK("Brick"),
    FRAME("Frame"),
    SILICATE_BRICK("Silicate brick"),
    PANEL("Panel"),
    FOAM_BLOCK("Foam block"),
    MONOLITH("Monolith");

    private final String displayValue;

    BuildingType(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
