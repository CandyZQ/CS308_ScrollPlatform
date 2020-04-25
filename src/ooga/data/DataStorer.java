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


public class DataStorer implements DataStorerAPI {
    private DataLoader dataLoader;
    private GameObjectConfiguration gameObjectConfiguration;

    /**
     * generate a storer
     */
    public DataStorer() {

        dataLoader = new DataLoader();
        gameObjectConfiguration = dataLoader.getGameObjectConfiguration();

    }

    /**
     * store the text under certain category by using its reference keyword
     * @param text
     * @param keyword
     * @param category
     */
    @Override
    public void StoreText(String text, String keyword, TextCategory category) {
        gameObjectConfiguration.setTextMap(text, keyword, category);
    }

    /**
     * store Weapons with its ID as reference
     * @param ID
     * @param weapon
     */
    @Override
    public void storeWeapons(int ID, WeaponBase weapon) {
        throw new DataLoadingException("store weapons is not implemented");
    }

    /**
     * store its character using specified ID
     * @param characterID
     * @param character
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
     * @param param
     * @param value
     * @param playerID
     */
    @Override
    public void setPlayerParam(PlayerParam param, int value, int playerID) {
        PlayerStatus tempPlayer = gameObjectConfiguration.getPlayerWithID(playerID);
        if (tempPlayer == null) {
            throw new DataLoadingException("Player Not Found");
        }
        tempPlayer.setPlayerParam(param, value);
    }

    /**
     * Add a player with the new ID
     * @param playerID
     */
    @Override
    public void addPlayer(int playerID) {
        gameObjectConfiguration.setPlayerWithID(playerID, new PlayerStatus(playerID));
    }

    /**
     * store user's key's setting
     * @param keyCodeMap
     * @param playerID
     */
    @Override
    public void storeKeyCode(Map<Integer, String> keyCodeMap, int playerID) {
        PlayerStatus tempPlayer = gameObjectConfiguration.getPlayerWithID(playerID);
        if (tempPlayer != null) {
            tempPlayer.setKeyCodeMap(keyCodeMap);
            gameObjectConfiguration.setPlayerWithID(playerID, tempPlayer);
        } else {
            throw new DataLoadingException("Player not Found");
        }
    }


    /**
     * Store images that belong to a specific category.
     * @param imagePath
     * @param ImageID
     * @param
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
     * @param map
     * @param level
     */
    @Override
    public void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level) {
        throw new DataLoadingException("Method storeSubMapWithSubmapIDRandom is not supported");
    }

    /**
     * store the submap for current game and level
     * @param map
     * @param level
     * @param subMapID
     */
    @Override
    public void storeSubMapForCurrentGame(Collection<Cell> map, int level, int subMapID) {
        storeSubMap( map, level, subMapID, gameObjectConfiguration.getCurrentGameID());
    }

    /**
     * store the submap
     * @param map
     * @param level
     * @param subMapID
     * @param gameID
     */
    @Override
    public void storeSubMap(Collection<Cell> map, int level, int subMapID, int gameID) {
        if (map.size() != GameMapGraph.SUBMAP_ROW_NUM * GameMapGraph.SUBMAP_COL_NUM) {
            throw new DataLoadingException("map stored didn't fit in dimension");
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
     * @param animations
     * @param animationType
     */
    @Override
    public void storeAnimations(Map<String, Animation2D> animations, AnimationType animationType) {
        gameObjectConfiguration.setAnimationMap(animationType.toString() + JSON_POSTFIX, animations);
    }

    /**
     * return the dataloader storer is using
     * @return
     */
    public DataLoader getDataLoader(){
        return dataLoader;
    }
}
