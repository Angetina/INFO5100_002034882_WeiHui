package exercise3b;

import java.io.*;

public class SerializationUtil {
    public static void serialize(Object obj, String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))){
            oos.writeObject(obj);
            System.out.println("Serialized " + " to " + fileName);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static Object deserialize(String fileName) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))){
            System.out.println("Deserialized " + " from " + fileName);
            return ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}
