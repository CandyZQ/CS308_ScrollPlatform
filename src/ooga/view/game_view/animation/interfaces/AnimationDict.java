package ooga.view.game_view.animation.interfaces;

/**
 * an abstract class that implements animation dictionary which directs the dependency between animation states
 * @author qiaoyi fang
 */
abstract public class AnimationDict {
  protected String direction;
  protected String initialAction;
  protected String initialDirection;
  protected String previousAction;
  protected String currentAction;

  /**
   * constructor
   * @param initialDirection initial direction
   * @param initialAction initial action
   */
  public AnimationDict(String initialDirection, String initialAction){
    this.initialAction = initialAction;
    this.initialDirection = initialDirection;
    this.previousAction = initialAction;
    this.currentAction = initialAction;
    this.direction = initialDirection;
  }

  /**
   * sets the currently used animation
   * @param direction the new direction
   * @param action the new action
   */
  public void setInUseAnimation (String direction, String action){
    this.direction = direction;
    this.previousAction = this.currentAction;
    this.currentAction = action;
    resetAnimationDict();
  }

  /**
   * gets the current direction
   * @return direction
   */
  public String getDirection() {
    return direction;
  }

  /**
   * resets the frame pointers of the animations in the dictionary
   */
  abstract protected void resetAnimationDict();

}
