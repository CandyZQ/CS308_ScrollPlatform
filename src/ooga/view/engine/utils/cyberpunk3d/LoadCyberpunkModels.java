package ooga.view.engine.utils.cyberpunk3d;

import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.animation.Animation3D;
import ooga.view.engine.graphics.assets.Asset3D;
import ooga.view.engine.io.ModelLoader;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.Test;

public class LoadCyberpunkModels {

  private static String DIR_MAP_PATH = "resources/view/models/3d/map/EnvironmentPack/Models/";
  private static String PATH_MAP_4E_MODEL = "SciFiCorridors4Exits.obj";
  private static String PATH_MAP_E_MODEL = "SciFiCorridorsEnd.obj";
  private static String PATH_MAP_L_MODEL = "SciFiCorridorsL.obj";
  private static String PATH_MAP_I_MODEL = "SciFiCorridorsNormal.obj";
  private static String PATH_MAP_T_MODEL = "SciFiCorridorsT.obj";
  private static String MAP_TEXTURE_PATH = "/view/models/3d/map/EnvironmentPack/Models/15110961-laser-light-show-gone-wild-shows-lights-lines-and-neon-lights-rainbow-colors-streak-and-swirl-across.jpg";

  private static String DIR_WHITE_BOT_WALK_PATH = "resources/view/models/3d/npc/whitebot/walk/";
  private static String DIR_WHITE_BOT_ATTACK_PATH = "resources/view/models/3d/npc/whitebot/attack/";
  private static String DIR_WHITE_BOT_DEATH_PATH = "resources/view/models/3d/npc/whitebot/death/";
  private static String DIR_WHITE_BOT_IDLE_PATH = "resources/view/models/3d/npc/whitebot/idle/";
  private static String WHITE_BOT_TEXTURE = "/view/models/3d/npc/whitebot/whitebot_texture.png";

  private static int WHITE_BOT_WALK_ST_ID = 1;
  private static int WHITE_BOT_WALK_AMOUNT = 100;

  private static int WHITE_BOT_ATTACK_ST_ID = 101;
  private static int WHITE_BOT_ATTACK_AMOUNT = 80;

  private static int WHITE_BOT_DEATH_ST_ID = 181;
  private static int WHITE_BOT_DEATH_AMOUNT = 180;

  private static int WHITE_BOT_IDLE_ST_ID = 361;
  private static int WHITE_BOT_IDLE_AMOUNT = 1;

  private static String[] tileTypes = {"4E", "E", "L", "I", "T"};

  private static Map<String, Mesh> tileDict;
  private static Map<String, Mesh> tileRotationalDict;
  private static Map<String, Boolean> tileRotationalUsedDict;
  private static Map<String, Animation3D> whiteBotAnimationDict;

  public static boolean isTileDictLoaded(){
    return tileDict != null;
  }
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

