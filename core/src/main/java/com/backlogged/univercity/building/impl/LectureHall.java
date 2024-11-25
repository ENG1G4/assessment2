package com.backlogged.univercity.building.impl;

import com.backlogged.univercity.building.AbstractBuilding;
import com.backlogged.univercity.building.BuildingType;
import com.badlogic.gdx.graphics.g2d.Sprite;

/** This class represents a basic Lecture Hall. */
public class LectureHall extends AbstractBuilding {

    /**
     * Constructs a LectureHall instance.
     *
     * @param sprite The {@link Sprite} to render for the building.
     */
    public LectureHall(Sprite sprite) {
        super(BuildingType.LECTURE_HALL, sprite);
    }
}
