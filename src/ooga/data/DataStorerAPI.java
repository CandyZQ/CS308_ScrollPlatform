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
     * @param text text content
     * @param keyword the keyword mapping the text
     * @param category the category of the text
     */
    void StoreText(String text, String keyword, TextCategory category);
    /**
     * store Weapons with its ID as reference
     * @param ID weapon ID
     * @param weapon weapon object
     */
    @Deprecated
    void storeWeapons(int ID, WeaponBase weapon);

    /**
     * store its character using specified ID
     * @param characterID character ID
     * @param character character objects
     */
    void storeCharacter(int characterID, ZeldaCharacter character);
    /**
     * set the param of playerstatus
     * @param param the enum identifying the param
     * @param value the value of the param
     * @param playerID the ID of the player
     */
    void setPlayerParam(PlayerParam param, int value, int playerID);
    /**
     * Add a player with the new ID
     * @param playerID player ID
     */
    void addPlayer(int playerID);
    /**
     * store user's key's setting
     * @param keyCodeMap Map storing keycode
     * @param playerID ID of player
     */
    void storeKeyCode(Map<Integer, String> keyCodeMap, int playerID);
    /**
     * Store images that belong to a specific category.
     * @param imagePath the path of the image
     * @param ImageID the ID of the image
     * @param imageCategory the category of image
     */
    void storeImage(String imagePath, int ImageID, ImageCategory imageCategory);
    /**
     * level = current level; subMapID = next available ID;
     * store submap with assigning a random ID
     * @param map collection of cells
     * @param level level of the games
     */
    @Deprecated
    void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level);
    /**
     * store the submap for current game and level
     * @param map collection of cells
     * @param level Game level of the submap
     * @param subMapID ID of the submap
     */
    void storeSubMapForCurrentGame(Collection<Cell> map, int level, int subMapID);
    /**
     * store the submap
     * @param map collection of cells
     * @param level Game level of the submap
     * @param subMapID ID of the submap
     * @param gameID ID of the game
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
     * @param animations animation object
     * @param animationType animation type
     */
    void storeAnimations(Map<String, Animation2D> animations, AnimationType animationType);
    /**
     * return the dataloader storer is using
     * @return dataloader
     */
    DataLoaderAPI getDataLoader();

}
