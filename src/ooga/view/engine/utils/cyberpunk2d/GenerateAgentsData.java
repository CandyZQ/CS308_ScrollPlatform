package ooga.view.engine.utils.cyberpunk2d;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.cyberpunk2d.animtions.LoadBigBoyAnimations;
import ooga.view.engine.utils.cyberpunk2d.animtions.LoadBulletAnimation;
import ooga.view.engine.utils.cyberpunk2d.animtions.LoadEngineerBotAnimations;
import ooga.view.engine.utils.cyberpunk2d.animtions.LoadMeleeBotAnimations;
import ooga.view.engine.utils.cyberpunk2d.animtions.LoadSoldierAnimations;
import ooga.view.engine.utils.cyberpunk2d.animtions.LoadSummonAnimations;
import ooga.view.engine.utils.cyberpunk2d.animtions.LoadTurretAnimations;
import ooga.view.engine.utils.cyberpunk2d.animtions.LoadTurretBulletAnimations;
import ooga.view.engine.utils.cyberpunk2d.animtions.LoadWatcherBotAnimations;
import ooga.view.game_view.agent.agent2d.Agent2DDataHolder;
import ooga.view.game_view.animation.dict2d.Animation2DDict;

public class GenerateAgentsData {

  final static private float Z_POSITION = 0f;
  final static private float X_PLACEHOLDER = -550f;
  final static private float Y_PLACEHOLDER = -550f;
  final static private String DIR_PLACEHOLDER = "P";

  public static float getXPlaceholder(){return X_PLACEHOLDER;}

  public static float getYPlaceholder(){return Y_PLACEHOLDER;}

  public static String getDirectionPlaceholder(){return DIR_PLACEHOLDER;}

  public static Agent2DDataHolder createBigBoy(float x, float y, String initialDirection) throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    // create initial params
    data.setInitialAction("IDLE");
    data.setInitialDirection(initialDirection);
    data.setDefaultAction("IDLE");
    data.setMoveAction("WALK");

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(), data.getInitialAction(),
        LoadBigBoyAnimations.loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    Vector3f position = new Vector3f(x,y,Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;
  }

  public static Agent2DDataHolder createBullet(float x, float y, String initialDirection)
      throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    data.setSpeedScale(5.0f);

    // create initial params
    data.setInitialAction("MOVE");
    data.setInitialDirection(initialDirection);
    data.setDefaultAction("MOVE");
    data.setMoveAction("MOVE");

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(), data.getInitialAction(),
        LoadBulletAnimation.loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    Vector3f position = new Vector3f(x,y,Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;
  }

  public static Agent2DDataHolder createTurret(float x, float y)throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    // create initial params
    data.setInitialAction("IDLE");
    data.setInitialDirection("E");
    data.setDefaultAction("IDLE");
    data.setMoveAction("NA");

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(), data.getInitialAction(),
        LoadTurretAnimations.loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    // create empty triggered object dict
    Map<String, Agent2DDataHolder> spawnerDict = new HashMap<>();
    spawnerDict.put("ATTACK_1_SIDE", createTurretBullet(X_PLACEHOLDER, Y_PLACEHOLDER, "E")); // positions need to be reset after when actually using spawner dict
    spawnerDict.put("ATTACK_2_SIDE", createTurretBullet(X_PLACEHOLDER, Y_PLACEHOLDER, "W"));
    data.setSpawnerDict(spawnerDict);

    Vector3f position = new Vector3f(x,y,Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;
  }

  public static Agent2DDataHolder createTurretBullet(float x, float y, String initialDirection)throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    data.setSpeedScale(5.0f);

    // create initial params
    data.setInitialAction("MOVE");
    data.setInitialDirection(initialDirection);
    data.setDefaultAction("MOVE");
    data.setMoveAction("MOVE");

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(), data.getInitialAction(),
        LoadTurretBulletAnimations.loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    // create empty triggered object dict
    Map<String, Agent2DDataHolder> spawnerDict = new HashMap<>();
    spawnerDict.put("ATTACK_1_SIDE", createTurretBullet(X_PLACEHOLDER, Y_PLACEHOLDER, "E"));
    spawnerDict.put("ATTACK_2_SIDE", createTurretBullet(X_PLACEHOLDER, Y_PLACEHOLDER, "W"));
    data.setSpawnerDict(spawnerDict);

    Vector3f position = new Vector3f(x,y,Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;}

  public static Agent2DDataHolder createWatcher(float x, float y, String initialDirection)throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    // create initial params
    data.setInitialAction("IDLE");
    data.setInitialDirection(initialDirection);
    data.setDefaultAction("IDLE");
    data.setMoveAction("WALK");

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(), data.getInitialAction(),
        LoadWatcherBotAnimations.loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    Vector3f position = new Vector3f(x,y,Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;}

