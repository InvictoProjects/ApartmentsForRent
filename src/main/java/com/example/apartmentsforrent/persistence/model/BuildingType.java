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

    public static BuildingType resolve(String type) {
        switch (type) {
            case "Brick":
                return BRICK;
            case "Frame":
                return FRAME;
            case "Silicate brick":
                return SILICATE_BRICK;
            case "Panel":
                return PANEL;
            case "Foam block":
                return FOAM_BLOCK;
            case "Monolith":
                return MONOLITH;
        }
        return null;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
