package ooga.view.engine.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

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

  public static int getImageWidth(String path){
    return loadImage(path).getWidth();
  }

  public static int getImageHeight(String path){
    return loadImage(path).getHeight();
  }

}
