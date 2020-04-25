package ooga.data;


import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.AnimationType;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.GameParam;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import static ooga.model.map.GameGridInMap.ID_NOT_DEFINED;

public class DataLoader implements DataLoaderAPI {
  public static String ERROR_MESSAGE_RESOURCES_PACKAGE = "Data/Error_Message";
  public static final int SubMapPerMap = 4;
  public static final String JSON_POSTFIX = ".json";
  private static GameObjectConfiguration gameObjectConfiguration;
  private ResourceBundle errorMessageResources;

  /**
   * fetch an gameObjectConfiguration instance
   * initializes error message resource bundle
   * @throws DataLoadingException
   */
  public DataLoader() throws DataLoadingException {
    gameObjectConfiguration = GameObjectConfiguration.getInstance();
    errorMessageResources = ResourceBundle.getBundle(ERROR_MESSAGE_RESOURCES_PACKAGE);
  }

  /**
   * load game's parameter using Gamepara enum
   * @param para
   * @return
   */
  @Override
  public int loadGameParam(GameParam para) {
    GameInfo gameInfo = gameObjectConfiguration.getCurrentGameInfo();
    return para.getValue(gameInfo);
  }

  @Override
  public int loadPlayerPara(PlayerParam playerParam, int playerID) throws DataLoadingException {
    PlayerStatus playerStatus = gameObjectConfiguration.getPlayerWithID(playerID);
    try {
      return playerStatus.getPlayerParam(playerParam);
    } catch (Exception e) {
      throw new DataLoadingException(String.format("Player %d does not exist", playerID), e);
    }
  }

  @Override
  public int loadCurrentPlayerPara(PlayerParam playerParam) throws DataLoadingException {
    return loadPlayerPara(playerParam, gameObjectConfiguration.getCurrentPlayerID());
  }

  @Override
  public List<Direction> loadAvailableDirection(GameParam para) {
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
  public Cell loadCell(int row, int col, int subMapID, int level) throws DataLoadingException {
    return loadMap(level, subMapID).getElement(row, col);
  }

  @Override
  public int getNextSubMapID(Direction direction, int current) {
    return ID_NOT_DEFINED;
  }

  @Override
  public GameMapGraph loadMap(int level, int subMapID) throws DataLoadingException {

    GameMapGraph map = new GameMapGraph();
    GameInfo gameInfo = gameObjectConfiguration.getCurrentGameInfo();
    String keyOfSubmap = gameInfo.getSubMapInfo().get(level).get(subMapID) + JSON_POSTFIX;
    try {
      Map<String, GameMapGraph> tempMap = gameObjectConfiguration.getGameMapList();
      if (tempMap.containsKey(keyOfSubmap)) {
        map = tempMap.get(keyOfSubmap);
        map.addBufferImage2D(this);//only works for 2D
      } else {
        throw new DataLoadingException(String.format("Map File not found with key %s", keyOfSubmap));
      }

    } catch (Exception e) {
      throw new DataLoadingException(e.getMessage(), e);
    }
    return map;
  }

  /**
   * load buffered Image by providing the image category and ID
   * @param ImageID
   * @param category
   * @return
   * @throws DataLoadingException
   */
  @Override
  public BufferedImage loadBufferImage(int ImageID, ImageCategory category) throws DataLoadingException {
    String imagePath = loadImagePath(ImageID, category);
    try {
      return ImageIO.read(new File(imagePath));
    } catch (IOException e) {
      throw new DataLoadingException(imagePath + " was not loaded.", e);
    }

  }

  /**
   * load text
   * @param keyword
   * @param category
   * @return
   * @throws DataLoadingException
   */
  @Override
  public String loadText(String keyword, String category) throws DataLoadingException {
    Map<String, String> textMap = gameObjectConfiguration.getTextMap().get(category);
    return loadValueOfMap(textMap, keyword);
  }

  /**
   * load charcter's property using ID
   * @param ID
   * @param property
   * @return
   * @throws DataLoadingException
   */
  @Override
  public int loadCharacter(int ID, CharacterProperty property) throws DataLoadingException {
    throw new DataLoadingException("load character property is not supported");
  }

  /**
   * load weapon's property using ID
   * @param ID
   * @param property
   * @return
   * @throws DataLoadingException
   */
  @Override
  public int loadWeapon(int ID, int property) throws DataLoadingException {
    throw new DataLoadingException("load weapon is not supported");
  }

  /**
   * load current level
   * @return
   */
  @Override
  public int currentLevel() {
    return loadGameParam(GameParam.LEVEL_NUM);
  }

  /**
   * keycode are stored in the player files.
   *
   * @param playerID
   * @return
   */
  @Override
  public Map<Integer, String> loadKeyCode(int playerID) throws DataLoadingException {
    PlayerStatus player;
    try {
      player = gameObjectConfiguration.getPlayerWithID(playerID);
    } catch (Exception e) {
      throw new DataLoadingException(String.format("Player with %s is not found while loading key code", playerID)
              , e);
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
  public String loadImagePath(int imageID, ImageCategory category) throws DataLoadingException {
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
  private String loadValueOfMap(Map<String, String> map, String key) throws DataLoadingException {
    if (map != null && checkKeyExist(map, key)) {
      return map.get(key);
    } else {
      throw new DataLoadingException("image not found");
    }
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

  public ResourceBundle getErrorMessageResources() {
    return errorMessageResources;
  }
}
