package ooga.controller.gamecontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import ooga.controller.FinishControl;
import ooga.controller.WindowControl;
import ooga.controller.gamecontrol.NPC.MainNPCControl;
import ooga.controller.gamecontrol.player.MainPlayerControl;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.DataStorer;
import ooga.data.DataStorerAPI;
import ooga.game.GameZelda2DSingle;
import ooga.model.Model;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.backend.MovingState;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.movables.Movable1D;
import org.lwjgl.glfw.GLFW;

public class GameController {

  public static final double MIN_DIS = 1;
  private ModelInterface myModel;
  private List<MainPlayerControl> myMainPlayerController = new ArrayList<>(); //user controled player
  private List<MainNPCControl> myNPCControl = new ArrayList<>();
  private PauseControl myPauseControl;
  private FinishControl myFinishControl;
  private WindowControl myWindowControl;
  private DisplayStatusControl mydDsplayControl;
  private DataLoaderAPI myDataLoader;
  private DataStorerAPI myDataStorer;
  private boolean dark;
  private String language;
  private GameZelda2DSingle myGameView;
  private AnimationTimer myTimer;
  private boolean win;

  public GameController(DataStorerAPI storer) throws DataLoadingException {
    myModel = new Model(storer.getDataLoader());
    myDataLoader = storer.getDataLoader();
    myDataStorer = storer;
    setUpPlayerandNPC();
    myPauseControl = new PauseControl(this);
    myFinishControl = new FinishControl(this);
    mydDsplayControl = new DisplayStatusControl();
    mydDsplayControl.showMenu();
  }


  public void startTimer() {
    myTimer = new AnimationTimer() {
      @Override
      public void handle(long now) {
        update();
      }
    };
    myTimer.start();
    myPauseControl.setTimer(myTimer);
  }

  private void setUpPlayerandNPC() {
    System.out.println(myDataLoader.getGameType());
    setGameType(myDataLoader.getGameType());
    for (MainPlayerControl mpc : myMainPlayerController) {
      mpc.setID();
      try {
        mpc.setNewKeyMap(myDataLoader.loadKeyCode(mpc.getID()));
      } catch (Exception e) {
        System.out.println("load key error");
      }
    }
    for (MainNPCControl npc : myNPCControl) {
      npc.setID();
    }
  }

  private void setGameType(int gameType) {
    for (Object player : myModel.getPlayers().values()) {
      MainPlayerControl curControl = new MainPlayerControl();
      curControl.setControl(gameType);
      curControl.setMyPlayer((Movable1D) player);
      myMainPlayerController.add(curControl);
    }

    for (Object NPC : myModel.getNPCs().values()) {
      MainNPCControl npcControl = new MainNPCControl();
      npcControl.setControl(((ZeldaCharacter) NPC).getType().getIndex());
      npcControl.setMyNPC((Movable1D) NPC);
      myNPCControl.add(npcControl);
    }
  }

  public void update() {
    deathCheck();
    mydDsplayControl.update(getSScoreList(), getLifeList());
    for (MainNPCControl npc : myNPCControl) {
      if (!npc.isHurt()) {
        npc.update();
      }
    }

    for (MainPlayerControl mpc : myMainPlayerController) {
      if (!mpc.isHurt()) {
        mpc.updateKey();
        if (!mpc.update()) {
          finishGame(mpc, false); // this is dead
          win = false;
        }
        if (mpc.hasWon()) {
          finishGame(mpc, true); // this is won
        }
      }
    }
    if (myGameView.getView().isKeyDown(GLFW.GLFW_KEY_P)) {
      pause();
    }
    distanceCheck();
    attackCheck();
  }

  private void deathCheck() {
    for (MainNPCControl npc : myNPCControl) {
      if (!npc.getCharacter().isAlive()) {
        npc.getCharacter().setState(MovingState.DEATH);
      }
    }
    myNPCControl.removeIf(npc -> npc.getCharacter().getState() == MovingState.DEATH);
  }

