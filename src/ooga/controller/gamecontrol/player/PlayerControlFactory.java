package ooga.controller.gamecontrol.player;

import ooga.controller.gamecontrol.PlayerControlInterface;

import java.util.*;

public class PlayerControlFactory {


  private Map<Integer, PlayerControlInterface> controlMap = new HashMap<>();

  /**
   * Originally created since we might be using mario players, which have a
   * different backend class (since it has very different behavior for, for example,
   * jump)
   */
  public PlayerControlFactory(){
    fillMap();
  }

  private void fillMap(){
    controlMap.put(1, new ZeldaPlayerControl());
//    controlMap.put(0, new MarioPlayerControl());
  }

  /**
   * sets player based on the type of player provided
   * @param type integer that tells which type of game we're playing
   * @return a lower level control specific for this player type
   */
  public PlayerControlInterface selectControl(int type){
    if(!controlMap.containsKey(type)) return null; //throw exception
    return controlMap.get(type);
  }
}
