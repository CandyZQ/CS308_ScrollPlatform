package ooga.view.engine.utils.cyberpunk2d.animtions;

import java.util.Map;
import ooga.view.engine.graphics.animation.Animation2D;
import com.google.gson.internal.LinkedHashTreeMap;

/**
 * a temporary class that loads the animation for watcher bot
 * @author qiaoyi fang
 */
public class LoadWatcherBotAnimations {
  final private static int fps = 18;

  final private static String DIR_PATH =  "/view/textures/2d/cyberpunk/nonplayable/Watcher/Individual_Sprites/";

  final private static String DIR_W = "W";
  final private static String DIR_E = "E";

  final private static String ATTACK = "ATTACK";
  final private static String DEATH = "DEATH";
  final private static String HURT = "HURT";
  final private static String WARNING_WALK = "WARNING_WALK";
  final private static String WARNING_IDLE = "WARNING_IDLE";
  final private static String WARNING = "WARNING";
  final private static String IDLE = "IDLE";
  final private static String WALK = "WALK";

  public static Map<String, Animation2D> loadAnimations() {
    String mapKey;
    LinkedHashTreeMap<String, Animation2D> watcherDict = new LinkedHashTreeMap<>();

    //load E animations
    mapKey = "E_ATTACK";
    watcherDict.put(mapKey, new Animation2D(17, fps, DIR_PATH, ATTACK));
    mapKey = "E_DEATH";
    watcherDict.put(mapKey, new Animation2D(13, fps, DIR_PATH, DEATH));
    mapKey = "E_HURT";
    watcherDict.put(mapKey, new Animation2D(8, fps, DIR_PATH, HURT));
    mapKey = "E_IDLE";
    watcherDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, IDLE));
    mapKey = "E_WALK";
    watcherDict.put(mapKey, new Animation2D(9, fps, DIR_PATH, WALK));
    mapKey = "E_WARNING_IDLE";
    watcherDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, WARNING_IDLE));
    mapKey = "E_WARNING_WALK";
    watcherDict.put(mapKey, new Animation2D(9, fps, DIR_PATH, WARNING_WALK));
    mapKey = "E_WARNING";
    watcherDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, WARNING));

    //TODO W animations
    return watcherDict;
  }
}
