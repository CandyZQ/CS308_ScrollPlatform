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

    public void setWin(boolean win, int id, int score){
        this.win = win;
        this.id = id;
        this.score = score;
        var resource = ResourceBundle.getBundle(resourceName, new Locale(myLanguage));
        if(win)finishText.setText(resource.getString("playerf") + " " + id + " " + resource.getString("win"));
        else finishText.setText(resource.getString("playerf") + " " + id + " " + resource.getString("dead"));

    }


    public Button getBackToMenuButton(){return BackToMenuButton;}


    @Override
    public Scene getMenuView() {
        return myScene;
    }

    @Override
    public void setLanguage(String language) {
        myLanguage = language;
        for(PrettyButtons button:myButtonList) button.changeLanguage(myLanguage);
        setWin(win, id, score);
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
