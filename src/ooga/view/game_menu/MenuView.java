package ooga.view.game_menu;

import javafx.scene.Scene;
import javafx.scene.paint.Color;

public interface MenuView {
    Scene getMenuView();

    void switchMode(boolean dark);

    void setLanguage(String language);

    void changColor(Color color);
}
