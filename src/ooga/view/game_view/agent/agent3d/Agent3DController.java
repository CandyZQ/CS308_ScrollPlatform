package ooga.view.game_view.agent.agent3d;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.assets.Asset3D;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.AgentController;
import ooga.view.game_view.animation.dict3d.Animation3DDict;

// String direction: N (go forward) | S (go backward) | W (move left without turning) | E (move right without turning)

public class Agent3DController extends AgentController {

  private String MOVE_ACTION;
  private Animation3DDict dict;

  private Vector3f position = Vector3f.zeros();
  private Vector3f rotation = Vector3f.zeros();
  private Vector3f scale = Asset3D.getWhiteBotScale();

  public Agent3DController(String moveAction, String initialAction, String initialDirection, String defaultAction){
    super();
    DEFAULT_ACTION = defaultAction;
    direction = initialDirection;
    action = initialAction;
    MOVE_ACTION = moveAction;
    dict = new Animation3DDict(initialDirection, initialAction);
    this.setCurrentAnimation(direction, action);
  }

  @Override
  public void setCurrentAnimation(String direction, String action) {
    this.direction = direction;
    this.action = action;
    dict.setInUseAnimation(direction, action);
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

  public void move(GameObject object) {
    if (!action.equals(MOVE_ACTION)) return;
    position = Vector3f.add(position, Asset3D.getTranslateVector(direction));
    object.setPosition(position);
  }

}
