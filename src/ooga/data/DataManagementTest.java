package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.CharacterProperty;
import ooga.model.enums.Direction;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.PlayerPara;
import ooga.model.interfaces.gameMap.Cell;
//import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.Test;

/**
 * Testing for DataManagement.
 * @author Guangyu Feng
 */

public class  DataManagementTest {
    private static DataLoader loader;

    static {
        try {
            loader = new DataLoader();
        } catch (DataLoadingException e) {
            e.printStackTrace();
        }
    }


    private static DataStorer storer;

    static {
        try {
            storer = new DataStorer();
        } catch (DataLoadingException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
//        gameMapLoadingTest();
//        characterLoadingStoringTest();
//        playerLoadingAndStoring();
//        KeyCodeTest();

    }

    /**
     * the following is testing the Game map loading and storing
     */
    @Test
    public void gameMapLoadingTest() throws DataLoadingException {
        ArrayList<Integer> a = new ArrayList<>();
        a.add(1);
        loader.setGameAndPlayer(1,a);
        ExampleDataGenerator.generateTheMapForFirstSprint();

        Cell testCell = loader.loadCell(6, 2, 0, 1);
        assertNotNull(testCell.getBufferedImage());
        assertTrue(testCell.isMapCellWalkable());
        assertEquals(testCell.getImage(), 82);
        System.out.println(testCell.getState());

    }
    /**
     * the following is testing character loading and storing
     *
     */
    @Test
    public void characterLoadingStoringTest() {

        ZeldaCharacter ZC = new ZeldaCharacter(9, 2, 3, 4,0,0);
        ZC.setFiringDirection(Direction.E);
        storer.storeCharacter(4, ZC);
        assertEquals(loader.loadCharacter(4, CharacterProperty.HP), 9);
        assertEquals(loader.loadCharacter(4, CharacterProperty.ATTACK), 3);
        loader.getGameObjectConfiguration().storeGameEverything();
    }

    @Test
    public void KeyCodeTest() throws DataLoadingException {
        Map<Integer, String> keyCodeMap = new HashMap<>();
        keyCodeMap.put(34, "hello");
        storer.addPlayer(3);
        storer.addPlayer(2);
        storer.storeKeyCode(keyCodeMap, 3);
        storer.storeKeyCode(keyCodeMap, 2);
        Map<Integer, String> keyCodeMap2 = loader.loadKeyCode(3);
        assertEquals("hello", loader.loadKeyCode(3).get(34));
        loader.getGameObjectConfiguration().storeGameEverything();
    }
    @Test
    public void imageLoadingStoringTest() {
        storer.storeImage("321", 2, ImageCategory.RESOURCE);
        storer.storeImage("123", 2, ImageCategory.RESOURCE);
        String imagePath = loader.loadImagePath(2, ImageCategory.RESOURCE);
        assertEquals("123", imagePath);
    }

    /**
     * todo: interger 99 != String 99
     */
    @Test
    public void loadAndStoreParam() throws DataLoadingException {
        int a = loader.loadPlayerPara(PlayerPara.CURRENT_SCORE, 3);
        storer.addPlayer(3);
        storer.setPlayerParam(PlayerPara.CURRENT_SCORE, 99, 3);

        assertEquals(99, loader.loadPlayerPara(PlayerPara.CURRENT_SCORE, 3));
        loader.getGameObjectConfiguration().storeGameEverything();
    }



}
