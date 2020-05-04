package ooga.view.engine.utils.cyberpunk2d.animtions;

import java.util.Map;
import ooga.view.engine.graphics.animation.Animation2D;
import com.google.gson.internal.LinkedHashTreeMap;

/**
 * a temporary class that loads the animation for the turret
 * @author qiaoyi fang
 */
public class LoadTurretAnimations {
  final private static int fps = 18;

  final private static String DIR_PATH =  "/view/textures/2d/cyberpunk/nonplayable/Turret/Individual_Sprites/";

  final private static String DIR_W = "W";
  final private static String DIR_E = "E";

  final private static String ATTACK_1_SIDE = "ATTACK_1_SIDE";
  final private static String ATTACK_2_SIDE = "ATTACK_2_SIDE";
  final private static String ATTACK_BLAST = "ATTACK_BLASTS";
  final private static String HURT = "HURT";
  final private static String IDLE = "IDLE";

  public static Map<String, Animation2D> loadAnimations() {
    String mapKey;
    LinkedHashTreeMap<String, Animation2D> turretDict = new LinkedHashTreeMap<>();

    mapKey = "E_ATTACK_1_SIDE";
    turretDict.put(mapKey, new Animation2D(8, fps, DIR_PATH, ATTACK_1_SIDE));
    mapKey = "E_ATTACK_2_SIDE";
    turretDict.put(mapKey, new Animation2D(15, fps, DIR_PATH, ATTACK_2_SIDE));
    mapKey = "E_ATTACK_BLAST";
    turretDict.put(mapKey, new Animation2D(4, fps, DIR_PATH, ATTACK_BLAST));
    mapKey = "E_HURT";
    turretDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, HURT));
    mapKey = "E_IDLE";
    turretDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, IDLE));

    return turretDict;

  }
}
