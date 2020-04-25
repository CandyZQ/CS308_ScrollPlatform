package ooga.model.move;

import ooga.model.interfaces.movables.Movable2D;

/**
 * An abstract class for all moving objects on a 2D surface
 */
public abstract class MovingObject2D extends MovingObject1D implements Movable2D {

  public static final int DEFAULT_Y_SPEED = 5;
  public static final int DEFAULT_Y = 0;

  protected double y;
  protected double ySpeed;
  protected double diagonalSpeed;

  protected boolean canMoveDiagonally;

  /**
   * Creates a new MovingObject2D
   */
  public MovingObject2D() {
    this(DEFAULT_X, DEFAULT_Y);
  }

  /**
   * Creates a new MovingObject2D
   *
   * @param x the x position
   * @param y the y position
   */
  public MovingObject2D(double x, double y) {
    this(x, y, DEFAULT_X_SPEED, DEFAULT_Y_SPEED);
  }

  /**
   * Creates a new MovingObject2D
   *
   * @param x      the x position
   * @param y      the y position
   * @param xSpeed the x speed
   * @param ySpeed the y speed
   */
  public MovingObject2D(double x, double y, double xSpeed, double ySpeed) {
    super(x, xSpeed);
    this.y = y;
    this.ySpeed = ySpeed;
    canMoveDiagonally = false;
    diagonalSpeed = 0;
  }

  /**
   * Sets the y position of this object
   *
   * @param y the y position of this object
   */
  @Override
  public void setY(double y) {
    this.y = y;
  }

  /**
   * Gets the y position of this object
   *
   * @return the y position of this object
   */
  @Override
  public double getY() {
    return y;
  }

  /**
   * Moves this object for a certain distance in the y direction. Note that this command uses the
   * Cartesian coordinate system. For example, move up for 5 should call moveInY(5)
   *
   * @param deltaY the y distance moved
   */
  @Override
  public void moveInY(double deltaY) {
    y += deltaY;
  }

  /**
   * Sets the speed this object moves in the y direction
   *
   * @param ySpeed the speed this object moves in the y direction
   */
  @Override
  public void setYSpeed(double ySpeed) {
    this.ySpeed = ySpeed;
  }

  /**
   * Gets the speed this object moves in the x direction
   *
   * @return the speed this object moves in the x direction
   */
  @Override
  public double getYSpeed() {
    return ySpeed;
  }

  /**
   * Sets the speed this object moves in both the x and y direction
   *
   * @param xSpeed the speed this object moves in the x direction
   * @param ySpeed the speed this object moves in the y direction
   */
  @Override
  public void setSpeed(double xSpeed, double ySpeed) {
    this.xSpeed = xSpeed;
    this.ySpeed = ySpeed;
  }

  /**
   * Sets the x and y position of this object
   *
   * @param x the x position of this object
   * @param y the y position of this object
   */
  @Override
  public void set2DPos(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * allows the object to move diagonally
   */
  @Override
  public void enableDiagonalMove() {
    canMoveDiagonally = true;
  }

  /**
   * allow the object to move only in NSWE directions
   */
  @Override
  public void disableDiagonalMove() {
    canMoveDiagonally = false;
  }

  /**
   * Sets the speed that this object moves diagonally
   *
   * @param dSpeed the diagonal speed of this object
   */
  @Override
  public void setsDiagonalSpeed(double dSpeed) {
    diagonalSpeed = dSpeed;
  }

  /**
   * Gets the speed that this object moves diagonally
   *
   * @return the diagonal speed of this object
   */
  @Override
  public double getDiagonalSpeed() {
    return diagonalSpeed;
  }

  /**
   * Gets if this object moves diagonally
   *
   * @return if this object moves diagonally
   */
  @Override
  public boolean isDiagonalMove() {
    return canMoveDiagonally;
  }
}
