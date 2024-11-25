package com.backlogged.univercity.building;

public enum BuildingAttribute {

    EDUCATION("Education"),
    ACCOMMODATION("Accommodation"),
    LEISURE("Leisure"),
    FOOD("Food");

    private final String name;

    BuildingAttribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
