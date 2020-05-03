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


/**
 * View of user profile
 *
 * @author Lucy
 */
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

    private static final int SIZE = 400;
    private static final String resourceName = "menu";
    private static final String resourceName2 = "user";


    public UserProfileView(String userName){
        this.userName = userName;
        setUpTempVal();
        setLabel();
        setUpVBox();
        switchMode(dark);
        myScene = new Scene(vBox, SIZE,SIZE);
    }

    /**
     *
     * @return scene
     */
    @Override
    public Scene getMenuView() {
        return myScene;
    }

    /**
     * change background to dark mode / light mode
     * @param dark true = dark mode
     */
    @Override
    public void switchMode(boolean dark) {
        this.dark = dark;
        vBox.setBackground(dark? Constants.darkModebox: Constants.lightModebox);
        Name.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        HighestScore.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
        LastPlayed.setTextFill(dark?Color.DARKGRAY:Color.BLACK);
    }

    /**
     * change text based on resource file
     * @param language name of language
     */
    @Override
    public void setLanguage(String language){
        myLanguage = language;
        changeLableText();
    }

    /**
     * change background
     * @param color input color
     */
    @Override
    public void changColor(Color color) {
        vBox.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void setUpVBox() {
        vBox = new VBox(10);
        vBox.setMaxWidth(SIZE*.75);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(Name, HighestScore, LastPlayed);
    }


    private void setUpTempVal() {
        try {
            var resource2 = ResourceBundle.getBundle(resourceName2, new Locale(userName));
            userName = resource2.getString("Name");
            tempHighest = Integer.parseInt(resource2.getString("High"));
            tempLast = Integer.parseInt(resource2.getString("Last"));
        }
        catch(Exception e) { //if resource bundle does not exist, use default and create this properties file
            Properties prop = new Properties(){{
                setProperty("Name", userName);
                setProperty("High", tempHighest + "");
                setProperty("Last", tempLast + "");
            }};
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
        Name = new Label();
        Name.setFont(Constants.font);
        HighestScore = new Label();
        HighestScore.setFont(Constants.font);
        LastPlayed = new Label();
        LastPlayed.setFont(Constants.font);
        changeLableText();
    }

    private void changeLableText(){
        var resource1 = ResourceBundle.getBundle(resourceName, new Locale(myLanguage));
        Name.setText(resource1.getString("Name") + userName);
        HighestScore.setText(resource1.getString("HighScore") + tempHighest);
        LastPlayed.setText(resource1.getString("Last") + tempLast);
    }

    /**
     *
     * @return the highest score as recorded by this view class
     */
    public int getHighest(){
        return tempHighest;
    }

    /**
     * set highest to be h
     * @param h integer score
     */
    public void setHighest(int h){
        if(h>tempHighest)tempHighest = h;
    }

    /**
     * set last played score to be h
     * @param h score
     */
    public void setLast(int h){
        tempLast = h;
    }
}
