package ooga.view.engine.utils.cyberpunk2d.animtions;

import java.util.Map;
import ooga.view.engine.graphics.animation.Animation2D;
import com.google.gson.internal.LinkedHashTreeMap;

/**
 * a temporary class that loads the animation for summoning
 * @author qiaoyi fang
 */
public class LoadSummonAnimations {
  final private static int fps = 18;

  final private static String DIR_PATH =  "/view/textures/2d/cyberpunk/nonplayable/Engineer/Individual_Sprites/";

  final private static String DIR_W = "W";
  final private static String DIR_E = "E";

  final private static String SUMMON_BIGBOY = "SUMMON_BIGBOY";
  final private static String SUMMON_WATCHER = "SUMMON_WATCHER";
  final private static String SUMMON_TURRET = "SUMMON_TURRET";

  public static Map<String, Animation2D> loadAnimations(){
    String mapKey;
    LinkedHashTreeMap<String, Animation2D> engineerDict = new LinkedHashTreeMap<>();

    mapKey = "E_SUMMON_BIGBOY";
    engineerDict.put(mapKey, new Animation2D(14, fps, DIR_PATH, SUMMON_BIGBOY));
    mapKey = "E_SUMMON_WATCHER";
    engineerDict.put(mapKey, new Animation2D(14, fps, DIR_PATH, SUMMON_WATCHER));
    mapKey = "E_SUMMON_TURRET";
    engineerDict.put(mapKey, new Animation2D(14, fps, DIR_PATH, SUMMON_TURRET));

    return engineerDict;
  }
}
