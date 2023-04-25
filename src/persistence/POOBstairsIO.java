package persistence;

import java.io.*;

public class POOBstairsIO {

    public static void saveClass(String filename, Object object) {
        try {
            // Create a file output stream to write the serialized object to a file
            FileOutputStream fos = new FileOutputStream(filename);

            // Create an object output stream to write the serialized object to the file
            // output stream
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            // Write the serialized object to the file
            oos.writeObject(object);

            // Close the object output stream and file output stream
            oos.close();
            fos.close();

            System.out.println("Object saved to " + filename);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object loadClass(String filename) {
        Object object = null;
        try {
            // Create a file input stream to read the serialized object from a file
            FileInputStream fis = new FileInputStream(filename);

            // Create an object input stream to read the serialized object from the file
            // input stream
            ObjectInputStream ois = new ObjectInputStream(fis);

            // Read the serialized object from the file
            object = ois.readObject();

            // Close the object input stream and file input stream
            ois.close();
            fis.close();

            System.out.println("Object loaded from " + filename);

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }
}
