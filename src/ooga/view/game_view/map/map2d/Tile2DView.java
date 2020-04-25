package ooga.view.game_view.map.map2d;

import java.io.IOException;
import ooga.view.engine.graphics.assets.Asset2D;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.Test;
import ooga.view.engine.utils.cyberpunk2d.Text2DMapReader;
import ooga.view.game_view.map.interfaces.TileView;

public class Tile2DView extends TileView {
  private static final float delta = 17;
  private Tile2DController controller;

  private Vertex[] vertices;
  private int[] indices;

  public Tile2DView(int map_x, int map_y, Text2DMapReader mapReader) {
    this.vertices = Mesh.verticesCopy(Asset2D.getTileVertices());
    this.indices = Asset2D.getTileIndices();

    controller = new Tile2DController(map_x, map_y, mapReader);
    //Test.printVector3f(setLocation(map_x, map_y, vertices)[0].getPosition());
    mesh = new Mesh( setLocation(map_x, map_y, vertices), this.indices, controller.getMaterial(), true);
    //Test.printThreeMeshVertices(mesh);
    object = new GameObject(Asset2D.getMapPosition(), Asset2D.getMapRotation(), Asset2D.getMapScale(), mesh);
    //Test.printThreeMeshVertices(mesh);
  }

  private Vertex[] setLocation(int x, int y, Vertex[] originalVertices){

    Vector3f translator = new Vector3f(y-delta, -x+delta, 0);
    Vertex[] newVertices = new Vertex[originalVertices.length];
    for (int i=0; i<newVertices.length; i++){
      newVertices[i] = new Vertex(originalVertices[i]);
      newVertices[i].setPosition(Vector3f.add(originalVertices[i].getPosition(), translator));
      //Test.printVector3f(newVertices[i].getPosition());
    }
    return newVertices;
  }

  public Vector2f getCenterLocation(){return mesh.getCenter();}

  public boolean isWalkable(){return controller.isWalkable();}

}
