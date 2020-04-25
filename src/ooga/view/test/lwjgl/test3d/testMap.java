package ooga.view.test.lwjgl.test3d;


import java.io.IOException;
import ooga.view.engine.graphics.Shader;
import ooga.view.engine.graphics.render.Renderer3D;
import ooga.view.engine.io.Input;
import ooga.view.engine.io.Window;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.Camera;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.Test;
import ooga.view.game_view.map.map3d.Map3DView;
import org.lwjgl.glfw.GLFW;

public class testMap implements Runnable {

  public Thread game;
  public Window window;
  public Renderer3D renderer;
  public Shader shader;
  public final int WIDTH = 1580, HEIGHT = 1080;

  public Map3DView mapView;
  public String mapPath = "/view/data/3d/map_test.txt";
  public Camera camera = new Camera(new Vector3f(1640.0f, 880.0f, 40.0f), new Vector3f(0, 0, 0));

  public void start() {
    game = new Thread(this, "game");
    game.start();
  }

  public void init() throws IOException {
    window = new Window(WIDTH, HEIGHT, "Game");
    shader = new Shader("/view/shaders/3d/mainVertex.glsl", "/view/shaders/2d/mainFragment.glsl");
    renderer = new Renderer3D(window, shader);
    window.setBackgroundColor(22.0f/255.0f, 23.0f/255.0f, 25.0f/255.0f);
    window.create();
    mapView = new Map3DView(mapPath);
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
    close();
  }

  private void update() {
    window.update();
    //object.update();
    camera.update();
    Test.printVector3f(camera.getPosition());
    if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
      System.out.println("X: " + Input.getScrollX() + ", Y: " + Input.getScrollY());
    }
  }

  private void render() {
    mapView.renderMesh(renderer, camera);
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


}