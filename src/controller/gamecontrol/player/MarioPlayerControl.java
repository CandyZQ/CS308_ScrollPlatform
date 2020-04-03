package controller.gamecontrol.player;

import controller.gamecontrol.JumpableControl;
import controller.gamecontrol.MovableControll1D;
import controller.gamecontrol.PlayerControlInterface;
import javafx.scene.input.KeyCode;
import ooga.model.MarioCharacter;
import ooga.model.interfaces.movement.Movable1D;

public class MarioPlayerControl implements PlayerControlInterface, MovableControll1D, JumpableControl {

  private MarioCharacter myPlayer;

  public MarioPlayerControl(){

  }

  @Override
  public void setMyPlayer(Movable1D myPlayer) {
    this.myPlayer = (MarioCharacter)myPlayer;
  }

  @Override
  public void keyInput(KeyCode key) {
    switch (key){
        case LEFT: left(1); break;
        case RIGHT: right(1); break;
        case UP: jump(); break;
    }
  }

  @Override
  public void update() {

  }

  @Override
  public void jump() {
    myPlayer.jump();
  }

  @Override
  public void left(double deltaX) {
    myPlayer.moveInX(-1*deltaX);
  }

  @Override
  public void right(double deltaX) {
    myPlayer.moveInX(deltaX);
  }
}
