package ooga.view.game_menu;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import ooga.view.game_menu.pretty.PrettyButtons;

import java.util.*;

/**
 * This class creates a view to the setting change (changing initial hp setting of the game). It contains
 * a label that denotes the current HP, a textfield for the user to enter in the desire HP, a confirm button,
 * and a message
 *
 * I chose this class as my masterpiece since it demonstrates clean and short methods. One thing I have take initiative
 * to refactor during analysis is the use of some magic values - they are now all static and non-changing. It is
 * also a snippet of similar menu view classes I have written in this project, and I think it demonstrates that I
 * am capable of writing well designed code
 */
public class SettingView implements MenuView{

    private static final int SIZE = 400;
    private static final int LOWESTLIFE = 0;
    private static final String resourceName = "menu";

    private Scene myScene;
    private VBox vBox;
    private TextField HPInput;
    private Label currentHP, HP, Message;
    private PrettyButtons Confirm;
    private int HPvalue;
    private String myLanguage = "English";

    /**
     * Initializes view by initializing labels, textfields, buttons, vbox, and creating a scene
     * from them. Default mode is light mode (ie, dark = false)
     */
    public SettingView(){
        setUpLabel();
        setUpField();
        setUpButton();
        setUpVBox();
        switchMode(false);
        myScene = new Scene(vBox, SIZE,SIZE);
    }

    /*
    initializes button
     */
    private void setUpButton() {
        Confirm = new PrettyButtons("Confirm", myLanguage);
    }

    /**
     *
     * @return the input user has typed into the textfield
     */
    public String getHPInput(){
        return HPInput.getText();
    }

    /**
     *
     * @return the confirm button
     */
    public Button getConfirm(){
        return Confirm;
    }

    /**
     *
     * @return the scene used to create a stage
     */
    @Override
    public Scene getMenuView() {
        return myScene;
    }

    /**
     * Switches the mode to dark or light mode depending on the input. Triggered when the
     * "dark mode" button is clicked on main menu
     * @param dark boolean value; false = light mode, true = dark mode
     */
    @Override
    public void switchMode(boolean dark) {
        vBox.setBackground(dark? Constants.darkModebox: Constants.lightModebox);
        Confirm.switchMode(dark);
        HP.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        currentHP.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
    }

    /**
     * Sets the language and change the texts in the button and labels accordingly
     * @param language name of language, taken from selection from combo box on main menu
     */
    @Override
    public void setLanguage(String language){
        myLanguage = language;
        Confirm.changeLanguage(myLanguage);
        changeLableText();
    }

    /**
     * Sets the color of scene
     * @param color color selected from color picker on main menu
     */
    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    /**
     * Controller will parse what is entered into the textfield into an integer, and if it is valid, the
     * integer will be passed back here in order to change the label text to reflect that
     * the user has successfully changed the HP value to what is entered,
     * @param i hp value taken from controller
     */
    public void setHP(int i){
        HPvalue = i;
        changeLableText();
    }

    /**
     * Sets the message to display that the user has made an mistake in entering the number
     */
    public void NotInt(){
        var resource = ResourceBundle.getBundle(resourceName, new Locale(myLanguage));
        Message.setText(resource.getString("NotInt"));
    }

    /*
    sets up vBox and its position on screen
     */
    private void setUpVBox() {
        vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(currentHP, HP, HPInput, Confirm, Message);
    }

    /*
    sets up textfield and size
     */
    private void setUpField() {
        HPInput = new TextField();
        HPInput.setMaxWidth(SIZE*.75);
    }

    /*
    initialize the labels, set the size & font of the label, and all change label text to set the
    text according to default language
     */
    private void setUpLabel() {
        currentHP = new Label();
        currentHP.setFont(Constants.font);
        HP = new Label();
        HP.setFont(Constants.font);
        Message = new Label();
        Message.setFont(Constants.font);
        changeLableText();
    }

    /*
    changes the lable text (called when language or value of HP changes)
     */
    private void changeLableText(){
        var resource = ResourceBundle.getBundle(resourceName, new Locale(myLanguage));
        currentHP.setText(resource.getString("currentHP")+HPvalue);
        if(HPvalue<=LOWESTLIFE) currentHP.setText(resource.getString("currentHP")+resource.getString("defaultHP"));
        HP.setText(resource.getString("HP"));
        Message.setText("");
    }

    /**
     * Clears the input to the textfield and set the message back to default (this method is usually
     * called when user closes the setting window and reopens it)
     */
    public void clearInput() {
        HPInput.clear();
        Message.setText("");
    }
}
