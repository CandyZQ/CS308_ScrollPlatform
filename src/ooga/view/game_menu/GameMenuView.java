package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ooga.view.game_menu.other.GameMenu;
import ooga.view.game_menu.pretty.PrettyBox;
import ooga.view.game_menu.pretty.PrettyButtons;
import ooga.view.game_menu.pretty.PrettyColorPicker;

import java.util.*;

public class GameMenuView implements GameMenu {
    private PrettyButtons myNewButton;
    private PrettyButtons myExitButton;
    private PrettyButtons myMode;
    private PrettyButtons myLoad;
    private PrettyButtons myUser;
    private List<PrettyButtons> myButtonList;
    private VBox vBox;
    private HBox hBox;
    private Scene myScene;
    private boolean dark;
    private boolean isColor;
    private String myLanguage;
    private PrettyBox myLanguagePicker;
    private PrettyColorPicker myColorPicker;
    private Label myColorLable;
    private VBox myColorBox;

    private Background darkMode = new Background(new BackgroundFill(new Color(0.15,0.15,0.15,1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background lightMode = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

    public GameMenuView(){
        myLanguage = "English";
        setUpColorPicker();
        setUpButton();
        setUpHBox();
        setUpVBox();
        setColor();
        myScene = new Scene(vBox);
    }

    private void setUpColorPicker() {
        var resource = ResourceBundle.getBundle("menu",new Locale(myLanguage));
        myColorLable = new Label(resource.getString("Color"));
        myColorLable.setFont(Font.font("Ariel", 14));
        myColorPicker = new PrettyColorPicker();
        myColorPicker.setSize(200, 30);
        myColorBox = new VBox(10);
        myColorBox.getChildren().addAll(myColorLable, myColorPicker);
    }

    @Override
    public Scene getMenuView() {

        return myScene;
    }

    @Override
    public ColorPicker getMyColorPicker(){return myColorPicker;}

    @Override
    public Button getNewGameButton() {
        return myNewButton;
    }

    @Override
    public Button getExitGameButton() {
        return myExitButton;
    }

    @Override
    public Button getBackgroundButton(){
        return myMode;
    }

    @Override
    public Button getLoadButton() {
        return myLoad;
    }

    @Override
    public Button getUserButton() {
        return myUser;
    }

    @Override
    public ComboBox getLanguagePicker() {
        return myLanguagePicker;
    }

    @Override
    public void switchMode(boolean dark){
        this.dark = dark;
        this.isColor = false;
        setColor();
    }

    @Override
    public void setLanguage(String language) {
        myLanguage = language;
        for (PrettyButtons button : myButtonList) button.changeLanguage(myLanguage);

        var resource = ResourceBundle.getBundle("menu",new Locale(myLanguage));
        myColorLable.setText(resource.getString("Color"));
    }

    @Override
    public void changColor(Color color) {
        isColor = true;
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setColor(){
        vBox.setBackground(dark?darkMode:lightMode);
        for(PrettyButtons button:myButtonList) button.switchMode(dark);
        myLanguagePicker.switchMode(dark);
        myColorPicker.switchMode(dark);
    }

    private void setUpButton(){
        myNewButton = new PrettyButtons("New", myLanguage);
        myExitButton = new PrettyButtons("Exit", myLanguage);
        myMode = new PrettyButtons("Background", myLanguage) ;
        myLoad = new PrettyButtons("Load", myLanguage);
        myUser = new PrettyButtons("User", myLanguage);
        myButtonList = List.of(myNewButton,  myLoad,  myUser,myMode, myExitButton);
    }

    private void setUpVBox(){
        vBox = new VBox(10);
        vBox.setAlignment(Pos.BASELINE_CENTER);
        vBox.getChildren().add(new Label("\n"));
        vBox.getChildren().add(hBox);
        vBox.getChildren().add(new Label("\n"));
        vBox.getChildren().addAll(myButtonList);
    }

    private void setUpHBox(){
        setUpLanguageMenu();
        hBox = new HBox(500);
        hBox.getChildren().addAll(myColorPicker, myLanguagePicker);
        hBox.setPrefHeight(60);
        hBox.setAlignment(Pos.BASELINE_CENTER);
    }

    private void setUpLanguageMenu(){
        var resource = ResourceBundle.getBundle("menu");
        myLanguagePicker = new PrettyBox("Language");
        myLanguagePicker.setSize(200, 30);
    }


}
