package ooga.view.game_view.agent.interfaces;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.objects.GameObject;

/**
 * an abstract class that structure the framework for 2D and 3D agent rendering
 * @author qiaoyi fang
 */
abstract public class AgentView {

  protected String MOVE_ACTION;

  protected int id;
  protected Vertex[] vertices;
  protected int[] indices;
  protected Mesh mesh;
  protected GameObject object;

  public AgentView(String moveAction){MOVE_ACTION = moveAction;}

  /**
   * create mesh
   */
  public void createMesh() {
    object.getMesh().create();
  }

  /**
   * destroy mesh
   */
  public void destroyMesh() {
    mesh.destroy();
  }

  /**
   * update the state of the current agent
   * @param direction the new direction
   * @param action the new action state
   */
  abstract public void update(String direction, String action);

  /**
   * get vertices
   * @return the vertex array of the mesh of the game object
   */
  public Vertex[] getVertices(){return vertices;}

}
