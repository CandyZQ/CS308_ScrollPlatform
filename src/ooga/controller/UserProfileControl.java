package ooga.controller;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.view.game_menu.LogInView;
import ooga.view.game_menu.UserProfileView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Control for the user profile view
 *
 * @author Lucy
 */
public class UserProfileControl {

    private Stage myStage;
    private UserProfileView myView;

    private Color mycolor = Color.WHITE;
    private boolean dark = false;
    private String language = "English";
    private boolean isColor = false;
    private String userName;

    /**
     * create the user profile control and create a new stage
     */
    public UserProfileControl(){
        myStage = new Stage();
    }


    /**
     * create the user profile view with the user name provided
     * set the profile view to preferred color/language
     * show the menu
     * @param s user name
     */

    public void setUserNameAndShow(String s){
        userName = s;
        myView = new UserProfileView(s);
        myView.switchMode(dark);
        if(isColor) myView.changColor(mycolor);
        myView.setLanguage(language);
        myStage.setScene(myView.getMenuView());
        myStage.show();
    }

    /**
     * change the background to dark mode/back to normal
     * is color is necessary since the view for this control is
     * not created until a user name is passed in (ie, the view does not
     * exist until user logs in and thus control need to remember if the color
     * need to be set or if it is using the dark/light default backgrounds)
     * @param dark
     */
    public void switchMode(boolean dark) {
        this.dark = dark;
        isColor = false;
    }

    /**
     * set the language of all label/button on this menu
     * @param language string that denotes the language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * set the color of background
     * is color is necessary since the view for this control is
     * not created until a user name is passed in (ie, the view does not
     * exist until user logs in and thus control need to remember if the color
     * need to be set or if it is using the dark/light default backgrounds)
     * @param color color
     */
    public void changColor(Color color) {
        mycolor = color;
        isColor = true;
    }


    /**
     * Saves the score to the resource file (property file) for the user
     * The "score" is naturally the score of the last played game
     * if it is higher than the highest score recorded, then change high score to it as well
     * store it to the property folder for the user
     * @param score     score of last played game
     */
    public void writeScore(int score){
        Properties prop = new Properties();
        try {
            InputStream in = new FileInputStream("resources/user_"+userName+".properties");//getClass().getResourceAsStream("/resources/xyzz.properties");
            prop.load(in);
        }
        catch(Exception e){
            System.out.println("user profile load error in profile control");
        }

        if(score>myView.getHighest()){
            myView.setHighest(score);
            prop.setProperty("High", score+"");
        }
        myView.setLast(score);
        prop.setProperty("Last", score+"");

        try {
            FileOutputStream fos = new FileOutputStream("resources/user_"+userName+".properties");
            prop.store(fos, "");
            fos.flush();
            fos.close();
        }
        catch(Exception ex){
            System.out.println("save score error in profile control");
        }

    }
}
