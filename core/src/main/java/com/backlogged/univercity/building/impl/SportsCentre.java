package com.backlogged.univercity.building.impl;

import com.backlogged.univercity.building.AbstractBuilding;
import com.backlogged.univercity.building.BuildingType;
import com.badlogic.gdx.graphics.g2d.Sprite;

/** This class represents a basic sports center. */
public class SportsCentre extends AbstractBuilding {

    /**
     * Constructs a SportsCentre instance.
     *
     * @param sprite The {@link Sprite} to render for the building.
     */
    public SportsCentre(Sprite sprite) {
        super(BuildingType.SPORTS_CENTRE, sprite);
    }
}
