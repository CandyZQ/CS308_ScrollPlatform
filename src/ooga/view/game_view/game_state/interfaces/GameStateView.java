package ooga.view.game_view.game_state.interfaces;

import java.io.IOException;
import ooga.view.engine.io.Input;
import ooga.view.engine.io.Window;
import org.lwjgl.glfw.GLFW;

abstract public class GameStateView {

  protected Window window;

  public GameStateView(){}

  abstract public void createWindow() throws IOException;

  abstract public void updateAgent(int id, String direction, String state) throws IOException;

  abstract public void deleteAgent(int id);

  abstract public void updateMap();

  public void renderAll() throws IOException {
    renderMap();
    renderAgents();
    renderWindow();
  }

  abstract public void renderAgents() throws IOException;

  abstract public void renderMap();

  public void renderWindow() {
    window.swapBuffers();
  }

  abstract public void closeWindow();

  public void updateWindow() {
    if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
      window.setFullscreen(!window.isFullscreen());
    }
    window.update();
  }

  public boolean shouldWindowClose() {
    return window.shouldClose() || Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE);
  }

  public boolean isKeyDown(int key) {
    return Input.isKeyDown(key);
  }

}
