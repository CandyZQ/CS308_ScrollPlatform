package ooga.view.engine.graphics.animation;

import ooga.view.engine.graphics.Material;
import ooga.view.engine.graphics.Mesh;

public class Animation3D extends Animation {

  private Mesh[] animatedFrames;
  private boolean is2D = false;

  public Animation3D(int cnt, int fps){
    super(cnt, fps);
    animatedFrames = new Mesh[cnt];
  }

  public void setAnimatedFrames(int idx, Mesh frame){
    animatedFrames[idx] = frame;
  }

  public Mesh getAnimatedFrames(){
    int framePointer = updateFramePointer(is2D);
    return framePointer==-1?null: animatedFrames[framePointer];
  }


}
