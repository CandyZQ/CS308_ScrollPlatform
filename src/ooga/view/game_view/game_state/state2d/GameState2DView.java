package ooga.view.game_view.game_state.state2d;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ooga.view.engine.graphics.Shader;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.io.Input;
import ooga.view.engine.io.Window;
import ooga.view.game_view.agent.agent2d.Agent2DView;
import ooga.view.game_view.game_state.interfaces.GameStateView;
import ooga.view.game_view.map.map2d.Map2DView;
import org.lwjgl.glfw.GLFW;

public class GameState2DView extends GameStateView {
  private static final float BACKGROUND_COLOR_R = 22.0f/255.0f;
  private static final float BACKGROUND_COLOR_G = 23.0f/255.0f;
  private static final float BACKGROUND_COLOR_B = 25.0f/255.0f;
  private static final String VERTEX_SHADER_PATH = "/view/shaders/2d/cyberpunkTitleVertex.glsl";
  private static final String FRAGMENT_SHADER_PATH = "/view/shaders/2d/cyberpunkTitleFragment.glsl";
  private static final int WIDTH = 1080;
  private static final int HEIGHT = 1080;

  private Map2DView map;
  private List<Agent2DView> players;
  private List<Agent2DView> npcs;
  private int numPlayers;
  private Window window;
  public Renderer2D renderer;
  public Shader shader;


  //TODO: remove this part!
  String mapPath = "/view/textures/2d/cyberpunk/map/map.txt";


  public GameState2DView(int playerNum) throws IOException {
    this.numPlayers = playerNum;
    this.players = new ArrayList<>();
    this.npcs = new ArrayList<>();
  }


  @Override
  public void createWindow() throws IOException {

    window = new Window(WIDTH, HEIGHT, "Game");
    shader = new Shader(VERTEX_SHADER_PATH, FRAGMENT_SHADER_PATH);
    renderer = new Renderer2D(shader);

    window.setBackgroundColor(BACKGROUND_COLOR_R, BACKGROUND_COLOR_G, BACKGROUND_COLOR_B);
    window.create();
    //window.setFullscreen(true);

    map = new Map2DView(mapPath);
    for(int i=0; i<numPlayers; i++){
      players.add(new Agent2DView());
      players.get(i).createMesh();
    }

    map.createMesh();
    shader.create();

  }


  @Override
  public void updatePlayer(int index, String direction, String state) {
    players.get(index).update(direction, state);
  }

  @Override
  public void updateNPC(int index, String direction, String state) {}

  @Override
  public void updateWindow(){
    if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
      window.setFullscreen(!window.isFullscreen());
    }
    window.update();
  }

  @Override
  public void renderPlayers() {
    for (Agent2DView player:players){
      player.renderMesh(renderer);
    }
  }

  @Override
  public void renderNPCs() { }

  @Override
  public void renderMap() {
    map.renderMesh(renderer);
  }

  @Override
  public void renderWindow() {
    window.swapBuffers();
  }

  @Override
  public void updateMap() {}

  @Override
  public void closeWindow() {
    window.destroy();

    for (Agent2DView player:players){
      player.destroyMesh();
    }

    map.destroyMesh();
    shader.destroy();
  }

  @Override
  public boolean shouldWindowClose() {
    return  window.shouldClose() || Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE);
  }

  @Override
  public boolean isKeyDown(int key) {
    return Input.isKeyDown(key);
  }
}
