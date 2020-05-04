package ooga.view.engine.graphics;

import java.nio.FloatBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryUtil;

import ooga.view.engine.maths.Matrix4f;
import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.FileUtils;

/**
 * a class that implements shader
 * @author codingAP
 */
public class Shader {
	private String vertexFile, fragmentFile;
	private int vertexID, fragmentID, programID;

	/**
	 * constructor
	 * @param vertexPath path for vertex shader
	 * @param fragmentPath path for fragment shader
	 */
	public Shader(String vertexPath, String fragmentPath) {
		vertexFile = FileUtils.loadAsString(vertexPath, "\n");
		fragmentFile = FileUtils.loadAsString(fragmentPath,"\n");
	}

	/**
	 * create the shaders
	 */
	public void create() {
		programID = GL20.glCreateProgram();
		vertexID = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
		
		GL20.glShaderSource(vertexID, vertexFile);
		GL20.glCompileShader(vertexID);
		
		if (GL20.glGetShaderi(vertexID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Vertex Shader: " + GL20.glGetShaderInfoLog(vertexID));
			return;
		}
		
		fragmentID = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
		
		GL20.glShaderSource(fragmentID, fragmentFile);
		GL20.glCompileShader(fragmentID);
		
		if (GL20.glGetShaderi(fragmentID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Fragment Shader: " + GL20.glGetShaderInfoLog(fragmentID));
			return;
		}
		
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
		
		GL20.glLinkProgram(programID);
		if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == GL11.GL_FALSE) {
			System.err.println("Program Linking: " + GL20.glGetProgramInfoLog(programID));
			return;
		}
		
		GL20.glValidateProgram(programID);
		if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == GL11.GL_FALSE) {
			System.err.println("Program Validation: " + GL20.glGetProgramInfoLog(programID));
			return;
		}
	}

	/**
	 * get the uniform location
	 * @param name name
	 * @return location
	 */
	public int getUniformLocation(String name) {
		return GL20.glGetUniformLocation(programID, name);
	}

	/**
	 * set the uniform location
	 * @param name name
	 * @param value value
	 */
	public void setUniform(String name, float value) {
		GL20.glUniform1f(getUniformLocation(name), value);
	}

	/**
	 * set the uniform location
	 * @param name name
	 * @param value value
	 */
	public void setUniform(String name, int value) {
		GL20.glUniform1i(getUniformLocation(name), value);
	}

	/**
	 * set the uniform location
	 * @param name name
	 * @param value value
	 */
	public void setUniform(String name, boolean value) {
		GL20.glUniform1i(getUniformLocation(name), value ? 1 : 0);
	}

	/**
	 * set the uniform location
	 * @param name name
	 * @param value value
	 */
	public void setUniform(String name, Vector2f value) {
		GL20.glUniform2f(getUniformLocation(name), value.getX(), value.getY());
	}

	/**
	 * set the uniform location
	 * @param name name
	 * @param value value
	 */
	public void setUniform(String name, Vector3f value) {
		GL20.glUniform3f(getUniformLocation(name), value.getX(), value.getY(), value.getZ());
	}

	/**
	 * set the uniform location
	 * @param name name
	 * @param value value
	 */
	public void setUniform(String name, Matrix4f value) {
		FloatBuffer matrix = MemoryUtil.memAllocFloat(Matrix4f.SIZE * Matrix4f.SIZE);
		matrix.put(value.getAll()).flip();
		GL20.glUniformMatrix4fv(getUniformLocation(name), true, matrix);
	}

	/**
	 * bind the shader
	 */
	public void bind() {
		GL20.glUseProgram(programID);
	}

	/**
	 * unbind the shader
	 */
	public void unbind() {
		GL20.glUseProgram(0);
	}

	/**
	 * destroy the shader
	 */
	public void destroy() {
		GL20.glDetachShader(programID, vertexID);
		GL20.glDetachShader(programID, fragmentID);
		GL20.glDeleteShader(vertexID);
		GL20.glDeleteShader(fragmentID);
		GL20.glDeleteProgram(programID);
	}
}