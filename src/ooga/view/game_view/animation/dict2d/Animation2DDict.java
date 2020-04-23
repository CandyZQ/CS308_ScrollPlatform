package ooga.view.game_view.animation.dict2d;

import java.io.IOException;
import java.util.Map;
import ooga.view.engine.graphics.animation.Animation2D;
import ooga.view.game_view.animation.interfaces.AnimationDict;

public class Animation2DDict extends AnimationDict {

  private Map<String, Animation2D> dict;

  public Animation2DDict(String initialDirection, String initialAction, Map<String, Animation2D> dict) throws IOException {
    super(initialDirection, initialAction);
    //dict = LoadCyberpunkAnimations.loadMeleeRobotAnimations();
    this.dict = dict;
  }

  @Override
  protected void resetAnimationDict(){
    for(String key:dict.keySet()){
      dict.get(key).resetAnimation();
    }
  }

  public Animation2D getAnimation(){

    //TODO: should remove from hardcoded!
    if (!previousAction.equals("SPRINT") && currentAction.equals("SPRINT")){
      return getAnimationMap(direction, "PRESPRINT");
    }

    if (!previousAction.equals("ATTACK1") && currentAction.equals("ATTACK3")){

      return getAnimationMap(direction, "ATTACKCOMBO");
    }


    return getAnimationMap(direction, currentAction);
  }

  private Animation2D getAnimationMap(String direction, String action){
    return dict.get(String.format("%s_%s", direction, action));
  }

}
