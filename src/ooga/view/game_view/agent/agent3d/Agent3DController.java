package ooga.view.game_view.agent.agent3d;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.assets.Asset3D;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.AgentController;
import ooga.view.game_view.animation.dict3d.Animation3DDict;

/**
 * constructor that controls the animation state and the moving of a 3D character
 * @author qiaoyi fang
 */
public class Agent3DController extends AgentController {

  // String direction: N (go forward) | S (go backward) | W (move left without turning) | E (move right without turning)

  private String MOVE_ACTION;
  private Animation3DDict dict;

  private Vector3f position = Vector3f.zeros();
  private Vector3f rotation = Vector3f.zeros();
  private Vector3f scale = Asset3D.getWhiteBotScale();

  /**
   * constructor
   * @param moveAction the action that moves the agent
   * @param initialAction the initial action/state (usually "idle")
   * @param initialDirection the initial direction
   * @param defaultAction the default action that the agent returns to when the an action animation is finished
   */
  public Agent3DController(String moveAction, String initialAction, String initialDirection, String defaultAction){
    super();
    DEFAULT_ACTION = defaultAction;
    direction = initialDirection;
    action = initialAction;
    MOVE_ACTION = moveAction;
    dict = new Animation3DDict(initialDirection, initialAction);
    this.setCurrentAnimation(direction, action);
  }

  /**
   * updates the current animation
   * @param direction the new direction
   * @param action the new action status
   */
  @Override
  public void setCurrentAnimation(String direction, String action) {
    this.direction = direction;
    this.action = action;
    dict.setInUseAnimation(direction, action);
  }

  /**
   * gets the animated mesh model at the current frame
   * @return mesh model
   */
  public Mesh getCurrentAnimatedMaterial() {

    Mesh frame = dict.getAnimation().getCurrentFrame();
    if (frame == null){
      setCurrentAnimation(direction, DEFAULT_ACTION);
      return dict.getAnimation().getCurrentFrame();
    }
    else{
      return frame;
    }
  }

  /**
   * move the character
   * @param object the gameobject to be moved
   */
  public void move(GameObject object) {
    if (!action.equals(MOVE_ACTION)) return;
    position = Vector3f.add(position, Asset3D.getTranslateVector(direction));
    object.setPosition(position);
  }

  public Vector3f getPosition() {
    return position;
  }

  public Vector3f getRotation() {
    return rotation;
  }

  public Vector3f getScale() {
    return scale;
  }

}
