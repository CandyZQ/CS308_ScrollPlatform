package ooga.model.interfaces.movables;

/**
 * This interface holds an object that can move on both x and y directions.
 *
 * @author cady
 * @see Movable1D
 */
public interface  Movable2D extends Movable1D {

  /**
   * Sets the y position of this object
   *
   * @param y the y position of this object
   */
  void setY(double y);

  /**
   * Gets the y position of this object
   *
   * @return the y position of this object
   */
  double getY();

  /**
   * Moves this object for a certain distance in the y direction. Note that this command uses the
   * Cartesian coordinate system. For example, move up for 5 should call moveInY(5)
   *
   * @param deltaY the y distance moved
   */
  void moveInY(double deltaY);


  /**
   * Sets the speed this object moves in the y direction
   *
   * @param ySpeed the speed this object moves in the y direction
   */
  void setYSpeed(double ySpeed);

  /**
   * Gets the speed this object moves in the x direction
   *
   * @return the speed this object moves in the x direction
   */
  double getYSpeed();

  /**
   * Sets the speed this object moves in both the x and y direction
   *
   * @param xSpeed the speed this object moves in the x direction
   * @param ySpeed the speed this object moves in the y direction
   */
  void setSpeed(double xSpeed, double ySpeed);

  /**
   * Sets the x and y position of this object
   *
   * @param x the x position of this object
   * @param y the y position of this object
   */
  void set2DPos(double x, double y);

  /**
   * allows the object to move diagonally
   */
  void enableDiagonalMove();

  /**
   * allow the object to move only in NSWE directions
   */
  void disableDiagonalMove();

  /**
   * Sets the speed that this object moves diagonally
   * @param dSpeed  the diagonal speed of this object
   */
  void setsDiagonalSpeed(double dSpeed);

  /**
   * Gets the speed that this object moves diagonally
   * @return the diagonal speed of this object
   */
  double getDiagonalSpeed();

  /**
   * Gets if this object moves diagonally
   * @return  if this object moves diagonally
   */
  boolean isDiagonalMove();

}
