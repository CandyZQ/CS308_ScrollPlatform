package ooga.controller;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.view.game_menu.LogInView;
import ooga.view.game_menu.SettingView;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class SettingControl {

    private Stage myStage;
    private SettingView myView;
    private WindowControl myControl;

    private Button ConfirmButton;

    /**
     * Create a setting menu
     * @param windowControl passes in the window control used to create this menu since would need
     *                      to send the information of player set life back to the window control and model
     */
    public SettingControl(WindowControl windowControl){
        myStage = new Stage();
        myView = new SettingView();
        myControl = windowControl;
        myStage.setScene(myView.getMenuView());
        setUpButtons();
    }

    private void setUpButtons() {
        ConfirmButton = myView.getConfirm();
        ConfirmButton.setOnAction(e->confirm());

    }

    private void confirm() {
        String UserInput = myView.getHPInput();
        try {
            int i = Integer.parseInt(UserInput);
            if(i>0){
                myControl.setLife(i);
                myView.setHP(i);
                myStage.close();
            }
            else{
                throw new Exception();
            }
        } catch(Exception e) {
            myView.NotInt();
        }
    }


    /**
     * ask the view to change the background to dark mode/back to normal
     * @param dark true -> dark mode; false-> light mode
     */
    public void switchMode(boolean dark) {
        myView.switchMode(dark);
    }

    /**
     * ask the view to change the language of all label/button on this menu
     * @param language string that denotes the language
     */
    public void setLanguage(String language) {
        myView.setLanguage(language);
    }

    /**
     * clear the previous input to hp value and show the menu
     */
    public void showSetting() {
        myView.clearInput();
        myStage.show();
    }

    /**
     * ask the view to change the color of background
     * @param color color
     */
    public void changColor(Color color) {
        myView.changColor(color);
    }
}
