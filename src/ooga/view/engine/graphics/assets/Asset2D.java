package ooga.view.engine.graphics.assets;

import static java.util.Map.entry;

import java.util.Map;
import ooga.view.engine.graphics.Vertex;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;

/**
 * a class that specifies all the magical values used in 2D frontend
 * @author qiaoyi fang
 */
public class Asset2D {

  private static final Map<String, String> rightSystem = Map.ofEntries(
      entry("E", "W"),
      entry("NE","NW"),
      entry("SE","SW")
      );

  private static final Map<String, String> mirrorSystem = Map.ofEntries(
      entry("W", "E"),
      entry("NW","NE"),
      entry("SW","WE")
  );

  private static final float SPEED_MELEE_SPRINT = 0.01f;

  /**
   * get tile vertices used in building its mesh
   * @return vertices
   */
  public static Vertex[] getTileVertices() {

    return new Vertex[]{
        new Vertex(new Vector3f(-1f, 1f, 0.0f), new Vector3f(1.0f, 0.0f, 0.0f),
            new Vector2f(0.0f, 0.0f)),
        new Vertex(new Vector3f(-1f, -1f, 0.0f), new Vector3f(0.0f, 1.0f, 0.0f),
            new Vector2f(0.0f, 2.0f)),
        new Vertex(new Vector3f(1f, -1f, 0.0f), new Vector3f(0.0f, 0.0f, 1.0f),
            new Vector2f(2.0f, 2.0f)),
        new Vertex(new Vector3f(1f, 1f, 0.0f), new Vector3f(1.0f, 1.0f, 0.0f),
            new Vector2f(2.0f, 0.0f))
    };

  }

  /**
   * gets tile indices (the order of drawing) used in building its mesh
   * @return indices
   */
  public static int[] getTileIndices() {
    return new int[]{
        0, 1, 2,
        0, 3, 2
    };
  }

  /**
   * gets the vertices used in building in character mesh
   * @return vertices
   */
  public static Vertex[] getAgentVertices() {
    return new Vertex[]{
        new Vertex(new Vector3f(-0.5f, 0.5f, -0.01f), new Vector3f(1.0f, 0.0f, 0.0f),
            new Vector2f(0.0f, 0.0f)),
        new Vertex(new Vector3f(-0.5f, -0.5f, -0.01f), new Vector3f(0.0f, 1.0f, 0.0f),
            new Vector2f(0.0f, 1f)),
        new Vertex(new Vector3f(0.5f, -0.5f, -0.01f), new Vector3f(0.0f, 0.0f, 1.0f),
            new Vector2f(1f, 1f)),
        new Vertex(new Vector3f(0.5f, 0.5f, -0.01f), new Vector3f(1.0f, 1.0f, 0.0f),
            new Vector2f(1f, 0f))
    };
  }

  /**
   * gets the UV for mirrored texture
   * @return mirrored texture coordinates
   */
  public static Vector2f[] getMirroredTextureCoords() {
    return new Vector2f[]{
        new Vector2f(1f, 0f),
        new Vector2f(1f, 1f),
        new Vector2f(0f, 1f),
        new Vector2f(0f, 0f)
    };
  }

  /**
   * gets the UV for normal texture
   * @return normal texture coordinates
   */
  public static Vector2f[] getNormalTextureCoords() {
    return new Vector2f[]{
        new Vector2f(0f, 0f),
        new Vector2f(0f, 1f),
        new Vector2f(1f, 1f),
        new Vector2f(1f, 0f)
    };
  }

  /**
   * gets the vertices used in building in bullet mesh
   * @return vertices
   */
  public static Vertex[] getBulletVertices() {
    return new Vertex[]{
        new Vertex(new Vector3f(-0.2f, 0.1f, -0.01f), new Vector3f(1.0f, 0.0f, 0.0f),
            new Vector2f(0.0f, 0.0f)),
        new Vertex(new Vector3f(-0.2f, -0.1f, -0.01f), new Vector3f(0.0f, 1.0f, 0.0f),
            new Vector2f(0.0f, 1f)),
        new Vertex(new Vector3f(0.2f, -0.1f, -0.01f), new Vector3f(0.0f, 0.0f, 1.0f),
            new Vector2f(1f, 1f)),
        new Vertex(new Vector3f(0.2f, 0.1f, -0.01f), new Vector3f(1.0f, 1.0f, 0.0f),
            new Vector2f(1f, 0.0f))
    };
  }

  /**
   * get the indices used in drawing character/bullet mesh
   * @return indices
   */
  public static int[] getAgentIndices() {
    return new int[]{
        0, 1, 2,
        0, 3, 2
    };
  }

  /**
   * a failed attempt of debugging | can ignored
   * @return debugging and life are both hard sometimes
   */
  public static Vector2f getIHateLife() {
      return new Vector2f(0f, 0f);
  }

  /**
   * gets the initial position of the map
   * @return position for the tile game object
   */
  public static Vector3f getMapPosition() {
    return new Vector3f(0, 0, 1f);
  }

  /**
   * gets the initial rotation of the map
   * @return rotation for the tile game object
   */
  public static Vector3f getMapRotation() {
    return new Vector3f(0, 0, 0);
  }

