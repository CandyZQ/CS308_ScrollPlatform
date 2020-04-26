package ooga.view.game_view.agent.agent2d;

import java.io.IOException;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.AgentView;
import ooga.view.game_view.game_state.state2d.BoundingBox;

public class Agent2DView extends AgentView {

  //TODO: should remove from hardcoded!

  protected Agent2DController controller;
  private BoundingBox box;
  private Vector2f halfBounds;
  private Vector3f scale;
  private String currentRawDirection;

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

  public boolean isShouldTerminated() {
    return controller.isShouldTerminated();
  }

  public Vector3f getScale() {
    return scale;
  }

  public void setScale(Vector3f scale) {
    this.scale = scale;
  }

  public int getId(){return id;}

  public Vector2f getHalfBounds() {
    return halfBounds;
  }

  public void terminate(){
    controller.setShouldTerminated(true);
  }

  public boolean renderMesh(Renderer2D renderer) {
    Material newFrame = controller.getCurrentAnimatedMaterial();
    if (newFrame!=null) {
      object.getMesh().setMaterial(newFrame);
      renderer.renderMesh(object);
      return true;
    }
    return false;
  }

  public Vector2f getCenterPosition(){return Vector2f.subtract(object.getMesh().getCenter(),
      Asset2D.getIHateLife());}

  public String getCurrentDirection(){return currentRawDirection;} //TODO FIX THIS

  public void update(String direction, String action) {
    currentRawDirection = direction;
    //System.out.println("action input in view"+action);
    controller.setCurrentAnimation(direction, action);
    if (action.equals(MOVE_ACTION)) controller.move(direction);
  }

  public boolean canMove(String direction){
    return controller.canMove(direction);
  }

  public String getAction(){return controller.getAction();}

  public GameObject getObject(){return object;}
}
