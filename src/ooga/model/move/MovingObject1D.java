package ooga.model.move;

import ooga.controller.gamecontrol.player.ZeldaPlayerControl;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.MovingState;

/**
 * An abstract class that moves in 1D
 */
public abstract class MovingObject1D implements ooga.model.interfaces.movables.Movable1D {

  public static final int DEFAULT_X_SPEED = 5;
  public static final int DEFAULT_X = 0;

  protected double x;
  protected double xSpeed;
  protected MovingState movingState;
  protected Direction movingDirection;

  /**
   * Creates a new MovingObject1D
   */
  public MovingObject1D() {
    this(DEFAULT_X);
  }

  /**
   * Creates a new MovingObject1D
   *
   * @param x the x position
   */
  public MovingObject1D(double x) {
    this(x, DEFAULT_X_SPEED);
  }

  /**
   * Creates a new MovingObject1D
   *
   * @param x      the x position
   * @param xSpeed the x speed
   */
  public MovingObject1D(double x, double xSpeed) {
    this.x = x;
    this.xSpeed = xSpeed;
    movingState = MovingState.IDLE;
    movingDirection = Direction.E;
  }

  /**
   * Sets the x position of this object
   *
   * @param x the x position of this object
   */
  @Override
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Moves this object on the line for a certain distance. Note that this command uses the Cartesian
   * coordinate system. For example, move right for 5 should call moveInX(5)
   *
   * @param deltaX the distance in which the object moves in the x direction
   */
  @Override
  public void moveInX(double deltaX) {
    x += deltaX;
  }

  /**
   * Gets the x position of this object
   *
   * @return the x position of this object
   */
  @Override
  public double getX() {
    return x;
  }

  /**
   * Sets the speed this object moves in the x direction
   *
   * @param xSpeed the speed this object moves in the x direction
   */
  @Override
  public void setXSpeed(double xSpeed) {
    this.xSpeed = xSpeed;
  }

  /**
   * Gets the speed this object moves in the x direction
   *
   * @return the speed this object moves in the x direction
   */
  @Override
  public double getXSpeed() {
    return xSpeed;
  }

  /**
   * Gets if the frontend is busy animating the last movement. If yes, backend states will not be
   * changed
   *
   * @return if frontend is animating
   */
  @Override
  public boolean isBusy() {
    return false;
  }

  /**
   * Sets the current moving state
   *
   * @param movingState the current moving state
   */
  @Override
  public void setState(MovingState movingState) {
    MovingState oldState = this.movingState;
    this.movingState = movingState;

    notifyChange(ZeldaPlayerControl.PROPERTY_STATE, oldState, movingState);
  }

  protected abstract void notifyChange(String property, Object oldState,
      Object newState);

  /**
   * Gets the current moving state
   *
   * @return the current moving state
   */
  @Override
  public MovingState getState() {
    return movingState;
  }

  /**
   * Sets the direction of moving
   *
   * @param direction of moving
   */
  @Override
  public void setDirection(Direction direction) {
    Direction oldDir = movingDirection;
    this.movingDirection = direction;
    notifyChange(ZeldaPlayerControl.PROPERTY_MOVING_DIRECTION, oldDir, movingDirection);
  }

  /**
   * Gets the direction of moving
   *
   * @return the direction of moving
   */
  @Override
  public Direction getDirection() {
    return movingDirection;
  }


}
