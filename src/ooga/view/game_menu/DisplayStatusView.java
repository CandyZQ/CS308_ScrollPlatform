package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ooga.view.game_menu.pretty.PrettyButtons;

import java.util.*;

/**
 * Display of life and score that updates simultaneously as the game is played
 *
 * @author Lucy
 */
public class DisplayStatusView implements MenuView {

    private List<PrettyButtons> myButtonList = new ArrayList<>();
    private static final int ZIZE = 300;

    private VBox vBox;
    private Scene myScene;
    private boolean dark;
    private String myLanguage= "English";

    private Label scorelist;
    private Label life;


    public DisplayStatusView(){
        scorelist = new Label();
        scorelist.setFont(Constants.font);
        life = new Label();
        life.setFont(Constants.font);
        setUpVBox();
        myScene = new Scene(vBox, ZIZE, ZIZE);
    }

    /**
     *
     * @return the scene containing the vbox
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
        vBox.setBackground(dark? Constants.darkModebox: Constants.darkModebutton);
        scorelist.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        life.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(scorelist, life);
    }

    /**
     * Update the text of score
     * @param list list of id:score
     */
    public void updateScore(Map<Integer, Integer> list){
        String s = "";
        for(int i:list.keySet()){
            s+= "Player "+i+" score: "+list.get(i)+"; \n";
        }
        s+="\n\n";
        scorelist.setText(s);
    }

    /**
     * Update the text of life
     * @param list list of id:hp
     */
    public void updateLife(Map<Integer, Integer> list) {
        String s = "";
        for(int i:list.keySet()){
            s+= "Player "+i+" life: "+list.get(i)+"; \n";
        }
        s+="\n\n";
        life.setText(s);

    }
}
