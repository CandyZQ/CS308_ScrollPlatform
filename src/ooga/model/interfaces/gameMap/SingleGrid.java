package ooga.model.interfaces.gameMap;

import java.util.List;
import ooga.model.enums.backend.Direction;

/**
 * This interface creates a single grid that is made up of {@link Cell}. The grid can be loaded from
 * an external file.
 *
 * @author cady
 * @see Cell
 */
public interface SingleGrid {

  /**
   * Sets the size of this grid
   *
   * @param length the length of this grid
   * @param width  the width of this grid
   */
  void setSize(int length, int width);

  /**
   * Gets the length of this grid
   *
   * @return the length of this grid
   */
  int getLength();

  /**
   * Gets the width of this grid
   *
   * @return the width of this grid
   */
  int getWidth();

  /**
   * Loads the grid from an external file
   *
   * @param id
   * @param level
   */
  void loadGrid(int id, int level);

  /**
   * Gets the cell state at a specific location
   *
   * @param row the row number of that cell
   * @param col the col number of that cell
   * @return the state of that cell
   */
  int getCellState(int row, int col);

  /**
   * Sets the cell state at a specific location
   *
   * @param row   the row number of that cell
   * @param col   the col number of that cell
   * @param state the state of that cell
   */
  void setCellState(int row, int col, int state);

  /**
   * Gets if a specific cell is connected to another grid on the same map
   *
   * @param row       the row number of that cell
   * @param col       the col number of that cell
   * @param direction the relative position in which the other grid is
   * @return if that cell is a connected to another grid
   */
  boolean isGateCell(int row, int col, Direction direction);

  /**
   * Gets the entire gird
   *
   * @return the gird
   */
  List<List<?>> getGrid();
}
