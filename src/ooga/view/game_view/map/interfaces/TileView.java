package ooga.view.game_view.map.interfaces;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.objects.GameObject;

abstract public class TileView {

  protected GameObject object;
  protected Mesh mesh;

  public void createMesh(){mesh.create();}

  public void destroyMesh(){mesh.destroy();}

  public GameObject getGameObject() {return object;}

}
