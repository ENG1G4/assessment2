package com.backlogged.univercity.building;

import com.backlogged.univercity.Coord;

public enum BuildingType {

    LECTURE_HALL(
        "Lecture Hall",
        new BuildingAttribute[]{BuildingAttribute.EDUCATION},
        "rhombus",
        new Coord(2, 2),
        "Students attend lectures here"),
    ACCOMMODATION(
        "Accommodation",
        new BuildingAttribute[]{BuildingAttribute.ACCOMMODATION},
        "square",
        new Coord(2, 2),
        "Students live here"
    ),
    SPORTS_CENTRE(
        "Sports Centre",
        new BuildingAttribute[]{BuildingAttribute.LEISURE},
        "hex",
        new Coord(2, 2),
        "Students can play sports here"
    ),
    FOOD_COURT(
        "Food Court",
        new BuildingAttribute[]{BuildingAttribute.FOOD},
        "circle",
        new Coord(2, 2),
        "Students can get food here"
    );

    private final BuildingAttribute[] buildingAttributes;
    private final String atlasRegion;
    private final Coord tileCoverageOffsets;
    private final String info;
    private final String name;

    BuildingType(String name, BuildingAttribute[] buildingAttributes, String atlasRegion, Coord tileCoverageOffsets, String info) {
        this.name = name;
        this.buildingAttributes = buildingAttributes;
        this.atlasRegion = atlasRegion;
        this.tileCoverageOffsets = tileCoverageOffsets;
        this.info = info;
    }

    public BuildingAttribute[] getBuildingAttributes() {
        return buildingAttributes;
    }

    public String getAtlasRegion() {
        return atlasRegion;
    }

    public Coord getTileCoverageOffsets() {
        return tileCoverageOffsets;
    }

    public String getInfo() {
        return info;
    }

    public String getName() {
        return name;
    }
}