  private void attackCheck() {
    for (MainPlayerControl mpc : myMainPlayerController) {
      if (myGameView.isAttacked(mpc.getID())) {
        mpc.getHurt();
      }
    }

    for (MainNPCControl npc : myNPCControl) {
      if (myGameView.isAttacked(npc.getID())) {
        System.out.println("attacked: " + npc.getID());
        npc.getHurt((ZeldaPlayer) myMainPlayerController.get(0).getPlayer());
      }
    }
  }

  private void distanceCheck() {
    for (MainPlayerControl mpc : myMainPlayerController) {
      for (MainNPCControl npc : myNPCControl) {
        if (Math.abs(myGameView.getXPos(mpc.getID()) - myGameView.getXPos(npc.getID())) < MIN_DIS &&
            Math.abs(myGameView.getYPos(mpc.getID()) - myGameView.getYPos(npc.getID())) < MIN_DIS) {
          npc.attack();
        }
      }
    }
  }

  public void pause() {
    myPauseControl.updateScore(getSScoreList());
    myPauseControl.updateLife(getLifeList());
    myPauseControl.showMenu();
  }

  public void finishGame(MainPlayerControl mpc, boolean win) {
    myTimer.stop();
    myFinishControl.showMenu(win, mpc.getID(), (int) ((ZeldaPlayer) mpc.getPlayer()).getScore());
    myFinishControl.setScore(getSScoreList());
  }

  public void setMode(boolean dark) {
    this.dark = dark;
    myPauseControl.setMode(dark);
    myFinishControl.setMode(dark);
  }

  public void setLanguage(String language) {
    this.language = language;
    myPauseControl.setLanguage(language);
    myFinishControl.setLanguage(language);
  }

  public void setView(GameZelda2DSingle view) {
    myGameView = view;
    myPauseControl.setView(view.getView());
    for (MainPlayerControl mpc : myMainPlayerController) {
      mpc.setView(view);
    }
    for (MainNPCControl npc : myNPCControl) {
      npc.setView(view);
    }
  }

  public void setWindowControl(WindowControl windowControl) {
    myWindowControl = windowControl;
    System.out.println(myWindowControl);
    myPauseControl.setWindowControl(windowControl);
    myFinishControl.setWindowControl(windowControl);
  }

  public void keyReleased() {
    for (MainPlayerControl mpc : myMainPlayerController) {
      mpc.keyReleased();
    }
  }

  public void save() {
    for (MainPlayerControl mpc : myMainPlayerController) {
      ((DataStorer) myDataStorer).storeCharacter(mpc.getID(), (ZeldaCharacter) mpc.getPlayer());
    }
    myDataStorer.writeAllDataIntoDisk();
    myWindowControl
        .saveUser((int) ((ZeldaPlayer) myMainPlayerController.get(0).getPlayer()).getScore());
    System.out.println("game controller - save method called");
  }

  public void setInitLife(int i) {
    for (MainPlayerControl mpc : myMainPlayerController) {
      ((ZeldaPlayer) mpc.getPlayer()).setHP(i);
    }
  }

  private Map<Integer, Integer> getSScoreList() {
    Map<Integer, Integer> ret = new HashMap<>();
    for (MainPlayerControl mpc : myMainPlayerController) {
      int id = mpc.getID();
      int score = (int) ((ZeldaPlayer) mpc.getPlayer()).getScore();
      ret.put(id, score);
    }
    return ret;
  }

  private Map<Integer, Integer> getLifeList() {
    Map<Integer, Integer> ret = new HashMap<>();
    for (MainPlayerControl mpc : myMainPlayerController) {
      int id = mpc.getID();
      int hp = ((ZeldaPlayer) mpc.getPlayer()).getHP();
      ret.put(id, hp);
    }
    return ret;
  }

  public void setColor(Color color) {
    myPauseControl.setColor(color);
    myFinishControl.setColor(color);
  }

  public int getGameID() {
    return myDataLoader.getCurrentPlayers().get(0).getPlayerParam(PlayerParam.Game);
  }

  public ModelInterface getMyModel() {
    return myModel;
  }

  public void reset() {
    myDataStorer.resetPlayerInfo();
  }

  public MainPlayerControl getMPC(int i) {
    return myMainPlayerController.get(i);
  }
}
