package ooga.controller.gamecontrol;

import ooga.controller.ZeldaControlInterface;
import ooga.game.GameZelda2DSingle;
import ooga.model.interfaces.movables.Movable1D;

public interface NPCControlInterface extends ZeldaControlInterface {
    void setMyNPC(Movable1D NPC);

  void setID();

  void setView(GameZelda2DSingle view);

  int getID();

  void attack();
}
