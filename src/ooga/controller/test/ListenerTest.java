//package ooga.controller.test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import javafx.scene.input.KeyCode;
//import ooga.controller.gamecontrol.player.ZeldaPlayerControl;
//import ooga.model.characters.ZeldaPlayer;
//import ooga.model.enums.backend.Direction;
//import ooga.model.enums.backend.MovingState;
//import ooga.view.engine.graphics.Renderer2D;
//import ooga.view.engine.graphics.Shader;
//import ooga.view.engine.io.Input;
//import ooga.view.engine.io.Window;
//import ooga.view.game_view.agent.playable.player2d.Player2DView;
//import ooga.view.test.lwjgl.testAgent;
//import org.junit.jupiter.api.Test;
//import org.lwjgl.glfw.GLFW;
//
//public class ListenerTest implements Runnable{
//
//  public Thread game;
//  public Window window;
//  public Renderer2D renderer;
//  public Shader shader;
//  public final int WIDTH = 1080, HEIGHT = 1080;
//
//  public Player2DView agentView;
//  public ZeldaPlayer player;
//  public ZeldaPlayerControl control;
//  public String mapPath = "/view/textures/2d/cyberpunk/map/map.txt";
//
//  public void start() {
//    game = new Thread(this, "game");
//    game.start();
//  }
//
//  public void run() {
//    try {
//      init();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    while (!window.shouldClose() && !Input.isKeyDown(GLFW.GLFW_KEY_ESCAPE)) {
//      update();
//      render();
//      if (Input.isKeyDown(GLFW.GLFW_KEY_F11)) {
//        window.setFullscreen(!window.isFullscreen());
//      }
//    }
//    close();
//  }
//
//  private void update() {
//    window.update();
//    //agentView.update();
//    if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
//      System.out.println("X: " + Input.getScrollX() + ", Y: " + Input.getScrollY());
//    }
//    if (Input.isKeyDown(GLFW.GLFW_KEY_Q)){
//      player.setState(MovingState.ATTACK1);
////      agentView.update("E","ATTACK1");
//    }
//    if (Input.isKeyDown(GLFW.GLFW_KEY_W)){
//      agentView.update("E","ATTACK3");
//    }
//    if (Input.isKeyDown(GLFW.GLFW_KEY_D)){
//      agentView.update("E","DEATH");
//    }
//    if (Input.isKeyDown(GLFW.GLFW_KEY_S)){
//      agentView.update("S","SPRINT");
//    }
//    if (Input.isKeyDown(GLFW.GLFW_KEY_N)){
//      agentView.update("N","SPRINT");
//    }
//    if (Input.isKeyDown(GLFW.GLFW_KEY_O)){
//      agentView.update("SE","SPRINT");
//    }
//  }
//
//  private void render() {
//    agentView.renderMesh(renderer);
//    window.swapBuffers();
//  }
//
//  private void close() {
//    window.destroy();
//    agentView.destroyMesh();
//    shader.destroy();
//  }
//
//  public void init() throws IOException {
//    //ResizeSprites.createCanvas();
//    //ResizeSprites.resizeAll();
//
//    window = new Window(WIDTH, HEIGHT, "Game");
//    shader = new Shader("/view/shaders/2d/cyberpunkTitleVertex.glsl", "/view/shaders/2d/cyberpunkTitleFragment.glsl");
//    renderer = new Renderer2D(shader);
//    window.setBackgroundColor(22.0f/255.0f, 23.0f/255.0f, 25.0f/255.0f);
//    window.create();
//    agentView = new Player2DView();
//    agentView.createMesh();
//    shader.create();
//
//    player = new ZeldaPlayer(100,0);
//    control = new ZeldaPlayerControl();
//    control.setMyPlayer(player);
//    control.setPlayerView(agentView);
//  }
//
//  @Test
//  public static void main(String[] args) {
//    new testAgent().start();
//  }
//}
