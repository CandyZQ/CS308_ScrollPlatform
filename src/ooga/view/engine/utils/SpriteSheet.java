package ooga.view.engine.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This Class is responsible for splitting up sprite sheets into multiple images.
 * @author Kenneth
 *
 */

public class SpriteSheet {
  private String GLOBAL = "resources";
  private String MAPTITLES_SAVE_PATH = "/view/textures/2d/cyberpunk/map/subtitles/";
  private BufferedImage sheet;

  /**
   * This constructor receives the image that needs to be modified.
   * @param sheet
   */
  public SpriteSheet(BufferedImage sheet){
    this.sheet = sheet;
  }

  /**
   * This crops a sprite sheet to get the subimage within the picture.
   * @param x
   * @param y
   * @param width
   * @param height
   * @return
   */
  public String crop(int x, int y, int width, int height, boolean isMap) {
    BufferedImage cropped = sheet.getSubimage(x*width, y*height, width, height);
    String imageName = String.format("%s_%s.png", String.valueOf(x),String.valueOf(y));
    saveCroppedImage(imageName, cropped);
    return String.format("%s%s", MAPTITLES_SAVE_PATH, imageName);
  }

  public void saveCroppedImage(String filename, BufferedImage cropped){
    try {
      File outputfile = new File(String.format("%s%s%s",GLOBAL, MAPTITLES_SAVE_PATH, filename));
      ImageIO.write(cropped, "png", outputfile);
    } catch (IOException e) {
      System.err.println("Cannot save this cursed image");
    }
  }

  public String crop(int x, int y, int width, int height, boolean isMap, String tag, String dir) {
    BufferedImage cropped = sheet.getSubimage(x*width, y*height, width, height);
    String imageName = String.format("%s_%s_%s.png", tag, String.valueOf(x), String.valueOf(y));
    saveCroppedImage(imageName, dir, cropped);
    return String.format("%s%s", dir, imageName);
  }

  public void saveCroppedImage(String filename, String dir, BufferedImage cropped){
    try {
      File outputfile = new File(String.format("%s%s/%s",GLOBAL, dir, filename));
      ImageIO.write(cropped, "png", outputfile);
    } catch (IOException e) {
      System.err.println("Cannot save this cursed image");
    }
  }

}
