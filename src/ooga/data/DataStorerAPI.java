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

    @Deprecated
    void storeWeapons(int ID, WeaponBase weapon);

    void storeCharacter(int characterID, ZeldaCharacter character);

    void setPlayerParam(PlayerParam para, int value, int playerID);

    void addPlayer(int playerID);

    void storeKeyCode(Map<Integer, String> keyCodeMap, int playerID);

    void storeImage(String image, int ImageID, ImageCategory imageCategory);
    @Deprecated
    void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level) throws DataLoadingException;

    void storeSubMapForCurrentGame(Collection<Cell> map, int level, int subMapID) throws DataLoadingException;

    void storeSubMap(Collection<Cell> map, int level, int subMapID, int gameID) throws DataLoadingException;

    void resetPlayerInfo();

    void writeAllDataIntoDisk();

    void storeAnimations(Map<String, Animation2D> animations, AnimationType animationType);

    DataLoaderAPI getDataLoader();

}
