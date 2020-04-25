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

    private PrettyButtons ResumeButton;
    private PrettyButtons BackToMenuButton;
    private PrettyButtons SaveGameButton;


    private List<PrettyButtons> myButtonList = new ArrayList<>();

    private VBox vBox;
    private Scene myScene;
    private boolean dark;
    private String myLanguage;

    private Label finishText;
    private Label scorelist;

    private boolean win = false;
    private int score = 0;
    private int id = 0;

    private Background darkMode = new Background(new BackgroundFill(new Color(0.15,0.15,0.15,1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background lightMode = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

    public FinishScreen(){
        myLanguage = "English";
        setUpLabel();
        setUpButton();
        setUpVBox();
        myScene = new Scene(vBox, 800, 800);
    }

    private void setUpLabel() {
        finishText = new Label();
        finishText.setFont(Font.font("Ariel", 18));
        scorelist = new Label();
        scorelist.setFont(Font.font("Ariel", 18));
    }
//
//    private void changeLabelText() {
//        var resource = ResourceBundle.getBundle("menu", new Locale(myLanguage));
//        if(win)finishText.setText(resource.getString("playerf") + " " + id + " " + resource.getString("win"));
//        else finishText.setText(resource.getString("playerf") + " " + id + " " + resource.getString("dead"));
//    }

    public void setWin(boolean win, int id, int score){
        this.win = win;
        this.id = id;
        this.score = score;
        var resource = ResourceBundle.getBundle("menu", new Locale(myLanguage));
        if(win)finishText.setText(resource.getString("playerf") + " " + id + " " + resource.getString("win"));
        else finishText.setText(resource.getString("playerf") + " " + id + " " + resource.getString("dead"));

        //scorelist.setText(resource.getString("playerscore")+" "+score);
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
        if(dark) vBox.setBackground(darkMode);
        else vBox.setBackground(lightMode);
        for(PrettyButtons button:myButtonList) button.switchMode(dark);

        finishText.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        scorelist.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
    }

    private void setUpButton(){
        BackToMenuButton = new PrettyButtons("menu", myLanguage);
        myButtonList = List.of( BackToMenuButton);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(finishText, scorelist, BackToMenuButton);
    }

    public void updateScore(Map<Integer, Integer> list){
        var resource = ResourceBundle.getBundle("menu", new Locale(myLanguage));
        String s = "";
        for(int i:list.keySet()){
            s+= (resource.getString("playerf")+i+resource.getString("playerscore")+list.get(i)+"; \n");
        }
        s+="\n\n";
        scorelist.setText(s);
    }
}
