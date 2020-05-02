package ooga.view.test.lwjgl.test3d;


import java.io.IOException;
import ooga.view.game_view.game_state.state3d.GameState3DView;

public class test3DMainView implements Runnable {

  public Thread game;
  public GameState3DView view;

  public static void main(String[] args) {
    new test3DMainView().start();
  }

  public void start() {
    game = new Thread(this, "game");
    game.start();
  }

  public void init() throws IOException {
    view = new GameState3DView();
    view.createWindow();
  }

  public void run() {
    try {
      init();
    } catch (IOException e) {
      System.out.println("Exception in game initialization");
    }
    while (!view.shouldWindowClose()) {
      try {
        update();
      } catch (IOException e) {
        System.out.println("Exception in game scene update");
      }
      try {
        render();
      } catch (IOException e) {
        System.out.println("Exception in game scene rendering");
      }
    }
    close();
  }

  private void update() throws IOException {
    view.updateWindow();
    view.updateAll();
  }

  private void render() throws IOException {
    view.renderAll();
    // can also render separately, renderWindow() is mandatory
  }

  private void close() {
    view.closeWindow();
  }
}