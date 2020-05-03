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

/**
 * Data Loader class responsible for loading all data
 * @author Guangyu Feng
 */
public class DataLoader implements DataLoaderAPI {
  public static String ERROR_MESSAGE_RESOURCES_PACKAGE = "Data/Error_Message";
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
   * @param param the parameter of the game
   * @return the value of the parameter of the game
   */
  @Override
  public int loadGameParam(GameParam param) {
    GameInfo gameInfo = gameObjectConfiguration.getCurrentGameInfo();
    return param.getValue(gameInfo);
  }

  /**
   * Load the param of any specific player
   * @param playerParam the parameter of player
   * @param playerID id of player
   * @return the parameter of player
   */
  @Override
  public int loadPlayerParam(PlayerParam playerParam, int playerID) throws DataLoadingException {
    PlayerStatus playerStatus = gameObjectConfiguration.getPlayerWithID(playerID);
    try {
      return playerStatus.getPlayerParam(playerParam);
    } catch (Exception e) {
      throw new DataLoadingException(String.format(errorMessageResources.getString("loadPlayerParam"), playerID), e);
    }
  }

  /**
   * load the param of current player
   * @param playerParam the parameter of player
   * @return the value of the player's property
   */
  @Override
  public int loadCurrentPlayerPara(PlayerParam playerParam) throws DataLoadingException {
    return loadPlayerParam(playerParam, gameObjectConfiguration.getCurrentPlayerID());
  }

  /**
   * load available directions that is available in current game
   * @return the list of Directions that are available
   */
  @Override
  public List<Direction> loadAvailableDirection() {
    GameInfo gameInfo = gameObjectConfiguration.getCurrentGameInfo();
    return gameInfo.getAvailableAttackDirections();
  }

  /**
   * ***this method has to be called before using any of the loader/storer.
   *
   * @param GameID the ID representing the type of the current Game
   * @param playersID the ID representing player
   */
  @Override
  public void setGameAndPlayer(int GameID, List<Integer> playersID) {
    gameObjectConfiguration.setCurrentPlayerAndGameID(GameID, playersID);
  }

  /**
   * get the type of the game people currently are working on
   * @return int indicating the type of the game
   */
  @Override
  public int getGameType() {
    return gameObjectConfiguration.getCurrentGameID();
  }

  /**
   * load a specific cell
   * @param row the row the cell is at
   * @param col the column the cell is at
   * @param subMapID the ID indicating which submap the cell locates
   * @param level the level the cell is used in
   * @return the Cell at the specified locatioin
   */
  @Override
  public Cell loadCell(int row, int col, int subMapID, int level) {
    return loadMap(level, subMapID).getElement(row, col);
  }

  /**
   * get the subMapID for the map in certain direction
   * @param direction direction of the next submap relative to the current submap
   * @param current the current submap
   * @return the ID of the next submap at the specified direction
   */
  @Override
  public int getNextSubMapID(Direction direction, int current) {
    throw new DataLoadingException("nextSubMapID");
  }

  /**
   * get the whole gameMapGraph object from data
   * @param level the level the map is in
   * @param subMapID the id of the submap the character is in
   * @return
   */
  @Override
  public GameMapGraph loadMap(int level, int subMapID) throws DataLoadingException {

    GameMapGraph map;
    GameInfo gameInfo = gameObjectConfiguration.getCurrentGameInfo();
    String keyOfSubmap = gameInfo.getSubMapInfo().get(level).get(subMapID) + JSON_POSTFIX;

    Map<String, GameMapGraph> tempMap = gameObjectConfiguration.getGameMapList();
    if (tempMap.containsKey(keyOfSubmap)) {
      map = tempMap.get(keyOfSubmap);
      map.addBufferImage2D(this);//only works for 2D
    } else {
      throw new DataLoadingException(String.format(errorMessageResources.getString("loadMap"), keyOfSubmap));
    }
    return map;
  }

