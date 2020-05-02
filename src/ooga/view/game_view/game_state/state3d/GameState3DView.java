package ooga.view.game_view.game_state.state3d;

import java.io.IOException;
import ooga.view.engine.graphics.Shader;
import ooga.view.engine.graphics.render.Renderer3D;
import ooga.view.engine.io.Input;
import ooga.view.engine.io.Window;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.Camera;
import ooga.view.engine.utils.Test;
import ooga.view.game_view.agent.agent3d.Agent3DView;
import ooga.view.game_view.game_state.interfaces.GameStateView;
import ooga.view.game_view.map.map3d.Map3DView;
import org.lwjgl.glfw.GLFW;

public class GameState3DView extends GameStateView {

  public Thread game;
  public Renderer3D renderer;
  public Shader shader;
  public final int WIDTH = 1580, HEIGHT = 1080;

  public Map3DView mapView;
  public Agent3DView agentView;
  public String mapPath = "/view/data/3d/map_demo.txt";
  public Camera camera = new Camera(new Vector3f(1020.0f, -220.0f, 270.0f), new Vector3f(0f, 0f, 0));


  @Override
  public void createWindow() throws IOException {
    window = new Window(WIDTH, HEIGHT, "Game");
    shader = new Shader("/view/shaders/3d/mainVertex.glsl", "/view/shaders/2d/mainFragment.glsl");
    renderer = new Renderer3D(window, shader);

    mapView = new Map3DView(mapPath);
    Test.printMapLoadingMessage();

    window.setBackgroundColor(22.0f/255.0f, 23.0f/255.0f, 25.0f/255.0f);
    window.create();
    agentView = new Agent3DView();
    Test.printAgentLoadingMessage();

    agentView.createMesh();
    Test.printAgentCreateMessage();
    mapView.createMesh();
    Test.printMapCreateMessage();
    shader.create();
  }

  public void updateAll(){
    update3DAgent();
    updateCamera();
    window.update();
    camera.update();
  }

  private void update3DAgent(){
    String direction = "E";
    if (Input.isKeyDown(GLFW.GLFW_KEY_A)){
      agentView.update(direction, "WALK");
    }
    if (Input.isKeyDown(GLFW.GLFW_KEY_E)){
      agentView.update(direction, "ATTACK");
    }
    if (Input.isKeyDown(GLFW.GLFW_KEY_D)){
      agentView.update(direction, "DEATH");
    }
  }


  private void updateCamera(){
    camera.update();
    if (Input.isKeyDown(GLFW.GLFW_KEY_UP)){
      camera.moveXForward();
    }
    if (Input.isKeyDown(GLFW.GLFW_KEY_DOWN)){
      camera.moveXBackward();
    }
    if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT)){
      camera.moveYForward();
    }
    if (Input.isKeyDown(GLFW.GLFW_KEY_RIGHT)){
      camera.moveYBackward();
    }
    if (Input.isKeyDown(GLFW.GLFW_KEY_M)){
      camera.moveZForward();
    }
    if (Input.isKeyDown(GLFW.GLFW_KEY_N)){
      camera.moveZBackward();
    }
  }

  @Override
  public void renderAgents() {
    agentView.renderMesh(renderer, camera);
  }

  @Override
  public void renderMap() {
    mapView.renderMesh(renderer, camera);
  }

  @Override
  public void renderAll(){
    renderAgents();
    renderMap();
    renderWindow();
    window.swapBuffers();
  }

  @Override
  public void closeWindow() {
    window.destroy();
    mapView.destroyMesh();
    agentView.destroyMesh();
    shader.destroy();
  }

  @Override
  public void updateAgent(int id, String direction, String state) {}

  @Override
  public void deleteAgent(int id) {}

  @Override
  public void updateMap() {}

}
