package ooga.view.game_view.map.map2d;

import java.io.IOException;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.cyberpunk2d.Text2DMapReader;
import ooga.view.game_view.map.interfaces.TileView;

public class Tile2DView extends TileView {
  private static final float delta = 17;
  private Tile2DController controller;

  private Vertex[] vertices;
  private int[] indices;

  public Tile2DView(int map_x, int map_y, Text2DMapReader mapReader) {
    this.vertices = Asset2D.getTileVertices();
    this.indices = Asset2D.getTileIndices();

    controller = new Tile2DController(map_x, map_y, mapReader);
    setLocation(map_x, map_y);

    mesh = new Mesh( vertices, indices, controller.getMaterial());
    object = new GameObject(Asset2D.getMapPosition(), Asset2D.getMapRotation(), Asset2D.getMapScale(), mesh);
  }

  private void setLocation(int x, int y){
    Vector3f translator = new Vector3f(y-delta, -x+delta, 0);
    for (Vertex v:vertices){
      v.setPosition(Vector3f.add(v.getPosition(), translator));
    }
  }


  public boolean isWalkable(){return controller.isWalkable();}

}
