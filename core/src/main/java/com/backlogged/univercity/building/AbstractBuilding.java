package com.backlogged.univercity.building;

import com.backlogged.univercity.Coord;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an abstract base class for buildings that can be placed on a map.
 * This class provides
 * core properties and behaviors for a building, such as its type, sprite, tile
 * coverage, and map
 * position. Concrete building classes should extend this class to implement
 * specific building
 * functionalities.
 */
public abstract class AbstractBuilding {

  private final BuildingType buildingType;
  private final Sprite sprite;
  private Coord mapPos;

  /**
   * Constructs an {@code AbstractBuilding} instance with the specified building
   * information.
   *
   * @param buildingType Information about the building, including its type,
   *                     sprite location, tile coverage,
   *                     and additional info.
   */
  public AbstractBuilding(BuildingType buildingType, Sprite sprite) {
    this.buildingType = buildingType;
    this.sprite = sprite;
  }

  /**
   * Retrieves the type(s) of this building.
   *
   * @return An immutable list of strings representing the type categories of the building.
   */
  public final List<String> getType() {
    return Arrays.stream(buildingType.getBuildingAttributes()).map(BuildingAttribute::getName).toList();
  }

  /**
   * Retrieves the sprite associated with this building, used for rendering the
   * building on the map.
   *
   * @return The {@code Sprite} representing the building's visual appearance.
   */
  public final Sprite getSprite() {
    return this.sprite;
  }

  /**
   * Retrieves the tile coverage offsets for this building. These offsets specify
   * the tiles that
   * this building occupies relative to its bottom-left most tile.
   *
   * @return An {@code ArrayList} of {@code Coord} representing the tile coverage
   *         offsets for this
   *         building.
   */
  public final List<Coord> getTileCoverageOffsets() {
    return List.of(
        new Coord(0, 0),
        new Coord(0, 1),
        new Coord(1, 0),
        new Coord(1, 1)
    );
  }

  /**
   * Retrieves information about this building.
   *
   * @return A string containing additional information about the building.
   */
  public final String getInfo() {
    return buildingType.getInfo();
  }

  /**
   * Sets the position of this building on the map.
   *
   * @param mapPos The {@code Coord} representing the building's position on the
   *               map grid.
   */
  public final void setMapPosition(Coord mapPos) {
    this.mapPos = mapPos;
  }

  /**
   * Retrieves the position of this building on the map.
   *
   * @return The {@code Coord} representing the building's position on the map
   *         grid.
   */
  public final Coord getMapPos() {
    return mapPos;
  }

  /**
   * Draws the building's sprite at its current map position using the specified
   * {@code
   * SpriteBatch}. This method sets the position of the sprite to the map
   * coordinates, renders it,
   * and then resets the sprite position to (0,0).
   *
   * @param batch The {@code SpriteBatch} used to draw the building's sprite.
   */
  public final void draw(SpriteBatch batch) {
    this.sprite.setPosition(this.mapPos.getRow(), this.mapPos.getColumn());
    this.sprite.draw(batch);
    this.sprite.setPosition(0, 0);
  }
}
