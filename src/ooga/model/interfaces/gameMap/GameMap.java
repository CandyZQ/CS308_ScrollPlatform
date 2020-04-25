package ooga.model.interfaces.gameMap;

import java.util.List;
import ooga.model.enums.backend.Direction;

/**
 * GameMap is the whole map for the game. It consists many {@link GridInMap} and talk to other parts
 * of the project.
 *
 * @author cady
 * @see GridInMap
 */
public interface  GameMap {

  /**
   * Gets the ID of current grid
   * @return  the ID of the current grid
   */
  int getCurrentGridID();

  /**
   * Sets the ID of current grid
   */
  void setCurrentGridID(int currentGrid);
  /**
   * Gets the game map representation in a list.
   *
   * @return the game map representation of all grids in a list.
   */
  List<GridInMap> getGameMap();

  /**
   * Gets the cell state at a specific location
   *
   * @param gridID the grid in which the cell is located at
   * @param row    the row of this cell
   * @param col    the col of this cell
   * @return the state of this cell
   */
  int getCellState(int gridID, int row, int col);

  /**
   * Gets the cell state at a specific location on this grid
   *
   * @param row    the row of this cell
   * @param col    the col of this cell
   * @return the state of this cell
   */
  int getCellState(int row, int col);

  /**
   * Gets the grid on {@code direction} relative to the grid of {@code gridID}
   * @param gridID  the id of that grid
   * @param direction the direction in which the returned map is relative to that grid
   * @return  the gird on {@code direction} of that grid, -1 if not existed
   */
  GridInMap getGridOn(int gridID, Direction direction);


  /**
   * Gets the grid on {@code direction} relative to the current grid
   * @param direction the direction in which the returned map is relative to the current grid
   * @return  the gird on {@code direction} of the current grid, -1 if not existed
   */
  GridInMap getGridOn(Direction direction);
}
