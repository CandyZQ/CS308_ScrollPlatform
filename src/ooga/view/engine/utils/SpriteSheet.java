package ooga.view.engine.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * a class for splitting up sprite sheets into multiple images.
 * @author kenneth, qiaoyi fang
 *
 */

public class SpriteSheet {
  private String GLOBAL = "resources";
  private String MAPTITLES_SAVE_PATH = "/view/textures/2d/cyberpunk/map/subtitles/";
  private BufferedImage sheet;

  /**
   * a constructor that receives the image that needs to be modified.
   * @param sheet
   */
  public SpriteSheet(BufferedImage sheet){
    this.sheet = sheet;
  }

  /**
   * crops a sprite sheet to get the subimage within the picture (for map tile)
   * @param x xth in row
   * @param y yth in column
   * @param width the cropped width
   * @param height the cropped height
   * @return the saved path
   */
  public String crop(int x, int y, int width, int height, boolean isMap) {
    BufferedImage cropped = sheet.getSubimage(x*width, y*height, width, height);
    String imageName = String.format("%s_%s.png", String.valueOf(x),String.valueOf(y));
    saveCroppedImage(imageName, cropped);
    return String.format("%s%s", MAPTITLES_SAVE_PATH, imageName);
  }

  /**
   * save the cropped image
   * @param filename filename
   * @param cropped the cropped image
   */
  public void saveCroppedImage(String filename, BufferedImage cropped){
    try {
      File outputfile = new File(String.format("%s%s%s",GLOBAL, MAPTITLES_SAVE_PATH, filename));
      ImageIO.write(cropped, "png", outputfile);
    } catch (IOException e) {
      System.err.println("Cannot save this cursed image");
    }
  }

  /**
   * crops a sprite sheet to get the subimage within the picture (sprites)
   * @param x xth in row
   * @param y yth in column
   * @param width the cropped width
   * @param height the cropped height
   * @return the saved path
   */
  public String crop(int x, int y, int width, int height, boolean isMap, String tag, String dir) {
    BufferedImage cropped = sheet.getSubimage(x*width, y*height, width, height);
    String imageName = String.format("%s_%s_%s.png", tag, String.valueOf(x), String.valueOf(y));
    saveCroppedImage(imageName, dir, cropped);
    return String.format("%s%s", dir, imageName);
  }

  /**
   * save the cropped image
   * @param dir file directory
   * @param filename relative filename
   * @param cropped the cropped image
   */
  public void saveCroppedImage(String filename, String dir, BufferedImage cropped){
    try {
      File outputfile = new File(String.format("%s%s/%s",GLOBAL, dir, filename));
      ImageIO.write(cropped, "png", outputfile);
    } catch (IOException e) {
      System.err.println("Cannot save this cursed image");
    }
  }

}
