package ooga.model.map;

import ooga.data.DataLoaderAPI;
import ooga.model.enums.backend.Direction;
import ooga.model.interfaces.gameMap.GridInMap;

import java.util.HashMap;
import java.util.Map;

/**
 * This class implements grids' relationship in a map
 *
 * @author cady
 */
public class  GameGridInMap extends GameSingleGrid implements GridInMap {

  public static final int ID_NOT_DEFINED = 666;

  protected int id;
  protected Map<Direction, Integer> surroundingMaps = new HashMap<>();

  public GameGridInMap(DataLoaderAPI loader, int id) {
    super(loader);
    this.id = id;
    initializeSurrounding();
  }

  private void initializeSurrounding() {
    for (Direction d: Direction.values()) {
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
   * Gets the grid on any of the {@link Direction} of the current grid at this cell location
   *
   * @param row       the row number of this cell
   * @param col       the col number of this cell
   * @param direction the direction in which the additional grid is on
   * @return the id of that grid, -1 if this cell is not connected to any other grids or there is no
   * grid on that direction
   */
  // TODO: implement use row and col in (next * 5) iteration
  @Override
  public int getGridIDOn(int row, int col, Direction direction) {
    return surroundingMaps.get(direction);
  }
  /**
   * Sets the grid on {@code direction} of this cell
   *
   * @param row       the row number of this cell
   * @param col       the col number of this cell
   * @param direction the direction in which the additional map is on
   * @param gridID    the ID of the additional gird
   */
  @Override
  public void setGridIDOn(int row, int col, Direction direction, int gridID) {
    surroundingMaps.put(direction, gridID);
  }

  /**
   * Loads this grid on specific level
   * @param level the level of grid
   */
  @Override
  public void loadGrid(int level) {
    loadGrid(this.id, level);
  }
}
