package ooga.view.engine.utils.cyberpunk3d;

import java.util.HashMap;
import java.util.Map;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.animation.Animation3D;
import ooga.view.engine.io.ModelLoader;

public class LoadCyberpunkModels {

  private static String DIR_MAP_PATH = "resources/view/models/3d/map/EnvironmentPack/Models/";
  private static String PATH_MAP_4E_MODEL = "SciFiCorridors4Exits.obj";
  private static String PATH_MAP_E_MODEL = "SciFiCorridorsEnd.obj";
  private static String PATH_MAP_L_MODEL = "SciFiCorridorsL.obj";
  private static String PATH_MAP_I_MODEL = "SciFiCorridorsNormal.obj";
  private static String PATH_MAP_T_MODEL = "SciFiCorridorsT.obj";
  private static String MAP_TEXTURE_PATH = "/view/models/3d/map/EnvironmentPack/Models/plain-white.png";

  private static String DIR_WHITE_BOT_WALK_PATH = "resources/view/models/3d/npc/whitebot/walk/";
  private static String DIR_WHITE_BOT_ATTACK_PATH = "resources/view/models/3d/npc/whitebot/attack/";
  private static String DIR_WHITE_BOT_DEATH_PATH = "resources/view/models/3d/npc/whitebot/death/";
  private static String WHITE_BOT_TEXTURE = "resources/view/models/3d/npc/whitebot/whitebot_texture.png";

  private static int WHITE_BOT_WALK_ST_ID = 1;
  private static int WHITE_BOT_WALK_AMOUNT = 100;

  private static int WHITE_BOT_ATTACK_ST_ID = 101;
  private static int WHITE_BOT_ATTACK_AMOUNT = 80;

  private static int WHITE_BOT_DEATH_ST_ID = 181;
  private static int WHITE_BOT_DEATH_AMOUNT = 180;

  private static Map<String, Mesh> tileDict;
  private static Map<String, Animation3D> whiteBotAnimationDict;

  public static void loadTileDict() {

    tileDict = new HashMap<>();

    tileDict.put("4E", ModelLoader
        .loadModel(String.format("%s%s", DIR_MAP_PATH, PATH_MAP_4E_MODEL), MAP_TEXTURE_PATH));
    tileDict.put("E", ModelLoader
        .loadModel(String.format("%s%s", DIR_MAP_PATH, PATH_MAP_E_MODEL), MAP_TEXTURE_PATH));
    tileDict.put("L", ModelLoader
        .loadModel(String.format("%s%s", DIR_MAP_PATH, PATH_MAP_L_MODEL), MAP_TEXTURE_PATH));
    tileDict.put("I", ModelLoader
        .loadModel(String.format("%s%s", DIR_MAP_PATH, PATH_MAP_I_MODEL), MAP_TEXTURE_PATH));
    tileDict.put("T", ModelLoader
        .loadModel(String.format("%s%s", DIR_MAP_PATH, PATH_MAP_T_MODEL), MAP_TEXTURE_PATH));

  }

  public static Mesh getTileMesh(String type){return tileDict.get(type);}

  public static void createAllTileMeshes(){
    for (String key:tileDict.keySet()){
      tileDict.get(key).create();
    }
  }

  public static void destroyAllTileMeshes(){
    for (String key:tileDict.keySet()){
      tileDict.get(key).destroy();
    }
  }


  public static void loadWhiteBotAnimationDict() {

    whiteBotAnimationDict = new HashMap<>();

    Animation3D walk = new Animation3D(WHITE_BOT_WALK_AMOUNT, 23);
    Animation3D attack = new Animation3D(WHITE_BOT_ATTACK_AMOUNT, 23);
    Animation3D death = new Animation3D(WHITE_BOT_DEATH_AMOUNT, 23);

    for (int i = 0; i < WHITE_BOT_WALK_AMOUNT; i++) {
      walk.setAnimatedFrames(i,
          ModelLoader.loadModel(getWhiteBotModelPath("WALK", i), WHITE_BOT_TEXTURE));
    }

    for (int i = 0; i < WHITE_BOT_ATTACK_AMOUNT; i++) {
      attack.setAnimatedFrames(i,
          ModelLoader.loadModel(getWhiteBotModelPath("ATTACK", i), WHITE_BOT_TEXTURE));
    }

    for (int i = 0; i < WHITE_BOT_DEATH_AMOUNT; i++) {
      death.setAnimatedFrames(i,
          ModelLoader.loadModel(getWhiteBotModelPath("DEATH", i), WHITE_BOT_TEXTURE));
    }

    whiteBotAnimationDict.put("ATTACK", attack);
    whiteBotAnimationDict.put("WALK", walk);
    whiteBotAnimationDict.put("DEATH", death);
  }

  public static Animation3D getWhiteBotAnimation(String key){
    return whiteBotAnimationDict.get(key);
  }

  public static String getWhiteBotModelPath(String action, int id) {
    if (action.equals("ATTACK")) {
      return String.format("%sMOVE_%s.obj", DIR_WHITE_BOT_ATTACK_PATH,
          getWhiteBotStrID(id + WHITE_BOT_ATTACK_ST_ID));
    } else if (action.equals("WALK")) {
      return String.format("%sMOVE_%s.obj", DIR_WHITE_BOT_WALK_PATH,
          getWhiteBotStrID(id + WHITE_BOT_WALK_ST_ID));
    } else if (action.equals("DEATH")) {
      return String.format("%sMOVE_%s.obj", DIR_WHITE_BOT_DEATH_PATH,
          getWhiteBotStrID(id + WHITE_BOT_DEATH_ST_ID));
    } else {
      System.out.println("Unknown animation mode for white bot");
      return null;
    }

  }

  private static String getWhiteBotStrID(int id) {
    if (id < 10) {
      return String.format("00000%s", id);
    } else if (id < 100) {
      return String.format("0000%s", id);
    } else {
      return String.format("000%s", id);
    }
  }


}
