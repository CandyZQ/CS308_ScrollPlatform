package ooga.view.test.lwjgl.test2d;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import ooga.view.engine.utils.cyberpunk2d.GenerateAgentsData;
import ooga.view.game_view.agent.agent2d.Agent2DDataHolder;
import ooga.view.game_view.game_state.state2d.GameState2DView;
import org.lwjgl.glfw.GLFW;

public class testMainView implements Runnable {

  public Thread game;
  public GameState2DView view;

  public void start() {
    game = new Thread(this, "game");
    game.start();
  }

  public void init() throws IOException {
    Map<Integer, Agent2DDataHolder> dataHolderMap = new HashMap<>();
    dataHolderMap.put(0, GenerateAgentsData.createSoldier(0f, 0f));
   view = new GameState2DView(dataHolderMap);
   view.createWindow();
  }

  public void run() {
    try {
      init();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (!view.shouldWindowClose()) {
     update();
     render();
    }
    close();
  }

  private void update() {
    view.updateWindow();
    view.updateMap(); //empty method
    if (view.isKeyDown(GLFW.GLFW_KEY_S)){
      view.updateAgent(0,"E","SPRINT");}
  }

  private void render() {
    view.renderAll();
    // can also render separately, renderWindow() is mandatory
  }

  private void close() {
    view.closeWindow();
  }

  public static void main(String[] args) {
    new testMainView().start();
  }
}