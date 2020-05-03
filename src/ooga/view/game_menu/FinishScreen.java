package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ooga.view.game_menu.pretty.PrettyButtons;

import java.util.*;


/**
 * Finish screen, displays win or lose and offers back to menu button
 *
 * @author Lucy
 */
public class FinishScreen implements MenuView {

    private PrettyButtons BackToMenuButton;
    private List<PrettyButtons> myButtonList = new ArrayList<>();
    private VBox vBox;
    private Scene myScene;
    private boolean dark;
    private String myLanguage = "English";

    private Label finishText, scorelist;

    private boolean win = false;
    private int score = 0;
    private int id = 0;

    private static final int ZIZE = 800;
    private static final String resourceName = "menu";

    public FinishScreen(){
        setUpLabel();
        setUpButton();
        setUpVBox();
        myScene = new Scene(vBox, ZIZE, ZIZE);
    }

    private void setUpLabel() {
        finishText = new Label();
        finishText.setFont(Constants.font);
        scorelist = new Label();
        scorelist.setFont(Constants.font);
    }

    /**
     * Change the text according to if the player won the game or not
     * @param win true = win, false = dead
     * @param id id of player that won
     * @param score score of the player
     */
    public void setWin(boolean win, int id, int score){
        this.win = win;
        this.id = id;
        this.score = score;
        var resource = ResourceBundle.getBundle(resourceName, new Locale(myLanguage));
        if(win)finishText.setText(resource.getString("playerf") + " " + id + " " + resource.getString("win"));
        else finishText.setText(resource.getString("playerf") + " " + id + " " + resource.getString("dead"));

    }


    /**
     *
     * @return back to menu button
     */
    public Button getBackToMenuButton(){return BackToMenuButton;}


    /**
     *
     * @return scene containing the box
     */
    @Override
    public Scene getMenuView() {
        return myScene;
    }

    /**
     * change text based on language
     * @param language name of language
     */
    @Override
    public void setLanguage(String language) {
        myLanguage = language;
        for(PrettyButtons button:myButtonList) button.changeLanguage(myLanguage);
        setWin(win, id, score);
    }
    /**
     * change the background to color specified
     * @param color input color
     */
    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }
    /**
     * change the color to dark mode/light mode
     * @param dark true = dark mode
     */
    @Override
    public void switchMode(boolean dark){
        this.dark = dark;
        setColor();
    }

    private void setColor(){
        if(dark) vBox.setBackground(Constants.darkModebox);
        else vBox.setBackground(Constants.lightModebox);
        for(PrettyButtons button:myButtonList) button.switchMode(dark);

        finishText.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        scorelist.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
    }

    private void setUpButton(){
        BackToMenuButton = new PrettyButtons(resourceName, myLanguage);
        myButtonList = List.of( BackToMenuButton);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(finishText, scorelist, BackToMenuButton);
    }
    /**
     * Update the text of score
     * @param list list of id:score
     */
    public void updateScore(Map<Integer, Integer> list){
        var resource = ResourceBundle.getBundle(resourceName, new Locale(myLanguage));
        String s = "";
        for(int i:list.keySet()){
            s+= (resource.getString("playerf")+i+resource.getString("playerscore")+list.get(i)+"; \n");
        }
        s+="\n\n";
        scorelist.setText(s);
    }
}
