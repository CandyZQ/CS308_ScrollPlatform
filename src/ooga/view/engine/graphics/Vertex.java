package ooga.view.engine.graphics;

import ooga.view.engine.maths.Matrix4f;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.maths.Vector4f;
import org.lwjglx.util.vector.Vector;

public class Vertex {

  private Vector3f position, normal;
  private Vector2f textureCoord;

  public Vertex(Vector3f position, Vector3f normal, Vector2f textureCoord) {
    this.position = position;
    this.normal = normal;
    this.textureCoord = textureCoord;
  }

  public void rotate(Vector3f rotation){
    this.position = Vector4f.reduceDim(Vector4f.multiply(Matrix4f.rotateAllAxis(rotation), Vector4f.increaseDim(position)));
  }

  public Vector3f getPosition() {
    return position;
  }

  public void setPosition(Vector3f newPos) {
    position = newPos;
  }

  public Vector3f getNormal() {
    return normal;
  }

  public Vector2f getTextureCoord() {
    return textureCoord;
  }
}