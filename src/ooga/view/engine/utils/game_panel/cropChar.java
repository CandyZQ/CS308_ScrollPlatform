package ooga.view.engine.utils.game_panel;

import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.SpriteCropper;
import ooga.view.engine.utils.cyberpunk3d.MeasureMapModel;
import org.lwjglx.test.spaceinvaders.Sprite;

public class cropChar implements Runnable {

  @Override
  public void run() {


  }

  private void printVector3f(String type, Vector3f x){
    System.out.println(String.format("%s: (%s, %s, %s)", type, x.getX(), x.getY(), x.getZ()));
  }

  public static void main(String[] args) {
    new cropChar().run();
  }

}
