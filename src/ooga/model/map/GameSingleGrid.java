package ooga.model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.data.DataLoaderAPI;
import ooga.model.enums.backend.Direction;
import ooga.model.interfaces.gameMap.SingleGrid;

/**
 * This class implements a single grid in a map
 *
 * @author cady
 */
public class GameSingleGrid implements SingleGrid {

  protected int width;
  protected int length;

  protected List<List<GameCell>> grid;
  protected DataLoaderAPI loader;

  public GameSingleGrid(DataLoaderAPI loader) {
    grid = new ArrayList<>();
    this.loader = loader;
  }

  /**
   * Sets the size of this grid
   *
   * @param length the length of this grid
   * @param width  the width of this grid
   */
  @Override
  public void setSize(int length, int width) {
    this.length = length;
    this.width = width;
  }

  /**
   * Gets the length of this grid
   *
   * @return the length of this grid
   */
  @Override
  public int getLength() {
    return length;
  }

  /**
   * Gets the width of this grid
   *
   * @return the width of this grid
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Loads the grid from an external file
   *
   * @param id
   * @param level
   */
  @Override
  public void loadGrid(int id, int level) {
    for (int r = 0; r < length; r++) {
      List<GameCell> currentRow = new ArrayList<>();
      for (int c = 0; c < width; c++) {
        currentRow.add((GameCell) loader.loadCell(r, c, id, level));
      }
      grid.add(currentRow);
    }
  }

  /**
   * Gets the cell state at a specific location
   *
   * @param row the row number of that cell
   * @param col the col number of that cell
   * @return the state of that cell
   */
  @Override
  public int getCellState(int row, int col) {
    return grid.get(row).get(col).getState();
  }

  /**
   * Sets the cell state at a specific location
   *
   * @param row   the row number of that cell
   * @param col   the col number of that cell
   * @param state the state of that cell
   */
  @Override
  public void setCellState(int row, int col, int state) {
    grid.get(row).get(col).setState(state);
  }

  /**
   * Gets if a specific cell is connected to another grid on the same map
   *
   * @param row       the row number of that cell
   * @param col       the col number of that cell
   * @param direction the relative position in which the other grid is
   * @return if that cell is a connected to another grid
   */
  // TODO: implement this if needed
  @Override
  public boolean isGateCell(int row, int col, Direction direction) {
    return false;
  }

  /**
   * Gets the entire gird
   *
   * @return the gird
   */
  @Override
  public List<List<?>> getGrid() {
    return Collections.unmodifiableList(grid);
  }
}
