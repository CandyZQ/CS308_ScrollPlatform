package ooga.view.game_view.map.map3d;

import ooga.view.engine.graphics.render.Renderer3D;
import ooga.view.engine.maths.Vector3f;
import ooga.view.engine.objects.Camera;
import ooga.view.engine.utils.cyberpunk3d.LoadCyberpunkModels;
import ooga.view.engine.utils.cyberpunk3d.Text3DMapReader;
import ooga.view.game_view.map.interfaces.MapView;

// mesh for each tile or shared mesh
//
//

public class Map3DView extends MapView {
  private static final Vector3f MAP_SCALE_MODEL = new Vector3f(1.0f, 1.0f, 1.0f);
  private static final float INITIAL_X_POS = 0f;
  private static final float INITIAL_Y_POS = 0f;
  private static final float INITIAL_Z_POS = 0f;
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
      Vector3f pos = getTilePos(mapReader.isTiLeNewline(i), mapReader.getTilePosDelta(i), mapReader.getMaxShape(i));
      tiles[i] = new Tile3DView(type, rot, pos, MAP_SCALE_MODEL);
    }

  }

  private Vector3f getTilePos(boolean isNewline, Vector3f delta, Vector3f shape){
    Vector3f res = Vector3f.add(currentPosition, delta);
    currentPosition = new Vector3f(res.getX(), res.getY(), res.getZ());
    if (isNewline){
      currentPosition.setX(INITIAL_X_POS);
      currentPosition.setY(currentPosition.getY() + shape.getY());
    }
    else{
      currentPosition.setX(currentPosition.getX() + shape.getX());
    }
    return res;
  }

  public void renderMesh(Renderer3D renderer, Camera camera) {
    for (int i=0; i<mapReader.getTileAmounts(); i++){
      renderer.renderMesh(tiles[i].getGameObject(), camera);
    }
  }

  @Override
  public void createMesh() {
    LoadCyberpunkModels.createAllTileMeshes();
  }

  @Override
  public void destroyMesh() {
    LoadCyberpunkModels.destroyAllTileMeshes();
  }
}
