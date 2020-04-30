package ooga.view.game_menu;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public abstract class Constants {
    public final static Background darkModebox = new Background(new BackgroundFill(new Color(0.15,0.15,0.15,1), CornerRadii.EMPTY, Insets.EMPTY));
    public final static Background lightModebox = new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY));

    public static final Background darkModebutton = new Background(new BackgroundFill(new Color(0.4,0.4,0.4,1), CornerRadii.EMPTY, Insets.EMPTY));
    public static final Background lightModebutton = new Background(new BackgroundFill(new Color(0.85,0.85,0.85,1), CornerRadii.EMPTY, Insets.EMPTY));


    public static final Font font = Font.font("Ariel", 18);
}
