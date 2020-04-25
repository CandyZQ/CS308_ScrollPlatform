package ooga.view.game_view.game_state.state2d;

import java.util.Map;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.game_view.agent.agent2d.Agent2DView;
import ooga.view.game_view.map.map2d.Map2DView;

public class BoundingBox  {
  private Map2DView map;
  private Map<Integer, Agent2DView> agents;
  private static final float MELEE_ATTACK_RANGE = 0.1f;
  private float eps = 0.00001f;
  private final static int NON_ID = -17;

  public BoundingBox(Map2DView map, Map<Integer, Agent2DView> agents){
    this.map = map;
    this.agents = agents;
  }

  public boolean canMove(boolean isAgent, boolean isBullet, Agent2DView object, Vector3f delta){
    //return true;
    if (isAgent) return canAgentMove(object, delta);
    if (isBullet) return canBulletMove(object, delta);

    System.out.println("WHY ARE YOU CALLING CANMOVE() - not bullet or agent");

    return true;
  }


  public int getNonId(){return NON_ID;}

  private boolean canAgentMove(Agent2DView agent, Vector3f delta){
    boolean isValid = isHitWall(agent, delta);

    for(int agentId:agents.keySet()){
      if (agentId != agent.getId()){
        isValid = isValid& notClose(agents.get(agentId).getCenterPosition(), agents.get(agentId).getHalfBounds(),
            move(agent.getCenterPosition(),delta), agent.getHalfBounds(), eps);
      }
    }

    return isValid;
  }


  private Vector2f move(Vector2f currentPos, Vector3f delta){
    return Vector2f.add(currentPos, new Vector2f(delta));
  }

  public boolean isHitWall(Agent2DView object, Vector3f delta){
    boolean isValid = true;

    for(int tileIdx =0; tileIdx<map.getTileTotal(); tileIdx++){
      isValid = isValid&(map.getTile(tileIdx).isWalkable()||
          notClose(map.getTile(tileIdx).getCenterLocation(), Asset2D.getMapTileBounds(),
          move(object.getCenterPosition(),delta), object.getHalfBounds(), eps));
    }
    return isValid;
  }


  private boolean canBulletMove(Agent2DView bullet, Vector3f delta){
    return isHitWall(bullet, delta);
  }

  public int isBulletAttack(Agent2DView bullet){

    for(int agentId:agents.keySet()){
      if (!notClose(agents.get(agentId).getCenterPosition(), agents.get(agentId).getHalfBounds(),
          bullet.getCenterPosition(), bullet.getHalfBounds(), eps))
      {
        return agentId;
      }
    }

    return NON_ID;
  }

  private boolean notClose(Vector2f posA, Vector2f halfBoundsA, Vector2f posB, Vector2f halfBoundsB, float dis){
    float distanceX = Math.abs(posA.getX() - posB.getX());
    float distanceY = Math.abs(posA.getY() - posB.getY());

    float boundsX = halfBoundsA.getX() + halfBoundsB.getY();
    float boundsY = halfBoundsB.getY() + halfBoundsB.getY();

    return (!(distanceX - boundsX < dis) && !(distanceY - boundsY < dis));
  }

  public int isAttackEffective(Agent2DView attacker){
    for (int agentId:agents.keySet()){
      if (agentId!=attacker.getId() && isAgentDirection(attacker.getCenterPosition(), agents.get(agentId).getCenterPosition(), attacker.getCurrentDirection())
      && !notClose(attacker.getCenterPosition(), attacker.getHalfBounds(), agents.get(agentId).getCenterPosition(), agents.get(agentId).getHalfBounds(), MELEE_ATTACK_RANGE)){
        return agentId;
      }
    }
    return NON_ID;
  }

  public boolean isAgentDirection(Vector2f agentX, Vector2f agentY, String direction){
    if (direction.equals("E")){
      return agentX.getX()-agentY.getX() < eps;
    }
    if (direction.equals("W")){
      return agentX.getX()-agentY.getX() > eps;
    }
    if (direction.equals("S")){
      return agentX.getY() - agentY.getY() > eps;
    }
    if (direction.equals("N")){
      return agentX.getY() - agentY.getY() < eps;
    }
    System.out.println("Strange direction");
    return false;
  }

}
