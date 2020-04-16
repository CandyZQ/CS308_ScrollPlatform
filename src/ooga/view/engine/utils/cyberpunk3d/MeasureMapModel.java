package ooga.view.engine.utils.cyberpunk3d;

import ooga.view.engine.maths.Vector2f;

public class MeasureMapModel {

  public static Vector2f getTileMeasurement(String type){
    LoadCyberpunkModels.loadTileDict();
    return new Vector2f(LoadCyberpunkModels.getTileMesh(type).getMaxWidth(), LoadCyberpunkModels.getTileMesh(type).getMaxHeight());
  }

}
