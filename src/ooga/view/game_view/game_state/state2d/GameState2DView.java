package ooga.view.game_view.game_state.state2d;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import ooga.view.engine.graphics.Shader;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.io.Input;
import ooga.view.engine.io.Window;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.cyberpunk2d.GenerateAgentsData;
import ooga.view.game_view.agent.agent2d.Agent2DDataHolder;
import ooga.view.game_view.agent.agent2d.Agent2DView;
import ooga.view.game_view.game_state.interfaces.GameStateView;
import ooga.view.game_view.map.map2d.Map2DView;
import org.lwjgl.glfw.GLFW;

public class GameState2DView extends GameStateView {

  private static final float BACKGROUND_COLOR_R = 22.0f / 255.0f;
  private static final float BACKGROUND_COLOR_G = 23.0f / 255.0f;
  private static final float BACKGROUND_COLOR_B = 25.0f / 255.0f;
  private static final String VERTEX_SHADER_PATH = "/view/shaders/2d/cyberpunkTitleVertex.glsl";
  private static final String FRAGMENT_SHADER_PATH = "/view/shaders/2d/cyberpunkTitleFragment.glsl";
  private static final int WIDTH = 1080;
  private static final int HEIGHT = 1080;
  public Renderer2D renderer;
  public Shader shader;
  public BoundingBox box;
  String mapPath = "/view/textures/2d/cyberpunk/map/map.txt";
  private Map2DView map;
  private Map<Integer, Agent2DView> agentMap;
  private Map<Integer, Agent2DView> bulletMap;
  private int numPlayers;
  private Window window;

  //TODO: remove this part!
  //TODO: loop through a list of agents to update its alive status/ adding new
  //      how much to move :set in Asset2D
  //TODO: COLLISION DETECT:
  //TODO:   0) whether the movement is valid (check before move)
  //        1) whether the attack is made (1. a method to calc the distance between two objects or with wall)
  private Map<Integer, Agent2DDataHolder> agentDataHolderMap;

  public GameState2DView(Map<Integer, Agent2DDataHolder> agentsData) throws IOException {
    this.agentDataHolderMap = agentsData;
    this.bulletMap = new HashMap<>();
    this.numPlayers = agentsData.keySet().size();
    this.agentMap = new HashMap<>();
    this.box = new BoundingBox(map, agentMap, bulletMap);
  }

  public float getCenterPositionX(int id){
    return agentMap.get(id).getCenterPosition().getX();
  }

  public float getCenterPositionY(int id){
    return agentMap.get(id).getCenterPosition().getY();
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

    for (int id : agentDataHolderMap.keySet()) {
      agentMap.put(id, new Agent2DView(id, agentDataHolderMap.get(id)));
      agentMap.get(id).createMesh();
    }

    map.createMesh();
    shader.create();
  }

  @Override
  public void updateAgent(int id, String direction, String state){}

  public void updateAgent(int id, String direction, String state, boolean isAttack)
      throws IOException {
    agentMap.get(id).update(direction, state);
    if (agentDataHolderMap.get(id).getSpawnerDict().containsKey(state)) // will spawn new agents
    {
      Vector3f parentPosition = new Vector3f(agentMap.get(id).getCenterPosition(), 0f);
      String parentDirection = agentMap.get(id).getCurrentDirection();
      Agent2DDataHolder newAgentData = positionNewAgent(
          agentDataHolderMap.get(id).getSpawnerDict().get(state),
          parentPosition, parentDirection);
      if (box.canMove(parentPosition, newAgentData.getPosition())) {
        if (newAgentData.isBullet()) {
          int newId = getNextBulletId();
          bulletMap.put(newId, new Agent2DView(newId, newAgentData));
        } else {
          int newId = getNextAgentId();
          agentDataHolderMap.put(newId, newAgentData);
          agentMap.put(newId, new Agent2DView(newId, newAgentData));
        }
      }
    }

    if (isAttack){

    }
  }

  public void updateBullets(){
    for (int key : bulletMap.keySet()) {
      //check if hit the agent or wall
    }
  }

  private Agent2DDataHolder positionNewAgent(Agent2DDataHolder data, Vector3f parentPosition,
      String parentDirection) {
    float MOVEMENT_DELTA = 1.5f;
    Agent2DDataHolder newAgentData = new Agent2DDataHolder(data);
    if (newAgentData.getInitialDirection().equals(GenerateAgentsData.getDirectionPlaceholder())) {
      newAgentData.setInitialDirection(parentDirection);
    }
    newAgentData.setPosition(Vector3f.add(parentPosition,
        Asset2D.convertDirectionalSpeed(newAgentData.getInitialDirection(), MOVEMENT_DELTA)));
    return newAgentData;
  }

  private int getNextAgentId() {
    int idx = 0;
    for (int key : agentDataHolderMap.keySet()) {
      idx = Math.max(key, idx);
    }
    return idx + 1;
  }

  private int getNextBulletId() {
    int idx = 0;
    for (int key : bulletMap.keySet()) {
      idx = Math.max(key, idx);
    }
    return idx + 1;
  }

  @Override
  public void updateWindow() {
    if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
      window.setFullscreen(!window.isFullscreen());
    }
    window.update();
  }

  @Override
  public void renderAgents() { // get rid of dead agents
    for (int id : agentMap.keySet()) {
      agentMap.get(id).renderMesh(renderer);
    }
  }

  public void renderBullets(){
    for (int id : bulletMap.keySet()) {
      bulletMap.get(id).renderMesh(renderer);
    }
  }

  @Override
  public void deleteAgent(int id) {
    agentMap.get(id).destroyMesh();
    agentMap.remove(id);
  }

  @Override
  public void renderMap() {
    map.renderMesh(renderer);
  }

  @Override
  public void renderWindow() {
    window.swapBuffers();
  }

  @Override
  public void updateMap() {
  }

  @Override
  public void closeWindow() {
    window.destroy();

    for (int key : agentMap.keySet()) {
      agentMap.get(key).destroyMesh();
    }

    map.destroyMesh();
    shader.destroy();
  }

  @Override
  public boolean shouldWindowClose() {
    return window.shouldClose() || Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE);
  }

  @Override
  public boolean isKeyDown(int key) {
    return Input.isKeyDown(key);
  }
}
