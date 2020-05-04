package ooga.view.game_view.game_state.interfaces;

import java.io.IOException;
import ooga.view.engine.io.Input;
import ooga.view.engine.io.Window;
import org.lwjgl.glfw.GLFW;

/**
 * an abstract class that structure the framework of game state view,
 * 1) allows the generation and rendering of opengl window
 * 2) updates the characters and map
 * @author qiaoyi fang
 */
abstract public class GameStateView {

  protected Window window;

  public GameStateView(){}

  /**
   * creates the window
   * @throws IOException
   */
  abstract public void createWindow() throws IOException;

  /**
   * updates the agent
   * @param id id of the agent
   * @param direction the new direction
   * @param state the new state
   * @throws IOException
   */
  abstract public void updateAgent(int id, String direction, String state) throws IOException;

  /**
   * deletes the agent
   * @param id id
   */
  abstract public void deleteAgent(int id);

  /**
   * updates the map
   */
  abstract public void updateMap();

  /**
   * render all components in the opengl window (map, agents, window)
   * @throws IOException
   */
  public void renderAll() throws IOException {
    renderMap();
    renderAgents();
    renderWindow();
  }

  /**
   * render all the agents
   * @throws IOException
   */
  abstract public void renderAgents() throws IOException;

  /**
   * render the map
   */
  abstract public void renderMap();

  /**
   * render the window
   */
  public void renderWindow() {
    window.swapBuffers();
  }

  /**
   * close the window
   */
  abstract public void closeWindow();

  /**
   * update the window
   */
  public void updateWindow() {
    if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
      window.setFullscreen(!window.isFullscreen());
    }
    window.update();
  }

  /**
   * returns true if the window should close
   * @return
   */
  public boolean shouldWindowClose() {
    return window.shouldClose() || Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE);
  }

  /**
   * returns if the key is pressed
   * @param key key
   * @return
   */
  public boolean isKeyDown(int key) {
    return Input.isKeyDown(key);
  }

}
