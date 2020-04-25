package ooga.model.interfaces.gameMap;

import java.awt.image.BufferedImage;

/**
 * This interface stores the information of a single cell.
 *
 * @author cady, Guangyu
 */
public interface Cell {

  /**
   * @param state the state value of the cell
   */
  void setState(int state);

  /**
   * @return the state value of the cell
   */
  int getState();

  /**
   * @return the index of image
   */
  int getImage();

  /**
   * @param imageIndex index of the image
   */
  void setImage(int imageIndex);

  /**
   * get cell's unique ID (unique within the level).
   *
   * @return cell's unique ID
   */
  int getUniqueID();

  /**
   * Sets the cell's ID
   *
   * @return the cell's ID
   */
  void setUniqueID(int id);

  /**
   * return whether the cell is walkable.
   */
  boolean isMapCellWalkable();

  /**
   * Sets if the cell is walkable
   * @param walkable  true if walkable
   */
  void setWalkable(boolean walkable);

  /**
   * Gets the buffered image for this cell
   * @return  the buffered image
   */
  BufferedImage getBufferedImage();

  /**
   * Sets the buffered image for this cell
   * @param bufferedImage the buffered image
   */
  void setBufferedImage(BufferedImage bufferedImage);
}
