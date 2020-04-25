package ooga.data;

import static ooga.data.DataLoader.JSON_POSTFIX;
import static ooga.game.GameMain.HEIGHT;
import static ooga.game.GameMain.WIDTH;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.TextCategory;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;
import ooga.view.engine.io.Window;

/**
 * this is the man, the object storing EVERY piece of info!
 */
public class GameObjectConfiguration {
  public static String PARAM_RESOURCES_PACKAGE = "Data/param_and_path";

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
  private int currentPlayerID = 1;
  private int currentGameID = 1;


  private static GameObjectConfiguration gameObjectConfiguration;

  /**
   * Static 'instance' method
   */
  public static GameObjectConfiguration getInstance() throws DataLoadingException {
    if (gameObjectConfiguration == null) {
      gameObjectConfiguration = new GameObjectConfiguration();
    }
    return gameObjectConfiguration;
  }

  private GameObjectConfiguration() throws DataLoadingException {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.serializeNulls(); //ensure gson storing null values.
    gsonStore = gsonBuilder.create();
    gsonBuilder.registerTypeAdapter(Cell.class, new InterfaceAdapter("ooga.model.map.GameCell"));
    gsonLoad = gsonBuilder.create();//3 lines above are the same as DataStorer

    resources = ResourceBundle.getBundle(PARAM_RESOURCES_PACKAGE);

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
      String[] value = resources.getString(key).split(",");
      String type = value[1];
      String directoryPath = value[0];
      String instanceClass = value[2];

      File dir = new File(directoryPath);
      File[] directoryListing = dir.listFiles();
      try {
        field.set(this, new ArrayList<>());
      } catch (Exception d) {
        field.set(this, new HashMap<>());
      }

      try {
        for (File child : directoryListing) {
          if (type.equals("List")) {
            loadFilesUnderDirectoryForList(directoryPath, child.getName(), field,
                Class.forName(instanceClass), type);
          } else if (type.equals("Map")) {
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
      } catch (Exception e) { }
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

  public void writeAllDataToDisk() {
    for (String key : Collections.list(resources.getKeys())) {

      try {
        Field field = initializeFieldObject(key);
        String[] value = resources.getString(key).split(",");
        String type = value[1];
        String directoryPath = value[0];

        if (type.equals("List")) {
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
    String[] pathArray = directoryPath.split("/");
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
      throw new DataLoadingException(String.format("file at %s hasn't been created.", fileName), e);
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
      //throw appropriate Exceptions
    }
  }

  public void setImageMap(Map<String, String> newImageMap, ImageCategory imageCategory) {
    String newKey = imageCategory.toString();
    insertElementToMap(imageMap, newKey, newImageMap);
  }

  public List<Integer> getCurrentPlayersID() {
    return currentPlayersID;
  }

  public int getCurrentPlayerID() {
    return currentPlayersID.get(0);
  }

  public int getCurrentGameID() {
    return currentGameID;
  }

  public void setCurrentPlayerAndGameID(int currentGameID, List<Integer> currentPlayersID) {
    this.currentPlayersID = currentPlayersID;
    this.currentGameID = currentGameID;
    List<PlayerStatus> tempPlayers = getPlayersWithID(currentPlayersID);
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

  public PlayerStatus getPlayerWithID(int playerID) {
    for (PlayerStatus i : playerList) {
      if (i.getPlayerID() == playerID) {
        return i;
      }
    }

    return null;
  }

  public List<PlayerStatus> getCurrentPlayers() {
    return getPlayersWithID(currentPlayersID);
  }

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

  public void setTextMap(String text, String keyword, TextCategory category) {
    Map<String, String> tempTextMap = textMap.get(category.toString());
    if (tempTextMap == null) {
      throw new DataLoadingException("text category not found");
    }
    tempTextMap = insertElementToMap(tempTextMap, keyword, text);
    textMap.replace(category.toString(), tempTextMap);
  }

  public GameInfo getCurrentGameInfo() {
    return getGameInfo(currentGameID, getCurrentPlayer().getPlayerParam(PlayerParam.CURRENT_LEVEL));
  }

  public GameInfo getGameInfo(int level, int id) {
    for (GameInfo i : gameInfoList) {
      if (i.getGameType() == id && i.getLevelNum() == level) {
        return i;
      }
    }
    return null;
  }

  public void setAnimationMap(String agent, Map<String, Animation2D> agentAnimation) {
    insertElementToMap(animationMap, agent, agentAnimation);
  }

  public Map<String, Animation2D> getSpecificAgentAnimation(String agent) {
    return animationMap.get(agent);
  }

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
