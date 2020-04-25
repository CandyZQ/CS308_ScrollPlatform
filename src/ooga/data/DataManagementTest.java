package ooga.data;

import ooga.model.characters.ZeldaCharacter;
import ooga.model.enums.ImageCategory;
import ooga.model.enums.backend.CharacterType;
import ooga.model.enums.backend.Direction;
import ooga.model.enums.backend.PlayerParam;
import ooga.model.interfaces.gameMap.Cell;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Testing for DataManagement.
 * @author Guangyu Feng
 */

public class  DataManagementTest {
    private static DataLoader loader;

    static {
            loader = new DataLoader();
    }


    private static DataStorer storer;

    static {
        storer = new DataStorer();
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
        assertEquals(82, testCell.getImage());
        System.out.println(testCell.getState());

    }
    /**
     * the following is testing character loading and storing
     *
     */
    @Test
    public void characterLoadingStoringTest() throws DataLoadingException {

        ZeldaCharacter ZC = new ZeldaCharacter(9, 2, 3, 4,0,0, CharacterType.PLAYER);
        ZeldaCharacter ZC2 = new ZeldaCharacter(93, 21, 33, 44,0,0, CharacterType.PLAYER);
        ZC.setFiringDirection(Direction.E);
        storer.storeCharacter(4, ZC);
        storer.storeCharacter(3, ZC2);
//        assertEquals(9, loader.loadCharacter(4, CharacterProperty.HP));
//        assertEquals(3, loader.loadCharacter(4, CharacterProperty.ATTACK));
//        assertEquals(2, loader.getZeldaCharacters().size());
//        storer.writeAllDataIntoDisk();
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
//        storer.writeAllDataIntoDisk();
    }
    @Test
    public void imageLoadingStoringTest() throws DataLoadingException {
        storer.storeImage("321", 2, ImageCategory.RESOURCE);
        storer.storeImage("123", 2, ImageCategory.RESOURCE);
        String imagePath = loader.loadImagePath(2, ImageCategory.RESOURCE);
        assertEquals("123", imagePath);
//        storer.writeAllDataIntoDisk();
    }

    /**
     * todo: interger 99 != String 99
     */
    @Test
    public void loadAndStoreParam() throws DataLoadingException {
        int a = loader.loadPlayerParam(PlayerParam.CURRENT_SCORE, 3);
        storer.addPlayer(3);
        storer.setPlayerParam(PlayerParam.CURRENT_SCORE, 99, 3);

        assertEquals(99, loader.loadPlayerParam(PlayerParam.CURRENT_SCORE, 3));
//        storer.writeAllDataIntoDisk();
    }



}
