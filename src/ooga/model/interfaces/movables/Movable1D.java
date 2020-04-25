package ooga.model.interfaces.movables;

import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.MovingState;

/**
 * This interface can be used by objects that move on a 1D line.
 *
 * @author cady
 */
public interface  Movable1D {

  /**
   * Sets the x position of this object
   *
   * @param x the x position of this object
   */
  void setX(double x);

  /**
   * Moves this object on the line for a certain distance. Note that this command uses the Cartesian
   * coordinate system. For example, move right for 5 should call moveInX(5)
   *
   * @param deltaX the distance in which the object moves in the x direction
   */
  void moveInX(double deltaX);

  /**
   * Gets the x position of this object
   *
   * @return the x position of this object
   */
  double getX();

  /**
   * Sets the speed this object moves in the x direction
   *
   * @param xSpeed the speed this object moves in the x direction
   */
  void setXSpeed(double xSpeed);

  /**
   * Gets the speed this object moves in the x direction
   *
   * @return the speed this object moves in the x direction
   */
  double getXSpeed();

  /**
   * Gets if the frontend is busy animating the last movement. If yes, backend states will not be
   * changed
   *
   * @return if frontend is animating
   */
  boolean isBusy();

  /**
   * Sets the current moving state
   * @param movingState the current moving state
   */
  void setState(MovingState movingState);

  /**
   * Gets the current moving state
   * @return  the current moving state
   */
  MovingState getState();

  /**
   * Sets the direction of moving
   * @param direction of moving
   */
  void setDirection(Direction direction);

  /**
   * Gets the direction of moving
   * @return the direction of moving
   */
  Direction getDirection();
}
