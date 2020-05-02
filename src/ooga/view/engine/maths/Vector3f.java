package ooga.view.engine.maths;

import org.lwjglx.util.vector.Vector;

public class Vector3f {
	private float x, y, z;
	
	public Vector3f(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vector3f(Vector2f other, float z){
		this.x = other.getX();
		this.y = other.getY();
		this.z = z;
	}

	public Vector3f(Vector3f other) {
		set(other.getX(),other.getY(),other.getZ());
	}
	
	public void set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static Vector3f zeros(){
		return new Vector3f(0f,0f,0f);
	}

	public static Vector3f ones(){
		return new Vector3f(1f,1f,1f);
	}
	
	public static Vector3f add(Vector3f vector1, Vector3f vector2) {
		return new Vector3f(vector1.getX() + vector2.getX(), vector1.getY() + vector2.getY(), vector1.getZ() + vector2.getZ());
	}
	
	public static Vector3f subtract(Vector3f vector1, Vector3f vector2) {
		return new Vector3f(vector1.getX() - vector2.getX(), vector1.getY() - vector2.getY(), vector1.getZ() - vector2.getZ());
	}
	
	public static Vector3f multiply(Vector3f vector1, Vector3f vector2) {
		return new Vector3f(vector1.getX() * vector2.getX(), vector1.getY() * vector2.getY(), vector1.getZ() * vector2.getZ());
	}
	
	public static Vector3f divide(Vector3f vector1, Vector3f vector2) {
		return new Vector3f(vector1.getX() / vector2.getX(), vector1.getY() / vector2.getY(), vector1.getZ() / vector2.getZ());
	}
	
	public static float length(Vector3f vector) {
		return (float) Math.sqrt(vector.getX() * vector.getX() + vector.getY() * vector.getY() + vector.getZ() * vector.getZ());
	}
	
	public static Vector3f normalize(Vector3f vector) {
		float len = Vector3f.length(vector);
		return Vector3f.divide(vector, new Vector3f(len, len, len));
	}
	
	public static float dot(Vector3f vector1, Vector3f vector2) {
		return vector1.getX() * vector2.getX() + vector1.getY() * vector2.getY() + vector1.getZ() * vector2.getZ();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x)*1;
		result = prime * result + Float.floatToIntBits(y)*2;
		result = prime * result + Float.floatToIntBits(z)*3;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector3f other = (Vector3f) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		if (Float.floatToIntBits(z) != Float.floatToIntBits(other.z))
			return false;
		return true;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
}