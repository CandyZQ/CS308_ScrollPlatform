package ooga.data;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.TextCategory;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;
import ooga.view.engine.io.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static ooga.data.DataLoader.JSON_POSTFIX;
import static ooga.game.GameMain.HEIGHT;
import static ooga.game.GameMain.WIDTH;

/**
 * Class responsible for centralized loading and storing
 * @author Guangyu Feng
 */
public class GameObjectConfiguration {
  private static final String CELL_CLASS_NAME = "ooga.model.map.GameCell";
  private static final String DELIMITER_PRAM_RESOURCE_FILE = ",";
  private static final String LIST_KEYWORD = "List";
  private static final String MAP_KEYWORD = "Map";
  private static final String FILE_PATH_DELIMITER = "/";
  private static final String EXCEPTION_KEYWORD = "Can't Load Data";
  private static String PARAM_RESOURCES_PACKAGE = "Data/param_and_path";

  private List<GameInfo> gameInfoList;
  private Map<String, GameMapGraph> gameMapList;
  private Map<String, Map<String, String>> imageMap;
  private List<PlayerStatus> playerList;
  private List<ZeldaCharacter> zeldaCharacterList;
  private Map<String, Map<String, String>> textMap; //Map<Category, Map<Keyword, Text>>
  private com.google.gson.Gson gsonLoad;
  private com.google.gson.Gson gsonStore;
  private ResourceBundle resources;


  private Map<String, Map<String, Animation2D>> animationMap;
  private List<Integer> currentPlayersID;
  private int currentPlayerID;
  private int currentGameID;


  private static GameObjectConfiguration gameObjectConfiguration;

  /**
   * Static 'instance' method
   */
  public static GameObjectConfiguration getInstance() {
    if (gameObjectConfiguration == null) {
      gameObjectConfiguration = new GameObjectConfiguration();
    }

    return gameObjectConfiguration;
  }

  private GameObjectConfiguration() throws DataLoadingException {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls();
    gsonStore = gsonBuilder.create();
    gsonBuilder.registerTypeAdapter(Cell.class, new InterfaceAdapter(CELL_CLASS_NAME));
    gsonLoad = gsonBuilder.create();

    resources = ResourceBundle.getBundle(PARAM_RESOURCES_PACKAGE);
    currentPlayerID = 1;
    currentGameID = 1;

    try {
      initiateDataStorageInstanceVariable();
    } catch (IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
      throw new DataLoadingException(e.getMessage(), e);
    }
  }


