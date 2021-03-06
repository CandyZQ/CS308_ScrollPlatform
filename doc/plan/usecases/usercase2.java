package ooga.usecases;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.view.game_menu.AbstractGameMenuView;
import ooga.view.game_menu.GameMenuView;

public class usercase2 extends Application {

    private WindowControl myMenu;
    private GameMenuView myMenuView;

    @Override
    public void start(Stage primaryStage) throws Exception {

        myMenu = new WindowControl(primaryStage);

        //this technically happens within myWindowControl
        myMenuView = new AbstractGameMenuView();
        Button myExitButton = myMenuView.getExitGameButton();
        myExitButton.setOnAction(e->primaryStage.close());

        Scene scene = myMenuView.getMenuView().getScene();
        primaryStage.setScene(scene);
        primaryStage.show();

        //when Exit Button is pressed, primary stage closes and exits the program

    }
}
