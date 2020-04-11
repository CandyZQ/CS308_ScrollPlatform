package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.Direction;
import ooga.model.interfaces.gameMap.Cell;

import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static ooga.data.DataStorer.characterKeyword;
import static ooga.data.DataStorer.mapKeyword;

public class DataLoader implements ooga.data.DataLoaderAPI {
  public static final String Image_Keyword = "image";
  public static final String Text_Keyword = "text";
  public static final String GAME_Keyword = "Game";
  private int currentLevel;
  private com.google.gson.Gson gson;
  public DataLoader() {
    com.google.gson.GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
    gsonBuilder.serializeNulls(); //ensure gson storing null values.
    gsonBuilder.registerTypeAdapter(Cell.class, new InterfaceAdapter());
    gson = gsonBuilder.create();//3 lines above are the same as DataStorer
  }
  public void setCurrentLevel(int currentLevel) {
    this.currentLevel = currentLevel;
  }

  public int getCurrentLevel() {
    return currentLevel;
  }

  @Override
  public void setGame(int GameID) {

  }

  @Override
  public int getGameType() {
    return 0;
  }

  @Override
  public Cell loadCell(int row, int col, int subMapID, int level) {
    return loadMap(level, subMapID).getElement(row, col);
  }

  @Override
  public int getNextSubMapID(Direction direction, int current) {
    return 0;
  }
  //todo: upgrade it from whole map to submap.
  private GameMapGraph loadMap(int level, int subMapID) {

    GameMapGraph map = new GameMapGraph();
    try {
      //two readings: 1. read level files 2. read subMap file.
      //Map<String, String> levelInfoMap = loadLevelInfo(GameName, level);
      //loadJson(GAME_Keyword + String.valueOf(level), );
      map = (GameMapGraph) loadJson(mapKeyword+ String.valueOf(level) + ".json", map.getClass());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return map;
  }
  @Override
  public String loadText(String keyword, String category) {
    Map<String, String> text = new HashMap<>();
    text = (Map<String, String>) loadJson(Text_Keyword + category, text.getClass());
    return text.get(keyword);
  }

  @Override
  public int loadCharacter(int ID, CharacterProperty property) {
    ZeldaCharacter zeldaCharacter = new ZeldaCharacter(1,2);

    zeldaCharacter = (ZeldaCharacter) loadJson(characterKeyword + ".json", zeldaCharacter.getClass());
    try {
      Method methodcall = zeldaCharacter.getClass().getDeclaredMethod("get" + property.toString().substring(0,1)+ property.toString().substring(1).toLowerCase());
      int a = (int) methodcall.invoke(zeldaCharacter);
      return (int) methodcall.invoke(zeldaCharacter);
    } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
      e.printStackTrace();
    }


    return 0;
  }

  @Override
  public int loadWeapon(int ID, int property) {
    return 0;
  }

  @Override
  public int currentLevel() {
    return 0;
  }

  @Override
  public Object loadInventoryElement(int ID) {
    return null;
  }

  @Override
  public Map<String, Integer> loadInternalStorage(String category) {
    return null;
  }

  @Override
  public Map<KeyCode, String> loadKeyCode(int playerID, String category) {
    return null;
  }

  @Override
  public Path loadImagePath(int imageID, String category) {
    Map<Integer, Path> imagePath = new HashMap<>();
    imagePath = (Map<Integer, Path>) loadJson(Image_Keyword + category, imagePath.getClass());
    return imagePath.get(imageID);
  }

  //seems I can't do generic for this guy and have to use the Object class?
  private Object loadJson(String fileName, Class clazz) {
    try {
      Reader reader = Files.newBufferedReader(Paths.get(fileName));
      return gson.fromJson(reader, clazz);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  //todo: finish the method
  public String loadSetting(int property) {
    return "";
  }
  @Override
  public Integer loadInteger(String keyword, String category) {
    return null;
  }

  @Override
  public int loadGameParam(String keyword) {
    return 0;
  }


}
