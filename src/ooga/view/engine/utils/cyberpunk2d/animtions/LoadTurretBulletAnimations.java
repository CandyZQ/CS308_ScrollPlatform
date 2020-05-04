package ooga.view.engine.utils.cyberpunk2d.animtions;

import java.util.Map;
import ooga.view.engine.graphics.animation.Animation2D;
import com.google.gson.internal.LinkedHashTreeMap;

/**
 * a temporary class that loads the animation for turret bullet
 * @author qiaoyi fang
 */
public class LoadTurretBulletAnimations {
  final private static int fps = 18;

  final private static String DIR_PATH =  "/view/textures/2d/cyberpunk/nonplayable/Turret/Individual_Sprites/";

  final private static String DIR_W = "W";
  final private static String DIR_E = "E";

  final private static String MOVE = "BULLET";

  public static Map<String, Animation2D> loadAnimations() {
    String mapKey;
    LinkedHashTreeMap<String, Animation2D> bulletDict = new LinkedHashTreeMap<>();

    mapKey = "E_MOVE";
    bulletDict.put(mapKey, new Animation2D(8, fps, DIR_PATH, MOVE));

    return bulletDict;
  }

}