  private void initiateDataStorageInstanceVariable() throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
    Window window = new Window(WIDTH, HEIGHT, PARAM_RESOURCES_PACKAGE);
    window.create();
    for (String key : Collections.list(resources.getKeys())) {
      Field field = initializeFieldObject(key);
      String[] value = resources.getString(key).split(DELIMITER_PRAM_RESOURCE_FILE);
      String directoryPath = value[0];
      String type = value[1];
      String instanceClass = value[2];

      File dir = new File(directoryPath);
      File[] directoryListing = dir.listFiles();
      try {
        field.set(this, new ArrayList<>());
      } catch (Exception e) {
        field.set(this, new HashMap<>());
      }

      try {
        for (File child : directoryListing) {
          if (type.equals(LIST_KEYWORD)) {
            loadFilesUnderDirectoryForList(directoryPath, child.getName(), field,
                Class.forName(instanceClass), type);
          } else if (type.equals(MAP_KEYWORD)) {
            loadFilesUnderDirectoryForMap(directoryPath, child.getName(), field,
                Class.forName(instanceClass), type);
          } else {
            Type type2 = new TypeToken<Map<String, Animation2D>>() {
            }.getType();

            Map<String, Animation2D> tempAgent = loadJson(directoryPath + child.getName(), type2);
            createTextureToAnimation(tempAgent);
            animationMap.put(child.getName(), tempAgent);

          }
        }
      } catch (Exception e) {
        //this catch is used because some teammate's computer will run into errors without it. However, this problem is not displayed when I am running the program.

        throw new DataLoadingException(EXCEPTION_KEYWORD + key, e);
      }
    }
    window.destroy();
  }

  private <T> void loadFilesUnderDirectoryForList (String myDirectoryPath, String fileName, Field field, Class clazz, String type) throws IllegalAccessException {
    List<T> tempList = (List<T>) field.get(this);
    tempList.add(loadJson(myDirectoryPath + fileName, clazz));
    field.set(this, tempList);
  }
  private <T> void loadFilesUnderDirectoryForMap (String myDirectoryPath, String fileName, Field field, Class clazz, String type) throws IllegalAccessException {
    Map<String, T> tempMap = (Map<String, T>) field.get(this);
    tempMap.put(fileName,
            loadJson(myDirectoryPath + fileName, clazz));
    field.set(this, tempMap);
  }

  private void createTextureToAnimation(Map<String, Animation2D> meleeRobotAnimations) {
    for (Animation2D i : meleeRobotAnimations.values()) {
      for (int j = 0; j < i.getFrameAmount(); j++) {
        i.getAnimatedFrame(j).createTexture();
      }
    }
  }

  /**
   * write data stored in the object into the disk
   */
  public void writeAllDataToDisk() {
    for (String key : Collections.list(resources.getKeys())) {

      try {
        Field field = initializeFieldObject(key);
        String[] value = resources.getString(key).split(DELIMITER_PRAM_RESOURCE_FILE);
        String type = value[1];
        String directoryPath = value[0];

        if (type.equals(LIST_KEYWORD)) {
          String getfileNameIDMethod = value[3];
          storeListToDisk(field, directoryPath, getfileNameIDMethod);
        } else {
          storeMapToDisk(field, directoryPath);
        }
      } catch (IllegalAccessException | NoSuchFieldException e) {
        throw new DataLoadingException(e.getMessage(), e);
      }
    }
  }
  private <T> void storeMapToDisk(Field field, String directoryPath) throws IllegalAccessException {
    Map<String, T> tempMap = (Map<String, T>) field.get(this);
    for (String j : tempMap.keySet()) {
      writeObjectTOJson(tempMap.get(j), directoryPath + j);
    }
  }
  private <E> void storeListToDisk(Field field, String directoryPath, String getfileNameIDMethod) throws IllegalAccessException {
    List<E> tempList = (List<E>) field.get(this);
    String[] pathArray = directoryPath.split(FILE_PATH_DELIMITER);
    String folderName = pathArray[pathArray.length - 1];

    for (E j : tempList) {
      Method methodcall = null;
      try {
        methodcall = j.getClass().getDeclaredMethod(getfileNameIDMethod); //getFileNameID method has to be no-arg
        writeObjectTOJson(j, directoryPath + folderName + methodcall.invoke(j) + JSON_POSTFIX);//naming convention of GameInfo is changed.
      } catch (NoSuchMethodException | InvocationTargetException e) {
        throw new DataLoadingException(e.getMessage(), e);
      }
    }
  }

  private Field initializeFieldObject(String key) throws NoSuchFieldException {
    Class cls = this.getClass();
    Field field = cls.getDeclaredField(key);
    field.setAccessible(true);
    return field;
  }

  /**
   * get the List of GameInfo object
   * @return
   */
  public List<GameInfo> getGameInfoList() {
    return gameInfoList;
  }

  public void setGameInfoList(List<GameInfo> gameInfoList) {
    this.gameInfoList = gameInfoList;
  }

  public Map<String, GameMapGraph> getGameMapList() {
    return gameMapList;
  }

  public void setGameMapList(Map<String, GameMapGraph> gameMapList) {
    this.gameMapList = gameMapList;
  }

  public Map<String, Map<String, String>> getImageMap() {
    return imageMap;
  }

  public void setImageMap(Map<String, Map<String, String>> imageMap) {
    this.imageMap = imageMap;
  }

  public List<PlayerStatus> getPlayerList() {
    return playerList;
  }

  public void setPlayerList(List<PlayerStatus> playerList) {
    this.playerList = playerList;
  }

  public List<ZeldaCharacter> getZeldaCharacterList() {
    return zeldaCharacterList;
  }

  public void setZeldaCharacterList(List<ZeldaCharacter> zeldaCharacterList) {
    this.zeldaCharacterList = zeldaCharacterList;
  }


  public Map<String, Map<String, String>> getTextMap() {
    return textMap;
  }

  public void setTextMap(Map<String, Map<String, String>> textMap) {
    this.textMap = textMap;
  }

  public <clazz> clazz loadJson(String fileName, Type clazz) {
    try {
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      return (clazz) gsonLoad.fromJson(reader, clazz);
    } catch (IOException e) {
      throw new DataLoadingException(EXCEPTION_KEYWORD, e);
    }
  }


  private void writeObjectTOJson(Object object, String filePath) {
    try {
      FileWriter Writer1 = new FileWriter(filePath);
      gsonStore.toJson(object, Writer1);
      Writer1.flush();
      Writer1.close();
    } catch (IOException e) {
      throw new DataLoadingException(e.getMessage(), e);
    }
  }

  /**
   * set the image map to corresponding values
   * @param newImageMap
   * @param imageCategory
   */
  public void setImageMap(Map<String, String> newImageMap, ImageCategory imageCategory) {
    String newKey = imageCategory.toString();
    insertElementToMap(imageMap, newKey, newImageMap);
  }

  /**
   * get the player ID of the current player
   * @return
   */
  public int getCurrentPlayerID() {
    return currentPlayersID.get(0);
  }

  /**
   * get the ID of the current game
   * @return
   */
  public int getCurrentGameID() {
    return currentGameID;
  }

  /**
   * set the current player and game using ID
   * @param currentGameID
   * @param currentPlayersID
   */
  public void setCurrentPlayerAndGameID(int currentGameID, List<Integer> currentPlayersID) {
    this.currentPlayersID = currentPlayersID;
    this.currentGameID = currentGameID;

  }

  /**
   * retrieve player with specific ID. If Player doesn't exist, returns null. Please handle all the
   * situations where player doesn't exist by the caller.
   *
   * @param playerID
   * @return
   */
  public List<PlayerStatus> getPlayersWithID(List<Integer> playerID) {
    List<PlayerStatus> playerStatuses = new ArrayList<>();
    for (Integer id: playerID) {
      boolean contains = false;
      for (PlayerStatus i : playerList) {
        if (i.getPlayerID() == id) {
          playerStatuses.add(i);
          contains = true;
        }
      }

      if (!contains) {
        PlayerStatus temp = new PlayerStatus(currentGameID, id);
        temp.setPlayerParam(PlayerParam.Game, currentGameID);
        playerStatuses.add(temp);
        setPlayerWithID(id, temp);
      }
    }
    return playerStatuses;
  }

  /**
   * get the Player status using its ID
   * @param playerID
   * @return
   */
  public PlayerStatus getPlayerWithID(int playerID) {
    for (PlayerStatus i : playerList) {
      if (i.getPlayerID() == playerID) {
        return i;
      }
    }

    return null;
  }

  /**
   * get the list of the current players in objects
   * @return
   */
  public List<PlayerStatus> getCurrentPlayers() {
    return getPlayersWithID(currentPlayersID);
  }

  /**
   * get the current player's player status
   * @return
   */
  public PlayerStatus getCurrentPlayer() {
    return getCurrentPlayers().get(0);
  }


  /**
   * this method handles adding players.
   *
   * @param playerID
   * @param player
   */
  public void setPlayerWithID(int playerID, PlayerStatus player) {
    List<PlayerStatus> tempList = new ArrayList<>();
    for (PlayerStatus i : gameObjectConfiguration.getPlayerList()) {
      if (i.getPlayerID() != playerID) {
        tempList.add(i);
      }
    }
    tempList.add(player);
    playerList = tempList;
  }

  /**
   * set the text map
   * @param text
   * @param keyword
   * @param category
   */
  public void setTextMap(String text, String keyword, TextCategory category) {
    Map<String, String> tempTextMap = textMap.get(category.toString());
    try {
      tempTextMap = insertElementToMap(tempTextMap, keyword, text);
      textMap.replace(category.toString(), tempTextMap);
    } catch (Exception e) {
      throw new DataLoadingException(e.getMessage(), e);
    }

  }

  /**
   * get the game info and param of current game
   * @return
   */
  public GameInfo getCurrentGameInfo() {
    return getGameInfo(currentGameID, getCurrentPlayer().getPlayerParam(PlayerParam.CURRENT_LEVEL));
  }

  /**
   * get the game information
   * @param level
   * @param id
   * @return
   */
  public GameInfo getGameInfo(int level, int id) {
    for (GameInfo i : gameInfoList) {
      if (i.getGameType() == id && i.getLevelNum() == level) {
        return i;
      }
    }
    return null;
  }

  /**
   * set the animation map
   * @param agent
   * @param agentAnimation
   */
  public void setAnimationMap(String agent, Map<String, Animation2D> agentAnimation) {
    insertElementToMap(animationMap, agent, agentAnimation);
  }

  /**
   * get the specific animation info
   * @param agent
   * @return
   */
  public Map<String, Animation2D> getSpecificAgentAnimation(String agent) {
    return animationMap.get(agent);
  }

  /**
   * insert an element into the map
   * @param map
   * @param newkey
   * @param newValue
   * @param <K>
   * @param <V>
   * @return
   */
  public <K, V> Map<K, V> insertElementToMap(Map<K, V> map, K newkey, V newValue) {
    if (map.containsKey(newkey)) {
      map.replace(newkey, newValue);
    } else {
      map.put(newkey, newValue);
    }
    return map;
  }
//  public <E> List<E> setelementInListWithID(List<E> list, E element, int ID) {
//    List<E> tempList = new ArrayList<>();
//    for (E i : list) {
//      if (i.getPlayerID() != ID) {
//        tempList.add(i);
//      }
//    }
//    tempList.add(element);
//    list = tempList;
//  }
}
