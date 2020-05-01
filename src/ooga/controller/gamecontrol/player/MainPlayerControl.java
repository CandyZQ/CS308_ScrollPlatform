package ooga.controller.gamecontrol.player;

import java.util.Map;
import ooga.controller.gamecontrol.PlayerControlInterface;
import ooga.game.GameZelda2DSingle;
import ooga.model.interfaces.movables.Movable1D;

/**
 * Main Player Control, holds and fascillitate the selection of different type of
 * individual player control, encapsulates what happens in the individual player controls
 *
 * @author Lucy
 */
public class MainPlayerControl implements PlayerControlInterface {
  private PlayerControlInterface myPlayerControl;
  private PlayerControlFactory myPlayerControlFactory;

  /**
   * Creates the control factory
   */
  public MainPlayerControl(){
    myPlayerControlFactory = new PlayerControlFactory();
  }

  /**
   * Select a control using the game type provided
   * @param gameType determines which player control to use
   */
  public void setControl(int gameType){
    myPlayerControl= myPlayerControlFactory.selectControl(gameType);
  }


  /**
   * Takes in a player and pass it onto individual player control
   * @param player backend player
   */
  @Override
  public void setMyPlayer(Movable1D player) {
    myPlayerControl.setMyPlayer(player);
  }

  /**
   * Takes in the keymap from data loading and pass it to player control
   * @param map key map
   */
  @Override
  public void setNewKeyMap(Map<Integer, String> map){
    myPlayerControl.setNewKeyMap(map);
  }

  /**
   *
   * @return player of this control
   */
  @Override
  public Movable1D getPlayer() {
    return myPlayerControl.getPlayer();
  }

  /**
   * checks if the game is finished
   * @param score goal
   * @return if the goal has been reached
   */
  @Override
  public boolean checkScore(int score) {
    return myPlayerControl.checkScore(score);
  }

  /**
   * Updates every frame to check if the player died
   * @return boolean that contains the information
   */
  @Override
  public boolean update() {
    return myPlayerControl.update();
  }

  /**
   * ask control to set id based on its player
   */
  @Override
  public void setID() {
    myPlayerControl.setID();
  }

  /**
   *
   * @return the id of this control
   */
  @Override
  public int getID() {
    return myPlayerControl.getID();
  }

  /**
   * Denotes what happens when keyboard is not pressed
   */
  @Override
  public void keyReleased() {
    myPlayerControl.keyReleased();
  }

  /**
   * Denotes how to update backend when keyboard is pressed
   */
  @Override
  public void updateKey(){
    myPlayerControl.updateKey();
  }

  /**
   * Pass the front end game view to the individual control
   * @param view front end game view
   */
  @Override
  public void setView(GameZelda2DSingle view) {
    myPlayerControl.setView(view);
  }

  /**
   *
   * @return if the player has won
   */
  @Override
  public boolean hasWon() {
    return myPlayerControl.hasWon();
  }

  /**
   * hurt the player
   */
  @Override
  public void getHurt() {
    myPlayerControl.getHurt();
  }

  /**
   *
   * @return of the player is hurt
   */
  @Override
  public boolean isHurt() {
    return myPlayerControl.isHurt();
  }
}