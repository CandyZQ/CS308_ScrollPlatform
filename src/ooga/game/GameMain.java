package ooga.game;

import javafx.application.Application;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.controller.gamecontrol.GameController;
import ooga.controller.gamecontrol.PauseControl;
import ooga.data.DataLoader;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.DataStorer;
import ooga.data.DataStorerAPI;
import ooga.model.Model;

public class GameMain extends Application {
  // TODO: delete place holders after dataloader is implemented
  public static final String TITLE = "Zelda";
  public static final int WIDTH = 1070;
  public static final int HEIGHT = 820;
  public static final boolean RESIZABLE = true;

  private WindowControl myWindowControl;
  private DataLoaderAPI myDataLoader;
  private DataStorerAPI myDataStorer;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage currentStage) throws DataLoadingException {

    myDataStorer = new DataStorer();
    myDataLoader = ((DataStorer) myDataStorer).getDataLoader();
    myWindowControl = new WindowControl(currentStage, myDataStorer);
    myWindowControl.showWindow(TITLE, HEIGHT, WIDTH, RESIZABLE);
  }
}
