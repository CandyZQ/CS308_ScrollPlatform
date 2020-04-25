package ooga.data;

import com.google.gson.Gson;
import ooga.model.interfaces.gameMap.Cell;
import ooga.model.map.GameCell;
import ooga.view.engine.utils.cyberpunk2d.Text2DMapReader;

import java.util.ArrayList;
import java.util.List;

import static ooga.data.GameMapGraph.PIXEL_PALETTE_WIDTH;
import static ooga.model.enums.ImageCategory.MAP2D;

/**
 * this class will not be part of the final submission. It's purely used for generating data for the example file for the presentation only.
 */
public class ExampleDataGenerator {

    static Gson gson;
    public static void main(String[] args) throws DataLoadingException {
        DataLoader a = new DataLoader();
        DataStorer b = new DataStorer();
        com.google.gson.GsonBuilder gsonBuilder = new com.google.gson.GsonBuilder();
        gsonBuilder.serializeNulls(); //ensure gson storing null values.
        gson = gsonBuilder.create();
        //generateLevelData();
        generateTheMapForFirstSprint();
    }
    //    private static void generateLevelData() {
//        int levelNum = 1;
//        int gameType = 1;
//        List<Integer> npcID= new ArrayList<>();
//        npcID.add(1);
//        List<Integer> playerID = new ArrayList<>();
//        playerID.add(1);
//        Map<Integer, String> subMapID = new HashMap<>();
//        Map<Integer, Map<Integer, String>> a = new HashMap<>();
//        for (int i = 0; i  < SubMapPerMap; i++) {
//
//            subMapID.put(i, UUID.randomUUID().toString());//file name is a random unique ID.
//        }
//        a.put(levelNum, subMapID);
//        GameInfo gameInfo1 = new GameInfo(npcID, playerID, levelNum, a, gameType);
//        writeObjectTOJson(gameInfo1, "data/GameInfo/Game"+gameType+"level"+levelNum+".json");
//    }
//    private static void writeObjectTOJson(Object object, String filePath) {
//        try {
//            FileWriter Writer1 = new FileWriter(filePath);
//            String jsonString2 = gson.toJson(object);
//            gson.toJson(object, Writer1);
//            Writer1.flush();
//            Writer1.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            //throw appropriate Exceptions
//        }
//
//    }
    protected static void generateTheMapForFirstSprint() throws DataLoadingException {
        String mapPath = "/view/textures/2d/cyberpunk/map/map.txt";
        Text2DMapReader textMapReader = new Text2DMapReader(mapPath);
        int subMapHeight = textMapReader.getMapHeight();
        int subMapWidth = textMapReader.getMapWidth();
        List<Cell> cellList = new ArrayList<>();
        List<Integer> availableImageID = new ArrayList<>();
        for (int i = 0; i < subMapHeight; i++) {
            for (int j = 0; j < subMapWidth; j++) {
                Cell tempCell = new GameCell();
                int imageID = textMapReader.getMapCell(i, j);
                tempCell.setImage(imageID);
                tempCell.setWalkable(textMapReader.isMapCellWalkable(i, j));
                cellList.add(tempCell);

                //store the image of Cell in the 2DMap folder under Image folder.
                if (!availableImageID.contains(imageID)) {
                    availableImageID.add(imageID);
                    DataStorer storer = new DataStorer();
                    int palette_x = imageID % PIXEL_PALETTE_WIDTH;
                    int palette_y = imageID / PIXEL_PALETTE_WIDTH;
                    storer.storeImage(String.format("data/2DMAP_PNG/%s_%s.png", palette_x, palette_y), imageID, MAP2D);
                }


            }
        }

        //store data
        DataStorer b = new DataStorer();
        System.out.println(cellList.size());
        b.storeSubMap(cellList, 1, 0, 1);
        DataLoader c = new DataLoader();
        b.writeAllDataIntoDisk();
    }


}
