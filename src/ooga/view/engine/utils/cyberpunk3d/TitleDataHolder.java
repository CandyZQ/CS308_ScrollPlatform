package ooga.view.engine.utils.cyberpunk3d;

import ooga.view.engine.graphics.assets.Asset3D;
import ooga.view.engine.maths.Vector3f;

public class TitleDataHolder {
  int id;
  private String type;
  private Vector3f rotation;
  private boolean newLine;

  public TitleDataHolder(int id, String type, int rotation, boolean newLine){
    this.id = id;
    this.type = type;
    this.rotation = factoryRotation(rotation);
    this.newLine = newLine;
  }

  private Vector3f factoryRotation(int rotation){
    if (rotation == 1){
      return Asset3D.RightOnceRot;
    }
    if (rotation == 2){
      return Asset3D.RightTwiceRot;
    }
    if (rotation == 3){
      return Asset3D.RightThirdRot;
    }
    return Asset3D.RightFourthRot;
  }

  public String getType(){
    return type;
  }

  public Vector3f getRotation(){
    return rotation;
  }

  public boolean isNewLine(){
    return newLine;
  }

  public int getId(){
    return id;
  }
}
