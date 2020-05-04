package ooga.view.game_view.agent.agent2d;

import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.AgentView;
import ooga.view.game_view.game_state.state2d.BoundingBox;

/**
 * a class that implements the rendering of a character
 * @author qiaoyi fang
 */
public class Agent2DView extends AgentView {

  protected Agent2DController controller;
  private Vector2f halfBounds;
  private Vector3f scale;
  private String currentRawDirection;

  /**
   * constructor that receives the index, data, and the bounding box
   * @param id index
   * @param data data holder to initialize the agent
   * @param box the bounding box
   */
  public Agent2DView(int id, Agent2DDataHolder data, BoundingBox box) {
    super(data.getMoveAction());
    this.id = id;
    vertices = data.getVertices();
    indices = data.getIndices();
    halfBounds = data.getHalfBounds();
    controller = new Agent2DController(id, data, box);
    scale = data.getScale();
    mesh = new Mesh(vertices, indices, controller.getCurrentAnimatedMaterial());
    object = new GameObject(Vector3f.zeros(), data.getRotation(), data.getScale(), mesh);
    controller.setObject(object);
    controller.setAgentView(this);
    currentRawDirection = data.getInitialDirection();
  }

  /**
   * returns if the agent should be terminated (to be deleted in the rendering list)
   * @return boolean status
   */
  public boolean isShouldTerminated() {
    return controller.isShouldTerminated();
  }

  /**
   * get the scale of the agent
   * @return scale
   */
  public Vector3f getScale() {
    return scale;
  }

  /**
   * set the new scale of the agent
   * @param scale the new scale
   */
  public void setScale(Vector3f scale) {
    this.scale = scale;
  }

  /**
   * get the id of the agent
   * @return id
   */
  public int getId(){return id;}

  /**
   * get the half bounds (width/2, height/2) of the agent
   * @return the bounds
   */
  public Vector2f getHalfBounds() {
    return halfBounds;
  }

  /**
   * terminates the agent
   */
  public void terminate(){
    controller.setShouldTerminated(true);
  }

  /**
   * renders the agent
   * @param renderer the renderer
   * @return true if successful (to handle the multithreading if the termination happened later)
   */
  public boolean renderMesh(Renderer2D renderer) {
    Material newFrame = controller.getCurrentAnimatedMaterial();
    if (newFrame!=null) {
      object.getMesh().setMaterial(newFrame);
      renderer.renderMesh(object);
      return true;
    }
    return false;
  }

  /**
   * gets the current center position
   * @return center position
   */
  public Vector2f getCenterPosition(){return Vector2f.subtract(object.getMesh().getCenter(),
      Asset2D.getIHateLife());}

  /**
   * gets the current direction (before mirroring)
   * @return current direction
   */
  public String getCurrentDirection(){return currentRawDirection;}

  /**
   * update the state of the current agent
   * @param direction the new direction
   * @param action the new action state
   */
  @Override
  public void update(String direction, String action) {
    currentRawDirection = direction;
    controller.setCurrentAnimation(direction, action);
    if (action.equals(MOVE_ACTION)) controller.move(direction);
  }

  /**
   * returns true if the agent can move in the direction
   * @param direction the new direction
   * @return boolean status
   */
  public boolean canMove(String direction){
    return controller.canMove(direction);
  }

  /**
   * returns the current action
   * @return action
   */
  public String getAction(){return controller.getAction();}

  /**
   * returns the game object of the agent
   * @return the game object
   */
  public GameObject getObject(){return object;}
}
