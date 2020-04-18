package ooga.view.game_view.agent.interfaces;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.objects.GameObject;
import ooga.view.game_view.agent.agent2d.Agent2DController;

abstract public class AgentView {

  protected static String MOVE_ACTION;

  protected Vertex[] vertices;
  protected int[] indices;
  protected Mesh mesh;
  protected GameObject object;

  public AgentView(String moveAction){MOVE_ACTION = moveAction;}

  public void createMesh() {
    mesh.create();
  }

  public void destroyMesh() {
    mesh.destroy();
  }

  //abstract public void renderMesh(Renderer2D renderer);

  abstract public void update(String direction, String action);

  public Vertex[] getVertices(){return vertices;}

}
