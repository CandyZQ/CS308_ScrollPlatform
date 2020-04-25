package ooga.data;


import com.google.gson.*;
import ooga.model.map.GameCell;

import java.lang.reflect.Type;

/**
 * Modified From https://technology.finra.org/code/serialize-deserialize-interfaces-in-java.html
 * @author Guangyu Feng
 */


public class InterfaceAdapter implements  JsonDeserializer{

    private String className;

    /**
     * create an interfaceAdapter
     * @param className the class of the object to be returned
     */
    public InterfaceAdapter(String className) {
        this.className = className;
    }

    /**
     * Specify how to deserialize the corresponding objects. Override deserialize method of Gson.
     * @param jsonElement element being deserialized
     * @param type the type of object being deserialzied to
     * @param jsonDeserializationContext the context where the deserialization happens
     * @return the object being parsed
     */
    public Object deserialize(JsonElement jsonElement, Type type,
                         JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject jsonObject = jsonElement.getAsJsonObject();

        Class klass = getObjectClass(className);
        GameCell gameCell = jsonDeserializationContext.deserialize(jsonObject, klass);
        return gameCell;
    }

    /****** Helper method to get the className of the object to be deserialized *****/
    public Class getObjectClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new JsonParseException(e.getMessage());
        }
    }
}

//import com.google.gson.InstanceCreator;
//import ooga.model.interfaces.gameMap.Cell;
//
//import java.lang.reflect.Type;
//
//public class InterfaceAdapter implements InstanceCreator<Cell> {
//    private int ID;
//    InterfaceAdapter(int ID) {
//        this.ID = ID;
//    }
//    @Override
//    public Cell createInstance(Type type) {
//        Cell gameCell = new GameCell(ID);
//        return gameCell;
//    }
//}