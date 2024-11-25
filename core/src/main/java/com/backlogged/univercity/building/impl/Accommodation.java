package com.backlogged.univercity.building.impl;

import com.backlogged.univercity.building.AbstractBuilding;
import com.backlogged.univercity.building.BuildingType;
import com.badlogic.gdx.graphics.g2d.Sprite;

/** This class represents basic student acccommodation. */
public class Accommodation extends AbstractBuilding {

  /**
   * Constructs an Accommodation instance.
   *
   * @param sprite The {@link Sprite} to render for the building.
   */
  public Accommodation(Sprite sprite) {
    super(BuildingType.ACCOMMODATION, sprite);
  }
}
