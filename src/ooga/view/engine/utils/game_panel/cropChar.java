package ooga.view.engine.utils.game_panel;

import ooga.view.engine.maths.Vector3f;

/**
 * an unfinished class that were supposed crop the alphabet palette
 * @author qiaoyi fang
 */
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
