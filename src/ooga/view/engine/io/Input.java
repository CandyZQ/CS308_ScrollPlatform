package ooga.view.engine.io;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWScrollCallback;

/**
 * a class that implements input to the window
 * @author codingAP
 */
public class Input {
	private static boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	private static boolean[] buttons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
	private static double mouseX, mouseY;
	private static double scrollX, scrollY;
	
	private GLFWKeyCallback keyboard;
	private GLFWCursorPosCallback mouseMove;
	private GLFWMouseButtonCallback mouseButtons;
	private GLFWScrollCallback mouseScroll;
	
	public Input() {
		keyboard = new GLFWKeyCallback() {
			public void invoke(long window, int key, int scancode, int action, int mods) {
				keys[key] = (action != GLFW.GLFW_RELEASE);
			}
		};
		
		mouseMove = new GLFWCursorPosCallback() {
			public void invoke(long window, double xpos, double ypos) {
				mouseX = xpos;
				mouseY = ypos;
			}
		};
		
		mouseButtons = new GLFWMouseButtonCallback() {
			public void invoke(long window, int button, int action, int mods) {
				buttons[button] = (action != GLFW.GLFW_RELEASE);
			}
		};
		
		mouseScroll = new GLFWScrollCallback() {
			public void invoke(long window, double offsetx, double offsety) {
				scrollX += offsetx;
				scrollY += offsety;
			}
		};
	}

	/**
	 * returns if the key is pressed
	 * @param key key id in lwjgl
	 * @return true if the key is down
	 */
	public static boolean isKeyDown(int key) {
		return keys[key];
	}

	/**
	 * returns if the mouse button is pressed
	 * @param button button id in lwjgl
	 * @return true if the button is down
	 */
	public static boolean isButtonDown(int button) {
		return buttons[button];
	}

	/**
	 * destroy the input
	 */
	public void destroy() {
		keyboard.free();
		mouseMove.free();
		mouseButtons.free();
		mouseScroll.free();
	}

	/**
	 * get the mouse X location
	 * @return mouse X
	 */
	public static double getMouseX() {
		return mouseX;
	}

	/**
	 * get the mouse Y location
	 * @return mouse Y
	 */
	public static double getMouseY() {
		return mouseY;
	}

	/**
	 * get the mouse X scroll
	 * @return mouse X
	 */
	public static double getScrollX() {
		return scrollX;
	}

	/**
	 * get the mouse Y scroll
	 * @return mouse Y
	 */
	public static double getScrollY() {
		return scrollY;
	}

	public GLFWKeyCallback getKeyboardCallback() {
		return keyboard;
	}

	public GLFWCursorPosCallback getMouseMoveCallback() {
		return mouseMove;
	}

	public GLFWMouseButtonCallback getMouseButtonsCallback() {
		return mouseButtons;
	}
	
	public GLFWScrollCallback getMouseScrollCallback() {
		return mouseScroll;
	}
}