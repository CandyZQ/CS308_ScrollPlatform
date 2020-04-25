package ooga.controller.gamecontrol.player;

import java.util.Map;
import ooga.controller.gamecontrol.PlayerControlInterface;
import ooga.game.GameZelda2DSingle;
import ooga.model.interfaces.movables.Movable1D;

public class MainPlayerControl implements PlayerControlInterface {
  private PlayerControlInterface myPlayerControl;
  private PlayerControlFactory myPlayerControlFactory;

  /**
   * Creates the control factory
   */
  public MainPlayerControl(){
    myPlayerControlFactory = new PlayerControlFactory();
  }

  public void setControl(int gameType){
    myPlayerControl= myPlayerControlFactory.selectControl(gameType);
  }


  @Override
  public void setMyPlayer(Movable1D player) {
    myPlayerControl.setMyPlayer(player);
  }

  @Override
  public void setNewKeyMap(Map<Integer, String> map){
    myPlayerControl.setNewKeyMap(map);
  }

  @Override
  public Movable1D getPlayer() {
    return myPlayerControl.getPlayer();
  }

  @Override
  public boolean checkScore(int score) {
    return myPlayerControl.checkScore(score);
  }

  @Override
  public boolean update() {
    return myPlayerControl.update();
  }

  @Override
  public void setID() {
    myPlayerControl.setID();
  }

  @Override
  public int getID() {
    return myPlayerControl.getID();
  }

  @Override
  public void keyReleased() {
    myPlayerControl.keyReleased();
  }

  @Override
  public void updateKey(){
    myPlayerControl.updateKey();
  }

  @Override
  public void setView(GameZelda2DSingle view) {
    myPlayerControl.setView(view);
  }

  @Override
  public boolean hasWon() {
    return myPlayerControl.hasWon();
  }

  @Override
  public void getHurt() {
    myPlayerControl.getHurt();
  }

  @Override
  public boolean isHurt() {
    return myPlayerControl.isHurt();
  }
}