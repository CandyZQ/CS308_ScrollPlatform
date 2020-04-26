package ooga.view.game_view.animation.dict2d;

import java.util.Map;
import javafx.util.Pair;
import ooga.view.engine.graphics.animation.Animation2D;
import ooga.view.game_view.animation.interfaces.AnimationDict;

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

  public Animation2DDict(Animation2DDict other){
    super(other.initialDirection, other.initialAction);
    this.animationDict = Map.copyOf(other.animationDict);
    this.prevDict = Map.copyOf(other.prevDict);
  }

  @Override
  protected void resetAnimationDict(){
    for(String key: animationDict.keySet()){
      animationDict.get(key).resetAnimation();
    }
  }


  public Animation2D getAnimation(){
    System.out.println("in anitionmation dict"+direction);
    for (Pair<Pair<String, Boolean>, String> key: prevDict.keySet()){
     // revDict.put(new Pair<>(new Pair<>("SPRINT",false), "SPRINT"), "PRESPRINT")
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
