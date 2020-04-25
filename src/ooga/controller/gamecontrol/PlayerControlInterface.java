package ooga.controller.gamecontrol;

import java.util.Map;
import ooga.game.GameZelda2DSingle;
import ooga.model.interfaces.movables.Movable1D;

public interface PlayerControlInterface{
//  void keyInput(KeyCode key) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException;
  void setMyPlayer(Movable1D player);
//  void setKeyCodeMap(Map<KeyCode, String> map);
  void setID();
  int getID();
  void keyReleased();
  void updateKey();
  void setView(GameZelda2DSingle view);

    void setNewKeyMap(Map<Integer, String> map);

  Movable1D getPlayer();

  boolean checkScore(int score);

  boolean update();

  boolean hasWon();

  void getHurt();

  boolean isHurt();
}
