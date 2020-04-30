package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
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


public class LogInView implements MenuView{

    private Scene myScene;
    private VBox vBox;
    private TextField nameInput;
    private PasswordField passwordInput;
    private Label userName;
    private Label userPassWord;
    private PrettyButtons LogInButton;
    private PrettyButtons SignUpButton;
    private List<PrettyButtons> myButtonList;
    private Label Message;

    private String myLanguage = "English";
    private boolean dark = false;

    private static final int SIZE = 400;
    private static final String resourcename = "menu";

    public LogInView(){
        setUpLabel();
        setUpField();
        setUpButton();
        setUpVBox();
        switchMode(dark);
        myScene = new Scene(vBox, SIZE,SIZE);
    }

    private void setUpButton() {
        LogInButton = new PrettyButtons("LogIn", myLanguage);
        SignUpButton = new PrettyButtons("Signup", myLanguage);

        myButtonList = List.of(LogInButton, SignUpButton);
    }

    public String getNameInput(){
        return nameInput.getText();
    }

    public String getPasswordInput(){
        return passwordInput.getText();
    }

    public Button getLogInButton(){
        return LogInButton;
    }

    public Button getSignUpButton(){
        return SignUpButton;
    }

    @Override
    public Scene getMenuView() {
        return myScene;
    }

    @Override
    public void switchMode(boolean dark) {
        this.dark = dark;
        vBox.setBackground(dark? Constants.darkModebox: Constants.lightModebox);
        for(PrettyButtons button:myButtonList) button.switchMode(dark);
        userName.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        userPassWord.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
    }

    public void setLanguage(String language){
        myLanguage = language;
        LogInButton.changeLanguage(myLanguage);
        SignUpButton.changeLanguage(myLanguage);
        changeLableText();
    }

    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

    }


    public void UserDNE(){
        var resource = ResourceBundle.getBundle(resourcename, new Locale(myLanguage));
        Message.setText(resource.getString("UserDNE"));
    }

    public void logInFail(){
        var resource = ResourceBundle.getBundle(resourcename, new Locale(myLanguage));
        Message.setText(resource.getString("LogInFail"));
    }

    public void signUpFail(){
        var resource = ResourceBundle.getBundle(resourcename, new Locale(myLanguage));
        Message.setText(resource.getString("SignUpFail"));
    }

    private void setUpVBox() {
        vBox = new VBox(10);
        vBox.setMaxWidth(300);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(userName, nameInput, userPassWord, passwordInput, LogInButton, SignUpButton, Message);
    }

    private void setUpField() {
        nameInput = new TextField();
        nameInput.setMaxWidth(300);
        passwordInput = new PasswordField();
        passwordInput.setMaxWidth(300);
    }

    private void setUpLabel() {
        var resource = ResourceBundle.getBundle(resourcename, new Locale(myLanguage));
        userName = new Label(resource.getString("UserName"));

        userName.setFont(Constants.font);
        userPassWord = new Label(resource.getString("PassWord"));
        userPassWord.setFont(Constants.font);
        Message = new Label("");
        Message.setFont(Constants.font);
    }

    private void changeLableText(){
        var resource = ResourceBundle.getBundle(resourcename, new Locale(myLanguage));
        userName.setText(resource.getString("UserName"));
        userPassWord.setText(resource.getString("PassWord"));
        Message.setText("");
    }

    public void clearInput() {
        nameInput.clear();
        passwordInput.clear();
    }
}
