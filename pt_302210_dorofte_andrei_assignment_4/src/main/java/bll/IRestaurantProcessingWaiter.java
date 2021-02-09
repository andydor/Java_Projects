package bll;

import java.io.IOException;

public interface IRestaurantProcessingWaiter {
    /**
     * Metoda pentru creare comanda
     * @param id
     * @param table
     */
    public void createOrder(int id, int table);

    /**
     * Metoda pentru calculare pret comanda
     * @param o
     * @return
     */
    public double computePrice(Order o);

    /**
     * Metoda pentru generare nota de plata in format txt
     * @param o
     * @throws IOException
     */
    public void generateBill(Order o) throws IOException;
}