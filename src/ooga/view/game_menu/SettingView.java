package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import ooga.view.game_menu.pretty.PrettyButtons;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class SettingView implements MenuView{

    private Scene myScene;
    private VBox vBox;
    private TextField HPInput;
    private Label currentHP;
    private Label HP;
    private PrettyButtons Confirm;
    private List<PrettyButtons> myButtonList;
    private Label Message;
    private int HPvalue;

    private String myLanguage = "English";
    private boolean dark = false;

    private Background darkMode = new Background(new BackgroundFill(new Color(0.15,0.15,0.15,1), CornerRadii.EMPTY, Insets.EMPTY));
    private Background lightMode = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));


    public SettingView(){
        setUpLabel();
        setUpField();
        setUpButton();
        setUpVBox();
        switchMode(dark);
        myScene = new Scene(vBox, 400,400);
    }

    private void setUpButton() {
        Confirm = new PrettyButtons("Confirm", myLanguage);
        myButtonList = List.of( Confirm);
    }

    public String getHPInput(){
        return HPInput.getText();
    }

    public Button getConfirm(){
        return Confirm;
    }

    @Override
    public Scene getMenuView() {
        return myScene;
    }

    @Override
    public void switchMode(boolean dark) {
        this.dark = dark;
        vBox.setBackground(dark?darkMode:lightMode);
        for(PrettyButtons button:myButtonList) button.switchMode(dark);
        HP.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        currentHP.setTextFill(dark?Color.DARKGRAY:Color.BLACK);

    }

    public void setLanguage(String language){
        myLanguage = language;
        Confirm.changeLanguage(myLanguage);
        changeLableText();
    }

    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void setHP(int i){
        HPvalue = i;
        var resource = ResourceBundle.getBundle("menu", new Locale(myLanguage));
        currentHP.setText(resource.getString("currentHP")+HPvalue);
    }

    public void NotInt(){
        var resource = ResourceBundle.getBundle("menu", new Locale(myLanguage));
        Message.setText(resource.getString("NotInt"));
    }

    private void setUpVBox() {
        vBox = new VBox(10);
        vBox.setMaxWidth(300);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(currentHP, HP, HPInput, Confirm, Message);
    }

    private void setUpField() {
        HPInput = new TextField();
        HPInput.setMaxWidth(300);
    }

    private void setUpLabel() {
        var resource = ResourceBundle.getBundle("menu", new Locale(myLanguage));
        if(HPvalue<=0) {
            currentHP = new Label(resource.getString("currentHP")+resource.getString("defaultHP"));
        }
        else{
            currentHP = new Label(resource.getString("currentHP")+HPvalue);
        }
        currentHP.setFont(Font.font("Ariel", 15));
        HP = new Label(resource.getString("HP"));
        HP.setFont(Font.font("Ariel", 18));
        Message = new Label("");
        Message.setFont(Font.font("Ariel", 18));
    }

    private void changeLableText(){
        var resource = ResourceBundle.getBundle("menu", new Locale(myLanguage));
        if(HPvalue<=0) {
            currentHP.setText(resource.getString("currentHP")+resource.getString("defaultHP"));
        }
        else{
            currentHP.setText(resource.getString("currentHP")+HPvalue);
        }
        HP.setText(resource.getString("HP"));
        Message.setText("");
    }

    public void clearInput() {
        HPInput.clear();
    }
}
