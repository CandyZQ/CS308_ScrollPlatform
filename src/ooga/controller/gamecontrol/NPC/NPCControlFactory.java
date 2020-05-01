package ooga.controller.gamecontrol.NPC;

import ooga.controller.gamecontrol.NPCControlInterface;

import java.util.HashMap;
import java.util.Map;
import ooga.model.enums.backend.CharacterType;

/**
 * Helps facillitate the selection of different NPC controls
 *
 * @author Lucy
 */
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

  /**
   * Return a NPC control depending on the type asked for by the control
   * @param type identifies which control is needed
   * @return NPC control
   */
  public NPCControlInterface selectControl(int type){
    if(!controlMap.containsKey(type)) return null; //throw exception
    return controlMap.get(type);
  }
}
