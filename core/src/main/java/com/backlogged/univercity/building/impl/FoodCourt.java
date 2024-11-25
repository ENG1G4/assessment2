package com.backlogged.univercity.building.impl;

import com.backlogged.univercity.building.AbstractBuilding;
import com.backlogged.univercity.building.BuildingType;
import com.badlogic.gdx.graphics.g2d.Sprite;

/** This class represents a basic food court. */
public class FoodCourt extends AbstractBuilding {

    /**
     * Constructs a FoodCourt instance.
     *
     * @param sprite The {@link Sprite} to render for the building.
     */
    public FoodCourt(Sprite sprite) {
        super(BuildingType.FOOD_COURT, sprite);
    }
}
