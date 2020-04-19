package ooga.view.game_view.animation.interfaces;

abstract public class AnimationDict {
  protected String direction;
  protected String previousAction;
  protected String currentAction;

  public AnimationDict(String initialDirection, String initialAction){
    this.previousAction = initialAction;
    this.currentAction = initialAction;
    this.direction = initialDirection;
  }

  public void setInUseAnimation (String direction, String action){
    this.direction = direction;
    this.previousAction = this.currentAction;
    this.currentAction = action;
    resetAnimationDict();
  }

  abstract protected void resetAnimationDict();

}