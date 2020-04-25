package ooga.data;

import ooga.model.enums.backend.Direction;
import ooga.model.enums.DisplayParam;
import ooga.model.enums.ImageCategory;
import ooga.model.interfaces.gameMap.Cell;
import ooga.model.map.GameCell;

import java.util.HashMap;
import java.util.Map;

public class GameMapGraph {

  public static final int SUBMAP_ROW_NUM = 22;//from frontend
  public static final int SUBMAP_COL_NUM = 32;//from frontend
  public static final int PIXEL_PALETTE_HEIGHT = 15;
  public static final int PIXEL_PALETTE_WIDTH = 8;

  private Cell[][] cellArray;
  private int level;
  private int gameID;
  private int subMapID;
  private Map<Direction, Integer> directionMap;
  private Map<DisplayParam, Integer> displayParamMap;

  public GameMapGraph() {
    directionMap = new HashMap<>();
    for (Direction i : Direction.values()) {
      directionMap.put(i, null);
    }
  }

  public GameMapGraph(int level, int subMapID, int row, int column, int GameID) {
    this();
    this.level = level;
    this.subMapID = subMapID;
    this.gameID = GameID;
    cellArray = new GameCell[row][column];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < column; j++) {
        cellArray[i][j] = new GameCell(i * (column - 1) + j);
      }
    }
  }

  public GameMapGraph(int level, int ID, int row, int column, int GameID,
      Map<Direction, Integer> newDirections) {
    this(level, ID, row, column, GameID);
    addDirectionMap(newDirections);
  }

  public void setElement(int row, int column, Cell cell) {
    cellArray[row][column] = cell;
  }

  public Cell getElement(int row, int column) {
    return cellArray[row][column];
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getGameID() {
    return gameID;
  }

  public void setGameID(int gameID) {
    this.gameID = gameID;
  }

  public void setDirectionMap(Map<Direction, Integer> directionMap) {
    this.directionMap = directionMap;
  }

  public void addDirectionMap(Map<Direction, Integer> newDirections) {
    for (Direction i : newDirections.keySet()) {
      directionMap.replace(i, newDirections.get(i));
    }
  }

  public Map<Direction, Integer> getDirectionMap() {
    return directionMap;
  }

  public int getSubMapID() {
    return subMapID;
  }

  public void setSubMapID(int subMapID) {
    this.subMapID = subMapID;
  }

  /**
   * causes circular dependency between loader and gameMapGraph class Since buffer imaged is not
   * stored in Json, we have to load them manually.
   */
  public void addBufferImage2D(DataLoader dataLoader) {
    for (Cell[] cellRow : cellArray) {
      for (Cell cell : cellRow) {
        cell.setBufferedImage(dataLoader.loadBufferImage(cell.getImage(), ImageCategory.MAP2D));
      }
    }
  }

  public int getRowNum() {
    return SUBMAP_ROW_NUM;
  }

  public int getColNum() {
    return SUBMAP_COL_NUM;
  }

  public int getPixelPaletteHeight() {
    return PIXEL_PALETTE_WIDTH;
  }

}