  public static Agent2DDataHolder createSoldier(float x, float y)throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    // create initial params
    data.setInitialAction("IDLE");
    data.setInitialDirection("E");
    data.setDefaultAction("IDLE");
    data.setMoveAction("WALK");

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(), data.getInitialAction(),
        LoadSoldierAnimations.loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    // create empty combo dict
    Map<String, String> comboDict = new HashMap<>();
    comboDict.put("JUMP", "FALL");
    data.setNextDict(comboDict);

    // create empty triggered object dict
    Map<String, Agent2DDataHolder> spawnerDict = new HashMap<>();
    spawnerDict.put("ATTACK", createBullet(X_PLACEHOLDER, Y_PLACEHOLDER, DIR_PLACEHOLDER));
    data.setSpawnerDict(spawnerDict);

    Vector3f position = new Vector3f(x,y,Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;

  }

  public static Agent2DDataHolder createEngineer(float x, float y) throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    // create initial params
    data.setInitialAction("IDLE");
    data.setInitialDirection("E");
    data.setDefaultAction("IDLE");
    data.setMoveAction("WALK");

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(), data.getInitialAction(),
        LoadEngineerBotAnimations.loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    // create empty combo dict
    Map<String, String> comboDict = new HashMap<>();
    comboDict.put("JUMP", "FALL");
    data.setNextDict(comboDict);

    // create empty triggered object dict
    Map<String, Agent2DDataHolder> spawnerDict = new HashMap<>();
    spawnerDict.put("ATTACK", createBullet(X_PLACEHOLDER, Y_PLACEHOLDER, DIR_PLACEHOLDER));
    spawnerDict.put("SUMMON_BIGBOY", createSummon(X_PLACEHOLDER, Y_PLACEHOLDER, DIR_PLACEHOLDER, "SUMMON_BIGBOY"));
    spawnerDict.put("SUMMON_TURRET", createSummon(X_PLACEHOLDER, Y_PLACEHOLDER, DIR_PLACEHOLDER, "SUMMON_TURRET"));
    spawnerDict.put("SUMMON_WATCHER", createSummon(X_PLACEHOLDER, Y_PLACEHOLDER, DIR_PLACEHOLDER, "SUMMON_WATCHER"));
    data.setSpawnerDict(spawnerDict);

    Vector3f position = new Vector3f(x,y,Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;
  }

  public static Agent2DDataHolder createSummon(float x, float y, String initialDirection, String initialAction)
      throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    data.setShouldConsumed(true);

    // create initial params
    data.setInitialAction(initialAction);
    data.setInitialDirection(initialDirection);
    data.setDefaultAction(initialAction);
    data.setMoveAction("NA");

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(), data.getInitialAction(),
        LoadSummonAnimations.loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    // create empty triggered object dict
    Map<String, Agent2DDataHolder> spawnerDict = new HashMap<>();
    spawnerDict.put("SUMMON_BIGBOY", createBigBoy(X_PLACEHOLDER, Y_PLACEHOLDER, DIR_PLACEHOLDER));
    spawnerDict.put("SUMMON_TURRET", createTurret(X_PLACEHOLDER, Y_PLACEHOLDER));
    spawnerDict.put("SUMMON_WATCHER", createWatcher(X_PLACEHOLDER, Y_PLACEHOLDER, DIR_PLACEHOLDER));
    data.setSpawnerDict(spawnerDict);

    Vector3f position = new Vector3f(x,y,Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;
  }

  public static Agent2DDataHolder createShieldman(float x, float y)  throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    // create initial params
    data.setInitialAction("IDLE");
    data.setInitialDirection("E");
    data.setDefaultAction("IDLE");
    data.setMoveAction("WALK");

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(), data.getInitialAction(),
        LoadSummonAnimations.loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    // create empty combo dict
    Map<String, String> comboDict = new HashMap<>();
    comboDict.put("JUMP", "FALL");
    data.setNextDict(comboDict);

    Vector3f position = new Vector3f(x,y,Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;

  }

  public static Agent2DDataHolder createMeleeBot(float x, float y) throws IOException {
    Agent2DDataHolder data = new Agent2DDataHolder();

    // create initial params
    data.setInitialAction("IDLE");
    data.setInitialDirection("E");
    data.setDefaultAction("IDLE");
    data.setMoveAction("SPRINT");

    Map<Pair<Pair<String, Boolean>, String>, String> prevDict = new HashMap<>();
    prevDict.put(new Pair<>(new Pair<>("SPRINT",false), "SPRINT"), "PRESPRINT");
    prevDict.put(new Pair<>(new Pair<>("ATTACK1", true), "ATTACK3"), "ATTACKCOMBO");
    data.setPrevDict(prevDict);

    // create animation dict
    Animation2DDict animationDict = new Animation2DDict(data.getInitialDirection(),
        data.getInitialAction(), LoadMeleeBotAnimations
        .loadAnimations(), data.getPrevDict());
    data.setAgentAnimationDict(animationDict);

    Vector3f position = new Vector3f(x, y, Z_POSITION);
    data.setPosition(position);

    Vector3f scale = Asset2D.getNonPlayerScale();
    data.setScale(scale);

    return data;

  }

}

