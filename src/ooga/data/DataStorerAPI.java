package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.AnimationType;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.TextCategory;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.gameElements.WeaponBase;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;

import java.util.Collection;
import java.util.Map;

public interface  DataStorerAPI {

    /**
     * store text files to the database.
     * @return
     */

    void StoreText(String text, String keyword, TextCategory category);
    /**
     * store Weapons with its ID as reference
     * @param ID
     * @param weapon
     */
    @Deprecated
    void storeWeapons(int ID, WeaponBase weapon);

    /**
     * store its character using specified ID
     * @param characterID
     * @param character
     */
    void storeCharacter(int characterID, ZeldaCharacter character);
    /**
     * set the param of playerstatus
     * @param param
     * @param value
     * @param playerID
     */
    void setPlayerParam(PlayerParam param, int value, int playerID);
    /**
     * Add a player with the new ID
     * @param playerID
     */
    void addPlayer(int playerID);
    /**
     * store user's key's setting
     * @param keyCodeMap
     * @param playerID
     */
    void storeKeyCode(Map<Integer, String> keyCodeMap, int playerID);
    /**
     * Store images that belong to a specific category.
     * @param imagePath
     * @param ImageID
     * @param
     */
    void storeImage(String imagePath, int ImageID, ImageCategory imageCategory);
    /**
     * level = current level; subMapID = next available ID;
     * store submap with assigning a random ID
     * @param map
     * @param level
     */
    @Deprecated
    void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level) throws DataLoadingException;
    /**
     * store the submap for current game and level
     * @param map
     * @param level
     * @param subMapID
     */
    void storeSubMapForCurrentGame(Collection<Cell> map, int level, int subMapID) throws DataLoadingException;
    /**
     * store the submap
     * @param map
     * @param level
     * @param subMapID
     * @param gameID
     */
    void storeSubMap(Collection<Cell> map, int level, int subMapID, int gameID) throws DataLoadingException;
    /**
     * method is called when the player restarts the game.
     * resets life, level, and score
     */
    void resetPlayerInfo();
    /**
     * call this method before program ends and all data will not be stored into disk without calling this method.
     */
    void writeAllDataIntoDisk();
    /**
     * store the animation to disk
     * @param animations
     * @param animationType
     */
    void storeAnimations(Map<String, Animation2D> animations, AnimationType animationType);
    /**
     * return the dataloader storer is using
     * @return
     */
    DataLoaderAPI getDataLoader();

}
