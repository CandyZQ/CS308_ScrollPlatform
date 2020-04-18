package ooga.view.game_view.agent.agent2d;

import java.io.IOException;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.interfaces.AgentController;
import ooga.view.game_view.agent.interfaces.AgentView;

public class Agent2DView extends AgentView {

  //TODO: should remove from hardcoded!

  protected Agent2DController controller;

  public Agent2DView() throws IOException {
    super("SPRINT");
    vertices = Asset2D.getAgentVertices();
    indices = Asset2D.getAgentIndices();
    controller = new Agent2DController();
    mesh = new Mesh(vertices, indices, controller.getCurrentAnimatedMaterial());
    object = new GameObject(Asset2D.getPlayerPosition(), Asset2D.getPlayerRotation(),
        Asset2D.getPlayerScale(), mesh);

  }

  public void renderMesh(Renderer2D renderer) {
    object.getMesh().setMaterial(controller.getCurrentAnimatedMaterial());
    renderer.renderMesh(object);
  }

  public Vector2f getCenter(){
    float centerX = 0f;
    float centerY = 0f;

    for(Vertex v:vertices){
      centerX+=v.getPosition().getX();
      centerX+=v.getPosition().getY();
    }
    return new Vector2f(centerX/2.0f, centerY/2.0f);
  }

  public void update(String direction, String action) {
    controller.setCurrentAnimation(direction, action);
    if (action.equals(MOVE_ACTION)) controller.move(direction, mesh);
  }
}
