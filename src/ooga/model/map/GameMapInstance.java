package ooga.model.map;

import ooga.data.DataLoaderAPI;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.GameParam;
import ooga.model.interfaces.gameMap.GameMap;
import ooga.model.interfaces.gameMap.GridInMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The implementation of an entire game map
 *
 * @author cady
 */
public class GameMapInstance implements GameMap {

  private int level;
  private int currentGrid;
  private DataLoaderAPI loader;
  private Map<Integer, GridInMap> allGrids = new HashMap<>();

  /**
   * Creates a new instance of the game map from data loader
   *
   * @param loader a data loader that loads the grids
   */
  public GameMapInstance(DataLoaderAPI loader) {
    this.loader = loader;
    this.level = loader.loadGameParam(GameParam.LEVEL_NUM);
    currentGrid = 0;
    initialize();
  }

  private void initialize() {
    for (int i = 0; i < loader.loadGameParam(GameParam.GRID_NUM); i++) {
      GridInMap grid = new GameGridInMap(loader, i);
      grid.loadGrid(grid.getID(), level);
      allGrids.put(i, grid);
    }
  }

  /**
   * Gets the ID of current grid
   *
   * @return the ID of the current grid
   */
  @Override
  public int getCurrentGridID() {
    return currentGrid;
  }

  /**
   * Sets the ID of current grid
   */
  @Override
  public void setCurrentGridID(int currentGrid) {
    this.currentGrid = currentGrid;
  }

  /**
   * Gets the game map representation in a list.
   *
   * @return the game map representation of all grids in a list.
   */
  @Override
  public List<GridInMap> getGameMap() {
    List<GridInMap> ret = (List<GridInMap>) allGrids.values();
    return Collections.unmodifiableList(ret);
  }

  /**
   * Gets the cell state at a specific location
   *
   * @param gridID the grid in which the cell is located at
   * @param row    the row of this cell
   * @param col    the col of this cell
   * @return the state of this cell
   */
  @Override
  public int getCellState(int gridID, int row, int col) {
    return allGrids.get(gridID).getCellState(row, col);
  }

  /**
   * Gets the cell state at a specific location on this grid
   *
   * @param row the row of this cell
   * @param col the col of this cell
   * @return the state of this cell
   */
  @Override
  public int getCellState(int row, int col) {
    return getCellState(currentGrid, row, col);
  }

  /**
   * Gets the grid on {@code direction} relative to the grid of {@code gridID}
   *
   * @param gridID    the id of that grid
   * @param direction the direction in which the returned map is relative to that grid
   * @return the gird on {@code direction} of that grid, -1 if not existed
   */
  @Override
  public GridInMap getGridOn(int gridID, Direction direction) {
    return allGrids.get(
        allGrids.get(gridID).getGridIDOn(direction));
  }

  /**
   * Gets the grid on {@code direction} relative to the current grid
   *
   * @param direction the direction in which the returned map is relative to the current grid
   * @return the gird on {@code direction} of the current grid, -1 if not existed
   */
  @Override
  public GridInMap getGridOn(Direction direction) {
    return getGridOn(currentGrid, direction);
  }
}
