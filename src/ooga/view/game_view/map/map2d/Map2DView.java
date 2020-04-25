package ooga.view.game_view.map.map2d;

import ooga.view.engine.graphics.render.Renderer2D;
import ooga.view.engine.utils.Test;
import ooga.view.engine.utils.cyberpunk2d.Text2DMapReader;
import ooga.view.game_view.map.interfaces.MapView;

public class Map2DView extends MapView {
  private Text2DMapReader mapReader;
  private Tile2DView[] titles;
  private Map2DController controller;

  public Map2DView(String path) {
    this.mapReader = new Text2DMapReader(path);
    titles = new Tile2DView[mapReader.getMapWidth()*mapReader.getMapHeight()];

    int idx = 0;
    for (int i=0; i<mapReader.getMapHeight(); i++){
      for (int j=0; j<mapReader.getMapWidth(); j++){
        titles[idx++] = new Tile2DView(i, j, mapReader);
        //Test.printVector2f(titles[idx - 1].getCenterLocation());
        //Test.printThreeMeshVertices(titles[idx-1].getGameObject().getMesh());
        //System.out.println(titles[idx-1].getGameObject().getMesh().getMaxHeight());
        //System.out.println(titles[idx-1].getGameObject().getMesh().getMaxWidth());
      }
    }

  }

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

  public int getTileTotal(){return titles.length;}

  public Tile2DView getTile(int idx){return titles[idx];}

  public boolean isWalkable(int i, int j){
    return titles[getIndex(i,j)].isWalkable();
  }

  public boolean isWalkable(int i){
    return titles[i].isWalkable();
  }

  public float getPosX(int i, int j){return titles[getIndex(i,j)].getGameObject().getMesh().getVertices()[3].getPosition().getX();}

  public float getNegX(int i, int j){return titles[getIndex(i,j)].getGameObject().getMesh().getVertices()[0].getPosition().getX();}

  public float getPosY(int i, int j){return titles[getIndex(i,j)].getGameObject().getMesh().getVertices()[0].getPosition().getY();}

  public float getNegY(int i, int j){return titles[getIndex(i,j)].getGameObject().getMesh().getVertices()[1].getPosition().getY();}

  private int getIndex(int i, int j){return i*mapReader.getMapHeight()+j;}
}