  /**
   * load buffered Image by providing the image category and ID
   * @param ImageID id of the image
   * @param category the category of image
   * @return the image in the type of the bufferred image
  */
  @Override
  public BufferedImage loadBufferImage(int ImageID, ImageCategory category) {
    String imagePath = loadImagePath(ImageID, category);
    try {
      return ImageIO.read(new File(imagePath));
    } catch (IOException e) {
      throw new DataLoadingException(imagePath + errorMessageResources.getString("loadBufferImage"), e);
    }
  }

  /**
   * load text
   * @param keyword the keyword that maps the text in data file
   * @param category the type of the text
   * @return the string user requesting
   */
  @Override
  public String loadText(String keyword, String category) throws DataLoadingException {
    Map<String, String> textMap = gameObjectConfiguration.getTextMap().get(category);
    return loadValueOfMap(textMap, keyword);
  }

  /**
   * load charcter's property using ID
   * @param ID character ID
   * @param property charcter's property type
   * @return charcter's property value
   */
  @Override
  public int loadCharacter(int ID, CharacterProperty property) throws DataLoadingException {
    throw new DataLoadingException(errorMessageResources.getString("loadCharacter"));
  }

  /**
   * load weapon's property using ID
   * @param ID weapon ID
   * @param property weapon property
   * @return weapon attack value
   */
  @Override
  public int loadWeapon(int ID, int property) throws DataLoadingException {
    throw new DataLoadingException(errorMessageResources.getString("loadWeapon"));
  }

  /**
   * load current level
   * @return current level
   */
  @Override
  public int currentLevel() {
    return loadGameParam(GameParam.LEVEL_NUM);
  }

  /**
   * keycode are stored in the player files.
   * @param playerID id of player
   * @return key code map
   */
  @Override
  public Map<Integer, String> loadKeyCode(int playerID) {
    PlayerStatus player;
    try {
      player = gameObjectConfiguration.getPlayerWithID(playerID);
    } catch (Exception e) {
      throw new DataLoadingException(String.format(errorMessageResources.getString("loadKeyCode"), playerID)
              , e);
    }
    return player.getKeyCodeMap();
  }

  /**
   * in Json, <int, String> always returns <Stirng, String>
   *
   * @param imageID id of image
   * @param category category of image
   * @return the path of the string
   */
  @Override
  public String loadImagePath(int imageID, ImageCategory category) {
    Map<String, String> imageMap = gameObjectConfiguration.getImageMap().get(category.toString());
    String key = String.valueOf(imageID);
    return loadValueOfMap(imageMap, key);
  }
  //using the key to load the value of the <String, String> map
  private String loadValueOfMap(Map<String, String> map, String key) {
    if (map != null && checkKeyExist(map, key)) {
      return map.get(key);
    } else {
      throw new DataLoadingException(errorMessageResources.getString("loadValueOfMap"));
    }
  }
  //check whether a key exists in a map
  private <K, V> boolean checkKeyExist(Map<K, V> map, K key) {
    return (map.get(key) != null);
  }

  /**
   * get the object that stores all loaded data
   * @return  object that stores all loaded data
   */
  public static GameObjectConfiguration getGameObjectConfiguration() {
    return gameObjectConfiguration;
  }

  /**
   * get the list of current available zelda characters
   * @return list of current available zelda characters
   */
  @Override
  public List<ZeldaCharacter> getZeldaCharacters() {
    return gameObjectConfiguration.getZeldaCharacterList();
  }

  /**
   * get the list of current available players
   * @return list of current available players
   */
  @Override
  public List<PlayerStatus> getCurrentPlayers() {
    return gameObjectConfiguration.getCurrentPlayers();
  }

  /**
   * get certain type of animation
   * @param animationType type of animation
   * @return <Animation Name, Animation Object> map
   */
  @Override
  public Map<String, Animation2D> loadAnimation(AnimationType animationType) {
    return gameObjectConfiguration.getSpecificAgentAnimation(animationType.toString() + JSON_POSTFIX);
  }

  /**
   * get the resource bundle that fetches the error message property file.
   * @return the error message from the resource
   */
  public ResourceBundle getErrorMessageResources() {
    return errorMessageResources;
  }
}