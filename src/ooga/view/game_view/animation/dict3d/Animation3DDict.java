package ooga.view.game_view.animation.dict3d;

import java.util.Map;
import java.util.Map.Entry;
import ooga.view.engine.graphics.animation.Animation3D;
import ooga.view.engine.utils.cyberpunk3d.LoadCyberpunkModels;
import ooga.view.game_view.animation.interfaces.AnimationDict;

/**
 * a class that implements the dictionary for 3D animations so as to
 * establish mapping between state string and animation
 * @author qiaoyi fang
 */
public class Animation3DDict extends AnimationDict {

  private Map<String, Animation3D> dict;

  public Animation3DDict(String initialDirection, String initialAction){
    super(initialDirection, initialAction);
    dict = LoadCyberpunkModels.loadWhiteBotAnimationDict();
  }

  /**
   * resets the frame pointers of the animations in the dictionary
   */
  @Override
  protected void resetAnimationDict() {
    for(Entry<String, Animation3D> entry:dict.entrySet()){
      entry.getValue().resetAnimation();
    }
  }

  /**
   * gets the current direction
   * @return direction
   */
  public Animation3D getAnimation(){
    return dict.get(currentAction);
  }
}
