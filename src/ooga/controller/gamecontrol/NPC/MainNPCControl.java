package ooga.controller.gamecontrol.NPC;

import ooga.controller.gamecontrol.NPCControlInterface;
import ooga.game.GameZelda2DSingle;
import ooga.model.interfaces.movables.Movable1D;

public class MainNPCControl implements NPCControlInterface {
  private NPCControlInterface myNPCControl;
  private NPCControlFactory myNPCControlFactory;

  public MainNPCControl(){
    myNPCControlFactory = new NPCControlFactory();
  }

  public  void setControl(int gameType){
    myNPCControl= myNPCControlFactory.selectControl(gameType);
  }


  @Override
  public void update() {
    myNPCControl.update();
  }

  @Override
  public void setMyNPC(Movable1D NPC) {
    myNPCControl.setMyNPC(NPC);
  }

  public void setID() {
    myNPCControl.setID();
  }

  @Override
  public void setView(GameZelda2DSingle view) {
    myNPCControl.setView(view);
  }

  public int getID() {
    return myNPCControl.getID();
  }

  public void attack() {
    myNPCControl.attack();
  }

  @Override
  public void getHurt() {
    myNPCControl.getHurt();
  }

  @Override
  public boolean isHurt() {
    return myNPCControl.isHurt();
  }
}