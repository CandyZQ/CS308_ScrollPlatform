package ooga.view.test.lwjgl.test3d;

import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.utils.cyberpunk3d.MeasureMapModel;
import ooga.view.test.lwjgl.test2d.testMap;

public class measureTiles implements Runnable {

  @Override
  public void run() {
    printVector2f("4E", MeasureMapModel.getTileMeasurement("4E"));
    printVector2f("E", MeasureMapModel.getTileMeasurement("E"));
    printVector2f("I", MeasureMapModel.getTileMeasurement("I"));
    printVector2f("L", MeasureMapModel.getTileMeasurement("L"));
    printVector2f("T", MeasureMapModel.getTileMeasurement("T"));
  }

  private void printVector2f(String type, Vector2f x){
    System.out.println(String.format("%s: (%s, %s)", type, x.getX(), x.getY()));
  }

  public static void main(String[] args) {
    new measureTiles().run();
  }

}
