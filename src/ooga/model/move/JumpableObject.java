package ooga.model.move;

import ooga.model.enums.backend.MovingState;
import ooga.model.interfaces.movables.Jumpable;

/**
 * An abstract class for all 2D moving object that can jump
 *
 * @author cady
 */
public abstract class  JumpableObject extends MovingObject1D implements Jumpable {

  public static final int DEFAULT_Z_SPEED = 5;
  protected double zSpeed;

  public JumpableObject() {
    this(DEFAULT_X, DEFAULT_Z_SPEED);
  }

  public JumpableObject(double x, double zSpeed) {
    this(x, DEFAULT_X_SPEED, zSpeed);
  }

  public JumpableObject(double x, double xSpeed, double zSpeed) {
    super(x, xSpeed);
    this.zSpeed = zSpeed;
    movingState = MovingState.IDLE;
  }


  @Override
  public void setZSpeed(double zSpeed) {
    this.zSpeed = zSpeed;
  }

  @Override
  public double getZSpeed(double zSpeed) {
    return zSpeed;
  }

  @Override
  public void jump() {
    setToJump();
    notifyAnimation();
  }

  // TODO
  private void notifyAnimation() {
  }

  @Override
  public void setToJump() {
    movingState = MovingState.JUMP;
  }

  @Override
  public void setNotToJump() {
    movingState = MovingState.IDLE;
  }
}
