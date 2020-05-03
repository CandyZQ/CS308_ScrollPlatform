/**
 * this code is a helper class that is the universal handler for most of the data object processing.
 * It's very interesting because I used reflection, method creation, generic, and customed exceptions appropriately to resolve large amount of if statements and code repetition.
 */
package ooga.data;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static ooga.data.DataLoader.JSON_POSTFIX;
import static ooga.data.GameObjectConfiguration.EXCEPTION_KEYWORD;
import static ooga.data.GameObjectConfiguration.FILE_PATH_DELIMITER;

/**
 * this is a helper class used to handle any object that needs to be handled in the gameObjectConfiguration class
 */
public class DataObjectHandler {
    private Gson gson;
    private GameObjectConfiguration dataHolder;

    /**
     * create a gson handler
     * @param gson the gson
     * @param dataHolder the class holding the data
     */
    public DataObjectHandler(Gson gson, GameObjectConfiguration dataHolder) {
        this.gson = gson;
        this.dataHolder = dataHolder;
    }

    /**
     * @param <T> the type of the data in the data map.
     * @param myDirectoryPath the directory path the Json file is under
     * @param fileName the file name of the Json data file
     * @param field the field that the data files stores data for
     * @param clazz the class of the object holding the data
     */
    public  <T> void loadFilesUnderDirectoryForList(String myDirectoryPath, String fileName, Field field, Class clazz) throws IllegalAccessException {
        List<T> tempList = getListField(field);
        tempList.add(loadJson(myDirectoryPath + fileName, clazz));
        field.set(dataHolder, tempList);
    }

    /**
     *
     * @param <T> the type of the data in the data map.
     * @param myDirectoryPath the directory path the Json file is under
     * @param fileName the file name of the Json data file
     * @param field the field that the data files stores data for
     * @param clazz the class of the object holding the data
     */
    public <T> void loadFilesUnderDirectoryForMap(String myDirectoryPath, String fileName, Field field, Class clazz) throws IllegalAccessException {
        Map<String, T> tempMap = getMapField(field);
        tempMap.put(fileName,
                loadJson(myDirectoryPath + fileName, clazz));
        field.set(dataHolder, tempMap);
    }

    /**
     * @param field the field the list belongs to.
     * @param directoryPath the path the field stores to as Json
     * @param <T> the type of the value in the Map
     */
    public  <T> void storeMapToDisk(Field field, String directoryPath) throws IllegalAccessException {
        Map<String, T> tempMap = getMapField(field);
        for (String j : tempMap.keySet()) {
            writeObjectTOJson(tempMap.get(j), directoryPath + j);
        }
    }

    /**
     *
     * @param field the field the list belongs to.
     * @param directoryPath the path the field stores to as Json
     * @param getfieldIDMethod the method that gives the field's ID
     * @param <E> the element type in the list
     */
    public  <E> void storeListToDisk(Field field, String directoryPath, String getfieldIDMethod) throws IllegalAccessException {
        List<E> tempList = getListField(field);
        String[] pathArray = directoryPath.split(FILE_PATH_DELIMITER);
        String folderName = pathArray[pathArray.length - 1];

        for (E j : tempList) {
            Method methodcall = null;
            try {
                methodcall = j.getClass().getDeclaredMethod(getfieldIDMethod);
                writeObjectTOJson(j, directoryPath + folderName + methodcall.invoke(j) + JSON_POSTFIX);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                throw new DataLoadingException(e.getMessage(), e);
            }
        }
    }
    /**
     * generic methods loading data from Json
     * @param fileName the data file we load from
     * @param clazz the class of the data
     * @param <clazz> the class of the data
     * @return the data in its appropriated class
     */
    public <clazz> clazz loadJson(String fileName, Type clazz) {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(fileName));
            return (clazz) gson.fromJson(reader, clazz);
        } catch (IOException e) {
            throw new DataLoadingException(EXCEPTION_KEYWORD, e);
        }
    }

    /**
     * write any random object into a Json file under a specific path
     * @param object any random object
     * @param filePath path where the Json file should locate
     */
    private void writeObjectTOJson(Object object, String filePath) {
        try {
            FileWriter Writer1 = new FileWriter(filePath);
            gson.toJson(object, Writer1);
            Writer1.flush();
            Writer1.close();
        } catch (IOException e) {
            throw new DataLoadingException(e.getMessage(), e);
        }
    }

    /**
     * insert an element into the map
     * @param map the map we insert to
     * @param newkey the new key of the inserted map element
     * @param newValue the new value of the inserted map element
     * @param <K> the type of the key of the inserted map element
     * @param <V> the type of the value of the inserted map element
     * @return the map after insertion is completed
     */
    public <K, V> Map<K, V> insertElementToMap(Map<K, V> map, K newkey, V newValue) {
        if (map.containsKey(newkey)) {
            map.replace(newkey, newValue);
        } else {
            map.put(newkey, newValue);
        }
        return map;
    }
    // get the field that is in a Map
    private <T> Map<String, T> getMapField(Field field) throws IllegalAccessException {
        return (Map<String, T>) field.get(dataHolder);
    }
    //get the field that is in a list
    private <E> List<E> getListField(Field field) throws IllegalAccessException {
        return (List<E>) field.get(dataHolder);
    }
}
