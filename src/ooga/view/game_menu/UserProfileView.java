package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


public class UserProfileView implements MenuView{

    private Scene myScene;
    private VBox vBox;

    private Label Name;
    private Label HighestScore;
    private Label LastPlayed;

    private int tempHighest;
    private int tempLast;


    private String myLanguage = "English";
    private String userName;
    private boolean dark = false;


    public UserProfileView(String userName){
        this.userName = userName;
        setUpTempVal();
        setLabel();
        setUpVBox();
        switchMode(dark);
        myScene = new Scene(vBox, 400,400);
    }

    @Override
    public Scene getMenuView() {
        return myScene;
    }

    @Override
    public void switchMode(boolean dark) {
        this.dark = dark;
        vBox.setBackground(dark? Constants.darkModebox: Constants.lightModebox);
        Name.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        HighestScore.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        LastPlayed.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
    }

    public void setLanguage(String language){
        myLanguage = language;
        changeLableText();
    }

    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setUpVBox() {
        vBox = new VBox(10);
        vBox.setMaxWidth(300);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(Name, HighestScore, LastPlayed);
    }


    private void setUpTempVal() {
        try {
            var resource2 = ResourceBundle.getBundle("user", new Locale(userName));
            userName = resource2.getString("Name");
            tempHighest = Integer.parseInt(resource2.getString("High"));
            tempLast = Integer.parseInt(resource2.getString("Last"));
        }
        catch(Exception e) {
            Properties prop = new Properties();
            prop.setProperty("Name", userName);
            prop.setProperty("High", tempHighest + "");
            prop.setProperty("Last", tempLast + "");
            try {
                FileOutputStream fos = new FileOutputStream("resources/user_" + userName + ".properties");
                prop.store(fos, "test");
                fos.flush();
                fos.close();
            } catch (Exception ex) {
                System.out.println("userProfileCreationFail");
            }
        }
    }

    private void setLabel(){
        var resource1 = ResourceBundle.getBundle("menu", new Locale(myLanguage));
        Name = new Label(resource1.getString("Name") + userName);
        Name.setFont(Font.font("Ariel", 18));
        HighestScore = new Label(resource1.getString("HighScore") + tempHighest);
        HighestScore.setFont(Font.font("Ariel", 18));
        LastPlayed = new Label(resource1.getString("Last") + tempLast);
        LastPlayed.setFont(Font.font("Ariel", 18));
    }

    public void changeLableText(){
        var resource1 = ResourceBundle.getBundle("menu", new Locale(myLanguage));
        Name.setText(resource1.getString("Name") + userName);
        HighestScore.setText(resource1.getString("HighScore") + tempHighest);
        LastPlayed.setText(resource1.getString("Last") + tempLast);
    }

    public int getHighest(){
        return tempHighest;
    }

    public void setHighest(int h){
        tempHighest = h;
    }

    public void setLast(int h){
        tempLast = h;
    }
}
