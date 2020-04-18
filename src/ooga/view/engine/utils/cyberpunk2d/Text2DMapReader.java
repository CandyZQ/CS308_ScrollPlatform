package ooga.view.engine.utils.cyberpunk2d;

import ooga.view.engine.utils.FileUtils;

public class Text2DMapReader {
  private String path;
  private String separator = ",";
  private int mapWidth;
  private int mapHeight;
  private int tilePixel;
  private int paletteWidth;
  private int paletteHeight;
  private int[][] mapCells;
  private int numWalls;
  private int[] walls;

  public Text2DMapReader(String path){

    String mapText = FileUtils.loadAsString(path, "");
    String[] mapContent = mapText.split(separator);

    int idx = 0;

    mapHeight = Integer.parseInt(mapContent[idx++]);
    mapWidth = Integer.parseInt(mapContent[idx++]);
    tilePixel = Integer.parseInt(mapContent[idx++]);
    paletteHeight = Integer.parseInt(mapContent[idx++]);
    paletteWidth = Integer.parseInt(mapContent[idx++]);
    numWalls = Integer.parseInt(mapContent[idx++]);

    walls = new int[numWalls];
    //System.out.println("start");

    for (int i=0; i<numWalls; i++){
      int pixel_x = Integer.parseInt(mapContent[idx++]);
      int pixel_y = Integer.parseInt(mapContent[idx++]);
      //System.out.println(pixel_x);
      //System.out.println(pixel_y);
      walls[i] = pixel_x*paletteWidth + pixel_y - 1;
      //System.out.println(walls[i]);
    }

    mapCells = new int[mapHeight][mapWidth];
    int x=0, y=0;

    //System.out.println("start");
    for (int i=idx; i<mapContent.length; i++){
      mapCells[x][y++] = Integer.parseInt(mapContent[i]);
      //System.out.println(mapCells[x][y-1]);
      x = y == mapWidth ? x+1:x;
      y = y == mapWidth ? 0:y;

    }
    //System.out.println("end");

  }

  public int getMapWidth() {
    return mapWidth;
  }

  public int getMapHeight(){
    return mapHeight;
  }

  public int getTilePixel(){
    return tilePixel;
  }

  public int getMapCell(int x, int y){
    return mapCells[x][y];
  }

  public int getPaletteWidth(){return paletteWidth;}

  public int getPaletteHeight(){return paletteHeight;}

  public boolean isMapCellWalkable(int x, int y){
    for (int wall : walls) {
      if (wall == mapCells[x][y]) {
        return false;
      }
    }
    return true;
  }
}
