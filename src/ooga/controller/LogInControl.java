package ooga.controller;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.view.game_menu.LogInView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * COntrol for the login menu
 *
 * @author Lucy
 */
public class LogInControl {

    private Stage myStage;
    private LogInView myView;
    private WindowControl myControl;

    private Button LogInButton;
    private Button SignUpButton;

    /**
     * Create login control using the windowcontrol where it is created
     * window control passed in is necessary to take the "islogin" information and user name
     * @param windowControl windowcontrol in which the login control is created
     */
    public LogInControl(WindowControl windowControl){
        myStage = new Stage();
        myView = new LogInView();
        myControl = windowControl;
        myStage.setScene(myView.getMenuView());
        setUpButtons();
    }

    private void setUpButtons() {
        LogInButton = myView.getLogInButton();
        LogInButton.setOnAction(e->checkLogIn());

        SignUpButton = myView.getSignUpButton();
        SignUpButton.setOnAction(e->signUp());
    }

    private void signUp() {
        var resources = ResourceBundle.getBundle("user");
        String UserInput = myView.getNameInput();
        if(resources.containsKey(UserInput)){
            myView.signUpFail();
            return;
        }

        try {
            Properties prop = new Properties();
            InputStream in = new FileInputStream("resources/user.properties");//getClass().getResourceAsStream("/resources/xyzz.properties");
            prop.load(in);
            prop.setProperty(UserInput, myView.getPasswordInput());
            FileOutputStream fos = new FileOutputStream("resources/user.properties");
            prop.store(fos, "test");
            fos.flush();
            fos.close();
            System.out.println(">");
        }
        catch(Exception e){
            System.out.println("Something went wrong...");
        }
        myControl.setUser(UserInput);
        myStage.close();

    }

    private void checkLogIn() {
        var resources = ResourceBundle.getBundle("user");
        String UserInput = myView.getNameInput();
        if(!resources.containsKey(UserInput)){
            myView.UserDNE();
            return;
        }
        String PassWord = myView.getPasswordInput();
        if(!resources.getString(UserInput).equals(PassWord)){
            myView.logInFail();
            return;
        }
        myControl.setUser(UserInput);
        myControl.setUser(UserInput);
        myStage.close();
    }

    /**
     * clear login input (user name and password) and show the stage
     */
    public void showLogIn(){
        myView.clearInput();
        myStage.show();
    }

    /**
     * ask view to switch the mode to dark mode / light mode
     * @param dark boolean that determines if should be dark or light
     */
    public void switchMode(boolean dark) {
        myView.switchMode(dark);
    }

    /**
     * ask view to switch language of button/label
     * @param language string that represents name of said language
     */
    public void setLanguage(String language) {
        myView.setLanguage(language);
    }

    /**
     * ask view to change background color
     * @param color color
     */
    public void changColor(Color color) {
        myView.changColor(color);
    }
}
