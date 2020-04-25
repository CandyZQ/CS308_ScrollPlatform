package ooga.view.game_menu.pretty;

import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ResourceBundle;

public class PrettyBox extends ComboBox {
    private static final int BUTTON_HEIGHT = 45, BUTTON_WIDTH = 300;
    private boolean dark = false;
    private static final Background darkMode = new Background(new BackgroundFill(new Color(0.4,0.4,0.4,1), CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background lightMode = new Background(new BackgroundFill(new Color(0.85,0.85,0.85,1), CornerRadii.EMPTY, Insets.EMPTY));
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
