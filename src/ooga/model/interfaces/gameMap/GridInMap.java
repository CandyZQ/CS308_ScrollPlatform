package ooga.model.interfaces.gameMap;

import ooga.model.enums.backend.Direction;

/**
 * This interface allows a map to contain multiple grids. IDs of grids around the current grid are
 * recorded.
 *
 * @author cady
 * @see SingleGrid
 */
public interface  GridInMap extends SingleGrid {

  /**
   * Sets the id of this grid
   *
   * @param id the id of this grid
   */
  void setID(int id);

  /**
   * Gets the id of this grid
   *
   * @return the id of this grid
   */
  int getID();

  /**
   * Gets the grid on any of the {@link Direction} of the current grid at this cell location
   *
   * @param row       the row number of this cell
   * @param col       the col number of this cell
   * @param direction the direction in which the additional grid is on
   * @return the id of that grid, -1 if this cell is not connected to any other grids or there is no
   * grid on that direction
   */
  int getGridIDOn(int row, int col, Direction direction);

  /**
   * Sets the grid on {@code direction} of this cell
   *
   * @param row       the row number of this cell
   * @param col       the col number of this cell
   * @param direction the direction in which the additional map is on
   * @param gridID    the ID of the additional gird
   */
  void setGridIDOn(int row, int col, Direction direction, int gridID);

  /**
   * Loads this grid on specific level
   * @param level the level of grid
   */
  void loadGrid(int level);
}
