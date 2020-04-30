package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ooga.view.game_menu.other.SelectMenu;
import ooga.view.game_menu.pretty.PrettyButtons;

import java.util.ArrayList;
import java.util.List;

public class SelectMenuView implements SelectMenu {
    private PrettyButtons myGameButton1;
    private PrettyButtons myGameButton2;
    private PrettyButtons myGameButton3;
    private PrettyButtons mySettingButton;

    private HBox description1;
    private HBox description2;
    private HBox description3;

    private List<PrettyButtons> myButtonList = new ArrayList<>();

    private VBox vBox;
    private Scene myScene;
    private boolean dark;
    private String myLanguage;


    public SelectMenuView(){
        myLanguage = "English";
        setUpButton();
        setUpDescription();
        setUpVBox();
        myScene = new Scene(vBox, 800, 800);
    }

    private void setUpDescription() {
       
    }

    @Override
    public Button getGame1() {
        return myGameButton1;
    }

    @Override
    public Button getGame2() {
        return myGameButton2;
    }

    @Override
    public Button getGame3() {
        return myGameButton3;
    }

    @Override
    public Button getSetting() {
        return mySettingButton;
    }

    @Override
    public Scene getMenuView() {
        return myScene;
    }

    @Override
    public void switchMode(boolean dark){
        this.dark = dark;
        setColor();
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

    private void setColor(){
        if(dark) vBox.setBackground(Constants.darkModebox);
        else vBox.setBackground(Constants.lightModebox);
        for(PrettyButtons button:myButtonList) button.switchMode(dark);
    }

    private void setUpButton(){
        myGameButton1 = new PrettyButtons("Type1", myLanguage);
        myGameButton2 = new PrettyButtons("Type2", myLanguage);
        myGameButton3 = new PrettyButtons("Type3", myLanguage) ;
        mySettingButton = new PrettyButtons("Setting", myLanguage);

        myButtonList = List.of(myGameButton1, myGameButton2, myGameButton3, mySettingButton);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(myButtonList);
    }
}
