package ooga.view.game_menu.other;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import ooga.view.game_menu.MenuView;

public interface GameMenu extends MenuView { // will be extending Scene

  Button getNewGameButton();

  Button getExitGameButton();

  Button getBackgroundButton();

  Button getLoadButton();

  Button getUserButton();

  ComboBox getLanguagePicker();

  ColorPicker getMyColorPicker();
}
