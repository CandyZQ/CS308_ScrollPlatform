package ooga.controller.gamecontrol.NPC;

import ooga.controller.gamecontrol.NPCControlInterface;

import java.util.HashMap;
import java.util.Map;
import ooga.model.enums.backend.CharacterType;

public class NPCControlFactory {


  private Map<Integer, NPCControlInterface> controlMap = new HashMap<>();

  public NPCControlFactory(){
    fillMap();
  }

  private void fillMap(){
    controlMap.put(CharacterType.LOADSOLDIER.getIndex(), new SoldierControl());
    controlMap.put(CharacterType.BIGBOY.getIndex(), new BigBoyControl());
    controlMap.put(CharacterType.ENGINEERBOT.getIndex(), new EngineerBotControl());
    controlMap.put(CharacterType.SHIELD.getIndex(), new ShieldControl());
//    controlMap.put(0, new MarioNPCControl());
  }

  public NPCControlInterface selectControl(int type){
    if(!controlMap.containsKey(type)) return null; //throw exception
    return controlMap.get(type);
  }
}
