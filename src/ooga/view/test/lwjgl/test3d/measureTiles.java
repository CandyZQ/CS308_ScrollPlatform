package ooga.view.test.lwjgl.test3d;

import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.cyberpunk3d.MeasureMapModel;

public class measureTiles implements Runnable {

  @Override
  public void run() {
    printVector3f("4E", MeasureMapModel.getTileMeasurement("4E"));
    printVector3f("E", MeasureMapModel.getTileMeasurement("E"));
    printVector3f("I", MeasureMapModel.getTileMeasurement("I"));
    printVector3f("L", MeasureMapModel.getTileMeasurement("L"));
    printVector3f("T", MeasureMapModel.getTileMeasurement("T"));
  }

  private void printVector3f(String type, Vector3f x){
    System.out.println(String.format("%s: (%s, %s, %s)", type, x.getX(), x.getY(), x.getZ()));
  }

  public static void main(String[] args) {
    new measureTiles().run();
  }

}
