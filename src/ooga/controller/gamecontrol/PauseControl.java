package ooga.controller.gamecontrol;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.data.DataStorerAPI;
import ooga.view.game_menu.PauseMenu;
import ooga.view.game_view.game_state.state2d.GameState2DView;

import java.util.List;
import java.util.Map;

public class PauseControl {

    private Stage myStage;
    private PauseMenu myPauseMenu;
    private Button myResumeButton;
    private Button myBackButton;
    private Button mySaveButton;

    private List<Button> buttonList;

    private WindowControl myWindowControl;
    private GameState2DView myView;
    private AnimationTimer myTimer;

    private DataStorerAPI myStorer;
    private GameController myGameController;

    public PauseControl(GameController gameController){
        myStage = new Stage();
        myPauseMenu = new PauseMenu();
        myGameController = gameController;
        myStage.setScene(myPauseMenu.getMenuView());
        setUpButton();
    }

    public void showMenu(){
        myTimer.stop();
        myStage.show();
    }

    private void setUpButton(){
        myResumeButton = myPauseMenu.getResumeButton();
        myResumeButton.setOnAction(e->resumeGame());
        myBackButton = myPauseMenu.getBackToMenuButton();
        myBackButton.setOnAction(e->backToMenu());
        mySaveButton = myPauseMenu.getSaveGameButton();
        mySaveButton.setOnAction(e->save());

        //buttonList = List.of(myResumeButton, myBackButton, mySaveButton);
    }

    private void resumeGame(){
        myStage.close();
        myTimer.start();
    }

    private void backToMenu(){
        myGameController.save();
        myStage.close();
        myView.closeWindow();
        myWindowControl.showWindowMenu();
    }

    private void save(){
        myGameController.save();
    }

    /**
     * sets window control to input (used for back to menu)
     * @param windowControl
     */
    public void setWindowControl(WindowControl windowControl) {
        myWindowControl = windowControl;
    }

    /**
     * sets view to input (used to close view window)
     * @param view
     */
    public void setView(GameState2DView view) {
        myView = view;
    }

    /**
     * sets the timer to be in sync with game (used to pause updating the game)
     * @param myTimer
     */
    public void setTimer(AnimationTimer myTimer) {
        this.myTimer = myTimer;
    }

    /**
     * ask view to set the background to light/dark
     * @param dark
     */
    public void setMode(boolean dark) {
        myPauseMenu.switchMode(dark);
    }

    /**
     * ask view to set language of button/label
     * @param language string  that is the name of language
     */
    public void setLanguage(String language){
        myPauseMenu.setLanguage(language);
    }

    /**
     * updates the score printed in view based on current list of score from game control
     * @param sScoreList the current list of score
     */
    public void updateScore(Map<Integer, Integer> sScoreList) {
        myPauseMenu.updateScore(sScoreList);
    }

    /**
     * ask view to set background color
     * @param color color
     */
    public void setColor(Color color) {
        myPauseMenu.changColor(color);
    }

    public void updateLife(Map<Integer, Integer> lifeList) {
        myPauseMenu.updateLife(lifeList);
    }
}
