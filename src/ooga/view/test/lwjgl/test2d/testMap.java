/*
package ooga.view.test.lwjgl.test2d;


import java.io.IOException;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.graphics.Shader;
import ooga.view.engine.io.Input;
import ooga.view.engine.io.Window;
import ooga.view.game_view.map.map2d.Map2DView;
import org.lwjgl.glfw.GLFW;

public class testMap implements Runnable {

  public Thread game;
  public Window window;
  public Renderer2D renderer;
  public Shader shader;
  public final int WIDTH = 1080, HEIGHT = 1080;

  public Map2DView mapView;
  public String mapPath = "/view/textures/2d/cyberpunk/map/map.txt";

  public void start() {
    game = new Thread(this, "game");
    game.start();
  }

  public void init() throws IOException {
    window = new Window(WIDTH, HEIGHT, "Game");
    shader = new Shader("/view/shaders/2d/cyberpunkTitleVertex.glsl", "/view/shaders/2d/cyberpunkTitleFragment.glsl");
    renderer = new Renderer2D(shader);
    //TitleCropper cropit = new TitleCropper();
    //GenerateCroppedSprites cropit = new GenerateCroppedSprites();
    //GenerateMergedSprites mergeit = new GenerateMergedSprites();
    window.setBackgroundColor(22.0f/255.0f, 23.0f/255.0f, 25.0f/255.0f);
    window.create();
    mapView = new Map2DView(mapPath);
    mapView.createMesh();
    shader.create();
  }

  public void run() {
    try {
      init();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
      update();
      render();
      if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
        window.setFullscreen(!window.isFullscreen());
      }
    }
    close();
  }

  private void update() {
    window.update();
    //object.update();
    if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
      System.out.println("X: " + Input.getScrollX() + ", Y: " + Input.getScrollY());
    }
  }

  private void render() {
    mapView.renderMesh(renderer);
    window.swapBuffers();
  }

  private void close() {
    window.destroy();
    mapView.destroyMesh();
    shader.destroy();
  }

  public static void main(String[] args) {
    new testMap().start();
  }
}*/