package ooga.view.engine.utils.cyberpunk3d;

import ooga.view.engine.maths.Vector3f;

public class MeasureMapModel {

  public static Vector3f getTileMeasurement(String type) {
    LoadCyberpunkModels.loadTileDict();
    return new Vector3f(LoadCyberpunkModels.getTileMesh(type).getMaxWidth(),
        LoadCyberpunkModels.getTileMesh(type).getMaxHeight(),
        LoadCyberpunkModels.getTileMesh(type).getMaxDepth());
  }

}
