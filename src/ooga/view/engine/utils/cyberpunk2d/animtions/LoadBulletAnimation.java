package ooga.view.engine.utils.cyberpunk2d.animtions;

import java.util.Map;
import ooga.view.engine.graphics.animation.Animation2D;
import com.google.gson.internal.LinkedHashTreeMap;

public class LoadBulletAnimation {

  final private static int fps = 18;

  final private static String DIR_PATH = "/view/textures/2d/cyberpunk/nonplayable/Watcher/Individual_Sprites/";
  final private static String DIR_W = "W";
  final private static String DIR_E = "E";

  final private static String BULLET = "BULLET";

  public static Map<String, Animation2D> loadAnimations() {
    String mapKey;
    LinkedHashTreeMap<String, Animation2D> bulletDict = new LinkedHashTreeMap<>();

    //load E animations
    mapKey = "E_MOVE";
    bulletDict.put(mapKey, new Animation2D(2, fps, DIR_PATH, BULLET));

    //TODO W ANIMATIONS

    return bulletDict;
  }
}
