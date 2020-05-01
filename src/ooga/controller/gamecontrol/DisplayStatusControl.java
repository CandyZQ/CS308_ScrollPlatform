package ooga.controller.gamecontrol;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.WindowControl;
import ooga.data.DataStorerAPI;
import ooga.view.game_menu.DisplayStatusView;
import ooga.view.game_menu.PauseMenu;
import ooga.view.game_view.game_state.state2d.GameState2DView;

import java.util.List;
import java.util.Map;

/**
 * Controller for the status page that displays the player scores and lives and updates live during game play
 *
 * @author Lucy
 */
public class DisplayStatusControl {

    private Stage myStage;
    private DisplayStatusView myStatus;


    public DisplayStatusControl(){
        myStage = new Stage();
        myStatus = new DisplayStatusView();
        myStage.setScene(myStatus.getMenuView());
    }

    /**
     * shows this stage
     */
    public void showMenu(){
        myStage.show();
    }

    /**
     * ask view to set the background to light/dark
     * @param dark
     */
    public void setMode(boolean dark) {
        myStatus.switchMode(dark);
    }

    /**
     * ask view to set language of button/label
     * @param language string  that is the name of language
     */
    public void setLanguage(String language){
        myStatus.setLanguage(language);
    }

    private void updateScore(Map<Integer, Integer> sScoreList) {
        myStatus.updateScore(sScoreList);
    }

    /**
     * ask view to set background color
     * @param color color
     */
    public void setColor(Color color) {
        myStatus.changColor(color);
    }

    private void updateLife(Map<Integer, Integer> lifeList) {
        myStatus.updateLife(lifeList);
    }

    /**
     * updates the score and life printed in view
     * @param sScoreList list of id:score
     * @param lifeList list of id:life
     */
    public void update(Map<Integer, Integer> sScoreList, Map<Integer, Integer> lifeList) {
        updateScore(sScoreList);
        updateLife(lifeList);
    }
}
