package ooga.view.game_menu;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

public interface MenuView {
    /**
     *
     * @return the scene containing the vbox
     */
    Scene getMenuView();
    /**
     * change the color to dark mode/light mode
     * @param dark true = dark mode
     */
    void switchMode(boolean dark);
    /**
     * change text based on language
     * @param language name of language
     */
    void setLanguage(String language);

    /**
     * change the background to color specified
     * @param color input color
     */
    void changColor(Color color);
}
