package ooga.view.engine.graphics;

import ooga.view.engine.maths.Matrix4f;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.maths.Vector4f;

/**
 * a class that implements vertex
 * @author codingAP
 */
public class Vertex {

  private Vector3f position, normal;
  private Vector2f textureCoord;

  /**
   * constructor
   * @param position position
   * @param normal normal
   * @param textureCoord uv
   */
  public Vertex(Vector3f position, Vector3f normal, Vector2f textureCoord) {
    this.position = position;
    this.normal = normal;
    this.textureCoord = textureCoord;
  }

  /**
   * clone constructor
   * @param other the other vertex
   */
  public Vertex(Vertex other) {
    this.position = new Vector3f(other.position);
    this.normal = new Vector3f(other.normal);
    this.textureCoord = new Vector2f(other.textureCoord);
  }

  /**
   * rotate the vertex
   * @param rotation rotation
   */
  public void rotate(Vector3f rotation){
    this.position = Vector4f.reduceDim(Vector4f.multiply(Matrix4f.rotateAllAxis(rotation), Vector4f.increaseDim(position)));
  }

  /**
   * gets the position
   * @return position
   */
  public Vector3f getPosition() {
    return position;
  }

  /**
   * sets the new position
   * @param newPos new position
   */
  public void setPosition(Vector3f newPos) {
    position = newPos;
  }

  /**
   * gets the normal
   * @return normal
   */
  public Vector3f getNormal() {
    return normal;
  }

  /**
   * gets the uv
   * @return uv
   */
  public Vector2f getTextureCoord() {
    return textureCoord;
  }

  /**
   * sets the new uv
   * @param newTextureCoord uv
   */
  public void setTextureCoord(Vector2f newTextureCoord){
    textureCoord.setX(newTextureCoord.getX());
    textureCoord.setY(newTextureCoord.getY());
  }
}