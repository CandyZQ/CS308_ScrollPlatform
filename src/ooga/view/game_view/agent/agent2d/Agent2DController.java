package ooga.view.game_view.agent.agent2d;

import java.io.IOException;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.maths.Vector3f;
import ooga.view.game_view.agent.interfaces.AgentController;
import ooga.view.game_view.animation.dict2d.Animation2DDict;

public class Agent2DController extends AgentController {

  //TODO: should remove from hardcoded!
  private String INITIAL_DIRECTION = "E";
  private String INITIAL_ACTION = "IDLE";
  private Animation2DDict dict;

  public Agent2DController() throws IOException {
    super();
    DEFAULT_ACTION = "IDLE";
    direction = INITIAL_DIRECTION;
    action = INITIAL_ACTION;
    dict = new Animation2DDict(direction, action);
    this.setCurrentAnimation(direction, action);
  }

  @Override
  public void setCurrentAnimation(String direction, String action){
    this.direction = direction;
    this.action = action;
    dict.setInUseAnimation(direction, action);
  }

  public Material getCurrentAnimatedMaterial(){
    Material frame = dict.getAnimation().getCurrentFrame();
    if (frame == null){
      setCurrentAnimation(direction, DEFAULT_ACTION);
      return dict.getAnimation().getCurrentFrame();
    }
    else{
      return frame;
    }
  }

  public void move(String direction, Mesh mesh) {
    for (int i=0;i<mesh.getVertices().length;i++){
      mesh.setVerticesPosition(i, Vector3f.add(mesh.getVertices()[i].getPosition(), Asset2D.convertDirectionalSpeed(direction)));
    }
  }

}
