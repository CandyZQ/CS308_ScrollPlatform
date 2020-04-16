package ooga.view.engine.graphics.assets;

import ooga.view.engine.maths.Vector3f;

public class Asset3D {

  //Right-handed Rotation

  public static Vector3f getRot(int x, int y, int z){return new Vector3f(x*90.0f, y*90.0f, z*90.0f);}



}
