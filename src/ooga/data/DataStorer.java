package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.AnimationType;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.TextCategory;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.gameElements.WeaponBase;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;

import java.util.*;

import static ooga.data.DataLoader.JSON_POSTFIX;
import static ooga.data.PlayerStatus.initLevel;
import static ooga.data.PlayerStatus.initLife;

/**
 * storer class storing all data
 * @author Guangyu Feng
 */
public class DataStorer implements DataStorerAPI {
    private DataLoader dataLoader;
    private GameObjectConfiguration gameObjectConfiguration;
    private ResourceBundle errorResourceBundle;

    /**
     * generate a storer
     */
    public DataStorer() {

        dataLoader = new DataLoader();
        errorResourceBundle = dataLoader.getErrorMessageResources();
        gameObjectConfiguration = dataLoader.getGameObjectConfiguration();

    }

    /**
     * store text files to the database.
     * @param text text content
     * @param keyword the keyword mapping the text
     * @param category the category of the text
     */
    @Override
    public void StoreText(String text, String keyword, TextCategory category) {
        gameObjectConfiguration.setTextMap(text, keyword, category);
    }

    /**
     * store Weapons with its ID as reference
     * @param ID weapon ID
     * @param weapon weapon object
     */
    @Override
    public void storeWeapons(int ID, WeaponBase weapon) {
        throw new DataLoadingException(errorResourceBundle.getString("storeWeapons"));
    }

    /**
     * store its character using specified ID
     * @param characterID character ID
     * @param character character objects
     */
    @Override
    public void storeCharacter(int characterID, ZeldaCharacter character) {

        character.setId(characterID);
        List<ZeldaCharacter> tempCharList = new ArrayList<>();
        for (ZeldaCharacter i : gameObjectConfiguration.getZeldaCharacterList()) {
            if (i.getId() != characterID) {
                tempCharList.add(i);
            }
        }
        tempCharList.add(character);
        gameObjectConfiguration.setZeldaCharacterList(tempCharList);
    }

    /**
     * set the param of playerstatus
     * @param param the enum identifying the param
     * @param value the value of the param
     * @param playerID the ID of the player
     */
    @Override
    public void setPlayerParam(PlayerParam param, int value, int playerID) {
        PlayerStatus tempPlayer = gameObjectConfiguration.getPlayerWithID(playerID);
        if (tempPlayer == null) {
            throw new DataLoadingException(errorResourceBundle.getString("setPlayerParam"));
        }
        tempPlayer.setPlayerParam(param, value);
    }

    /**
     * Add a player with the new ID
     * @param playerID player ID
     */
    @Override
    public void addPlayer(int playerID) {
        gameObjectConfiguration.setPlayerWithID(playerID, new PlayerStatus(playerID));
    }

    /**
     * store user's key's setting
     * @param keyCodeMap Map storing keycode
     * @param playerID ID of player
     */
    @Override
    public void storeKeyCode(Map<Integer, String> keyCodeMap, int playerID) {
        PlayerStatus tempPlayer = gameObjectConfiguration.getPlayerWithID(playerID);
        if (tempPlayer != null) {
            tempPlayer.setKeyCodeMap(keyCodeMap);
            gameObjectConfiguration.setPlayerWithID(playerID, tempPlayer);
        } else {
            throw new DataLoadingException(errorResourceBundle.getString("setPlayerParam"));
        }
    }


    /**
     * Store images that belong to a specific category.
     * @param imagePath the path of the image
     * @param ImageID the ID of the image
     * @param imageCategory the category of image
     */
    @Override
    public void storeImage(String imagePath, int ImageID, ImageCategory imageCategory) {
        String imageIDString = String.valueOf(ImageID);
        Map<String, String> imageMap = gameObjectConfiguration.getImageMap().get(imageCategory.toString());

        if (imageMap != null) {
            imageMap = gameObjectConfiguration.insertElementToMap(imageMap, imageIDString + JSON_POSTFIX, imagePath);
        } else {
            imageMap = new HashMap<>();
            imageMap.put(imageIDString, imagePath);
        }
        gameObjectConfiguration.setImageMap(imageMap, imageCategory);
    }

    /**
     * level = current level; subMapID = next available ID;
     * store submap with assigning a random ID
     * @param map collection of cells
     * @param level level of the games
     */
    @Override
    public void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level) {
        throw new DataLoadingException(errorResourceBundle.getString("storeSubMapWithSubmapIDRandom"));
    }

    /**
     * store the submap for current game and level
     * @param map collection of cells
     * @param level Game level of the submap
     * @param subMapID ID of the submap
     */
    @Override
    public void storeSubMapForCurrentGame(Collection<Cell> map, int level, int subMapID) {
        storeSubMap( map, level, subMapID, gameObjectConfiguration.getCurrentGameID());
    }
    /**
     * store the submap
     * @param map collection of cells
     * @param level Game level of the submap
     * @param subMapID ID of the submap
     * @param gameID ID of the game
     */
    @Override
    public void storeSubMap(Collection<Cell> map, int level, int subMapID, int gameID) {
        if (map.size() != GameMapGraph.SUBMAP_ROW_NUM * GameMapGraph.SUBMAP_COL_NUM) {
            throw new DataLoadingException(errorResourceBundle.getString("storeSubMap"));
        }

        GameMapGraph mapGraph = new GameMapGraph(level, subMapID, GameMapGraph.SUBMAP_ROW_NUM, GameMapGraph.SUBMAP_COL_NUM, gameID);
        int i = 0;
        for (Cell cell: map) {
            mapGraph.setElement(i/ GameMapGraph.SUBMAP_COL_NUM, i% GameMapGraph.SUBMAP_ROW_NUM, cell);
            i++;
        }

        GameInfo currentGameInfo = gameObjectConfiguration.getGameInfo(level, gameObjectConfiguration.getCurrentGameID());
        String subMapFileName = currentGameInfo.getSubMapInfo().get(level).get(subMapID) + JSON_POSTFIX;
        Map<String, GameMapGraph> currentGameMapList =  gameObjectConfiguration.getGameMapList();
        currentGameMapList = gameObjectConfiguration.insertElementToMap(currentGameMapList, subMapFileName, mapGraph);
        gameObjectConfiguration.setGameMapList(currentGameMapList);
    }

    /**
     * method is called when the player restarts the game.
     * resets life, level, and score
     */
    @Override
    public void resetPlayerInfo() {
        int currentPlayerID = gameObjectConfiguration.getCurrentPlayer().getPlayerID();
        setPlayerParam(PlayerParam.CURRENT_LEVEL, initLevel, currentPlayerID);
        setPlayerParam(PlayerParam.LIFE, initLife, currentPlayerID);
        setPlayerParam(PlayerParam.CURRENT_SCORE, 0, currentPlayerID);
    }


    /**
     * call this method before program ends and all data will not be stored into disk without calling this method.
     */
    @Override
    public void writeAllDataIntoDisk() {
        gameObjectConfiguration.writeAllDataToDisk();
    }

    /**
     * store the animation to disk
     * @param animations animation object
     * @param animationType animation type
     */
    @Override
    public void storeAnimations(Map<String, Animation2D> animations, AnimationType animationType) {
        gameObjectConfiguration.setAnimationMap(animationType.toString() + JSON_POSTFIX, animations);
    }
    /**
     * return the dataloader storer is using
     * @return dataloader
     */
    public DataLoader getDataLoader(){
        return dataLoader;
    }
}
