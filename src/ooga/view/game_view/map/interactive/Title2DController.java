package ooga.view.game_view.map.interactive;

import ooga.view.engine.graphics.Material;
import ooga.view.engine.utils.ImageLoader;
import ooga.view.engine.utils.TextMapReader;

public class Title2DController {
  private static String path = "/view/textures/2d/cyberpunk/map/Itch_release_raw_tileset_01.png";
  private static int id_idx = 0;
  private boolean walkable;
  private int map_x, map_y;
  private int palette_x, palette_y;
  private Material material;
  private int id;

  public Title2DController(int map_x, int map_y, TextMapReader mapReader){

    this.id = id_idx++;
    this.map_x = map_x;
    this.map_y = map_y;

    this.palette_x = mapReader.getMapCell(map_x, map_y)%mapReader.getPaletteWidth();
    this.palette_y = mapReader.getMapCell(map_x, map_y)/mapReader.getPaletteWidth();

    this.material = new Material(path);
    this.material.createTitledTexture(palette_x,palette_y,mapReader.getTitlePixel(), mapReader.getTitlePixel());

    this.walkable = mapReader.isMapCellWalkable(map_x, map_y);

  }

  public Material getMaterial(){return material;}

  public boolean isWalkable(){return walkable;}

}
