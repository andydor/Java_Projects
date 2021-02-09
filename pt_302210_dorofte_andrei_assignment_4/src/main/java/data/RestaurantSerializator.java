package data;

import bll.Restaurant;

import java.io.*;

public class RestaurantSerializator {

    public void serialize(Restaurant[] restaurant, String filename) {
        //String filename = "restaurant.ser";

        // Serialization
        try {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(restaurant[0]);

            out.close();
            file.close();

            System.out.println("Restaurant saved");

        }

        catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    public void deserialize(Restaurant[] restaurant, String filename) {
        //String filename = "restaurant.ser";
        // Deserialization
        try {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            restaurant[0] = (Restaurant)in.readObject();

            in.close();
            file.close();

            System.out.println("Restaurant loaded");
        }

        catch(IOException ex) {
            ex.printStackTrace();
        }

        catch(ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
