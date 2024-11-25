package com.backlogged.univercity.building.factory;

import com.backlogged.univercity.building.AbstractBuilding;
import com.backlogged.univercity.building.BuildingTextureCache;
import com.backlogged.univercity.building.BuildingType;
import com.backlogged.univercity.building.impl.Accommodation;
import com.backlogged.univercity.building.impl.FoodCourt;
import com.backlogged.univercity.building.impl.LectureHall;
import com.backlogged.univercity.building.impl.SportsCentre;
import com.badlogic.gdx.graphics.g2d.Sprite;

/** Factory for creating building instances. */
public class BuildingFactory {

    private final BuildingTextureCache textureCache;

    public BuildingFactory(BuildingTextureCache spriteCache) {
        this.textureCache = spriteCache;
    }

    /**
     * Instantiates an AbstractBuilding based on the specified {@link BuildingType}
     */
    public AbstractBuilding createBuilding(BuildingType buildingType) {
        Sprite sprite = textureCache.getSprite(buildingType.getAtlasRegion());

        return switch (buildingType) {
          case LECTURE_HALL -> new LectureHall(sprite);
          case ACCOMMODATION -> new Accommodation(sprite);
          case SPORTS_CENTRE -> new SportsCentre(sprite);
          case FOOD_COURT -> new FoodCourt(sprite);
        };
    }
}
