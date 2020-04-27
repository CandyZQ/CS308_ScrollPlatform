package ooga.controller;

import java.io.IOException;
import java.util.List;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import ooga.controller.gamecontrol.GameController;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.DataStorerAPI;
import ooga.game.GameType;
import ooga.game.GameZelda2DSingle;
import ooga.model.Model;
import ooga.view.game_menu.other.GameMenu;
import ooga.view.game_menu.GameMenuView;
import ooga.view.game_menu.SelectMenuView;
import ooga.view.game_view.game_state.state2d.GameState2DView;


public class WindowControl {

  public static final int CURRENT_PLAYER_ID = 0;

  private Button myStartButton;
  private Button myExitButton;
  private Button myLoadButton;
  private Button myChangeBackgroundButton;
  private Button myGameButton1;
  private Button myGameButton2;
  private Button myGameButton3;
  private Button mySettingButton;
  private Button myUserButton;

  private ColorPicker myColorPicker;

  private String myUserName = "";
  private boolean isLogIn = false;

  private boolean isColored = false;
  private Color color = Color.WHITE;
  private boolean resetGame = true;

  private ComboBox myLanguagePicker;

  private LogInControl myLogIn;
  private SettingControl mySettingControl;
  private GameController myGameController;
  private UserProfileControl myUserProfileControl;

  private Stage myStage;
  private Stage secondStage;
  private SelectMenuView mySelectView;
  private GameMenu myMenuView;
  private DataLoaderAPI myDataLoader;
  private DataStorerAPI myDataStorer;

  private int myPlayerHP = 0;

  private boolean dark = false;
  private String language = "English";

  private GameState2DView myGameView;

  /**
   * Create the menu based on the stage provided
   * @param currentStage
   */
  public WindowControl(Stage currentStage) {
    myLogIn = new LogInControl(this);
    myStage = currentStage;
    myMenuView = new GameMenuView();
    mySelectView = new SelectMenuView();
    mySettingControl = new SettingControl(this);
    myUserProfileControl = new UserProfileControl();

    setMenuScene();
    initializeButtons();
  }

  /**
   * Create the window using the current stage and the data storer provided
   * @param currentStage
   * @param datastorer
   * @throws DataLoadingException
   */
  public WindowControl(Stage currentStage, DataStorerAPI datastorer) throws DataLoadingException {
    this(currentStage);
    setDataLoader(datastorer.getDataLoader());
    myDataStorer = datastorer;
  }

  private void setDataLoader(DataLoaderAPI Loader) {
    myDataLoader = Loader;
  }

  private void setMenuScene() {
    myStage.setScene(myMenuView.getMenuView());
  }

  /**
   * show menu
   */
  public void showWindowMenu() {
    myStage.show();
  }


  private void initializeButtons() {
    myStartButton = myMenuView.getNewGameButton();
    //myStartButton.setOnAction(e->startGame(myStage));
    myStartButton.setOnAction(e -> selectGameMenu());
    myExitButton = myMenuView.getExitGameButton();
    myExitButton.setOnAction(e -> myStage.close());
    myChangeBackgroundButton = myMenuView.getBackgroundButton();
    myChangeBackgroundButton.setOnAction(e -> switchMode());
    myLoadButton = myMenuView.getLoadButton();
    myLoadButton.setOnAction(e -> {
      try {
        loadlist();
      } catch (DataLoadingException | IOException ex) {
        System.out.println("WINDOW CONTROL LOAD LAST PLAYED");
      }
    });
    myUserButton = myMenuView.getUserButton();
    myUserButton.setOnAction(e -> showProfile());

    myGameButton1 = mySelectView.getGame1();
    myGameButton1.setOnAction(e -> {
      try {
        startGame1();
      } catch (DataLoadingException | IOException ex) {
        System.out.println("WINDOW CONTROL STARTGAME1");
      }
    });
    myGameButton2 = mySelectView.getGame2();
    myGameButton2.setOnAction(e -> {
      try {
        startGame2();
      } catch (Exception ex) {
        System.out.println("WINDOW CONTROL STARTGAME2");
      }
    });
    myGameButton3 = mySelectView.getGame3();
    myGameButton3.setOnAction(e -> {
      try {
        startGame3();
      } catch (IOException|DataLoadingException ex) {
        System.out.println("WINDOW CONTROL STARTGAME3");
      }
    });
    mySettingButton = mySelectView.getSetting();
    mySettingButton.setOnAction(e -> changeSettings());

    myLanguagePicker = myMenuView.getLanguagePicker();
    myLanguagePicker.setOnAction(e -> setLanguage(myLanguagePicker.getValue().toString()));

    myColorPicker = myMenuView.getMyColorPicker();
    myColorPicker.setOnAction(e -> setColor(myColorPicker.getValue()));

  }


