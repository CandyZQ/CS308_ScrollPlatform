package ooga.view.game_view.agent.agent2d;

import java.util.Map;
import javafx.util.Pair;
import com.google.gson.internal.LinkedHashTreeMap;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.maths.Vector3f;
import ooga.view.game_view.animation.dict2d.Animation2DDict;

public class Agent2DDataHolder {

  private String initialDirection = "E";
  private String initialAction = "IDLE";
  private String defaultAction = "IDLE";
  private String moveAction = "SPRINT";
  private boolean shouldConsumed = false;
  private Map<String, String> nextDict = new LinkedHashTreeMap<>();
  private Map<Pair<Pair<String, Boolean>, String>, String> prevDict = new LinkedHashTreeMap<>();
    // what would come next to the current animation
    // if returns null -> default, no need to notify backend
    // if not, notifies backend
  private Map<String, Agent2DDataHolder> spawnerDict = new LinkedHashTreeMap<>();
  private Animation2DDict agentAnimationDict;

  private Vector3f rotation = Asset2D.getPlayerRotation();
  private Vector3f position;
  private Vector3f scale;

  private Vertex[] vertices = Asset2D.getAgentVertices();
  private int[] indices = Asset2D.getAgentIndices();

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

  public void setNextDict(Map<String, String> nextDict) {
    this.nextDict = nextDict;
  }

  public Map<String, String> getNextDict() {
    return nextDict;
  }

  public void setPrevDict(
      Map<Pair<Pair<String, Boolean>, String>, String> prevDict) {
    this.prevDict = prevDict;
  }

  public Map<Pair<Pair<String, Boolean>, String>, String> getPrevDict() {
    return prevDict;
  }

  public void setVertices(Vertex[] vertices) {
    this.vertices = vertices;
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

  public void setIndices(int[] indices){this.indices = indices;}

  public int[] getIndices() {
    return indices;
  }

}
