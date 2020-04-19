package ooga.data;

import javafx.scene.input.KeyCode;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.PlayerPara;
import ooga.model.enums.TextCategory;
import ooga.model.gameElements.WeaponBase;
import ooga.model.interfaces.Inventory;
import ooga.model.interfaces.gameMap.Cell;

import java.util.Collection;
import java.util.Map;

public interface  DataStorerAPI {

    /**
     * store text files to the database.
     * @return
     */

    void StoreText(String text, String keyword, TextCategory category);

//    void storeCharacter(int ID, UnchangableCharacter character);

    void storeWeapons(int ID, WeaponBase weapon);

    void StoreInventory(Inventory inventory);

    void storePlayerParamToCurrentPlayer(PlayerPara para, int value);

    void setPlayerParam(PlayerPara para, int value, int playerID);

    void addPlayer(int playerID);

    void storeKeyCode(Map<KeyCode, String> keyCodeMap, int playerID);


    void storeImage(String image, int ImageID, ImageCategory imageCategory);

    void storeSubMapWithSubmapIDRandom(Collection<Cell> map, int level);



    void storeSubMapForCurrentGame(Collection<Cell> map, int level, int subMapID);

    void storeSubMap(Collection<Cell> map, int level, int subMapID, int gameID);

}