  private void setColor(Color color) {
    isColored = true;
    this.color = color;
    myMenuView.changColor(color);
    mySelectView.changColor(color);
    myLogIn.changColor(color);
    mySettingControl.changColor(color);
    myUserProfileControl.changColor(color);
  }

  private void changeSettings() {
    mySettingControl.showSetting();
  }

  private void showProfile() {
    if (!isLogIn) {
      myLogIn.showLogIn();
    } else {
      setUser(myUserName);
    }
  }

  /**
   * When user logs in through user profile control, window control takes note of the username and that there is a user logged in
   * @param s username
   */
  public void setUser(String s) {
    isLogIn = true;
    myUserName = s;
    myUserProfileControl.setUserNameAndShow(s);
  }

  private void selectGameMenu() {
    resetGame = true;
    secondStage = new Stage();
    secondStage.setScene(mySelectView.getMenuView());
    secondStage.show();
  }

  private void setLanguage(String language) {
    this.language = language;
    myMenuView.setLanguage(language);
    mySelectView.setLanguage(language);
    myLogIn.setLanguage(language);
    mySettingControl.setLanguage(language);
    myUserProfileControl.setLanguage(language);
  }

  private void startGame() throws DataLoadingException, IOException {
    if (resetGame) {
      myDataStorer.resetPlayerInfo();
    }

    setUpController();
    Model model = (Model) myGameController.getMyModel();
    GameZelda2DSingle zelda2D = new GameZelda2DSingle(model.getPlayers(), model.getNPCs());
    zelda2D.start();
    while (zelda2D.getView() == null);
    myGameController.setView(zelda2D);
    myGameController.startTimer();
    //myStage.close();
    if (resetGame) {
      secondStage.close();
    }
  }

  private void startGame1() throws DataLoadingException, IOException {
    myDataLoader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(1,2));
    startGame();
  }

  private void startGame2() throws DataLoadingException, IOException {
    myDataLoader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(CURRENT_PLAYER_ID));
    startGame();
  }

  private void setUpController() throws DataLoadingException {
    myGameController = new GameController(myDataStorer);
    if (!isColored) {
      myGameController.setMode(dark);
    } else {
      myGameController.setColor(color);
    }
    myGameController.setLanguage(language);
    myGameController.setWindowControl(this);
    if (myPlayerHP > 0) {
      myGameController.setInitLife(myPlayerHP);
    }
  }


  private void startGame3() throws IOException, DataLoadingException {
    myDataLoader.setGameAndPlayer(GameType.ZELDA.getIndex(), List.of(CURRENT_PLAYER_ID));
    startGame();
  }

  /**
   * Set up menu
   * @param title       title of stage
   * @param height      size of stage
   * @param width       size of stage
   * @param isResizable allow stage to be resized
   */
  public void showWindow(String title, int height, int width, boolean isResizable) {
    myStage.setTitle(title);
    myStage.setWidth(width);
    myStage.setHeight(height);
    myStage.setResizable(isResizable);
    myStage.show();
  }


  private void switchMode() {
    dark = !dark;
    isColored = false;
    myMenuView.switchMode(dark);
    mySelectView.switchMode(dark);
    myLogIn.switchMode(dark);
    mySettingControl.switchMode(dark);
    myUserProfileControl.switchMode(dark);
  }

  private void loadlist() throws DataLoadingException, IOException {
    setUpController();
    resetGame = false;
    int gameID = myGameController.getGameID();
    System.out.println("gameID " + gameID);
    switch (gameID) {
      case 0:
        startGame1();
        break;
      case 1:
        startGame2();
        break;
      case 2:
        startGame3();
        break;
    }

//    setUpController();
//    myGameController.finishGame(myGameController.getMPC(0), true);
  }

  /**
   * Allows window control and thus model in game controller to recognize player set initial hp
   * @param i hp value set in setting
   */
  public void setLife(int i) {
    myPlayerHP = i;
  }

  /**
   * Save score to user profile if there is a user logged in
   * @param score the score of this game
   */
  public void saveUser(int score) {
    if (isLogIn) {
      myUserProfileControl.writeScore(score);
      System.out.println("saved?");
    }
  }

  public boolean isLogIn(){
    return isLogIn;
  }

  public String getUserName() {
    return myUserName;
  }

  public LogInControl getLogInControl() {
    return myLogIn;
  }
}
