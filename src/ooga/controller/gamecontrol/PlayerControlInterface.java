package ooga.controller.gamecontrol;

import java.util.Map;
import ooga.game.GameZelda2DSingle;
import ooga.model.interfaces.movables.Movable1D;

/**
 * Basic functions of a player control
 *
 * @author Lucy
 */
public interface PlayerControlInterface{

  /**
   * Set the player held in this control
   * @param player backend player model
   */
  void setMyPlayer(Movable1D player);

  /**
   * Set the id of control to be the same as player id
   */
  void setID();

  /**
   * get player or control id (same)
   * @return control id
   */
  int getID();

  /**
   * Set player to idle state when no key is pressed
   */
  void keyReleased();

  /**
   * Update the player backend and frontend depending on what key is pressed on keyboard
   */
  void updateKey();

  /**
   * Store the front end view in order to be used to update frontend when key pressed
   * @param view front end view
   */
  void setView(GameZelda2DSingle view);

    void setNewKeyMap(Map<Integer, String> map);

  /**
   *
   * @return player held by this control
   */
  Movable1D getPlayer();

  /**
   * Checks if the score of the player in this control exceeded goal
   * @param score goal score
   * @return true if score > goal
   */
  boolean checkScore(int score);

  /**
   * updates each frame and checks if the plaeyr died
   * @return if the player died
   */
  boolean update();

  /**
   *
   * @return if the player in this control has won the game
   */
  boolean hasWon();

  /**
   * Set the state of the player held by this control to hurt and subtract hp
   */
  void getHurt();

  /**
   *
   * @return whether the player contained in this control is hurt or not
   */
  boolean isHurt();
}
