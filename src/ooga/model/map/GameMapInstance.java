package ooga.model.map;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import ooga.data.DataLoaderAPI;
import ooga.model.enums.Direction;
import ooga.model.interfaces.gameMap.GameMap;
import ooga.model.interfaces.gameMap.GridInMap;

public class GameMapInstance implements GameMap {

  public static final int DEFAULT_LEVEL = 0;
  public static final int ROW_PLACE_HOLDER = 0;
  public static final int COL_PLACE_HOLDER = 0;
  //TODO: delete this
  int mapNumber = 3;

  protected int level;
  protected int currentGrid;
  protected DataLoaderAPI loader;
  protected Map<Integer, GridInMap> allGrids;

  public GameMapInstance(DataLoaderAPI loader) {
    this(loader, DEFAULT_LEVEL);
  }

  public GameMapInstance(DataLoaderAPI loader, int level) {
    this.loader = loader;
    this.level = level;
    currentGrid = 0;
    initialize();
  }

  private void initialize() {
    for (int i = 0; i < mapNumber; i++) {
      GridInMap grid = new GameGridInMap(loader, i);
      grid.loadGrid(level);
      allGrids.put(i, grid);
    }
  }

  @Override
  public int getCurrentGridID() {
    return currentGrid;
  }

  @Override
  public void setCurrentGridID(int currentGrid) {
    this.currentGrid = currentGrid;
  }

  @Override
  public List<GridInMap> getGameMap() {
    List<GridInMap> ret = (List) allGrids.values();
    return Collections.unmodifiableList(ret);
  }

  @Override
  public int getCellState(int gridID, int row, int col) {
    return allGrids.get(gridID).getCellState(row, col);
  }

  @Override
  public int getCellState(int row, int col) {
    return getCellState(currentGrid, row, col);
  }

  @Override
  public GridInMap getGridOn(int gridID, Direction direction) {
    return allGrids.get(
        allGrids.get(gridID).getGridIDOn(ROW_PLACE_HOLDER, COL_PLACE_HOLDER, direction));
  }

  @Override
  public GridInMap getGridOn(Direction direction) {
    return getGridOn(currentGrid, direction);
  }
}