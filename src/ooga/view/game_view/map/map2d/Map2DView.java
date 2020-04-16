package ooga.view.game_view.map.map2d;

import java.io.IOException;
import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.utils.cyberpunk2d.Text2DMapReader;
import ooga.view.game_view.map.interfaces.MapView;

public class Map2DView extends MapView {
  private String path;
  private Text2DMapReader mapReader;
  private Tile2DView[] titles;
  private Map2DController controller;

  public Map2DView(String path, float window_x, float window_y) throws IOException {
    this.mapReader = new Text2DMapReader(path);
    titles = new Tile2DView[mapReader.getMapWidth()*mapReader.getMapHeight()];

    float title_x = window_x / mapReader.getMapWidth();
    float title_y = window_y / mapReader.getMapHeight();

    float scale_x = title_x / mapReader.getTitlePixel();
    float scale_y = title_y / mapReader.getTitlePixel();

    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        titles[idx++] = new Tile2DView(i, j, mapReader);
      }
    }

  }

  @Override
  public void createMesh(){
    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        titles[idx++].createMesh();
      }
    }
  }

  @Override
  public void destroyMesh(){
    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        titles[idx++].destroyMesh();
      }
    }
  }

  public void renderMesh(Renderer2D renderer){

    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        renderer.renderMesh(titles[idx++].getGameObject());
      }
    }

  }

  public boolean isWalkable(int i, int j){
    return titles[getIndex(i,j)].isWalkable();
  }

  public float getPosX(int i, int j){return titles[getIndex(i,j)].getGameObject().getMesh().getVertices()[3].getPosition().getX();}

  public float getNegX(int i, int j){return titles[getIndex(i,j)].getGameObject().getMesh().getVertices()[0].getPosition().getX();}

  public float getPosY(int i, int j){return titles[getIndex(i,j)].getGameObject().getMesh().getVertices()[0].getPosition().getY();}

  public float getNegY(int i, int j){return titles[getIndex(i,j)].getGameObject().getMesh().getVertices()[1].getPosition().getY();}

  private int getIndex(int i, int j){return i*mapReader.getMapHeight()+j;}
}
