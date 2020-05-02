package ooga.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.controller.gamecontrol.GameController;
import ooga.data.DataStorerAPI;
import ooga.view.game_menu.FinishScreen;
import ooga.view.game_menu.PauseMenu;
import ooga.view.game_view.game_state.state2d.GameState2DView;

import java.util.List;
import java.util.Map;

/**
 * Control for the finish screen
 *
 * @author Lucy
 */
public class FinishControl {

    private Stage myStage;
    private FinishScreen myFinishScreen;
    private Button myBackButton;


    private WindowControl myWindowControl;

    private GameController myGameController;

    /**
     * Create the finish control
     * @param gameController
     */
    public FinishControl(GameController gameController){
        myGameController = gameController;
        reset();
        myStage = new Stage();
        myFinishScreen = new FinishScreen();
        myStage.setScene(myFinishScreen.getMenuView());
        setUpButton();
    }

    private void reset() {
        myGameController.reset();
    }

    /**
     * show the finish screen
     * @param win       whether the player won the game or not
     * @param id        the id of the plaeyr
     * @param score     the score of the player
     */
    public void showMenu(boolean win, int id, int score){
        myFinishScreen.setWin(win, id, score);
        myStage.show();
    }

    private void setUpButton(){
        myBackButton = myFinishScreen.getBackToMenuButton();
        myBackButton.setOnAction(e->backToMenu());
    }

    private void backToMenu(){
        myGameController.save();
        myStage.close();
        myWindowControl.showWindowMenu();
    }

    private void save(){
        myGameController.save();
    }

    /**
     * set the window control to input; window control is used for back to menu (need
     * to reopen window control menu)
     * @param windowControl window control input
     */
    public void setWindowControl(WindowControl windowControl) {
        myWindowControl = windowControl;
    }

    /**
     * ask the view to change background to light / dark mode
     * @param dark boolean that determines which to use
     */
    public void setMode(boolean dark) {
        myFinishScreen.switchMode(dark);
    }

    /**
     * ask view to change language of text / label / button
     * @param language string that is the name of the language
     */
    public void setLanguage(String language){
        myFinishScreen.setLanguage(language);
    }

    /**
     * ask view to set the color of the background
     * @param color color
     */
    public void setColor(Color color) {
        myFinishScreen.changColor(color);
    }

    public void setScore(Map<Integer, Integer> sScoreList) {
        myFinishScreen.updateScore(sScoreList);
    }
}
