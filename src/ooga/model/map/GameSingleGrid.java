package ooga.model.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import ooga.data.DataLoaderAPI;
import ooga.model.interfaces.gameMap.SingleGrid;

/**
 * This class implements a independent single grid. It is well encapsulated by using unmodifiable
 * list. It can be used on its own for simple maps or further extended as shown in othet masterpiece
 * classes.
 *
 * @author cady
 */
public class GameSingleGrid implements SingleGrid {

  protected int width;
  protected int length;
  protected DataLoaderAPI loader;

  private List<List<GameCell>> grid;

  /**
   * Creates a new instance of grid from loader
   *
   * @param loader the data loader that loads map from configuration file
   */
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
   * @param id    the id of this grid
   * @param level the level in which this grid is in
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
   * Gets the entire gird
   *
   * @return the gird
   */
  @Override
  public List<List<?>> getGrid() {
    return Collections.unmodifiableList(grid);
  }
}
