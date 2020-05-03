package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.*;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.GameParam;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.interfaces.gameMap.Cell;
import ooga.view.engine.graphics.animation.Animation2D;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

/**
 * The interface for game loader
 */
public interface DataLoaderAPI {
    /**
     * load the param of current player
     * @param playerParam
     * @return
     * @throws DataLoadingException
     */
    int loadCurrentPlayerPara(PlayerParam playerParam) throws DataLoadingException;

    /**
     * Load the param of any specific player
     * @param playerParam the parameter of player
     * @param playerID id of player
     * @return the parameter of player
     */
    int loadPlayerParam(PlayerParam playerParam, int playerID) throws DataLoadingException;

    /**
     * load game's parameter using Gamepara enum
     * @param param the parameter of the game
     * @return the value of the parameter of the game
     */
    int loadGameParam(GameParam param);

    /**
     * load available directions that is available in current game
     * @return the list of Directions that are available
     */
    List<Direction> loadAvailableDirection();

    /**
     * ***this method has to be called before using any of the loader/storer.
     *
     * @param GameID the ID representing the type of the current Game
     * @param PlayersID the ID representing player
     */
    void setGameAndPlayer(int GameID, List<Integer> PlayersID);

    /**
     * get the type of the game people currently are working on
     * @return int indicating the type of the game
     */
    int getGameType();

    /**
     * load a specific cell
     * @param row the row the cell is at
     * @param col the column the cell is at
     * @param subMapID the ID indicating which submap the cell locates
     * @param level the level the cell is used in
     * @return the Cell at the specified locatioin
     */
    Cell loadCell(int row, int col, int subMapID, int level);
    /**
     * get the subMapID for the map in certain direction
     * @param direction direction of the next submap relative to the current submap
     * @param current the current submap
     * @return the ID of the next submap at the specified direction
     */
    @Deprecated
    int getNextSubMapID(Direction direction, int current);
    /**
     * get the whole gameMapGraph object from data
     * @param level
     * @param subMapID
     * @return
     * @throws DataLoadingException
     */
    GameMapGraph loadMap(int level, int subMapID) throws DataLoadingException;
    /**
     * load buffered Image by providing the image category and ID
     * @param ImageID id of the image
     * @param category the category of image
     * @return the image in the type of the bufferred image
     */
    BufferedImage loadBufferImage(int ImageID, ImageCategory category) throws DataLoadingException;

    /**
     * load text
     * @param keyword the keyword that maps the text in data file
     * @param category the type of the text
     * @return the string user requesting
     */
    String loadText(String keyword, String category) throws DataLoadingException;
    /**
     * load charcter's property using ID
     * @param ID character ID
     * @param property charcter's property type
     * @return charcter's property value
     */
    @Deprecated
    int loadCharacter(int ID, CharacterProperty property) throws DataLoadingException;

    /**
     * load weapon's property using ID
     * @param ID weapon ID
     * @param property weapon property
     * @return weapon attack value
    */
     @Deprecated
    int loadWeapon(int ID, int property) throws DataLoadingException;
    /**
     * load current level
     * @return current level
     */
    int currentLevel();
    /**
     * keycode are stored in the player files.
     *
     * @param playerID id of player
     * @return key code map
     */
    Map<Integer, String> loadKeyCode(int playerID) throws DataLoadingException;
    /**
     * in Json, <int, String> always returns <Stirng, String>
     *
     * @param imageID id of image
     * @param category category of image
     * @return the path of the string
     */
    String loadImagePath(int imageID, ImageCategory category) throws DataLoadingException;

    /**
     * get the list of current available zelda characters
     * @return list of current available zelda characters
     */
    List<ZeldaCharacter> getZeldaCharacters();
    /**
     * get the list of current available players
     * @return list of current available players
     */
    List<PlayerStatus> getCurrentPlayers();
    /**
     * get certain type of animation
     * @param animationType type of animation
     * @return <Animation Name, Animation Object> map
     */
    Map<String, Animation2D> loadAnimation(AnimationType animationType);

}
