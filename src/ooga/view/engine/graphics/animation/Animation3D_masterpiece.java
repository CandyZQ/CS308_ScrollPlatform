package ooga.view.engine.graphics.animation;

import ooga.view.engine.graphics.Mesh;

/**
 * a class that implements 3D animation
 * @author qiaoyi fang
 */
public class Animation3D_masterpiece extends Animation {

  private Mesh[] animatedFrames;
  private boolean is2D = false;

  /**
   * constructor
   * @param cnt total amount of frames
   * @param fps frames per second
   */
  public Animation3D_masterpiece(int cnt, int fps){
    super(cnt, fps);
    animatedFrames = new Mesh[cnt];
  }

  /**
   * sets the content of an animation frame
   * @param idx index
   * @param frame frame content (Mesh)
   */
  public void setAnimatedFrame(int idx, Mesh frame){
    animatedFrames[idx] = frame;
  }

  /**
   * gets the current frame
   * @return frame content (Mesh)
   */
  public Mesh getCurrentFrame(){
    int framePointer = updateFramePointer(is2D);
    return framePointer==-1?null: animatedFrames[framePointer];
  }
}
