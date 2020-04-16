package ooga.view.engine.utils;

import java.awt.image.BufferedImage;

public class SpriteCropper {

  String path = "/view/textures/2d/cyberpunk/map/Itch_release_raw_tileset_01.png";

  public SpriteCropper() {
    for (int i = 0; i < 15; i++) {
      for (int j = 0; j < 8; j++) {
        SpriteSheet palette = new SpriteSheet(ImageLoader.loadImage(path));
        String imageName = palette.crop(j, i, 8, 8, true);

      }
    }
  }

  public SpriteCropper(String path, int amount, String dir, String tag) {
    //System.out.println(path);
    BufferedImage totalFrames = ImageLoader.loadImage(path.replace('\\', '/'));
    SpriteSheet palette = new SpriteSheet(ImageLoader.loadImage(path.replace('\\', '/')));

    for (int j = 0; j < amount; j++) {
      String imageName = palette
          .crop(j, 0, totalFrames.getWidth() / amount, totalFrames.getHeight(), true, tag, dir);
    }

  }
}
