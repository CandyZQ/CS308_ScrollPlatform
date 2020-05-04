package ooga.view.engine.utils.cyberpunk3d;

import ooga.view.engine.maths.Vector3f;

/**
 * a temporary util class that measure the sprites
 * @author qiaoyi fang
 */
public class MeasureMapModel {

  public static Vector3f getTileMeasurement(String type, Vector3f rotation) {
    if (!LoadCyberpunkModels.isTileDictLoaded()) LoadCyberpunkModels.loadTileDict();
    return new Vector3f(LoadCyberpunkModels.getRotationalTileMesh(type, rotation).getMaxWidth(),
        LoadCyberpunkModels.getRotationalTileMesh(type, rotation).getMaxHeight(),
        LoadCyberpunkModels.getRotationalTileMesh(type, rotation).getMaxDepth());
  }

}
