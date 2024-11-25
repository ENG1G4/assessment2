package com.backlogged.univercity.building;

import com.backlogged.univercity.render.IBuildingRenderer;
import com.backlogged.univercity.building.factory.BuildingFactory;
import com.backlogged.univercity.building.factory.BuildingState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Manages building placement etc. within the game and Handles input, rendering,
 * and state
 * transitions for buildings.
 */
public class BuildingManager {

  private final IBuildingRenderer renderer;
  private final IBuildingPlacementManager placementManager;
  private boolean isChoosingLocation = false;
  private AbstractBuilding buildingToBePlaced;
  private BuildingType buildingToBePlacedType;
  private int currentRow;
  private int currentColumn;
  private BuildingState buildingState = BuildingState.NOT_BUILDING;
  private OrthographicCamera camera;
  private final HashMap<BuildingType, Integer> buildingCounts = new HashMap<>() {
    @Override
    public String toString() {
      StringBuilder out = new StringBuilder();
      for (var entry : this.entrySet()) {
        out.append(String.format("%s : %d\n", entry.getKey().getName(), entry.getValue()));
      }
      return out.toString();
    }
  };
  private boolean canBePlacedAtCurrentLocation;
  private final BuildingFactory buildingFactory;


  /**
   * Constructs a building manager instance.
   *
   * @param unitScale        Scale factor to match with tiled map renderer
   *                         projection matrix
   * @param renderer         Renderer for displaying buildings.
   * @param placementManager Manager for handling building placements.
   */
  public BuildingManager(
      float unitScale, IBuildingRenderer renderer, IBuildingPlacementManager placementManager) {
    if (renderer == null || placementManager == null) {
      throw new IllegalArgumentException("renderer and placement manager MUST both be initialized");
    }

    BuildingTextureCache buildingTextureCache = new BuildingTextureCache(renderer.getAtlas(), unitScale);
    this.buildingFactory = new BuildingFactory(buildingTextureCache);

    initBuildingCounters();
    this.renderer = renderer;
    this.placementManager = placementManager;
  }

  /** Initialises counters for all building types to zero. */
  private void initBuildingCounters() {
    for (var buildingType : BuildingType.values()) {
      buildingCounts.put(buildingType, 0);
    }
  }

  /**
   * Transitions the BuildingManager to building mode if not currently building.
   */
  public void setBuildingState() {
    if (buildingState == BuildingState.NOT_BUILDING) {
      buildingState = BuildingState.BUILDING;
    }
  }

  /** Resets building placement state. */
  private void resetState() {
    buildingToBePlacedType = null;
    buildingToBePlaced = null;
    isChoosingLocation = false;
  }

  /**
   * Returns the count of each type of building currently placed.
   *
   * @return A string of all building types in the form: BuildingType : Count.
   */
  public String getBuildingTypeCounts() {
    return buildingCounts.toString();
  }

  /**
   * Places the building at the specified coordinates.
   *
   * @param row    Row coordinate for placement.
   * @param column Column coordinate for placement.
   */
  private void placeBuilding(int row, int column) {
    placementManager.placeBuilding(row, column, buildingToBePlaced);
    var countForBuildingTypePlaced = buildingCounts.get(buildingToBePlacedType);
    buildingCounts.put(buildingToBePlacedType, ++countForBuildingTypePlaced);
    resetState();
  }

  /**
   * Initiates location choosing state for placing a building.
   *
   * @param buildingType Name of the building type to place.
   */
  public void chooseLocationOfBuilding(BuildingType buildingType) {
    AbstractBuilding building = this.buildingFactory.createBuilding(buildingType);
    buildingToBePlacedType = buildingType;
    buildingToBePlaced = building;
    isChoosingLocation = true;
  }

  /**
   * Sets the camera used for projecting world coordinates.
   *
   * @param camera Camera instance to set.
   */
  public void setCamera(OrthographicCamera camera) {
    this.camera = camera;
  }

  /**
   * Converts screen coordinates to world coordinates, adjusted for the camera's
   * perspective.
   *
   * @return Vector2 representing world coordinates of the cursor position.
   */
  private Vector2 getWorldCoordinates() {
    var cursorPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
    var worldCoordinates = camera.unproject(cursorPos);
    return new Vector2(worldCoordinates.x, worldCoordinates.y);
  }

  /** Processes input based on the current building state. */
  public void handleInput() {
    switch (buildingState) {
      case NOT_BUILDING: {
      }
        break;
      case BUILDING: {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && canBePlacedAtCurrentLocation) {
          placeBuilding(currentRow, currentColumn);
          resetState();
          buildingState = BuildingState.NOT_BUILDING;
        }
      }
        break;
      case DELETING:
        {
        // TODO: UNIMPLEMENTED
        }
        break;
      case MOVING:
        {
        // TODO: UNIMPLEMENTEDt
        }
        break;
      default:
        break;
    }
    if (buildingState != BuildingState.NOT_BUILDING && Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
      resetState();
      buildingState = BuildingState.NOT_BUILDING;
    }
  }

  /**
   * Updates the BuildingManager, updating the row and column based on cursor
   * location if placing a
   * building.
   */
  public void update() {
    if (isChoosingLocation) {
      var worldCoordinates = getWorldCoordinates();
      currentRow = (int) worldCoordinates.x;
      currentColumn = (int) worldCoordinates.y;
      canBePlacedAtCurrentLocation = placementManager.canBePlacedAtLocationIgnoreTerrain(
          currentRow, currentColumn, buildingToBePlaced);
    }
  }

  /** Resets the building placement manager, clearing all placed buildings. */
  public void reset() {
    placementManager.reset();
  }

  /**
   * Gets the total count of buildings currently placed.
   *
   * @return Integer count of placed buildings.
   */
  public int getBuildingCount() {
    return placementManager.getCount();
  }

  /**
   * Renders buildings, and the placement squares if {@code buildingState ==
   * BuildingState.BUILDING}.
   */
  public void render() {

    renderer.renderBuildings(placementManager.getPlacedBuildings(), camera);
    if (isChoosingLocation) {
      renderer.renderPlacementFeedback(
          canBePlacedAtCurrentLocation, currentRow, currentColumn, camera, buildingToBePlaced);
    }
  }
}
