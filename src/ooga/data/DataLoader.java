package ooga.data;


import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.AnimationType;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.backend.CharacterType;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.GamePara;
import ooga.model.enums.backend.PlayerPara;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static ooga.model.map.GameGridInMap.ID_NOT_DEFINED;

public class DataLoader implements DataLoaderAPI {

  public static final int SubMapPerMap = 4;
  public static final String JSON_POSTFIX = ".json";
  private static GameObjectConfiguration gameObjectConfiguration;

  private com.google.gson.Gson gson;

  public DataLoader() throws DataLoadingException {
    gameObjectConfiguration = GameObjectConfiguration.getInstance();
  }

  @Override
  public int loadGameParam(GamePara para) {
    GameInfo gameInfo = gameObjectConfiguration.getCurrentGameInfo();
    int level = gameInfo.getLevelNum();
    switch (para) {
      case GRID_NUM:
        return gameInfo.getSubMapInfo().get(level).size();
      case LEVEL_NUM:
        return level;
      case NPC_NUM:
        return gameInfo.getNPC_ID().size();
      case GAME_TYPE:
        return gameInfo.getGameType();
      case PLAYER_NUM:
        return gameInfo.getPlayer_ID().size();
      //TODO: move XY to player
      case INIT_POS_X:
        return gameInfo.getInitialPosition()[0];
      case INIT_POS_Y:
        return gameInfo.getInitialPosition()[1];
    }
    return ID_NOT_DEFINED;
  }

  @Override
  public int loadPlayerPara(PlayerPara playerPara, int playerID) throws DataLoadingException {
    PlayerStatus playerStatus = gameObjectConfiguration.getPlayerWithID(playerID);
    if (playerStatus != null) {
      return playerStatus.getPlayerParam(playerPara);
    }
    throw new DataLoadingException("Player " + playerID + " does not exist");
  }

  @Override
  public int loadCurrentPlayerPara(PlayerPara playerPara) throws DataLoadingException {
    return loadPlayerPara(playerPara, gameObjectConfiguration.getCurrentPlayerID());
  }

  @Override
  public List<Direction> loadAvailableDirection(GamePara para) {
    GameInfo gameInfo = gameObjectConfiguration.getCurrentGameInfo();
    return gameInfo.getAvailableAttackDirections();
  }

  /**
   * ***this method has to be called before using any of the loader/storer.
   *
   * @param GameID
   * @param playersID
   */
  @Override
  public void setGameAndPlayer(int GameID, List<Integer> playersID) {
    gameObjectConfiguration.setCurrentPlayerAndGameID(GameID, playersID);
  }

  @Override
  public int getGameType() {
    return gameObjectConfiguration.getCurrentGameID();
  }

  @Override
  public Cell loadCell(int row, int col, int subMapID, int level) {
    return loadMap(level, subMapID).getElement(row, col);
  }

  @Override
  public int getNextSubMapID(Direction direction, int current) {
    return ID_NOT_DEFINED;
  }

  @Override
  public GameMapGraph loadMap(int level, int subMapID) {

    GameMapGraph map = new GameMapGraph();
    GameInfo gameInfo = gameObjectConfiguration.getCurrentGameInfo();
    String keyOfSubmap = gameInfo.getSubMapInfo().get(level).get(subMapID) + ".json";
    try {
      Map<String, GameMapGraph> tempMap = gameObjectConfiguration.getGameMapList();
      if (tempMap.containsKey(keyOfSubmap)) {
        map = tempMap.get(keyOfSubmap);
        map.addBufferImage2D(this);//only works for 2D
      } else {
        //todo: throw errors for file not found.
      }

//      map = loadJson("data/GameMap/"+ gameInfo.getSubMapInfo().get(level).get(subMapID), map.getClass());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }

  @Override
  public BufferedImage loadBufferImage(int ImageID, ImageCategory category) {
    String imagePath = loadImagePath(ImageID, category);
    try {
      return ImageIO.read(new File(imagePath));
    } catch (IOException e) {
      // If the image cannot be loaded, the window closes
      System.err.println(imagePath + " was not loaded.");
    }
    return null;
  }

  @Override
  public String loadText(String keyword, String category) {
    Map<String, String> textMap = gameObjectConfiguration.getTextMap().get(category);
    return loadValueOfMap(textMap, keyword);
  }

  @Override
  public int loadCharacter(int ID, CharacterProperty property) {
    ZeldaCharacter zeldaCharacter = new ZeldaCharacter(1, 2, CharacterType.UNKNOWN);

//    zeldaCharacter =  loadJson("data/ZeldaCharacter/" + characterKeyword + ID + ".json", zeldaCharacter.getClass());
    for (ZeldaCharacter i : gameObjectConfiguration.getZeldaCharacterList()) {
      if (i.getId() == ID) {
        zeldaCharacter = i;
      }
    }


    return property.getProperty(zeldaCharacter);
  }

  @Override
  public int loadWeapon(int ID, int property) {
    System.out.println("load weapon is not supported");
    return 0;
  }

  @Override
  public int currentLevel() {
    return loadGameParam(GamePara.LEVEL_NUM);
  }

  /**
   * keycode are stored in the player files.
   *
   * @param playerID
   * @return
   */
  @Override
  public Map<Integer, String> loadKeyCode(int playerID) throws DataLoadingException {
    PlayerStatus player = gameObjectConfiguration.getPlayerWithID(playerID);
    if (player == null) {
      throw new DataLoadingException(
          "Player with" + playerID + "is not found while loading key code");
    }

    return player.getKeyCodeMap();
  }

  /**
   * in Json, <int, String> always returns <Stirng, String>
   *
   * @param imageID
   * @param category
   * @return
   */
  @Override
  public String loadImagePath(int imageID, ImageCategory category) {
    Map<String, String> imageMap = gameObjectConfiguration.getImageMap().get(category.toString());
    String key = String.valueOf(imageID);
    return loadValueOfMap(imageMap, key);
  }

  /**
   * load value of the map
   *
   * @param map
   * @param key
   * @return
   */
  private String loadValueOfMap(Map<String, String> map, String key) {
    if (map != null && checkKeyExist(map, key)) {
      return map.get(key);
    }
    //todo: throw errors for file not found. Throw errors when imageID is not found in the file.
    return null;
  }

  private <K, V> boolean checkKeyExist(Map<K, V> map, K key) {
    return (map.get(key) != null);
  }

  public static GameObjectConfiguration getGameObjectConfiguration() {
    return gameObjectConfiguration;
  }

  @Override
  public List<ZeldaCharacter> getZeldaCharacters() {
    return gameObjectConfiguration.getZeldaCharacterList();
  }

  @Override
  public List<PlayerStatus> getCurrentPlayers() {
    return gameObjectConfiguration.getCurrentPlayers();
  }

  @Override
  public Map<String, Animation2D> loadAnimation(AnimationType animationType) {
    return gameObjectConfiguration.getSpecificAgentAnimation(animationType.toString() + JSON_POSTFIX);
  }
}
