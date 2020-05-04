package ooga.view.engine.objects;

import org.lwjgl.glfw.GLFW;

import ooga.view.engine.io.Input;
import ooga.view.engine.maths.Vector3f;

/**
 * a class that implements camera
 * @author codingAP, qiaoyi fang
 */
public class Camera {
	private Vector3f position, rotation;
	private float moveSpeed = 0.05f, mouseSensitivity = 0.15f, distance = 20.0f, horizontalAngle = 0, verticalAngle = 0;
	private double oldMouseX = 0, oldMouseY = 0, newMouseX, newMouseY;

	public Camera(Vector3f position, Vector3f rotation) {
		this.position = position;
		this.rotation = rotation;
	}

	/**
	 * move the camera forward on the x axis
	 */
	public void moveXForward(){
		position.setX(position.getX()+moveSpeed*200);
	}

	/**
	 * move the camera forward on the y axis
	 */
	public void moveYForward(){
		position.setY(position.getY()+moveSpeed*200);
	}

	/**
	 * move the camera forward on the z axis
	 */
	public void moveZForward(){
		position.setZ(position.getZ()+moveSpeed*200);
	}

	/**
	 * move the camera backward on the x axis
	 */
	public void moveXBackward(){
		position.setX(position.getX()-moveSpeed*200);
	}

	/**
	 * move the camera backward on the y axis
	 */
	public void moveYBackward(){
		position.setY(position.getY()-moveSpeed*200);
	}

	/**
	 * move the camera backward on the z axis
	 */
	public void moveZBackward(){
		position.setZ(position.getZ()-moveSpeed*200);
	}

	/**
	 * updates the camera
	 */
	public void update() {
		newMouseX = Input.getMouseX();
		newMouseY = Input.getMouseY();
		
		float x = (float) Math.sin(Math.toRadians(rotation.getY())) * moveSpeed;
		float z = (float) Math.cos(Math.toRadians(rotation.getY())) * moveSpeed;
		
		if (Input.isKeyDown(GLFW.GLFW_KEY_A)) position = Vector3f.add(position, new Vector3f(-z, 0, x));
		if (Input.isKeyDown(GLFW.GLFW_KEY_D)) position = Vector3f.add(position, new Vector3f(z, 0, -x));
		if (Input.isKeyDown(GLFW.GLFW_KEY_W)) position = Vector3f.add(position, new Vector3f(-x, 0, -z));
		if (Input.isKeyDown(GLFW.GLFW_KEY_S)) position = Vector3f.add(position, new Vector3f(x, 0, z));
		if (Input.isKeyDown(GLFW.GLFW_KEY_SPACE)) position = Vector3f.add(position, new Vector3f(0, moveSpeed, 0));
		if (Input.isKeyDown(GLFW.GLFW_KEY_LEFT_SHIFT)) position = Vector3f.add(position, new Vector3f(0, -moveSpeed, 0));
		
		float dx = (float) (newMouseX - oldMouseX);
		float dy = (float) (newMouseY - oldMouseY);
		
		rotation = Vector3f.add(rotation, new Vector3f(-dy * mouseSensitivity, -dx * mouseSensitivity, 0));
		
		oldMouseX = newMouseX;
		oldMouseY = newMouseY;
	}

	/**
	 * update the camera based on object
	 * @param object game object
	 * @param translateDelta translate delta to the object
	 */
	public void update(GameObject object, Vector3f translateDelta) {
		newMouseX = Input.getMouseX();
		newMouseY = Input.getMouseY();
		
		float dx = (float) (newMouseX - oldMouseX);
		float dy = (float) (newMouseY - oldMouseY);
		
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_LEFT)) {
			verticalAngle -= dy * mouseSensitivity;
			horizontalAngle += dx * mouseSensitivity;
		}
		if (Input.isButtonDown(GLFW.GLFW_MOUSE_BUTTON_RIGHT)) {
			if (distance > 0) {
				distance += dy * mouseSensitivity / 4;
			} else {
				distance = 0.1f;
			}
		}
		
		float horizontalDistance = (float) (distance * Math.cos(Math.toRadians(verticalAngle)));
		float verticalDistance = (float) (distance * Math.sin(Math.toRadians(verticalAngle)));
		
		float xOffset = (float) (horizontalDistance * Math.sin(Math.toRadians(-horizontalAngle)));
		float zOffset = (float) (horizontalDistance * Math.cos(Math.toRadians(-horizontalAngle)));
		
		position.set(object.getPosition().getX() + xOffset, object.getPosition().getY() - verticalDistance, object.getPosition().getZ() + zOffset);
		position = Vector3f.add(position, translateDelta);
		rotation.set(verticalAngle, -horizontalAngle, 0);
		
		oldMouseX = newMouseX;
		oldMouseY = newMouseY;
	}

	public Vector3f getPosition() {
		return position;
	}

	public Vector3f getRotation() {
		return rotation;
	}
}