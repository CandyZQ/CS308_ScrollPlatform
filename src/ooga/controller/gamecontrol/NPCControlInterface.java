package ooga.controller.gamecontrol;

import ooga.controller.ZeldaControlInterface;
import ooga.game.GameZelda2DSingle;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.interfaces.movables.Movable1D;

public interface NPCControlInterface extends ZeldaControlInterface {
  void setMyNPC(Movable1D NPC);

  void setID();

  void setView(GameZelda2DSingle view);

  int getID();

  void attack();

  void getHurt(ZeldaPlayer player);

  boolean isHurt();

  ZeldaCharacter getCharacter();
}
