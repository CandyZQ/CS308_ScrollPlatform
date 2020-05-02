package ooga.model;

import static ooga.model.characters.ZeldaCharacter.DEFAULT_ATTACK;
import static ooga.model.characters.ZeldaCharacter.DEFAULT_WEAPON;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import ooga.data.DataLoaderAPI;
import ooga.data.DataLoadingException;
import ooga.data.PlayerStatus;
import ooga.game.GameType;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.characters.ZeldaPlayer;
import ooga.model.enums.backend.CharacterType;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.gameElements.Element;
import ooga.model.interfaces.ModelInterface;
import ooga.model.interfaces.gameMap.GameMap;

@SuppressWarnings("unchecked")
public class Model implements ModelInterface {

  public static final int THRESHOLD = 10;
  private DataLoaderAPI dataLoader;
  private GameMap gameMap;
  private Map players;
  private Map npcs;

  public Model(DataLoaderAPI dataLoader) throws DataLoadingException {
    this.dataLoader = dataLoader;
//    gameMap = new GameMapInstance(dataLoader);
    switch (GameType.byIndex(dataLoader.getGameType())) {
      case ZELDA:
        initializeZelda();
        break;
      default:
        throw new DataLoadingException("Game type is not supported in the backend");
    }
  }

  private void initializeZelda() {
    List<ZeldaCharacter> characters = dataLoader.getZeldaCharacters();
    npcs = new HashMap<Integer, ZeldaCharacter>();
    for (ZeldaCharacter c : characters) {
//      int rand = new Random().nextInt(CharacterType.values().length - 2) + 1;
      int rand = new Random().nextInt(3) + 1;
      if (c.getId() >= THRESHOLD) {
        ZeldaCharacter zc = new ZeldaCharacter(c.getHP(), DEFAULT_WEAPON, DEFAULT_ATTACK, c.getId(),
            c.getX(), c.getY(), CharacterType.LOADSOLDIER);
//        zc.setType(CharacterType.byIndex(rand));
        zc.setType(CharacterType.ENGINEERBOT);
        npcs.put(zc.getId(), zc);
      }
    }

    players = new HashMap<Integer, ZeldaPlayer>();
    List<Double> positionX = List.of(-1d, -1.2);
    int index = 0;
    List<PlayerStatus> playerStatuses = dataLoader.getCurrentPlayers();
    for (PlayerStatus p : playerStatuses) {
      ZeldaPlayer current = new ZeldaPlayer(
          p.getPlayerParam(PlayerParam.LIFE),
          p.getPlayerID(),
          p.getPlayerParam(PlayerParam.CURRENT_SCORE),
          p.getPlayerParam(PlayerParam.SCORE_GOAL),
          CharacterType.PLAYER);
      current.setX(positionX.get(index));
      current.setY(0);
      players.put(p.getPlayerID(), current);
      index ++;
    }
  }

  @Override
  public void saveGame(String directory) {
  }

  @Override
  public Map getPlayers() {
    return players;
  }

  @Override
  public Map getNPCs() {
    return npcs;
  }

  @Override
  public GameMap getMap() {
    return gameMap;
  }

  @Override
  public Map<Element, Integer> getInventory() {
    return null;
  }

  @Override
  public List<?> getGameElements() {
    return null;
  }

}
