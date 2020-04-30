package ooga.controller;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.view.game_menu.SettingView;

/**
 * This class is the control to user profile view. It creates the view and connect the elements in the view
 * to their essential functions, and reports back to window control when/if the user actually changes HP
 *
 * I chose this class to be one of my master pieces as it is connected to the user profile view class and demonstrates
 * the feature in our game where we allow the player to use the UI to input an initial HP value. This class is also a snippet
 * to all other similar control classes, each with different menus and different functions, so I think it can represent
 * my ability to code well.
 */

public class SettingControl {

    private static final int LOWESTLIFE = 0;

    private Stage myStage;
    private SettingView myView;
    private WindowControl myControl;
    private Button ConfirmButton;

    /**
     * Controller for setting (ie, allowing the user to change initial game configuration / values)
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

    /*
    Sets up the button's action
     */
    private void setUpButtons() {
        ConfirmButton = myView.getConfirm();
        ConfirmButton.setOnAction(e->confirm());
    }

    /*
     If an exception is caught in the process of running this code, either what the user enters is not an
     integer (ie, parseInt went wrong), or that the number entered is below 0. Since life (HP) cannot be
     below 0, an exception is thrown. In either of the cases, the view shows a message that notifies
     the user that what is entered is not allowed and demands the user to enter a valid number for life.
     If the value entered by the user is valid, the hp value is passed to the window control to be distributed to
     the model.
     */
    private void confirm() {
        String UserInput = myView.getHPInput();
        try {
            int i = Integer.parseInt(UserInput);
            if(i<LOWESTLIFE) throw new Exception();
            myControl.setLife(i);
            myView.setHP(i);
            myStage.close();
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