package ooga.game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.view.engine.utils.cyberpunk2d.GenerateAgentsData;
import ooga.view.game_view.agent.agent2d.Agent2DDataHolder;
import ooga.view.game_view.game_state.state2d.GameState2DView;

public class GameZelda2DSingle implements Runnable {

  private Thread game;
  private GameState2DView view;

  private boolean isAnimating;
  private List<Boolean> isAttacking;
  private List<Integer> ids;
  private List<String> direction;
  private List<String> state;
  private Map<Integer, Agent2DDataHolder> dataHolderMap;

  public GameZelda2DSingle(Map<Integer, ZeldaPlayer> players, Map<Integer, ZeldaCharacter> npcs)
      throws IOException {
    dataHolderMap = new HashMap<>();
    isAttacking = new ArrayList<>();
    ids = new ArrayList<>();
    direction = new ArrayList<>();
    state = new ArrayList<>();


    for (ZeldaPlayer p: players.values()) {
      System.out.println(p.getId());
      dataHolderMap.put(p.getId(), GenerateAgentsData.createMeleeBot((float) p.getX(), (float) p.getY()));
    }

    for (ZeldaCharacter c: npcs.values()) {
      Agent2DDataHolder holder = getData(c);
      if (holder == null) {
        System.out.println("holder is null");
      } else {
        dataHolderMap.put(c.getId(), holder);
      }
    }
  }

  private Agent2DDataHolder getData(ZeldaCharacter c) throws IOException {
    float x = (float) c.getX();
    float y = (float) c.getY();
    switch (c.getType()) {
      case BIGBOY:
        return GenerateAgentsData.createBigBoy(x, y, c.getDirection().toString());
      case SHIELD:
        return GenerateAgentsData.createShieldman(x, y);
      case TURRET:
        return GenerateAgentsData.createTurret(x, y);
      case WATCHERBOT:
        return GenerateAgentsData.createWatcher(x, y, c.getDirection().toString());
      case ENGINEERBOT:
        return GenerateAgentsData.createEngineer(x, y);
      case LOADSOLDIER:
        return GenerateAgentsData.createSoldier(x, y);
      default:
        return null;
    }
  }

  public void start() {
    game = new Thread(this, "game");
    game.start();
  }

  public void init() throws IOException {
    view = new GameState2DView(dataHolderMap);
    view.createWindow();
  }

  public void run() {
    try {
      init();
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (!view.shouldWindowClose()) {
      try {
        update();
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        render();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    close();
  }

  private void update() throws IOException {
    view.updateWindow();
    view.updateBullets();
    view.updateMap(); //empty method
    if (ids.size() > 0) isAnimating = true;
      if (isAnimating) {
        System.out.println("SIZE: " + ids.size());
        System.out.printf("Rendering, id: %d, direction :%s, state :%s, isAttacking: %s", ids.get(0), direction.get(0), state.get(0), isAttacking.get(0));
        view.updateAgent(ids.get(0), direction.get(0), state.get(0), isAttacking.get(0));
        ids.remove(0);
        direction.remove(0);
        state.remove(0);
        isAttacking.remove(0);
        isAnimating = false;
      }
    }


  public void updateCharacter(int id, String direction, String state, boolean isAttacking) {
    this.ids.add(id);
    this.isAttacking.add(isAttacking);
    this.direction.add(direction);
    this.state.add(state);
  }


  private void render() throws IOException {
    view.renderAll();
    // can also render separately, renderWindow() is mandatory
  }

  private void close() {
    view.closeWindow();
  }

  public GameState2DView getView() {
    return view;
  }

  public float getXPos(int id) {
    return view.getCenterPositionX(id);
  }

  public float getYPos(int id) {
    return view.getCenterPositionY(id);
  }

  public boolean isAttacked(int id) {
//    return view.getHurtStatus(id);
    return false;
  }
}
