package ooga.view.engine.graphics;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import ooga.view.engine.maths.Vector2f;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.utils.Test;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.system.MemoryUtil;

public class Mesh {
	private Vertex[] vertices;
	private int[] indices;
	private Material material;
	private int vao, pbo, ibo, cbo, tbo;
	
	public Mesh(Vertex[] vertices, int[] indices, Material material) {
		this.vertices = vertices;
		this.indices = indices;
		this.material = material;
		Mesh.normalize(this);
	}

	public Mesh(Vertex[] vertices, int[] indices, Material material, boolean isMap) {
		this.vertices = vertices;
		this.indices = indices;
		this.material = material;
	}

	public Mesh(Mesh mesh, Vector3f rotation){
		this.vertices = verticesCopy(mesh.vertices);
		this.indices = mesh.getIndices().clone();
		this.material = mesh.getMaterial();
		rotateVertices(rotation);
		Mesh.normalize(this);
	}

	public static Vertex[] verticesCopy(Vertex[] v) {
		Vertex[] ret = new Vertex[v.length];
		for (int i = 0; i < ret.length; i++) {
			ret[i] = new Vertex(v[i]);
		}
		return ret;
	}

	public void setTextureCoords(Vector2f[] newTextureCoords){
		for(int idx=0; idx<newTextureCoords.length; idx++){
			vertices[idx].setTextureCoord(newTextureCoords[idx]);
		}
	}

	public void rotateVertices(Vector3f rotation){
		for (Vertex vertex : this.vertices) {
			vertex.rotate(rotation);
		}
	}

	public void create() {
		material.createTexture();
		
		vao = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vao);
		
		FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] positionData = new float[vertices.length * 3];
		for (int i = 0; i < vertices.length; i++) {
			positionData[i * 3] = vertices[i].getPosition().getX();
			positionData[i * 3 + 1] = vertices[i].getPosition().getY();
			positionData[i * 3 + 2] = vertices[i].getPosition().getZ();
		}
		positionBuffer.put(positionData).flip();
		
		pbo = storeData(positionBuffer, 0, 3);
		
		FloatBuffer textureBuffer = MemoryUtil.memAllocFloat(vertices.length * 2);
		float[] textureData = new float[vertices.length * 2];
		for (int i = 0; i < vertices.length; i++) {
			textureData[i * 2] = vertices[i].getTextureCoord().getX();
			textureData[i * 2 + 1] = vertices[i].getTextureCoord().getY();
		}
		textureBuffer.put(textureData).flip();
		
		tbo = storeData(textureBuffer, 2, 2);
		
		IntBuffer indicesBuffer = MemoryUtil.memAllocInt(indices.length);
		indicesBuffer.put(indices).flip();
		
		ibo = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, ibo);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
	}
	
	private int storeData(FloatBuffer buffer, int index, int size) {
		int bufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, bufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(index, size, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
		return bufferID;
	}
	
	public void destroy() {
		GL15.glDeleteBuffers(pbo);
		GL15.glDeleteBuffers(cbo);
		GL15.glDeleteBuffers(ibo);
		GL15.glDeleteBuffers(tbo);
		
		GL30.glDeleteVertexArrays(vao);
		
		material.destroy();
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public void setVerticesPosition(int i, Vector3f newPos){
		vertices[i].setPosition(newPos);
		destroy();
		create();
	}

	public int[] getIndices() {
		return indices;
	}

	public int getVAO() {
		return vao;
	}

	public int getPBO() {
		return pbo;
	}
	
	public int getCBO() {
		return cbo;
	}
	
	public int getTBO() {
		return tbo;
	}

	public int getIBO() {
		return ibo;
	}
	
	public Material getMaterial() {
		return material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

	public float getMaxWidth(){
		return getMaxX() - getMinX();
	}

	public float getMaxHeight(){
		return getMaxY() - getMinY();
	}

	public float getMaxDepth(){
		return getMaxZ() - getMinZ();
	}

	public Vector2f getCenter(){
		return new Vector2f(getMinX() + (getMaxX()-getMinX())/2.0f, getMinY() + (getMaxY() - getMinY())/2.0f);
	}

	private float getMaxX(){
		float maxX = vertices[0].getPosition().getX();
		for (Vertex v:vertices){
			maxX = (Math.max(maxX, v.getPosition().getX()));
		}
		return maxX;
	}

	private float getMaxY(){
		float maxY = vertices[0].getPosition().getY();
		for (Vertex v:vertices){
			maxY = (Math.max(maxY, v.getPosition().getY()));
		}
		return maxY;
	}

	private float getMinX(){
		float minX = vertices[0].getPosition().getX();
		for (Vertex v:vertices){
			minX = (Math.min(minX, v.getPosition().getX()));
		}
		return minX;
	}

	private float getMinY(){
		float minY = vertices[0].getPosition().getY();
		for (Vertex v:vertices){
			minY = (Math.min(minY, v.getPosition().getY()));
		}
		return minY;
	}

	private float getMaxZ(){
		float maxZ = vertices[0].getPosition().getZ();
		for (Vertex v:vertices){
			maxZ = (Math.max(maxZ, v.getPosition().getZ()));
		}
		return maxZ;
	}

	private float getMinZ(){
		float minZ = vertices[0].getPosition().getZ();
		for (Vertex v:vertices){
			minZ = (Math.min(minZ, v.getPosition().getZ()));
		}
		return minZ;
	}

	private FloatBuffer getPositionalBuffer(){
		FloatBuffer positionBuffer = MemoryUtil.memAllocFloat(vertices.length * 3);
		float[] positionData = new float[vertices.length * 3];
		for (int i = 0; i < vertices.length; i++) {
			positionData[i * 3] = vertices[i].getPosition().getX();
			positionData[i * 3 + 1] = vertices[i].getPosition().getY();
			positionData[i * 3 + 2] = vertices[i].getPosition().getZ();
		}
		positionBuffer.put(positionData).flip();
		return positionBuffer;
	}

	public static Mesh normalize(Mesh mesh){
		float deltaX = mesh.getVertices()[0].getPosition().getX();
		float deltaY = mesh.getVertices()[0].getPosition().getY();
		float deltaZ = mesh.getVertices()[0].getPosition().getZ();

		for (Vertex v:mesh.getVertices()){
			if (deltaX>v.getPosition().getX() ){
				deltaX = v.getPosition().getX();
			}
			if (deltaY>v.getPosition().getY() ){
				deltaY = v.getPosition().getY();
			}
			if (deltaZ>v.getPosition().getZ() ){
				deltaZ = v.getPosition().getZ();
			}
		}

		for(Vertex v:mesh.getVertices()){
			v.setPosition(new Vector3f(v.getPosition().getX() - deltaX, v.getPosition().getY() - deltaY, v.getPosition().getZ() - deltaZ));
		}

		return mesh;
	}

	public static Vector3f getAgentBackViewpoint(Mesh agentMesh){
		return null;
	}

	public static Vector3f getAgentFront(Mesh agentMesh){
		return null;
	}

}