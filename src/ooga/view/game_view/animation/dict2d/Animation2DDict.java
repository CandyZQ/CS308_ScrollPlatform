package ooga.view.game_view.animation.dict2d;

import java.util.Map;
import javafx.util.Pair;
import ooga.view.engine.graphics.animation.Animation2D;
import ooga.view.game_view.animation.interfaces.AnimationDict;

/**
 * a class that implements the dictionary for 2D animations so as to
 * 1) establish mapping between state string and animation
 * 2) direct the dependency of animations (such as combo effects and pre-sprint)
 * @author qiaoyi fang
 */
public class Animation2DDict extends AnimationDict {

  private Map<String, Animation2D> animationDict;
  private Map<Pair<Pair<String, Boolean>, String>, String> prevDict;

  public Animation2DDict(String initialDirection, String initialAction,
      Map<String, Animation2D> animationDict,
      Map<Pair<Pair<String, Boolean>, String>, String> prevDict) {
    super(initialDirection, initialAction);
    this.animationDict = animationDict;
    this.prevDict = prevDict;
  }

  /**
   * resets the frame pointers of the animations in the dictionary
   */
  @Override
  protected void resetAnimationDict(){
    for(String key: animationDict.keySet()){
      animationDict.get(key).resetAnimation();
    }
  }

  /**
   * gets the animation of the current direction and state
   * @return
   */
  public Animation2D getAnimation(){
    for (Pair<Pair<String, Boolean>, String> key: prevDict.keySet()){
      if (currentAction.equals(key.getValue()) && ((key.getKey().getValue() && previousAction.equals(key.getKey().getKey())) ||
          (!key.getKey().getValue() && !previousAction.equals(key.getKey().getKey())))){
        return getAnimationMap(direction, prevDict.get(key));
      }
    }

    return  getAnimationMap(direction, currentAction);
  }

  private Animation2D getAnimationMap(String direction, String action){
    return animationDict.get(String.format("%s_%s", direction, action));
  }

}
