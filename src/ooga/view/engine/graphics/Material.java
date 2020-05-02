package ooga.view.engine.graphics;

import ooga.view.engine.utils.ImageLoader;
import ooga.view.engine.utils.SpriteSheet;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.GL_TEXTURE_WRAP_T;
import static org.lwjgl.opengl.GL11C.GL_TEXTURE_MIN_FILTER;

public class Material {
	private static int BYTES_PER_PIXEL = 3; //4 for RGBA, 3 for RGB
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
			//System.out.println(path);
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

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public int getTextureID() {
		return textureID;
	}

	public void bind(){
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL13.glBindTexture(GL11.GL_TEXTURE_2D, textureID);
	}

	public static int loadTexture(BufferedImage image){
		if (image == null) {
			return 0;
		}

		int[] pixels = new int[image.getWidth() * image.getHeight()];
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

		ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); //4 for RGBA, 3 for RGB

		for(int y = 0; y < image.getHeight(); y++){
			for(int x = 0; x < image.getWidth(); x++){
				int pixel = pixels[y * image.getWidth() + x];
				buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
				buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
				buffer.put((byte) (pixel & 0xFF));               // Blue component
				buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
			}
		}

		buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS

		// You now have a ByteBuffer filled with the color data of each pixel.
		// Now just create a texture ID and bind it. Then you can load it using
		// whatever OpenGL method you want, for example:

		int textureID = GL20.glGenTextures();
		GL20.glBindTexture(GL20.GL_TEXTURE_2D, textureID);

		//setup wrap mode
		GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

		//setup texture scaling filtering
		GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL20.glTexParameteri(GL20.GL_TEXTURE_2D, GL20.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

		//Send texel data to OpenGL
		GL20.glTexImage2D(GL20.GL_TEXTURE_2D, 0, GL20.GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, buffer); //GL_RGBA8 was GL_RGB8A

		return textureID;
	}
}