package ooga.view.game_view.agent.interfaces;

import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;

abstract public class AgentController {

  protected static String DEFAULT_ACTION;
  protected String direction;
  protected String action;

  public AgentController(){}

  public abstract void setCurrentAnimation(String direction, String action);

  //public abstract Material getCurrentAnimatedMaterial();

  //public abstract void move(String direction, Mesh mesh);

}
