package ooga.model.map;

import ooga.model.interfaces.gameMap.Cell;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * This class implements a single cell
 *
 * @author cady
 */
public class GameCell implements Cell {

  private ArrayList<Cell> neighbours;
  private int ID;
  private int cellState;
  private int imageIndex;
  private boolean walkable;
  private transient BufferedImage bufferedImage;

  public GameCell() {

  }

  public GameCell(int ID) {
    this.ID = ID;
  }

  /**
   * @param state the state value of the cell
   */
  @Override
  public void setState(int state) {
    cellState = state;
  }

  /**
   * @return the state value of the cell
   */
  @Override
  public int getState() {
    return cellState;
  }

  /**
   * @return the index of image
   */
  @Override
  public int getImage() {
    return imageIndex;
  }

  /**
   * @param imageIndex index of the image
   */
  @Override
  public void setImage(int imageIndex) {
    this.imageIndex = imageIndex;
  }

  /**
   * get cell's unique ID (unique within the level).
   *
   * @return cell's unique ID
   */
  @Override
  public int getUniqueID() {
    return this.ID;
  }

  /**
   * Sets the cell's ID
   *
   * @return the cell's ID
   */
  @Override
  public void setUniqueID(int id) {
    this.ID = id;
  }

  /**
   * return whether the cell is walkable.
   */
  @Override
  public boolean isMapCellWalkable() {
    return walkable;
  }

  /**
   * Sets if the cell is walkable
   *
   * @param walkable true if walkable
   */
  @Override
  public void setWalkable(boolean walkable) {
    this.walkable = walkable;
  }

  /**
   * Gets the buffered image for this cell
   *
   * @return the buffered image
   */
  @Override
  public BufferedImage getBufferedImage() {
    return bufferedImage;
  }

  /**
   * Sets the buffered image for this cell
   *
   * @param bufferedImage the buffered image
   */
  @Override
  public void setBufferedImage(BufferedImage bufferedImage) {
    this.bufferedImage = bufferedImage;
  }
}
