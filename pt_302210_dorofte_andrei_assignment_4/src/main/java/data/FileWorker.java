package data;

import bll.MenuItem;
import bll.Order;
import bll.Restaurant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public interface FileWorker {
    /**
     * Metoda pentru scriere comanda in fisier text
     * @param order
     * @param items
     * @param restaurant
     * @throws IOException
     */
    public static void writeFile(Order order, ArrayList<MenuItem> items, Restaurant restaurant) throws IOException {
        File filename = new File("table " + order.getTable() + " bill.txt");
        if (!filename.exists()) {
            filename.createNewFile();
        }

        FileWriter fw = new FileWriter(filename);
        BufferedWriter bw = new BufferedWriter(fw);

        int i = 0;

        bw.append("Order ID: " + order.getOrderId());
        bw.newLine();
        bw.append("Order Date: " + order.getDate().toString());
        bw.newLine();
        bw.append("Table: " + order.getTable());
        bw.newLine();
        bw.newLine();

        for(MenuItem m : items) {
            bw.append("Item " + ++i + ": " + m.getName() + " Price: " + String.valueOf(m.getPrice()));
            bw.newLine();
        }
        bw.append("Total: " + String.valueOf(restaurant.computePrice(order)));

        bw.close();
    }
}