    loadTileRotDict();
  }

  private static void loadTileRotDict() {
    tileRotationalDict = new HashMap<>();
    tileRotationalUsedDict = new HashMap<>();

    for (String type : tileTypes) {
      for (int rotX = 0; rotX < 4; rotX++) {
        for (int rotY = 0; rotY < 4; rotY++) {
          for (int rotZ = 0; rotZ < 4; rotZ++) {
            Vector3f rot = Asset3D.getRotationVector(rotX, rotY, rotZ);
            Pair<String, Vector3f> key = new Pair<>(type, rot);
            Mesh mesh = new Mesh(tileDict.get(type), rot);
            tileRotationalDict.put(convertPairKey2String(key), mesh);
            tileRotationalUsedDict.put(convertPairKey2String(key), false);
          }
        }
      }
    }
  }

  public static Mesh getTileMesh(String type) {
    return tileDict.get(type);
  }

  public static Mesh getRotationalTileMesh(String type, int rotX, int rotY, int rotZ) {
    return getRotationalTileMesh(type, Asset3D.getRotationVector(rotX, rotY, rotZ));
  }

  public static Mesh getRotationalTileMesh(String type, Vector3f rotation) {
    tileRotationalUsedDict.replace(convertPairKey2String(new Pair<>(type, rotation)), true);
    Mesh mesh = tileRotationalDict.get(convertPairKey2String(new Pair<>(type, rotation)));
    return mesh;
  }

  public static void createUsedRotationalTileMeshes() {
    for (String key : tileRotationalDict.keySet()) {
      if (tileRotationalUsedDict.get(key)) {
        Mesh mesh = tileRotationalDict.get(key);
        mesh.create();
      }
    }
  }

  public static void destroyUsedRotationalTileMeshes() {
    for (String key : tileRotationalDict.keySet()) {
      if (tileRotationalUsedDict.get(key)) {
        tileRotationalDict.get(key).destroy();
      }
    }
  }

  public static void createAllRotationalTileMeshes() {
    for (String key : tileRotationalDict.keySet()) {
      tileRotationalDict.get(key).create();
    }
  }

  public static void createAllTileMeshes() {
    for (String key : tileDict.keySet()) {
      tileDict.get(key).create();
    }
  }

  public static void destroyAllTileMeshes() {
    for (String key : tileRotationalDict.keySet()) {
      tileRotationalDict.get(key).destroy();
    }
  }

  public static Map<String, Animation3D> loadWhiteBotAnimationDict() {

    whiteBotAnimationDict = new HashMap<>();

    Animation3D walk = new Animation3D(WHITE_BOT_WALK_AMOUNT, 23);
    Animation3D attack = new Animation3D(WHITE_BOT_ATTACK_AMOUNT, 23);
    Animation3D death = new Animation3D(WHITE_BOT_DEATH_AMOUNT, 23);
    Animation3D idle = new Animation3D(WHITE_BOT_IDLE_AMOUNT, 23);

    for (int i = 0; i < WHITE_BOT_WALK_AMOUNT; i++) {
      walk.setAnimatedFrame(i,
          ModelLoader.loadModel(getWhiteBotModelPath("WALK", i), WHITE_BOT_TEXTURE));
    }

    for (int i = 0; i < WHITE_BOT_ATTACK_AMOUNT; i++) {
      attack.setAnimatedFrame(i,
          ModelLoader.loadModel(getWhiteBotModelPath("ATTACK", i), WHITE_BOT_TEXTURE));
    }

    for (int i = 0; i < WHITE_BOT_DEATH_AMOUNT; i++) {
      death.setAnimatedFrame(i,
          ModelLoader.loadModel(getWhiteBotModelPath("DEATH", i), WHITE_BOT_TEXTURE));
    }

    for (int i=0; i < WHITE_BOT_IDLE_AMOUNT; i++){
      idle.setAnimatedFrame(i,
          ModelLoader.loadModel(getWhiteBotModelPath("IDLE", i), WHITE_BOT_TEXTURE));
    }

    whiteBotAnimationDict.put("ATTACK", attack);
    whiteBotAnimationDict.put("WALK", walk);
    whiteBotAnimationDict.put("DEATH", death);
    whiteBotAnimationDict.put("IDLE", idle);

    return whiteBotAnimationDict;
  }

  public static Animation3D getWhiteBotAnimation(String key) {
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
    }
    else if (action.equals("IDLE")){
      return String.format("%sMOVE_%s.obj", DIR_WHITE_BOT_IDLE_PATH,
          getWhiteBotStrID(id+WHITE_BOT_IDLE_ST_ID));
    }else {
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

  public static void printL180(){
    Pair key = new Pair("L", new Vector3f(180.0f, 0f,0f));
    Test.printRotationalTileDict(key);
    if (tileRotationalDict.containsKey(convertPairKey2String(key)))
      Test.printThreeMeshVertices(tileRotationalDict.get(convertPairKey2String(key)));
    else{
      System.out.println("no");
    }
  }

  public static void printRotTableEntry(Pair key){
    Test.printRotationalTileDict(key);
    if (tileRotationalDict.containsKey(convertPairKey2String(key)))
      Test.printThreeMeshVertices(tileRotationalDict.get(convertPairKey2String(key)));
    else{
      System.out.println("no");
    }
  }


  public static void printRotationalTileDict() {
    String type = "L";
      for (int rotX = 0; rotX < 4; rotX++) {
        for (int rotY = 0; rotY < 4; rotY++) {
          for (int rotZ = 0; rotZ < 4; rotZ++) {
            Vector3f rot = Asset3D.getRotationVector(rotX, rotY, rotZ);
            Pair key = new Pair(type, rot);
            Test.printRotationalTileDict(key);
            if (tileRotationalDict.containsKey(convertPairKey2String(key)))
              Test.printThreeMeshVertices(tileRotationalDict.get(convertPairKey2String(key)));
            else{
              System.out.println("no");
            }
          }
        }
      }
  }

  private static String convertPairKey2String(Pair<String, Vector3f> key){
    return String.format("%s_%s_%s_%s", key.getKey(), key.getValue().getX(), key.getValue().getY(), key.getValue().getZ());
  }


}
