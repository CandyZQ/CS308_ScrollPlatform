package ooga.view.game_view.game_state.state2d;

import java.util.Map;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.Test;
import ooga.view.game_view.agent.agent2d.Agent2DView;
import ooga.view.game_view.map.map2d.Map2DView;

public class BoundingBox  {
  private Map2DView map;
  private Map<Integer, Agent2DView> agents;
  private static final float MELEE_ATTACK_RANGE = 0.1f;
  private float eps = 0.0000000001f;
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
    System.out.println("after checking wall");
    System.out.println(isValid);
    System.out.println();
    for(int agentId:agents.keySet()){
      if (agentId != agent.getId()){
        isValid = isValid&& notClose(
            agents.get(agentId).getCenterPosition(), agents.get(agentId).getHalfBounds(), agents.get(agentId).getScale(),
            move(agent.getCenterPosition(),delta), agent.getHalfBounds(), agent.getScale(), eps);
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
      //System.out.println(map.getTile(tileIdx).getGameObject().getMesh().getMaxHeight());
      //System.out.println(map.getTile(tileIdx).getGameObject().getMesh().getMaxWidth());
      //System.out.println(map.getTile(tileIdx).;
      System.out.println("tile");
      System.out.println(tileIdx);
      Test.printVector2f(map.idontcare(tileIdx));
      System.out.println(map.getTile(tileIdx).isWalkable()?"walkable":"not walkable");
      System.out.println("value of isvalid");
      System.out.println(isValid);
      Test.printVector2f(Vector2f.multiply(map.getTile(tileIdx).getCenterLocation(), new Vector2f(Asset2D.getMapScale())));
      Test.printVector2f(Asset2D.getMapTileBounds());
      System.out.println();
      Test.printVector2f(Vector2f.multiply(move(object.getCenterPosition(), delta), new Vector2f(object.getScale())));
      Test.printVector2f(object.getHalfBounds());
      //System.out.println(map.getTile(tileIdx).isWalkable());
      System.out.println();

      isValid = isValid&&(map.getTile(tileIdx).isWalkable()||
          notClose(map.getTile(tileIdx).getCenterLocation(), Asset2D.getMapTileBounds(), Asset2D.getMapScale(),
          move(object.getCenterPosition(),delta), object.getHalfBounds(), object.getScale(), eps));
    }
    return isValid;
  }


  private boolean canBulletMove(Agent2DView bullet, Vector3f delta){
    return isHitWall(bullet, delta);
  }

  public int isBulletAttack(Agent2DView bullet){

    for(int agentId:agents.keySet()){
      if (!notClose(agents.get(agentId).getCenterPosition(), agents.get(agentId).getHalfBounds(), agents.get(agentId).getScale(),
          bullet.getCenterPosition(), bullet.getHalfBounds(), bullet.getScale(), eps))
      {
        return agentId;
      }
    }

    return NON_ID;
  }

  private boolean notClose(Vector2f posA, Vector2f halfBoundsA, Vector3f scaleA, Vector2f posB, Vector2f halfBoundsB, Vector3f scaleB, float dis){
    float distanceX = Math.abs(posA.getX()*scaleA.getX() - posB.getX()*scaleB.getX());
    float distanceY = Math.abs(posA.getY()*scaleA.getY() - posB.getY()*scaleB.getY());

    float boundsX = halfBoundsA.getX() + halfBoundsB.getX();
    float boundsY = halfBoundsA.getY() + halfBoundsB.getY();

    return (!(distanceX - boundsX < dis) || !(distanceY - boundsY < dis));
  }

  /*private boolean notClose(Vector2f posA, Mesh meshA, Vector3f scaleA, Vector2f posB, Mesh meshB, Vector3f scaleB, float dis){
    float distanceX = Math.abs(posA.getX()*scaleA.getX() - posB.getX()*scaleB.getX());
    float distanceY = Math.abs(posA.getY()*scaleA.getY() - posB.getY()*scaleB.getY());

    float boundsX = meshA.getMaxWidth()/2.0 + ;
    float boundsY = halfBoundsA.getY() + halfBoundsB.getY();

    return (!(distanceX - boundsX < dis) || !(distanceY - boundsY < dis));
  }*/

  public int isAttackEffective(Agent2DView attacker){
    for (int agentId:agents.keySet()){
      if (agentId!=attacker.getId() && isAgentDirection(attacker.getCenterPosition(), agents.get(agentId).getCenterPosition(), attacker.getCurrentDirection())
      && !notClose(attacker.getCenterPosition(), attacker.getHalfBounds(), attacker.getScale(),
          agents.get(agentId).getCenterPosition(), agents.get(agentId).getHalfBounds(), agents.get(agentId).getScale(), MELEE_ATTACK_RANGE)){
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
