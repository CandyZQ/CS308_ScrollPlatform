package ooga.view.game_view.agent.agent3d;

import ooga.view.engine.graphics.render.Renderer3D;
import ooga.view.engine.objects.Camera;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.cyberpunk3d.LoadCyberpunkModels;
import ooga.view.game_view.agent.interfaces.AgentView;

public class Agent3DView extends AgentView {

  private Agent3DController controller;

  public Agent3DView() {
    super("WALK");
    LoadCyberpunkModels.loadWhiteBotAnimationDict();
    controller = new Agent3DController(MOVE_ACTION, "IDLE", "N", "IDLE");
    mesh = controller.getCurrentAnimatedMaterial();
    object = new GameObject(controller.getPosition(), controller.getRotation(), controller.getScale(), mesh);
  }

  public void renderMesh(Renderer3D renderer, Camera camera) {
    object.getMesh().destroy();
    controller.move(object);
    controller.getCurrentAnimatedMaterial().create();
    object.setMesh(controller.getCurrentAnimatedMaterial());
    renderer.renderMesh(object, camera);
  }

  @Override
  public void update(String direction, String action) {
    controller.setCurrentAnimation(direction, action);
  }

  public GameObject getObject(){return object;}
}
