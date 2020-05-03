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
    private PrettyButtons myNewButton,  myExitButton, myMode, myLoad, myUser;
    private List<PrettyButtons> myButtonList;
    private VBox vBox;
    private HBox hBox;
    private Scene myScene;
    private boolean dark;
    private String myLanguage= "English";
    private PrettyBox myLanguagePicker;
    private PrettyColorPicker myColorPicker;
    private Label myColorLable;
    private VBox myColorBox;


    private static final String resourcename = "menu";

    public GameMenuView(){
        setUpColorPicker();
        setUpButton();
        setUpHBox();
        setUpVBox();
        setColor();
        myScene = new Scene(vBox);
    }

    private void setUpColorPicker() {
        var resource = ResourceBundle.getBundle(resourcename,new Locale(myLanguage));
        myColorLable = new Label(resource.getString("Color"));
        myColorLable.setFont(Constants.font);
        myColorPicker = new PrettyColorPicker();
        myColorBox = new VBox();
        myColorBox.getChildren().addAll(myColorLable, myColorPicker);
    }

    @Override
    public Scene getMenuView() {

        return myScene;
    }

    /**
     *
     * @return color picker
     */
    @Override
    public ColorPicker getMyColorPicker(){return myColorPicker;}

    /**
     *
     * @return game button
     */
    @Override
    public Button getNewGameButton() {
        return myNewButton;
    }

    /**
     *
     * @return exit button
     */
    @Override
    public Button getExitGameButton() {
        return myExitButton;
    }

    /**
     *
     * @return dark mode button
     */
    @Override
    public Button getBackgroundButton(){
        return myMode;
    }

    /**
     *
     * @return load last game button
     */
    @Override
    public Button getLoadButton() {
        return myLoad;
    }

    /**
     *
     * @return user profile/login button
     */
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
        setColor();
    }

    @Override
    public void setLanguage(String language) {
        myLanguage = language;
        for (PrettyButtons button : myButtonList) button.changeLanguage(myLanguage);

        var resource = ResourceBundle.getBundle(resourcename,new Locale(myLanguage));
        myColorLable.setText(resource.getString("Color"));
    }

    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setColor(){
        vBox.setBackground(dark? Constants.darkModebox: Constants.lightModebox);
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
        hBox.setAlignment(Pos.BASELINE_CENTER);
    }

    private void setUpLanguageMenu(){
        myLanguagePicker = new PrettyBox("Language");
    }


}
