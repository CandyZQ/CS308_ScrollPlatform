package ooga.view.engine.utils.cyberpunk2d.animtions;

import java.util.Map;
import ooga.view.engine.graphics.animation.Animation2D;
import com.google.gson.internal.LinkedHashTreeMap;

/**
 * a temporary class that loads the animation for engineering bot
 * @author qiaoyi fang
 */
public class LoadEngineerBotAnimations {
  final private static int fps = 18;

  final private static String DIR_PATH =  "/view/textures/2d/cyberpunk/nonplayable/Engineer/Individual_Sprites/";

  final private static String DIR_W = "W";
  final private static String DIR_E = "E";

  final private static String ATTACK = "ATTACK";
  final private static String DEATH = "DEATH";
  final private static String HURT = "HURT";
  final private static String WALK = "WALK";
  final private static String IDLE = "IDLE";
  final private static String JUMP = "JUMP";
  final private static String FALL = "FALL";

  final private static String SUMMON = "SUMMON"; // "SUMMON_BIGBOY", "SUMMON_TURRET", "SUMMON_WATCHER"


  public static Map<String, Animation2D> loadAnimations(){
    String mapKey;
    LinkedHashTreeMap<String, Animation2D> engineerDict = new LinkedHashTreeMap<>();

    //load E animations
    mapKey = "E_ATTACK";
    engineerDict.put(mapKey, new Animation2D(12, fps, DIR_PATH, ATTACK));
    mapKey = "E_DEATH";
    engineerDict.put(mapKey, new Animation2D(10, fps, DIR_PATH, DEATH));
    mapKey = "E_HURT";
    engineerDict.put(mapKey, new Animation2D(4, fps, DIR_PATH, HURT));
    mapKey = "E_IDLE";
    engineerDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, IDLE));
    mapKey = "E_WALK";
    engineerDict.put(mapKey, new Animation2D(10, fps, DIR_PATH, WALK));
    mapKey = "E_JUMP";
    engineerDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, JUMP));
    mapKey = "E_FALL";
    engineerDict.put(mapKey, new Animation2D(4, fps, DIR_PATH, FALL));
    mapKey = "E_SUMMON_BIGBOY";
    engineerDict.put(mapKey, new Animation2D(13, fps, DIR_PATH, SUMMON));
    mapKey = "E_SUMMON_TURRET";
    engineerDict.put(mapKey, new Animation2D(13, fps, DIR_PATH, SUMMON));
    mapKey = "E_SUMMON_WATCHER";
    engineerDict.put(mapKey, new Animation2D(13, fps, DIR_PATH, SUMMON));

    return engineerDict;
  }

}
