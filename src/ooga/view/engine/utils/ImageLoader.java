package ooga.view.engine.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * a class that loads image
 * @author qiaoyi fang
 */
public class ImageLoader {
  /**
   * This method tries to load in the selected image from the path given.
   * @param path
   * @return
   */
  public static BufferedImage loadImage(String path){
    try {
      return ImageIO.read(ImageLoader.class.getResourceAsStream(path));
      //Loads in image
    } catch (IOException e) {
      // System.exit(1);
      // If the image cannot be loaded, the window closes
      System.err.println(path + " was not loaded.");
    }
    return null;
  }

  /**
   * get the image width
   * @param path image path
   * @return width
   */
  public static int getImageWidth(String path){
    return loadImage(path).getWidth();
  }

  /**
   * get the image height
   * @param path image path
   * @return height
   */
  public static int getImageHeight(String path){
    return loadImage(path).getHeight();
  }

}
