package ooga.view.engine.graphics.assets;

import ooga.view.engine.maths.Vector3f;

/**
 * a class that specifies all the magical values used in 3D frontend
 * @author qiaoyi fang
 */
public class Asset3D {

  final private static float AGENT_SPEED = 0.01f;
  //Right-handed Rotation

  /**
   * gets the rotation vector in the given directions
   * @param x turns in x axis
   * @param y turns in y axis
   * @param z turns in z axis
   * @return rotation vector
   */
  public static Vector3f getRotationVector(int x, int y, int z){return new Vector3f(x*90.0f, y*90.0f, z*90.0f);}

  // N (go forward) | S (go backward) | W (move left without turning) | E (move right without turning)

  /**
   * gets the translate vector in the given direction
   * @param direction direction
   * @return translte vector
   */
  public static Vector3f getTranslateVector(String direction){
    if (direction.equals("N")){
      return new Vector3f(AGENT_SPEED, 0f, 0f);
    }
    else if (direction.equals("S")){
      return new Vector3f(-AGENT_SPEED, 0f, 0f);
    }
    else if (direction.equals("W")){
      return new Vector3f(0f, 0f, -AGENT_SPEED);
    }
    else if (direction.equals("E")){
      return new Vector3f(0f, 0f, AGENT_SPEED);
    }
    System.out.println("Unknown direction");
    return Vector3f.zeros();
  }

  /**
   * gets the scale for white bot
   * @return scale vector
   */
  public static Vector3f getWhiteBotScale(){
    return new Vector3f(100.0f, 100.0f, 100.0f);
  }

  /**
   * gets the delta for third person camera
   * @return delta vector
   */
  public static Vector3f getThirdPersonCameraDelta(){
    return new Vector3f(0f,-0.5f,-5.0f);
  }

}
