package ooga.data;

import javafx.scene.image.Image;
import ooga.model.gameElements.Weapon;
import ooga.model.interfaces.Inventory;

import java.util.Map;

public interface DataStorerAPI {

    /**
     * store text files to the database.
     * @return
     */
    void StoreText(String text, String keyword, String category);

    void StoreCharacter(int ID, UnchangableCharacter character);

    void storeWeapons(int ID, Weapon weapon);

    void StoreInventory(Inventory inventory);

    void storeImage(Image image, String keyword, String category);

    void storeInteger(String keyword, String category, int value);

    void updateParamSetting(Map<String, Integer> playerPreference, int category);


}
