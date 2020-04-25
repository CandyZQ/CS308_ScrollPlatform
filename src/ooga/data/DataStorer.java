package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.AnimationType;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.TextCategory;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.gameElements.WeaponBase;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;

import java.io.File;
import java.util.*;

import static ooga.data.DataLoader.JSON_POSTFIX;
import static ooga.data.PlayerStatus.initLevel;
import static ooga.data.PlayerStatus.initLife;

//import ooga.model.gameElements.Weapon;

public class DataStorer implements DataStorerAPI {
    private com.google.gson.Gson gson;
    private DataLoader dataLoader; //for just tentative measure.
    private GameObjectConfiguration gameObjectConfiguration;

    public DataStorer() throws DataLoadingException {
        com.google.gson.GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
        gsonBuilder.serializeNulls(); //ensure gson storing null values.
        gson = gsonBuilder.create();
        dataLoader = new DataLoader();
        gameObjectConfiguration = dataLoader.getGameObjectConfiguration();

    }

    //todo: test not done
    @Override
    public void StoreText(String text, String keyword, TextCategory category) {
        gameObjectConfiguration.setTextMap(text, keyword, category);
    }

//    @Override
//    public void storeCharacter(int ID, UnchangableCharacter character) {
//
//    }

    @Override
    public void storeWeapons(int ID, WeaponBase weapon) {
        throw new DataLoadingException("store weapons is not implemented");
    }


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
//        writeObjectTOJson(character, "data/ZeldaCharacter/" + characterKeyword + characterID + ".json");
    }


    @Override
    public void setPlayerParam(PlayerParam param, int value, int playerID) {
        PlayerStatus tempPlayer = gameObjectConfiguration.getPlayerWithID(playerID);
        if (tempPlayer == null) {
            System.out.println("Player not created(storer 114)");
            //todo: throw errors.
        }
        tempPlayer.setPlayerParam(param, value);
    }
    @Override
    public void addPlayer(int playerID) {
        gameObjectConfiguration.setPlayerWithID(playerID, new PlayerStatus(playerID));
    }
    @Override
    public void storeKeyCode(Map<Integer, String> keyCodeMap, int playerID) {
        PlayerStatus tempPlayer = gameObjectConfiguration.getPlayerWithID(playerID);
        if (tempPlayer != null) {
            tempPlayer.setKeyCodeMap(keyCodeMap);
            gameObjectConfiguration.setPlayerWithID(playerID, tempPlayer);
        } else {
            System.out.println("player not found in Storer 144");
            //todo: throw playerNotFound error
        }
    }

    private boolean fileExist(String filePath) {
        File tmpDir = new File(filePath);
        return tmpDir.exists();
    }

    /**
     * Slow if serialize every time?
     * @param imagePath
     * @param ImageID
     * @param
     */
    @Override
    //todo: finish testing
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
     * @param map
     * @param level
     */
    @Override
    public void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level) {
        throw new DataLoadingException("map stored didn't fit in dimension");
    }
    @Override
    public void storeSubMapForCurrentGame(Collection<Cell> map, int level, int subMapID) {
        storeSubMap( map, level, subMapID, gameObjectConfiguration.getCurrentGameID());
    }
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
        /**
         * How storer knows the name of the game map file being stored is challenging.
         * Storer and loader are therefore not independent.
         *
         */
        GameInfo currentGameInfo = gameObjectConfiguration.getGameInfo(level, gameObjectConfiguration.getCurrentGameID());
        String subMapFileName = currentGameInfo.getSubMapInfo().get(level).get(subMapID) + ".json";
        Map<String, GameMapGraph> currentGameMapList =  gameObjectConfiguration.getGameMapList();
        if (currentGameMapList.keySet().contains(subMapFileName)) {
            currentGameMapList.replace(subMapFileName, mapGraph);
        } else  {
            currentGameMapList.put(subMapFileName, mapGraph);
        }
        gameObjectConfiguration.setGameMapList(currentGameMapList);
//         writeObjectTOJson(mapGraph, gameMapAddressPrefix + subMapFileName);

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

    @Override
    public void storeAnimations(Map<String, Animation2D> animations, AnimationType animationType) {
        gameObjectConfiguration.setAnimationMap(animationType.toString() + JSON_POSTFIX, animations);
    }

    public DataLoader getDataLoader(){
        return dataLoader;
    }


}
