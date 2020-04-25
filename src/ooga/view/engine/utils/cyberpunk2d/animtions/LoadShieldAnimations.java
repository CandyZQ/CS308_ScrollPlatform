package ooga.view.engine.utils.cyberpunk2d.animtions;

import java.util.Map;
import ooga.view.engine.graphics.animation.Animation2D;
import com.google.gson.internal.LinkedHashTreeMap;

public class LoadShieldAnimations {

  final private static int fps = 18;

  final private static String DIR_PATH =  "/view/textures/2d/cyberpunk/nonplayable/Shieldman/Individual_Sprites/";

  final private static String DIR_W = "W";
  final private static String DIR_E = "E";

  final private static String ATTACK = "ATTACK";
  final private static String DEATH = "DEATH";
  final private static String HURT = "HURT";
  final private static String FALL = "FALL";
  final private static String JUMPING = "JUMPING";
  final private static String SHIELD_HURT = "SHIELD_HURT";
  final private static String IDLE = "IDLE";
  final private static String WALK = "WALK";

  public static Map<String, Animation2D> loadAnimations() {
    String mapKey;
    LinkedHashTreeMap<String, Animation2D> shieldDict = new LinkedHashTreeMap<>();

    //load E animations
    mapKey = "E_ATTACK";
    shieldDict.put(mapKey, new Animation2D(7, fps, DIR_PATH, ATTACK));
    mapKey = "E_DEATH";
    shieldDict.put(mapKey, new Animation2D(10, fps, DIR_PATH, DEATH));
    mapKey = "E_FALL";
    shieldDict.put(mapKey, new Animation2D(5, fps, DIR_PATH, FALL));
    mapKey = "E_HURT";
    shieldDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, HURT));
    mapKey = "E_IDLE";
    shieldDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, IDLE));
    mapKey = "E_JUMP";
    shieldDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, JUMPING));
    mapKey = "E_SHIELD_HURT";
    shieldDict.put(mapKey, new Animation2D(6, fps, DIR_PATH, SHIELD_HURT));
    mapKey = "E_WALK";
    shieldDict.put(mapKey, new Animation2D(10, fps, DIR_PATH, WALK));

    //TODO W animations

    return shieldDict;
  }
}
