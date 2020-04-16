package ooga.view.game_view.map.map2d;

import java.io.IOException;
import ooga.view.engine.graphics.Material;
import ooga.view.engine.utils.cyberpunk2d.Text2DMapReader;

public class Tile2DController {
  private static String path = "/view/textures/2d/cyberpunk/map/subtitles";
  private boolean walkable;
  private Material material;

  public Tile2DController(int map_x, int map_y, Text2DMapReader mapReader) {

    int palette_x = mapReader.getMapCell(map_x, map_y) % mapReader.getPaletteWidth();
    int palette_y = mapReader.getMapCell(map_x, map_y) / mapReader.getPaletteWidth();

    this.material = new Material(String.format("%s/%s_%s.png", path, palette_x, palette_y));
    this.walkable = mapReader.isMapCellWalkable(map_x, map_y);

  }

  public Material getMaterial(){return material;}

  public boolean isWalkable(){return walkable;}

}
