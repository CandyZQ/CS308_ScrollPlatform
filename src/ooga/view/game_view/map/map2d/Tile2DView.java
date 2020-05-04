package ooga.view.game_view.map.map2d;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.cyberpunk2d.Text2DMapReader;
import ooga.view.game_view.map.interfaces.TileView;

/**
 * a class that implements the view for 2D tile
 * @author qiaoyi fang
 */
public class Tile2DView extends TileView {
  private static final float delta = 17; // adjustment to the window
  private Tile2DController controller;

  private Vertex[] vertices;
  private int[] indices;

  /**
   * constructor
   * @param map_x location x in the map
   * @param map_y location y in the map
   * @param mapReader map reader
   */
  public Tile2DView(int map_x, int map_y, Text2DMapReader mapReader) {
    this.vertices = Mesh.verticesCopy(Asset2D.getTileVertices());
    this.indices = Asset2D.getTileIndices();

    controller = new Tile2DController(map_x, map_y, mapReader);
    mesh = new Mesh( setLocation(map_x, map_y, vertices), this.indices, controller.getMaterial(), true);
    object = new GameObject(Asset2D.getMapPosition(), Asset2D.getMapRotation(), Asset2D.getMapScale(), mesh);
  }

  /**
   * returns the center location of the tile
   * @return location vector2f
   */
  public Vector2f getCenterLocation(){return mesh.getCenter();}

  /**
   * returns true if the tile is walkable
   * @return if walkable
   */
  public boolean isWalkable(){return controller.isWalkable();}

  private Vertex[] setLocation(int x, int y, Vertex[] originalVertices){

    Vector3f translator = new Vector3f(y-delta, -x+delta, 0);
    Vertex[] newVertices = new Vertex[originalVertices.length];
    for (int i=0; i<newVertices.length; i++){
      newVertices[i] = new Vertex(originalVertices[i]);
      newVertices[i].setPosition(Vector3f.add(originalVertices[i].getPosition(), translator));
    }
    return newVertices;
  }

}
