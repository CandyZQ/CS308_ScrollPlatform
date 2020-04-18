package ooga.view.game_view.map.map3d;

import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.Test;
import ooga.view.engine.utils.cyberpunk3d.LoadCyberpunkModels;
import ooga.view.game_view.map.interfaces.TileView;
import org.lwjgl.system.CallbackI.S;

public class Tile3DView extends TileView {

  public Tile3DView(String type, Vector3f rot, Vector3f pos, Vector3f scale){
    //System.out.println("rot");
    //Test.printVector3f(rot);
    this.mesh = LoadCyberpunkModels.getRotationalTileMesh(type, rot);
    //this.mesh = LoadCyberpunkModels.getTileMesh(type);
    //Test.printThreeMeshVertices(mesh);
    /*System.out.println(mesh.getVertices().length);
    System.out.println(mesh.getMaxWidth());
    System.out.println(mesh.getMaxHeight());
    System.out.println(mesh.getMaxDepth());
    //Test.printMeshVertices(mesh);
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    Test.printVector3f(pos);
    Test.printVector3f(scale);
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();*/
    this.object = new GameObject(pos, Vector3f.zeros(), scale, mesh);
    //this.object = new GameObject(pos, rot, scale, mesh);
  }

}
