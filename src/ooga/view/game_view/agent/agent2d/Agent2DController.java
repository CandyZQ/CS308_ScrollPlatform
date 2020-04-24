package ooga.view.game_view.agent.agent2d;

import java.io.IOException;
import java.util.Map;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.AgentController;
import ooga.view.game_view.animation.dict2d.Animation2DDict;

public class Agent2DController extends AgentController {

  private Animation2DDict animationDict;
  private Map<String, String> nextDict;
  private GameObject object;
  private float speedScale;
  private Vector3f initialPos;

  public Agent2DController(Agent2DDataHolder data) throws IOException {
    super();
    DEFAULT_ACTION = data.getDefaultAction();
    direction = data.getInitialDirection();
    speedScale = data.getSpeedScale();
    action = data.getInitialAction();
    animationDict = data.getAgentAnimationDict();
    nextDict = data.getNextDict();
    initialPos = data.getPosition();
    this.setCurrentAnimation(direction, action);
  }

  public void setObject(GameObject object) {
    this.object = object;
    translate(initialPos);
  }

  @Override
  public void setCurrentAnimation(String direction, String action) {
    this.direction = direction;
    this.action = action;
    animationDict.setInUseAnimation(direction, action);
  }

  public Material getCurrentAnimatedMaterial() {
    Material frame = animationDict.getAnimation().getCurrentFrame();
    if (frame == null) {
      if (nextDict.containsKey(action)) {
        setCurrentAnimation(direction, nextDict.get(action));
      } else {
        setCurrentAnimation(direction, DEFAULT_ACTION);
      }
      return animationDict.getAnimation().getCurrentFrame();
    } else {
      return frame;
    }
  }

  public String getCurrentDirection(){return direction;}

  public void move(String direction) { //TODO if valid
    translate(Asset2D.convertDirectionalSpeed(direction, speedScale));
    //object.setPosition(Vector3f.add(object.getPosition(), Asset2D.convertDirectionalSpeed(direction, speedScale)));
  }

  public void translate(Vector3f delta){
    for (int i = 0; i < object.getMesh().getVertices().length; i++) {
      object.getMesh().setVerticesPosition(i, Vector3f
          .add(object.getMesh().getVertices()[i].getPosition(), delta));
  }
  }





}
