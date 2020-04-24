package ooga.view.game_view.game_state.state2d;

import java.util.Map;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.game_view.agent.agent2d.Agent2DView;
import ooga.view.game_view.map.map2d.Map2DView;

public class BoundingBox  {
  private Map2DView map;
  private Map<Integer, Agent2DView> agents;
  private static final double MELEE_BOT_HEIGHT = 0.2;
  private static final double MELEE_BOT_WIDTH = 0.1;

  public BoundingBox(Map2DView map, Map<Integer, Agent2DView> agents, Map<Integer, Agent2DView> bullets){
    this.map = map;
    this.agents = agents;
  }

  public boolean canMove(Vector3f currentPosition, Vector3f nextPosition){
    return true;
  }

  public boolean isAttackEffective(Vector3f position, String direction){
    return true;
  }

  public boolean isAgentDirection(Vector3f agentX, Vector3f agentY, String direction){
    return false;
  }

}
