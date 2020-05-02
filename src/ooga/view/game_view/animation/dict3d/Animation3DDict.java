package ooga.view.game_view.animation.dict3d;

import java.util.Map;
import java.util.Map.Entry;
import ooga.view.engine.graphics.animation.Animation3D;
import ooga.view.engine.utils.cyberpunk3d.LoadCyberpunkModels;
import ooga.view.game_view.animation.interfaces.AnimationDict;

public class Animation3DDict extends AnimationDict {

  private Map<String, Animation3D> dict;

  public Animation3DDict(String initialDirection, String initialAction){
    super(initialDirection, initialAction);
    dict = LoadCyberpunkModels.loadWhiteBotAnimationDict();
  }

  @Override
  protected void resetAnimationDict() {
    for(Entry<String, Animation3D> entry:dict.entrySet()){
      entry.getValue().resetAnimation();
    }
  }

  public Animation3D getAnimation(){
    return dict.get(currentAction);
  }
}
