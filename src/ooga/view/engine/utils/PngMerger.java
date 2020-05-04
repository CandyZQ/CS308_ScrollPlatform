package ooga.view.engine.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

/**
 * a util class that merges two png images (stack one image onto the other)
 * @author qiaoyi fang
 */
public class PngMerger {
  public PngMerger(){}

  /**
   * merges the list of images
   * @param dir image directory
   * @param paths relative image paths
   * @param newpath where the merged image is to be stored
   * @return the absolute stored path
   * @throws IOException
   */
  public static String mergePngPics(String dir, List<String> paths, String newpath)
      throws IOException {

    List<String> fullPaths = new ArrayList<>();
    for (String path:paths){
      fullPaths.add(String.format("%s/%s", dir, path));
    }

    BufferedImage combined = new BufferedImage(ImageLoader.getImageWidth(fullPaths.get(0)), ImageLoader.getImageHeight(fullPaths.get(0)), BufferedImage.TYPE_INT_ARGB);

    Graphics g = combined.getGraphics();
    for(String path:fullPaths){
      BufferedImage layer = ImageLoader.loadImage(path);
      g.drawImage(layer, 0, 0, null);
    }

    g.dispose();

    String newImagePath = String.format("resources/%s/%s", dir, newpath);
    File outputfile = new File(newImagePath);
    ImageIO.write(combined, "png", outputfile);

    return newImagePath;
  }

  /**
   * merge the image to the center of canvas
   * @param canvasPath the path of the transparent background
   * @param pic the relative path to the picture
   * @param newpath the relative image path to be stored
   * @return the absolute newpath
   * @throws IOException
   */
  public static String mergeCanvasPic(String canvasPath, String pic, String newpath)
      throws IOException {

    BufferedImage combined = new BufferedImage(ImageLoader.getImageWidth(canvasPath), ImageLoader.getImageHeight(canvasPath), BufferedImage.TYPE_INT_ARGB);

    Graphics g = combined.getGraphics();

    BufferedImage canvas = ImageLoader.loadImage(canvasPath);
    g.drawImage(canvas, 0, 0, null);

    BufferedImage layer = ImageLoader.loadImage(pic);
    g.drawImage(layer, (canvas.getWidth()-layer.getWidth())/2, (canvas.getHeight() - layer.getHeight())/2, null);

    g.dispose();

    String newImagePath = String.format("resources/%s", newpath);
    File outputfile = new File(newImagePath);
    ImageIO.write(combined, "png", outputfile);

    return newImagePath;
  }

}
