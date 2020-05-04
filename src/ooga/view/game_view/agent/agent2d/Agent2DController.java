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

/**
 * a class that control the animation state and the moving of the character
 * @author qiaoyi fang
 */
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

  /**
   * constructor that receives the character id, data holder to initialize the character, and the bounding box
   * @param id id
   * @param data data holder
   * @param box bounding box
   */
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

  /**
   * set the agent view
   * @param view view
   */
  public void setAgentView(Agent2DView view) {
    this.agentView = view;
  }

  /**
   * returns if the character should be terminated
   * @return true if the character should be terminated
   */
  public boolean isShouldTerminated() {
    return shouldTerminated;
  }

  /**
   * set the terminated status
   * @param shouldTerminated boolean status
   */
  public void setShouldTerminated(boolean shouldTerminated) {
    this.shouldTerminated = !isNotGonnaDie && shouldTerminated;
  }

  /**
   * get the current action status
   * @return action status
   */
  public String getAction() {
    return action;
  }

  /**
   * set the game object of the character
   * @param object object
   */
  public void setObject(GameObject object) {
    this.object = object;
    if (isBullet){
      System.out.println("printing bullet translate information");
      Test.printVector3f(initialPos);
      Test.printVector3f(Asset2D.getBulletDelta() );
    }
    translate(initialPos);
  }

  /**
   * updates the current animation
   * @param direction the new direction
   * @param action the new action status
   */
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

  /**
   * gets the animated texture material at the current frame
   * @return texture material
   */
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

  /**
   * move the character
   * @param direction the moving direction
   */
  public void move(String direction) {

    if (canMove(direction)) {
      translate(Asset2D.convertDirectionalSpeed(direction, speedScale));
    }
  }

  /**
   * return if the character can move
   * @param direction the moving direction
   * @return boolean status
   */
  public boolean canMove(String direction){
    return box.canMove(!isBullet, isBullet, agentView,
        Asset2D.convertDirectionalSpeed(direction, speedScale));
  }


  private void translate(Vector3f delta) {

    for (int i = 0; i < object.getMesh().getVertices().length; i++) {
      object.getMesh().setVerticesPosition(i,
          Vector3f.add(object.getMesh().getVertices()[i].getPosition(), delta));
    }

  }

}
