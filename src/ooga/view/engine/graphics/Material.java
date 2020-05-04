package ooga.view.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import ooga.view.engine.utils.ImageLoader;
import ooga.view.engine.utils.SpriteSheet;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 * a Material class that processes texture
 * @author qiaoyi fang
 */
public class Material {

	private String path;
	private transient Texture texture;
	private float width, height;
	private int textureID;
	
	public Material(String path) {
		this.path = path;
	}

	/**
	 * Read texture image as a whole
	 */
	public void createTexture() {
		try {
			texture = TextureLoader.getTexture(path.split("[.]")[1], Material.class.getResourceAsStream(path), GL11.GL_NEAREST);
			width = texture.getWidth();
			height = texture.getHeight();
			textureID = texture.getTextureID();
		} catch (IOException e) {
			System.err.println("Can't find texture at " + path);
		}
	}

	/**
	 * Read texture as titles
	 */
	public void createTitledTexture(int x, int y) {
		String imageName = String.format("%s/%s_%s.png", path, y, x);

		try{
				texture =  TextureLoader.getTexture(imageName.split("[.]")[1], Material.class.getResourceAsStream(imageName), GL11.GL_NEAREST);
				width = texture.getWidth();
				height = texture.getHeight();
				textureID = texture.getTextureID();
		} catch (IOException e){
			System.err.println("Can't find texture at " + path);
		}

	}

	/**
	 * Read texture as sprites
	 */
	public void createSpriteTexture(int y, int cnt) throws IOException {
		BufferedImage sheet = ImageLoader.loadImage(path);
		int textureWidth = sheet.getWidth() / cnt;
		int textureHeight = sheet.getHeight();
		SpriteSheet Titles = new SpriteSheet(ImageLoader.loadImage(path));
		createTitledTexture(0, y);
	}
	
	public void destroy() {
		GL13.glDeleteTextures(textureID);
	}

	/**
	 * get the texture width
	 * @return width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * get the texture height
	 * @return height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * get the textureID
	 * @return id
	 */
	public int getTextureID() {
		return textureID;
	}

	/**
	 * bind the texture
	 */
	public void bind(){
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL13.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	}

}