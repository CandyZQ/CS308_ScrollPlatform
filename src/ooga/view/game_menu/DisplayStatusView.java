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

    @Override
    public Scene getMenuView() {
        return myScene;
    }

    @Override
    public void setLanguage(String language) {
        myLanguage = language;
        for(PrettyButtons button:myButtonList) button.changeLanguage(myLanguage);
    }

    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void switchMode(boolean dark){
        this.dark = dark;
        setColor();
    }

    public void setColor(){
        vBox.setBackground(dark? Constants.darkModebox: Constants.darkModebutton);
        scorelist.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        life.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(scorelist, life);
    }

    public void updateScore(Map<Integer, Integer> list){
        String s = "";
        for(int i:list.keySet()){
            s+= "Player "+i+" score: "+list.get(i)+"; \n";
        }
        s+="\n\n";
        scorelist.setText(s);
    }

    public void updateLife(Map<Integer, Integer> list) {
        String s = "";
        for(int i:list.keySet()){
            s+= "Player "+i+" life: "+list.get(i)+"; \n";
        }
        s+="\n\n";
        life.setText(s);

    }
}
