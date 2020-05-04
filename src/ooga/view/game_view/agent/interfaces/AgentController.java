package ooga.view.game_view.agent.interfaces;

/**
 * a class that structure the framework of 2D and 3D agent controller
 * @author qiaoyi fang
 */
abstract public class AgentController {

  protected String DEFAULT_ACTION;
  protected String direction;
  protected String action;

  public AgentController(){}

  /**
   * updates the current animation
   * @param direction the new direction
   * @param action the new action status
   */
  public abstract void setCurrentAnimation(String direction, String action);

}
