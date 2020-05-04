package ooga.view.game_view.map.interfaces;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.objects.GameObject;

/**
 * an abstract class that structures the framework for tile view
 * @author qiaoyi fang
 */
abstract public class TileView {

  protected GameObject object;
  protected Mesh mesh;

  /**
   * creates mesh
   */
  public void createMesh(){mesh.create();}

  /**
   * destroys mesh
   */
  public void destroyMesh(){mesh.destroy();}

  /**
   * gets the game object associated to the tile
   * @return the game object
   */
  public GameObject getGameObject() {return object;}

}
