package ooga.view.game_view.game_state.interfaces;

import java.io.IOException;

abstract public class GameStateView {

  public GameStateView(){}

  abstract public void createWindow() throws IOException;

  abstract public void updateAgent(int id, String direction, String state) throws IOException;

  abstract public void deleteAgent(int id);

  abstract public void updateMap();

  abstract public void updateWindow();

  public void renderAll(){
    renderMap();
    renderAgents();
    renderWindow();
  }

  abstract public void renderAgents();

  abstract public void renderMap();

  abstract public void renderWindow();

  abstract public void closeWindow();

  abstract public boolean shouldWindowClose();

  abstract public boolean isKeyDown(int key);

}
