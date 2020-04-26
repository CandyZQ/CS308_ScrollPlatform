package ooga.view.game_view.agent.agent2d;

import java.util.Map;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.Test;
import ooga.view.game_view.agent.interfaces.AgentController;
import ooga.view.game_view.animation.dict2d.Animation2DDict;
import ooga.view.game_view.game_state.state2d.BoundingBox;

public class Agent2DController extends AgentController {

  private Animation2DDict animationDict;
  private Map<String, String> nextDict;
  private GameObject object;
  private float speedScale;
  private Vector3f initialPos;
  private boolean shouldConsumed;
  private boolean shouldTerminated = false;
  private boolean isSummon;
  private Agent2DView agentView;
  private BoundingBox box;
  private int id;
  private boolean isBullet;
  private boolean isNSAnimationAvail;
  private boolean isNotGonnaDie;
  private boolean temp_switch=false;

  public Agent2DController(int id, Agent2DDataHolder data, BoundingBox box) {
    super();
    DEFAULT_ACTION = data.getDefaultAction();
    direction = data.getInitialDirection();
    speedScale = data.getSpeedScale();
    action = data.getInitialAction();
    animationDict = data.getAgentAnimationDict();
    nextDict = data.getNextDict();
    initialPos = Vector3f
        .add(data.getPosition(), data.isBullet() ? Asset2D.getBulletDelta() : Vector3f.zeros());
    if (data.isBullet()){
      System.out.println("printing bullet initialization information");
      Test.printVector3f(data.getPosition());
      Test.printVector3f(Asset2D.getBulletDelta() );
    }
    initialPos = Vector3f
        .add(initialPos, data.isSummon() ? Asset2D.getSummonDelta() : Vector3f.zeros());
    shouldConsumed = data.shouldConsumed();
    isBullet = data.isBullet();
    isSummon = data.isSummon();
    isNSAnimationAvail = data.isNWAnimationAvail();
    isNotGonnaDie = data.isNotGonnaDie();
    this.box = box;
    this.id = id;
    this.setCurrentAnimation(direction, action);
  }

  public void setAgentView(Agent2DView view) {
    this.agentView = view;
  }

  public boolean isShouldTerminated() {
    return shouldTerminated;
  }

  public void setShouldTerminated(boolean shouldTerminated) {
    this.shouldTerminated = !isNotGonnaDie && shouldTerminated;
  }

  public String getAction() {
    return action;
  }

  public void setObject(GameObject object) {
    this.object = object;
    if (isBullet){
      System.out.println("printing bullet translate information");
      Test.printVector3f(initialPos);
      Test.printVector3f(Asset2D.getBulletDelta() );
    }
    translate(initialPos);
  }

  @Override
  public void setCurrentAnimation(String direction, String action) {
    if (Asset2D.isRightSystem(direction)){
      if (object!=null) object.getMesh().setTextureCoords(Asset2D.getNormalTextureCoords());
      this.direction = direction;
    }
    else if (Asset2D.isMirrorSystem(direction)){
      if (object!=null) object.getMesh().setTextureCoords(Asset2D.getMirroredTextureCoords());
      this.direction = Asset2D.getMirroredRightDirection(direction);
    }
    else {
      this.direction = !isNSAnimationAvail?this.direction:direction;
    }
    this.action = action;
    //System.out.println("action -"+action);
    animationDict.setInUseAnimation(this.direction, action);
  }

  public Material getCurrentAnimatedMaterial() {

    Material frame = animationDict.getAnimation().getCurrentFrame();
    if (frame == null) {

      if (nextDict.containsKey(action)) {
        setCurrentAnimation(direction, nextDict.get(action));
      } else {
        setCurrentAnimation(direction, isSummon ? action : DEFAULT_ACTION);
      }

      return (shouldConsumed || shouldTerminated) ? null
          : animationDict.getAnimation().getCurrentFrame();
    } else {
      return frame;
    }
  }

  public String getCurrentAction() {
    return animationDict.getCurrentAction();
  }

  public void move(String direction) {

    if (canMove(direction)) {
      translate(Asset2D.convertDirectionalSpeed(direction, speedScale));
    }
  }

  public boolean canMove(String direction){
    return box.canMove(!isBullet, isBullet, agentView,
        Asset2D.convertDirectionalSpeed(direction, speedScale));
  }

  private void translate(Vector3f delta) {
    if (!temp_switch && isBullet) System.out.println("before");
    if (!temp_switch && isBullet) Test.printThreeMeshVertices(object.getMesh());
    for (int i = 0; i < object.getMesh().getVertices().length; i++) {
      object.getMesh().setVerticesPosition(i,
          Vector3f.add(object.getMesh().getVertices()[i].getPosition(), delta));
    }
    if (!temp_switch && isBullet) System.out.println("after");
    if (!temp_switch && isBullet) Test.printThreeMeshVertices(object.getMesh());
    temp_switch = true;
  }

}
