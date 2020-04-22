package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.ResourceBundle;

public class PrettyColorPicker extends ColorPicker {
    private static final int BUTTON_HEIGHT = 45, BUTTON_WIDTH = 300;
    private boolean dark = false;
    private static final Background darkMode = new Background(new BackgroundFill(new Color(0.4,0.4,0.4,1), CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background lightMode = new Background(new BackgroundFill(new Color(0.85,0.85,0.85,1), CornerRadii.EMPTY, Insets.EMPTY));
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
        if(dark) setBackground(darkMode);
        else setBackground(lightMode);
        shadow.setColor(dark? Color.CORNFLOWERBLUE:Color.LIGHTBLUE);
        this.dark = dark;
    }

    private void mouseUpdateListener() {
        this.setOnMouseEntered(e -> setEffect(shadow));
        this.setOnMouseExited(e -> setEffect(null));
    }
}
