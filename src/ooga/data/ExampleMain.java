 //package ooga.data;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.ObjectReader;
//import java.io.IOException;
//
//public class ExampleMain {
//    public static void main(String[] args) throws IOException {
//        String inputJson = "{\"name\":\"Jake\",\"salary\":3000,"
//                + "\"address\":{\"street\":\"101 Blue Dr\",\"city\":\"White Smoke\"}}";
//        System.out.println("input json: " + inputJson);
//
//        Employee existingEmployee = "{name='John', dept='Dev', salary=1000, phone='222-222-222', address=Address{street='101 Blue Dr', city='SunBridge', zipCode23456}}";
//        System.out.println("existing object: " + existingEmployee);
//        System.out.println("existing object hashCode: " + System.identityHashCode(existingEmployee));
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        ObjectReader objectReader = objectMapper.readerForUpdating(existingEmployee);
//        Employee updatedEmployee = objectReader.readValue(inputJson);
//        System.out.println("updated object: " + updatedEmployee);
//        System.out.println("updated object hashCode: " + System.identityHashCode(updatedEmployee));
//
//    }
//}
