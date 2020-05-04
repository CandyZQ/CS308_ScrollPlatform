package ooga.view.engine.utils.cyberpunk2d;

import ooga.view.engine.utils.ImageLoader;
import ooga.view.engine.utils.SpriteSheet;

/**
 * a temporary util class that crops tiles
 * @author qiaoyi fang
 */
public class GenerateCroppedTitles {
  String path = "/view/textures/2d/cyberpunk/map/Itch_release_raw_tileset_01.png";

  public GenerateCroppedTitles(){
    for (int i=0; i<15; i++){
      for (int j=0; j<8;j++){
        SpriteSheet palette = new SpriteSheet(ImageLoader.loadImage(path));
        String imageName = palette.crop(j,i,8, 8, true);
      }
    }

  }
}
