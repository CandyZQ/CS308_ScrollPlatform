package ooga.view.engine.graphics.animation;

import ooga.view.engine.utils.Timer;

/**
 * An abstract class that implements the frame control mechanism and structure the framework for 2D and 3D animation
 * @author qiaoyi fang
 **/
abstract public class Animation {

  protected final static int DEFAULT_FPS_2D = 18;
  protected final static int DEFAULT_FPS_3D = 23;
  protected final static double HARD_SET_2D_TIME = 1.0/ (double) DEFAULT_FPS_2D;
  protected final static double HARD_SET_3D_TIME = 1.0/ (double) DEFAULT_FPS_3D;

  protected int framePointer;
  protected int frameAmount;

  protected double elapsedTime;
  protected double currentTime;
  protected double lastTime;
  protected double fps;

  public Animation(int cnt, int fps){
    resetAnimation();
    this.fps = 1.0 / fps;
    this.frameAmount = cnt;
  }

  /**
   * resets the animation
   */
  public void resetAnimation(){
    this.framePointer = 0;
    this.elapsedTime = 0;
    this.currentTime = 0;
    this.lastTime = Timer.getTime();
  }

  /**
   * get the amount of frames
   * @return total amount of frames
   */
  public int getFrameAmount(){return frameAmount;}

  /**
   * updates the current frame pointer
   * @param is2D whether the animation is 2D (determines the FPS)
   * @return the pointer to the current frame
   */
  public int updateFramePointer(boolean is2D){
    this.currentTime = Timer.getTime();
    this.elapsedTime += currentTime - lastTime;

    if (elapsedTime >= fps || elapsedTime >= (is2D? HARD_SET_2D_TIME:HARD_SET_3D_TIME) ){
      elapsedTime = 0;
      framePointer++;
      this.lastTime = currentTime;
    }

    if (framePointer >= frameAmount)
    {
      framePointer = 0;
      return -1;
    }

    return framePointer;
  }

  /**
   * for testing
   */
  public void printStatus(){
    System.out.println(framePointer);
    System.out.println(frameAmount);
    System.out.println(currentTime);
    System.out.println(elapsedTime);
    System.out.println(lastTime);
    System.out.println(currentTime - lastTime);
    System.out.println(fps);
    System.out.println();
  }

}
