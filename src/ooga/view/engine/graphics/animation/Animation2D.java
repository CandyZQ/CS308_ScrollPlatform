package ooga.view.engine.graphics.animation;

import ooga.view.engine.graphics.Material;

/**
 * an class that implements 2D animation
 * @author qiaoyi fang
 */
public class Animation2D extends Animation{

  private Material[] animatedFrames;

  /**
   * constructor
   * @param cnt total amount of frames
   * @param fps frame per second
   * @param dir directory
   */
  public Animation2D(int cnt, int fps, String dir) {
    super(cnt,fps);
    this.animatedFrames = new Material[cnt];

    for(int i=0; i<cnt;i++){
      String spritePath = String.format("%s/%s_.png", dir.replace("\\", "/"), i);
      this.animatedFrames[i] = new Material(spritePath);
      this.animatedFrames[i].createTexture();
    }
  }

  /**
   * constructor
   * @param cnt total amount of frames
   * @param fps frame per second
   * @param dir directory
   * @param action animation state
   */
  public Animation2D(int cnt, int fps, String dir, String action) {
    super(cnt,fps);
    this.animatedFrames = new Material[cnt];

    for(int i=0; i<cnt;i++){
      String spritePath = String.format("%s%s%s.png", dir, action.toLowerCase(), i+1);
      this.animatedFrames[i] = new Material(spritePath);
      this.animatedFrames[i].createTexture();
    }
  }

  /**
   * empty constructor
   * @param cnt total amount of frame
   * @param fps frame per second
   */
  public Animation2D(int cnt, int fps) {
    super(cnt, fps);
    this.animatedFrames = new Material[cnt];
  }

  /**
   * combines two animations
   * @param animation_1 the first animation
   * @param animation_2 the second animation
   * @return the combined animation
   */
  public static Animation2D combineAnimations(Animation2D animation_1, Animation2D animation_2){

    int totalFrames = animation_1.getFrameAmount()+animation_2.getFrameAmount();
    Animation2D combined = new Animation2D(totalFrames, DEFAULT_FPS_2D);

    int idx = 0;
    for (int i=0; i<animation_1.getFrameAmount(); i++){
      combined.setAnimatedFrame(idx++, animation_1.getAnimatedFrame(i));
    }

    for (int i=0; i<animation_2.getFrameAmount(); i++){
      combined.setAnimatedFrame(idx++, animation_2.getAnimatedFrame(i));
    }

    return combined;
  }

  /**
   * sets the content of an animation frame
   * @param idx index
   * @param frame frame content (texture)
   */
  public void setAnimatedFrame(int idx, Material frame){
    this.animatedFrames[idx] = frame;
  }

  /**
   * gets the content of an animation frame
   * @param idx index
   * @return frame content (texture)
   */
  public Material getAnimatedFrame(int idx){
    return this.animatedFrames[idx];
  }

  /**
   * gets the current frame
   * @return frame content (texture)
   */
  public Material getCurrentFrame(){
    boolean is2D = true;
    int framePointer = updateFramePointer(is2D);
    return framePointer==-1?null: animatedFrames[framePointer];
  }
}
