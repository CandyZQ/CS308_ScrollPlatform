package ooga.view.engine.graphics.render;

import ooga.view.engine.graphics.Shader;
import ooga.view.engine.io.Window;
import ooga.view.engine.maths.Matrix4f;
import ooga.view.engine.objects.Camera;
import ooga.view.engine.objects.GameObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;

/**
 * a class that renders 3D GameObject instances
 * @author qiaoyi fang
 */
public class Renderer3D {
	private Shader shader;
	private Window window;

	/**
	 * constructor that takes in the used shaders and window
	 * @param window window object
	 * @param shader shader object
	 */
	public Renderer3D(Window window, Shader shader) {
		this.shader = shader;
		this.window = window;
	}

	/**
	 * renders the given GameObject instance in the given camera view
	 * @param object the object to be rendered
	 * @param camera the camera view
	 */
	public void renderMesh(GameObject object, Camera camera) {
		GL30.glBindVertexArray(object.getMesh().getVAO());
		GL30.glEnableVertexAttribArray(0);
		GL30.glEnableVertexAttribArray(1);
		GL30.glEnableVertexAttribArray(2);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, object.getMesh().getIBO());
		object.getMesh().getMaterial().bind();
		shader.bind();
		shader.setUniform("model", Matrix4f.transform(object.getPosition(), object.getRotation(), object.getScale()));
		shader.setUniform("view", Matrix4f.view(camera.getPosition(), camera.getRotation()));
		shader.setUniform("projection", window.getProjectionMatrix());
		GL11.glDrawElements(GL11.GL_TRIANGLES, object.getMesh().getIndices().length, GL11.GL_UNSIGNED_INT, 0);
		shader.unbind();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
		GL30.glDisableVertexAttribArray(0);
		GL30.glDisableVertexAttribArray(1);
		GL30.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
	}
}