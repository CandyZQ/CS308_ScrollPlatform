package ooga.controller.gamecontrol.NPC;

import static ooga.controller.gamecontrol.player.ZeldaPlayerControl.PROPERTY_MOVING_DIRECTION;
import static ooga.controller.gamecontrol.player.ZeldaPlayerControl.PROPERTY_STATE;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Random;
import ooga.controller.gamecontrol.NPCControlInterface;
import ooga.game.GameZelda2DSingle;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.MovingState;
import ooga.model.interfaces.movables.Movable1D;

public class NPCSubControl implements NPCControlInterface, PropertyChangeListener {

  protected ZeldaCharacter myNPC;
  private int myID;
  private GameZelda2DSingle myView;
  private int attackCounter = 0;
  private int hurtCount = 0;

  public NPCSubControl() {
  }

  public void setMyNPC(Movable1D myNPC) {
    this.myNPC = (ZeldaCharacter) myNPC;
    this.myNPC.addListener(this);
  }

  @Override
  public void setID() {
    myID = myNPC.getId();
  }

  public int getID() {
    return myID;
  }

  @Override
  public void attack() {
    myNPC.setState(MovingState.ATTACK);
  }

  @Override
  public void getHurt() {
    myNPC.setState(MovingState.HURT);
  }

  @Override
  public boolean isHurt() {
    if (myNPC.getState() == MovingState.ATTACK && hurtCount > 200) {
      myNPC.setState(MovingState.IDLE);
      hurtCount = 0;
      return false;
    } else if (myNPC.getState() == MovingState.ATTACK1) {
      hurtCount++;
      return true;
    }
//    System.out.println("Hurt: " + hurtCount);
    return false;
  }

  public void setView(GameZelda2DSingle view) {
    myView = view;
  }

  public Movable1D getNPC() {
    return myNPC;
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    String s = evt.getPropertyName();
    switch (s) {
      case PROPERTY_STATE:
      case PROPERTY_MOVING_DIRECTION:
//        System.out.println("notified");
        myView
            .updateCharacter(myID, myNPC.getDirection().toString(),
                myNPC.getState().toString(), (myNPC.getState() == MovingState.ATTACK));
    }
  }

  public void death() {
    myNPC.setState(MovingState.DEATH);
    myNPC.setDirection(Direction.E);
  }

  @Override
  public void update() {
//    System.out.println(myNPC.getId()+ ": " + myNPC.getState());
//    System.out.println(attackCounter);
    if (!myNPC.isAlive()) {
      myNPC.setState(MovingState.DEATH);
    }

    if (isAttacking()) {
      if (attackCounter > 500) {
        myNPC.setState(MovingState.IDLE);
        attackCounter = 0;
      } else {
        attackCounter++;
      }
      return;
    }

    randomMove();
  }

    private void randomMove() {
      if (myNPC.getState() == MovingState.IDLE) {
        if (new Random().nextInt(2) == 0) {
          switch (new Random().nextInt(2)) {
            case 0:
              myNPC.setDirection(Direction.E);
              break;
            case 1:
              myNPC.setDirection(Direction.E);
          }
          myNPC.setState(MovingState.WALK);
        }
      }
    }

    protected boolean isAttacking () {
      return myNPC.getState() != MovingState.IDLE;
    }
  }
