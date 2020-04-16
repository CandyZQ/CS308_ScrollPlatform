package ooga.view.engine.utils.cyberpunk3d;

import ooga.view.engine.graphics.assets.Asset3D;
import ooga.view.engine.maths.Vector3f;

public class TitleDataHolder {

  int id;
  private String type;
  private Vector3f rotation;
  private Vector3f position_adjustment;
  private boolean newLine;
  private Vector3f maxShape;

  public TitleDataHolder(int id, String type, int rotation, int pos_delta_x, int pos_delta_y,
      boolean newLine) {
    this.id = id;
    this.type = type;
    this.rotation = factoryRotation(rotation);
    this.position_adjustment = new Vector3f(pos_delta_x, pos_delta_y, 0f);
    this.newLine = newLine;
    this.maxShape = MeasureMapModel.getTileMeasurement(type);
  }

  private Vector3f factoryRotation(int rotation) {
    if (rotation == 1) {
      return Asset3D.RightOnceRot;
    }
    if (rotation == 2) {
      return Asset3D.RightTwiceRot;
    }
    if (rotation == 3) {
      return Asset3D.RightThirdRot;
    }
    return Asset3D.RightFourthRot;
  }

  public String getType() {
    return type;
  }

  public Vector3f getRotation() {
    return rotation;
  }

  public boolean isNewLine() {
    return newLine;
  }

  public Vector3f getPositionAdjustment() {
    return position_adjustment;
  }

  public int getId() {
    return id;
  }

  public Vector3f getMaxShape(){
    return maxShape;
  }
}
