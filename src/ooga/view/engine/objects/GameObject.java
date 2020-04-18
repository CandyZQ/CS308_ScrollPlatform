package ooga.view.engine.objects;

import ooga.view.engine.graphics.Mesh;
import ooga.view.engine.maths.Vector3f;

public class GameObject {
	private Vector3f position, rotation, scale;
	private Mesh mesh;
	
	public GameObject(Vector3f position, Vector3f rotation, Vector3f scale, Mesh mesh) {
		this.position = position;
		this.rotation = rotation;
		this.scale = scale;
		this.mesh = mesh;
	}
	
	public void update() {}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position){ this.position = position;}

	public Vector3f getRotation() {
		return rotation;
	}

	public void setRotation(Vector3f rotation){ this.rotation = rotation;}

	public Vector3f getScale() {
		return scale;
	}

	public void setScale(Vector3f scale){this.scale = scale;}

	public Mesh getMesh() {
		return mesh;
	}

	public void setMesh(Mesh mesh) {
		this.mesh = mesh;
	}
}