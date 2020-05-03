package ooga.view.game_menu.pretty;

import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import ooga.view.game_menu.Constants;

import java.util.ResourceBundle;

/**
 * Custom combo box
 *
 * @author Lucy
 */
public class PrettyBox extends ComboBox {
    private static final int BUTTON_HEIGHT = 30, BUTTON_WIDTH = 200;
    private boolean dark = false;
    private DropShadow shadow = new DropShadow();
    private String myResource;

    public PrettyBox(String resource) {
        myResource = resource;
        setContent();
        setPrefHeight(BUTTON_HEIGHT);//45
        setPrefWidth(BUTTON_WIDTH);//190
        setStyle("-fx-font: 18px \"Ariel\";");
        switchMode(dark);
        shadow.setSpread(0.7);
        mouseUpdateListener();
    }

    private void setContent(){
        var resources = ResourceBundle.getBundle("menu");
        this.getItems().addAll(resources.keySet());
        this.setPromptText(myResource);
    }

    /**
     * change the size of the box
     * @param x width
     * @param y height
     */
    public void setSize(int x, int y){
        setPrefHeight(y);
        setPrefWidth(x);
    }

    /**
     * change background and shadow color to light/dark mode
     * @param dark true = dark mode
     */
    public void switchMode(boolean dark){
        if(dark) setBackground(Constants.darkModebutton);
        else setBackground(Constants.lightModebutton);
        shadow.setColor(dark? Color.CORNFLOWERBLUE:Color.LIGHTBLUE);
        this.dark = dark;
    }

    private void mouseUpdateListener() {
        this.setOnMouseEntered(e -> setEffect(shadow));
        this.setOnMouseExited(e -> setEffect(null));
    }
}
