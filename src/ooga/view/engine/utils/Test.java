package ooga.view.engine.utils;

import javafx.util.Pair;
import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector3f;

public class Test {

  public static void printVector3f(Vector3f vec){
    System.out.println(String.format("(%s, %s, %s)",vec.getX(), vec.getY(), vec.getZ()));
  }

  public static void printMeshVertices(Mesh mesh){
    for (Vertex v:mesh.getVertices()){
      printVector3f(v.getPosition());
    }
  }

  public static void printThreeMeshVertices(Mesh mesh){
    System.out.println("first position in mesh");
    Test.printVector3f(mesh.getVertices()[0].getPosition());
    System.out.println("second position in mesh");
    Test.printVector3f(mesh.getVertices()[1].getPosition());
    System.out.println("last position in mesh");
    Test.printVector3f(mesh.getVertices()[mesh.getVertices().length-1].getPosition());
    System.out.println();
  }

  public static void printRotationalTileDict(Pair<String, Vector3f> key){
    System.out.println("type:");
    System.out.println(key.getKey());
    System.out.println("rotation");
    Test.printVector3f(key.getValue());
  }

}
