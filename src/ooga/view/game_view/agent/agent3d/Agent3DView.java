package ooga.view.game_view.agent.agent3d;

import ooga.view.engine.graphics.render.Renderer3D;
import ooga.view.engine.objects.Camera;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.cyberpunk3d.LoadCyberpunkModels;
import ooga.view.game_view.agent.interfaces.AgentView;

/**
 * a class that implements the rendering of 3D agent
 * @author qiaoyi fang
 */
public class Agent3DView extends AgentView {

  private Agent3DController controller;

  public Agent3DView() {
    super("WALK");
    LoadCyberpunkModels.loadWhiteBotAnimationDict();
    controller = new Agent3DController(MOVE_ACTION, "IDLE", "N", "IDLE");
    mesh = controller.getCurrentAnimatedMaterial();
    object = new GameObject(controller.getPosition(), controller.getRotation(), controller.getScale(), mesh);
  }

  /**
   * renders the mesh of the agent
   * @param renderer renderer
   * @param camera the camera view
   */
  public void renderMesh(Renderer3D renderer, Camera camera) {
    object.getMesh().destroy();
    controller.move(object);
    controller.getCurrentAnimatedMaterial().create();
    object.setMesh(controller.getCurrentAnimatedMaterial());
    renderer.renderMesh(object, camera);
  }

  /**
   * update the state of the current agent
   * @param direction the new direction
   * @param action the new action state
   */
  @Override
  public void update(String direction, String action) {
    controller.setCurrentAnimation(direction, action);
  }

  /**
   * returns the game object of the agent
   * @return the game object
   */
  public GameObject getObject(){return object;}
}
