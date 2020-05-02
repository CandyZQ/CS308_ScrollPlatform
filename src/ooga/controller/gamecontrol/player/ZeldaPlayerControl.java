package ooga.controller.gamecontrol.player;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import ooga.controller.gamecontrol.PlayerControlInterface;
import ooga.controller.gamecontrol.playerInterface.AttackerControl;
import ooga.controller.gamecontrol.playerInterface.MovableControll2D;
import ooga.game.GameZelda2DSingle;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.MovingState;
import ooga.model.interfaces.movables.Movable1D;

public class ZeldaPlayerControl implements PlayerControlInterface, MovableControll2D,
    AttackerControl, PropertyChangeListener {

  public static final String PROPERTY_STATE = "state";
  public static final String PROPERTY_MOVING_DIRECTION = "direction";

  private ZeldaPlayer myPlayer;
  private GameZelda2DSingle myView;
  private Map<Integer, String> myGLFWMap = new HashMap<>();
  private int myID;

  private int hurtCount;


  public ZeldaPlayerControl() {
//    //keycode map hard code
//    myKeyCodeMap.put(KeyCode.LEFT, "left");
//    myKeyCodeMap.put(KeyCode.RIGHT, "right");
//    myKeyCodeMap.put(KeyCode.UP, "up");
//    myKeyCodeMap.put(KeyCode.DOWN, "down");
//    myKeyCodeMap.put(KeyCode.Q, "attack0");
//    myKeyCodeMap.put(KeyCode.W, "attack1");
//
//    //glfw map hard code
//    myGLFWMap.put(GLFW.GLFW_KEY_LEFT, "left");
//    myGLFWMap.put(GLFW.GLFW_KEY_RIGHT, "right");
//    myGLFWMap.put(GLFW.GLFW_KEY_UP, "up");
//    myGLFWMap.put(GLFW.GLFW_KEY_DOWN, "down");
//    myGLFWMap.put(GLFW.GLFW_KEY_Q, "attack0");
//    myGLFWMap.put(GLFW.GLFW_KEY_W, "attack1");

  }

  @Override
  public void setMyPlayer(Movable1D myPlayer) {
    this.myPlayer = (ZeldaPlayer) myPlayer;
    this.myPlayer.addListener(this);
  }

  @Override
  public void setID() {
    myID = myPlayer.getId();
  }

  @Override
  public int getID() {
    return myID;
  }

  @Override
  public void keyReleased() {
    myPlayer.setState(MovingState.IDLE);
  }

  @Override
  public void up() {
    myPlayer.setState(MovingState.SPRINT);
    myPlayer.setDirection(Direction.N);
  }

  @Override
  public void down() {
    myPlayer.setState(MovingState.SPRINT);
    myPlayer.setDirection(Direction.S);
  }

  @Override
  public void left() {
    myPlayer.setState(MovingState.SPRINT);
    myPlayer.setDirection(Direction.W);
  }

  @Override
  public void right() {
    myPlayer.setState(MovingState.SPRINT);
    myPlayer.setDirection(Direction.E);
  }

  @Override
  public void attack0() {
    myPlayer.setState(MovingState.ATTACK1);
  }

  @Override
  public void attack1() {
    myPlayer.setState(MovingState.ATTACK1);
  }

  @Override
  public void attack2() {
    myPlayer.setState(MovingState.ATTACK3);
  }

  public void death() {
    myPlayer.setState(MovingState.DEATH);
    myPlayer.setDirection(Direction.E);
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String s = evt.getPropertyName();
//    System.out.println(s);
    switch (s) {
      case PROPERTY_STATE:
      case PROPERTY_MOVING_DIRECTION:
        myView
            .updateCharacter(myID, myPlayer.getDirection().toString(),
                myPlayer.getState().toString(), (myPlayer.getState() == MovingState.ATTACK1
                    || myPlayer.getState() == MovingState.ATTACK3));
    }
  }

  @Override
  public void updateKey() {
    boolean keyPressed = false;
    try {
      for (int i : myGLFWMap.keySet()) {
        if (myView.getView().isKeyDown(i)) {
          keyPressed = true;
          this.getClass().getDeclaredMethod(myGLFWMap.get(i)).invoke(this);
//          System.out.println(myPlayer.getDirection().toString());
//          System.out.println(myPlayer.getState().toString());
          break;
        }
      }

      if (!keyPressed) {
        keyReleased();
      }
    } catch (Exception e) {
      System.out.println("key map fault");
    }
  }

  /**
   * set the game view to input
   * @param view
   */
  @Override
  public void setView(GameZelda2DSingle view) {
    myView = view;
  }

  /**
   * set the key map to the map read in loader
   * @param map input map
   */
  @Override
  public void setNewKeyMap(Map<Integer, String> map) {
    if (map != null) {
      myGLFWMap = map;
    }
  }

  /**
   *
   * @return the player
   */
  @Override
  public Movable1D getPlayer() {
    return myPlayer;
  }

  /**
   * checks if player's score has exceeded goal
   * @param score score
   * @return true if player score > goal
   */
  @Override
  public boolean checkScore(int score) {
    return score <= myPlayer.getScore();
  }

  /**
   * Return false if dead
   *
   * @return
   */
  @Override
  public boolean update() {
//    if (myPlayer.getState() == MovingState.SPRINT) {
//      myPlayer.addScore(10);
//    }

    if (!myPlayer.isAlive()) {
      death();
      return false;
    }

    return true;
  }

  @Override
  public boolean hasWon() {
    return myPlayer.hasWon();
  }

  @Override
  public void getHurt() {
    myPlayer.setState(MovingState.HURT);
    myPlayer.subtractHP(1);
  }

  @Override
  public boolean isHurt() {
    if (myPlayer.getState() == MovingState.HURT && hurtCount > 200){
      myPlayer.setState(MovingState.IDLE);
      hurtCount = 0;
      return false;
    } else if (myPlayer.getState() == MovingState.HURT) {
      hurtCount ++;
      return true;
    }
//    System.out.println("Hurt: " + hurtCount);
    return false;
  }
}
