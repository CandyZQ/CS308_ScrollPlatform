package ooga.view.game_view.map.map3d;

import ooga.view.engine.graphics.render.Renderer3D;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.Camera;
import ooga.view.engine.objects.GameObject;
import ooga.view.engine.utils.cyberpunk3d.LoadCyberpunkModels;
import ooga.view.engine.utils.cyberpunk3d.Text3DMapReader;
import ooga.view.game_view.map.interfaces.MapView;


public class Map3DView extends MapView {
  private static final Vector3f MAP_SCALE_MODEL = new Vector3f(1.0f, 1.0f, 1.0f);
  private static final float INITIAL_X_POS = 0f;
  private static final float INITIAL_Y_POS = 0f;
  private static final float INITIAL_Z_POS = 0f;
  private static final float SCALE = 1f;
  private static Vector3f currentPosition = new Vector3f(INITIAL_X_POS, INITIAL_Y_POS, INITIAL_Z_POS);
  private Text3DMapReader mapReader;
  private Tile3DView[] tiles;

  public Map3DView(String path){

    LoadCyberpunkModels.loadTileDict();
    mapReader = new Text3DMapReader(path);
    tiles = new Tile3DView[mapReader.getTileAmounts()];
    for (int i=0; i<mapReader.getTileAmounts(); i++){
      String type = mapReader.getTileType(i);
      Vector3f rot = mapReader.getTileRotation(i);
      Vector3f shape = (i==0?new Vector3f(0,0,0):mapReader.getMaxShape(i-1));
      boolean isNewline = (i != 0 && mapReader.isTiLeNewline(i - 1));
      Vector3f pos = getTilePos(isNewline, mapReader.getTilePosDelta(i), shape);
      tiles[i] = new Tile3DView(type, rot, pos, MAP_SCALE_MODEL);
    }

  }

  private Vector3f getTilePos(boolean isNewline, Vector3f delta, Vector3f shape){
    currentPosition = Vector3f.add(currentPosition, new Vector3f(delta.getX()*SCALE, delta.getY()*SCALE, delta.getZ()*SCALE));
    if (isNewline){
      currentPosition.setX(INITIAL_X_POS);
      currentPosition.setY(currentPosition.getY() + shape.getY()*SCALE);
    }
    else{
      currentPosition.setX(currentPosition.getX() + shape.getX()*SCALE);
    }
    return currentPosition;
  }

  public void renderMesh(Renderer3D renderer, Camera camera) {
    for (int i=0; i<mapReader.getTileAmounts(); i++){
      renderer.renderMesh(tiles[i].getGameObject(), camera);
    }
  }

  @Override
  public void createMesh() {
    LoadCyberpunkModels.createUsedRotationalTileMeshes();
  }

  @Override
  public void destroyMesh() {
    LoadCyberpunkModels.destroyUsedRotationalTileMeshes();
  }

  private Vector3f adjustRot(Vector3f pos, Vector3f shape){
    Vector3f res = new Vector3f(pos.getX(), pos.getY(), pos.getZ());
    if (pos.getX() > 0){ res.setY(res.getY() - shape.getY());}
    if (pos.getY() > 0){res.setX(res.getX() - shape.getX());}
    if (pos.getZ() == 1) {res.setY(res.getY() - shape.getY());}
    else if (pos.getZ() == 2){res.setY(res.getY() - shape.getY()); res.setX(res.getX() + shape.getX());}
    else if (pos.getZ() == 3){res.setY(res.getY() + shape.getY() - shape.getX()); res.setX(res.getX() + shape.getX());}
    return res;
  }

  private void printVector3f(Vector3f vec){
    System.out.println(String.format("(%s, %s, %s)", vec.getX(), vec.getY(), vec.getZ()));
  }

  public GameObject[] getTileObjects(){
    GameObject[] tileObjects = new GameObject[tiles.length];
    int idx = 0;
    for(Tile3DView tile:tiles){
      tileObjects[idx++] = tile.getGameObject();
    }
    return tileObjects;
  }
}
