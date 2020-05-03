package ooga.model.map;

import ooga.data.DataLoaderAPI;
import ooga.model.enums.backend.Direction;
import ooga.model.interfaces.gameMap.GridInMap;
import java.util.HashMap;
import java.util.Map;

/**
 * This class implements grids that holds relationship to other grids in a map. It is well designed
 * as extends from {@link GameSingleGrid} to adapt a larger map.
 *
 * @author cady
 */
public class GameGridInMap extends GameSingleGrid implements GridInMap {

  public static final int ID_NOT_DEFINED = 666;

  private int id;
  private Map<Direction, Integer> surroundingMaps = new HashMap<>();

  /**
   * Creats a new instance of a grid in a map
   * @param loader  a data loader that loads a grid
   * @param id  the id of this grid
   */
  public GameGridInMap(DataLoaderAPI loader, int id) {
    super(loader);
    this.id = id;
    initializeSurrounding();
  }

  // initializes all surrounding grids
  private void initializeSurrounding() {
    for (Direction d : Direction.values()) {
      int neighbor = loader.getNextSubMapID(d, id);
      if (neighbor != ID_NOT_DEFINED) {
        surroundingMaps.put(d, neighbor);
      }
    }
  }

  /**
   * Sets the id of this grid
   *
   * @param id the id of this grid
   */
  @Override
  public void setID(int id) {
    this.id = id;
  }

  /**
   * Gets the id of this grid
   *
   * @return the id of this grid
   */
  @Override
  public int getID() {
    return id;
  }

  /**
   * Gets the grid on any of the {@link Direction} of the current grid
   *
   * @param direction the direction in which the additional grid is on
   * @return the id of that grid, -1 if this cell is not connected to any other grids or there is no
   * grid on that direction
   */
  @Override
  public int getGridIDOn(Direction direction) {
    return surroundingMaps.get(direction);
  }

  /**
   * Sets the grid on {@code direction} of this cell
   *
   * @param direction the direction in which the additional map is on
   * @param gridID    the ID of the additional gird
   */
  @Override
  public void setGridIDOn(Direction direction, int gridID) {
    surroundingMaps.put(direction, gridID);
  }

  /**
   * Loads this grid on specific level
   *
   * @param level the level of grid
   */
  @Override
  public void loadGrid(int level) {
    loadGrid(this.id, level);
  }
}
