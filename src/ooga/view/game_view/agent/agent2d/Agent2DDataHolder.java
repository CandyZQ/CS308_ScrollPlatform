package ooga.view.game_view.agent.agent2d;

import com.google.gson.internal.LinkedHashTreeMap;
import java.util.Map;
import javafx.util.Pair;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.game_view.animation.dict2d.Animation2DDict;

/**
 * a class that holds the data needed to initialize a 2D agent
 * @author qiaoyi fang
 */
public class Agent2DDataHolder {

  private String type;
  private String initialDirection;
  private String initialAction;
  private String defaultAction;
  private String moveAction;
  private boolean shouldConsumed = false;
  private boolean isBullet = false;
  private boolean isNotGonnaDie = false;
  private boolean isSummon = false;
  private float speedScale = 10f;
  private Map<String, String> nextDict = new LinkedHashTreeMap<>();
  private Map<Pair<Pair<String, Boolean>, String>, String> prevDict = new LinkedHashTreeMap<>();
  private Map<String, Agent2DDataHolder> spawnerDict = new LinkedHashTreeMap<>();
  private Animation2DDict agentAnimationDict;

  private Vector3f rotation = Asset2D.getPlayerRotation();
  private Vector3f position;
  private Vector3f scale;

  private boolean isNWAnimationAvail = false;
  private Vertex[] vertices = Asset2D.getAgentVertices();
  private int[] indices = Asset2D.getAgentIndices();
  private Vector2f halfBounds;

  public Agent2DDataHolder() {
  }

  /**
   * A clone constructor
   * @param other the data holder to be cloned
   */
  public Agent2DDataHolder(Agent2DDataHolder other) {
    this.type = other.type;
    this.isBullet = other.isBullet;
    this.isSummon = other.isSummon;
    this.initialDirection = other.getInitialDirection();
    this.initialAction = other.getInitialAction();
    this.defaultAction = other.getDefaultAction();
    this.moveAction = other.getMoveAction();
    this.speedScale = other.getSpeedScale();
    this.shouldConsumed = other.shouldConsumed();
    this.nextDict = Map.copyOf(other.getNextDict());  //fixme maybe do a deep copy?
    this.prevDict = Map.copyOf(other.getPrevDict());
    this.spawnerDict = Map.copyOf(other.getSpawnerDict());
    this.rotation = new Vector3f(other.getRotation());
    this.position = new Vector3f(other.getPosition());
    this.scale = new Vector3f(other.getScale());
    this.vertices = Mesh.verticesCopy(Asset2D.getAgentVertices());
    this.indices = Asset2D.getAgentIndices();
    this.halfBounds = other.halfBounds;
    this.isNWAnimationAvail = other.isNWAnimationAvail;
    this.isNotGonnaDie = other.isNotGonnaDie;
  }

  public void setHalfBounds(Vector2f halfBounds) {
    this.halfBounds = halfBounds;
  }

  public void setNWAnimationAvail(boolean NWAnimationAvail) {
    isNWAnimationAvail = NWAnimationAvail;
  }

  public void setNotGonnaDie(boolean notGonnaDie) {
    isNotGonnaDie = notGonnaDie;
  }

  public boolean isNotGonnaDie() {
    return isNotGonnaDie;
  }

  public boolean isNWAnimationAvail() {
    return isNWAnimationAvail;
  }

  public Vector2f getHalfBounds() {
    return halfBounds;
  }

  public boolean isSummon() {
    return isSummon;
  }

  public void setSummon(boolean summon) {
    isSummon = summon;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public boolean isBullet() {
    return isBullet;
  }

  public void setIsObject(boolean isObject) {
    this.isBullet = isObject;
  }

  public float getSpeedScale() {
    return speedScale;
  }

  public void setSpeedScale(float speedScale) {
    this.speedScale = speedScale;
  }

  public String getInitialDirection() {
    return initialDirection;
  }

  public void setInitialDirection(String direction) {
    this.initialDirection = direction;
  }

  public String getInitialAction() {
    return initialAction;
  }

  public void setInitialAction(String action) {
    this.initialAction = action;
  }

  public String getDefaultAction() {
    return defaultAction;
  }

  public void setDefaultAction(String action) {
    this.defaultAction = action;
  }

  public String getMoveAction() {
    return moveAction;
  }

  public void setMoveAction(String action) {
    this.moveAction = action;
  }

  public void setShouldConsumed(boolean shouldConsumed) {
    this.shouldConsumed = shouldConsumed;
  }

  public boolean shouldConsumed() {
    return shouldConsumed;
  }

  public Animation2DDict getAgentAnimationDict() {
    return agentAnimationDict;
  }

  public void setAgentAnimationDict(
      Animation2DDict agentAnimationDict) {
    this.agentAnimationDict = agentAnimationDict;
  }

  public Map<String, Agent2DDataHolder> getSpawnerDict() {
    return spawnerDict;
  }

  public void setSpawnerDict(
      Map<String, Agent2DDataHolder> spawnerDict) {
    this.spawnerDict = spawnerDict;
  }

  public Map<String, String> getNextDict() {
    return nextDict;
  }

  public void setNextDict(Map<String, String> nextDict) {
    this.nextDict = nextDict;
  }

  public Map<Pair<Pair<String, Boolean>, String>, String> getPrevDict() {
    return prevDict;
  }

  public void setPrevDict(
      Map<Pair<Pair<String, Boolean>, String>, String> prevDict) {
    this.prevDict = prevDict;
  }

  public Vector3f getScale() {
    return scale;
  }

  public void setScale(Vector3f scale) {
    this.scale = scale;
  }

  public Vector3f getRotation() {
    return rotation;
  }

  public void setRotation(Vector3f rotation) {
    this.rotation = rotation;
  }

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(Vector3f position) {
    this.position = position;
  }

  public Vertex[] getVertices() {
    return vertices;
  }

  public void setVertices(Vertex[] vertices) {
    this.vertices = vertices;
  }

  public int[] getIndices() {
    return indices;
  }

  public void setIndices(int[] indices) {
    this.indices = indices;
  }

}
