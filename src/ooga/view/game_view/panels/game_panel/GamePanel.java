/*
package ooga.view.game_view.panels.game_panel;

import static org.lwjgl.opengl.GL11.GL_NEAREST;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_RGBA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MAG_FILTER;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_MIN_FILTER;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glGenTextures;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTexImage2D;
import static org.lwjgl.opengl.GL11.glTexParameterf;
import static org.lwjgl.opengl.GL11.glVertex3d;

import java.awt.Color;
import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;

public class GamePanel {

  private final int offset = 32;
  private final int WINDOW_WIDTH = 1366;
  private final int WINDOW_HEIGHT = 768;

*/
  /*
	This method draws text on the screen. The parameters (x,y) allow the user to place the
	text to its intended location. The user can also decide the font size.
	 */
 /* private void drawText(String text, float x, float y, int fontSize) throws Exception{
    text = text.toUpperCase();
    text = text.replace("", " ");
    float startX = x, startY = y;

    for (int i = 0; i < text.length(); i++){
      int ascii = text.charAt(i) - offset;
      int charCnt = 0;

      //finds the location of the every character within the texture
      for (int j = 0; j < charTex.getBI().getWidth(); j++){
        Colour c = charTex.getPixel(j, 0);
        if (charCnt > ascii)
          break;
        if (c.getR() == yellow.getR() && c.getG() == yellow.getG() && c.getB() == yellow.getB()){
          charCnt++;
          end = j;
        }
        if (c.getR() == blue.getR() && c.getG() == blue.getG() && c.getB() == blue.getB()){
          start = j + 1;
        }
      }

      //stores the starting and ending pixel for that specific character
      int x_length = end - start;
      float endX = (startX * WINDOW_WIDTH + x_length * fontSize) / WINDOW_WIDTH;
      float endY = (startY * WINDOW_HEIGHT - charTex.getBI().getHeight() * fontSize) / WINDOW_HEIGHT;

      //renders the texture of the character onto a GL_QUAD
      glBegin(GL_QUADS);
      glTexCoord2d((double)start / charTex.getBI().getWidth(), 0);
      double adjust = -0.35;
      glVertex3d(startX, startY, adjust);
      glTexCoord2d((double)end / charTex.getBI().getWidth(), 0);
      glVertex3d(endX, startY, adjust);
      glTexCoord2d((double)end / charTex.getBI().getWidth(), 1);
      glVertex3d(endX, endY, adjust);
      glTexCoord2d((double)start / charTex.getBI().getWidth(), 1);
      glVertex3d(startX, endY, adjust);
      glEnd();
      startX = endX;
    }
  }

  //changes the colour of the pixel based on user input
  public void setPixel(int col) throws Exception {
    int width = bi.getWidth();
    int height = bi.getHeight();
    Colour pink = new Colour(255,0,255);
    //update the pixel colour inside the pixels_raw[] array
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Colour c = getPixel(i,j);
        if (c.getR() == pink.getR() && c.getG() == pink.getG() && c.getB() == pink.getB()) {
          pixels_raw[j * bi.getWidth() + i] = col;
        }
      }
    }
    ByteBuffer pixels = BufferUtils.createByteBuffer(width*height*4);
    //loop through the image pixels, and load the colour
    for (int i = 0; i < width; i++){
      for (int j = 0; j < height; j++){
        if (i * height + j < pixels_raw.length){
          Color temp = new Color(pixels_raw[i * height + j]);
          pixels.put((byte)temp.getRed());
          pixels.put((byte)temp.getGreen());
          pixels.put((byte)temp.getBlue());
          pixels.put((byte)temp.getAlpha());
        }
      }
    }
    pixels.flip();
    id = glGenTextures();
    glBindTexture(GL_TEXTURE_2D, id);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, pixels);
  }
}*/
