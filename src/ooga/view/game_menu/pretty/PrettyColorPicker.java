package ooga.view.game_menu.pretty;

import javafx.scene.control.ColorPicker;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import ooga.view.game_menu.Constants;

public class PrettyColorPicker extends ColorPicker {
    private static final int BUTTON_HEIGHT = 30, BUTTON_WIDTH = 200;
    private boolean dark = false;
    private DropShadow shadow = new DropShadow();

    public PrettyColorPicker() {
        setPrefHeight(BUTTON_HEIGHT);//45
        setPrefWidth(BUTTON_WIDTH);//190
        setStyle("-fx-font: 18px \"Ariel\";");
        switchMode(dark);
        shadow.setSpread(0.7);
        mouseUpdateListener();
    }

    public void setSize(int x, int y){
        setPrefHeight(y);
        setPrefWidth(x);
    }

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
