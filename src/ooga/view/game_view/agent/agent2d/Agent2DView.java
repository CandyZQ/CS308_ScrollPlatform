package ooga.view.game_view.agent.agent2d;

import java.io.IOException;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.AgentView;

public class Agent2DView extends AgentView {

  //TODO: should remove from hardcoded!

  protected Agent2DController controller;

  public Agent2DView(int id, Agent2DDataHolder data) throws IOException {
    super(data.getMoveAction());
    this.id = id;
    vertices = data.getVertices();
    indices = data.getIndices();
    controller = new Agent2DController(data);
    mesh = new Mesh(vertices, indices, controller.getCurrentAnimatedMaterial());
    object = new GameObject(data.getPosition(), data.getRotation(), data.getScale(), mesh);
    controller.setObject(object);
  }

  public void renderMesh(Renderer2D renderer) {
    object.getMesh().setMaterial(controller.getCurrentAnimatedMaterial());
    renderer.renderMesh(object);
  }

  public Vector2f getCenterPosition(){return object.getMesh().getCenter();}

  public Vector2f getCenter(){
    float centerX = 0f;
    float centerY = 0f;

    for(Vertex v:vertices){
      centerX+=v.getPosition().getX();
      centerX+=v.getPosition().getY();
    }
    return new Vector2f(centerX/2.0f, centerY/2.0f);
  }

  public String getCurrentDirection(){return controller.getCurrentDirection();}

  public void update(String direction, String action) {
    controller.setCurrentAnimation(direction, action);
    if (action.equals(MOVE_ACTION)) controller.move(direction);
  }
}
