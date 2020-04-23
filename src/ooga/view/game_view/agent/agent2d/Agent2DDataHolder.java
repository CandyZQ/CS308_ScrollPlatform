package ooga.view.game_view.agent.agent2d;

import java.util.Map;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.maths.Vector3f;
import ooga.view.game_view.animation.dict2d.Animation2DDict;

public class Agent2DDataHolder {

  private String initialDirection = "E";
  private String initialAction = "IDLE";
  private String defaultAction = "IDLE";
  private String moveAction = "SPRINT";
  private Map<String, String> comboDict; // should be inside animation dict
  private Map<String, Agent2DDataHolder> triggeredObjectDict;
  private Animation2DDict agentAnimationDict;

  private Vector3f position;
  private Vector3f rotation;
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

  public void setAgentAnimationDict(
      Animation2DDict agentAnimationDict) {
    this.agentAnimationDict = agentAnimationDict;
  }

  public Animation2DDict getAgentAnimationDict() {
    return agentAnimationDict;
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

  public void setPosition(Vector3f position) {
    this.position = position;
  }

  public Vector3f getPosition() {
    return position;
  }

  public Vertex[] getVertices() {
    return vertices;
  }

  public int[] getIndices() {
    return indices;
  }

}
