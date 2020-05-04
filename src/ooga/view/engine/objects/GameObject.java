package ooga.view.engine.objects;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.maths.Vector3f;

/**
 * a class that implements the game object
 * @author codingAP, qiaoyi fang
 */
public class GameObject {
	private Vector3f position, rotation, scale;
	private Mesh mesh;

	/**
	 * constructor
	 * @param position initial position
	 * @param rotation initial rotation
	 * @param scale initial scale
	 * @param mesh initial mesh
	 */
	public GameObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
	}

	/**
	 * @return mesh position
	 */
	public Vector3f getPosition() {
		return position;
	}

	/**
	 * sets the position
	 * @param position new position
	 */
	public void setPosition(Vector3f position){ this.position = position;}

	/**
	 * @return mesh rotation
	 */
	public Vector3f getRotation() {
		return rotation;
	}

	/**
	 * sets the rotation
	 * @param rotation new rotation
	 */
	public void setRotation(Vector3f rotation){ this.rotation = rotation;}

	/**
	 * @return mesh scale
	 */
	public Vector3f getScale() {
		return scale;
	}

	/**
	 * sets the scale
	 * @param scale new scale
	 */
	public void setScale(Vector3f scale){this.scale = scale;}

	/**
	 * @return mesh
	 */
	public Mesh getMesh() {
		return mesh;
	}

	/**
	 * sets the mesh
	 * @param mesh the new mesh
	 */
	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}

	public void update() {}

}