  /**
   * gets the initial scale of the map
   * @return scale for the tile game object
   */
  public static Vector3f getMapScale() {
    return new Vector3f(0.06f, 0.06f, 1f);
  }

  /**
   * gets the initial rotation for the character
   * @return rotation for the character game object
   */
  public static Vector3f getPlayerRotation() {
    return new Vector3f(0, 0, 0);
  }

  /**
   * gets the initial scale for the character
   * @return scale for the character game object
   */
  public static Vector3f getPlayerScale() {
    return new Vector3f(0.8f, 0.8f, 1f);
  }

  /**
   * gets the scale for the non-player
   * @return scale for the non-player game object
   */
  public static Vector3f getNonPlayerScale() {
    return new Vector3f(0.3f, 0.3f, 1f);
  }

  /**
   * gets the scale for the bullet
   * @return scale for the bullet game object
   */
  public static Vector3f getBulletScale() {
    return new Vector3f(0.020f, 0.010f, 1f);
  }

  /**
   * get the movement delta to the position of its parent character in generating a new bullet
   * @return delta
   */
  public static Vector3f getBulletDelta() {
    return new Vector3f(0.0f, 0.005f, 0f);
  }

  /**
   * get the movement delta to the position of its parent character in summoning a new agent
   * @return delta
   */
  public static Vector3f getSummonDelta() {
    return new Vector3f(0.0f, -0.005f, 0f);
  }

  /**
   * gets the Vector2f bounds for non-player object
   * @return width, height
   */
  public static Vector2f getNonPlayerBounds() {
    return new Vector2f(0.05f * getNonPlayerScale().getX(), 0.06f * getNonPlayerScale().getY());
  }

  /**
   * gets the Vector2f bounds for player object
   * @return width, height
   */
  public static Vector2f getPlayerBounds() {
    return new Vector2f(0.05f * getPlayerScale().getX(), 0.05f * getPlayerScale().getY());
  }

  /**
   * gets the Vector2f bounds for bullet object
   * @return width, height
   */
  public static Vector2f getBulletBounds() {
    return new Vector2f(0.02f * getBulletScale().getX(), 0.02f * getBulletScale().getY());
  }

  /**
   * gets the Vector2f bounds for map tile
   * @return width, height
   */
  public static Vector2f getMapTileBounds() {
    return new Vector2f(0.5f * getMapScale().getX(), 0.5f * getMapScale().getY());
  }

  /**
   * gets the bullet speed
   * @return speed
   */
  public static float getBulletSpeed() {
    return 100.0f;
  }

  /**
   * gets the directional speed
   * @param direction direction
   * @param speed_scale speed scale
   * @return movement delta in x, y, and z axis
   */
  public static Vector3f convertDirectionalSpeed(String direction, float speed_scale) {
    if (direction.equals("E")) {
      return new Vector3f(SPEED_MELEE_SPRINT * speed_scale, 0, 0);
    }
    if (direction.equals("N")) {
      return new Vector3f(0, SPEED_MELEE_SPRINT * speed_scale, 0);
    }
    if (direction.equals("S")) {
      return new Vector3f(0, -SPEED_MELEE_SPRINT * speed_scale, 0);
    }
    if (direction.equals("W")) {
      return new Vector3f(-SPEED_MELEE_SPRINT * speed_scale, 0, 0);
    }
    if (direction.equals("NE")) {
      return new Vector3f(SPEED_MELEE_SPRINT * (float) Math.sqrt(2) * speed_scale,
          SPEED_MELEE_SPRINT * (float) Math.sqrt(2) * speed_scale, 0);
    }
    if (direction.equals("SE")) {
      return new Vector3f(SPEED_MELEE_SPRINT * (float) Math.sqrt(2) * speed_scale,
          SPEED_MELEE_SPRINT * (float) Math.sqrt(2) * speed_scale, 0);
    }
    if (direction.equals("NW")) {
      return new Vector3f(SPEED_MELEE_SPRINT * (float) Math.sqrt(2) * speed_scale,
          SPEED_MELEE_SPRINT * (float) Math.sqrt(2) * speed_scale, 0);
    }
    if (direction.equals("SW")) {
      return new Vector3f(SPEED_MELEE_SPRINT * (float) Math.sqrt(2) * speed_scale,
          SPEED_MELEE_SPRINT * (float) Math.sqrt(2) * speed_scale, 0);
    }
    System.err.println(String.format("Location not found: %s", direction));
    return new Vector3f(SPEED_MELEE_SPRINT, 0, 0);
  }

  /**
   * returns if the given direction is in the right-handed system (to determine whether the animation needed to be mirrored)
   * @param direction direction
   * @return TF
   */
  public static boolean isRightSystem(String direction) {
    return rightSystem.containsKey(direction);
  }

  /**
   * returns if the given direction is not in the right-handed system (to determine whether the animation needed to be mirrored)
   * @param direction direction
   * @return TF
   */
  public static boolean isMirrorSystem(String direction) {
    return mirrorSystem.containsKey(direction);
  }

  /**
   * gets the direction in the right-handed system that is mapped to the given direction
   * @param direction mirrored direction
   * @return right-handed direction
   */
  public static String getMirroredRightDirection(String direction){
    return mirrorSystem.get(direction);
  }

}
