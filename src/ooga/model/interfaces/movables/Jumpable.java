package ooga.model.interfaces.movables;

/**
 * This interface holds object that jumps following physics rules. This interface should always
 * be implemented together with {@link Movable1D} or {@link Movable2D} to make a reasonable
 * physics model.
 *
 * @author cady
 * @see Movable1D
 * @see Movable2D
 */
public interface  Jumpable{

  /**
   * Sets the speed this object moves in the z direction
   *
   * @param zSpeed the speed this object moves in the z direction
   */
  void setZSpeed(double zSpeed);

  /**
   * Gets the speed this object moves in the z direction
   *
   * @return the speed this object moves in the z direction
   */
  double getZSpeed(double zSpeed);

  /**
   * Jumps and calculates the position of this object after jumping.
   */
  void jump();

  /**
   * Sets the state of this object to JUMP (Busy)
   */
  void setToJump();

  /**
   * Sets the state of this object not to jump so that other actions can be performed
   */
  void setNotToJump();
}
