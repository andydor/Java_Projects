package bll;

import data.FileWorker;
import presentation.Observer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.List;

/**
 * Clasa principala a proiectului. Aici se definesc toate metodele cu care restaurantul lucreaza
 */

public class Restaurant implements Serializable, Observable, IRestaurantProcessingAdmin, IRestaurantProcessingWaiter, FileWorker {
    private static final long serialVersionUID = 6529685098267757690L;

    private HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<Order, ArrayList<MenuItem>>();
    private ArrayList<Observer> observers = new ArrayList<Observer>();
    public ArrayList<MenuItem> menu = new ArrayList<MenuItem>();

    public Restaurant() {}

    /**
     * Verificare daca item-ul se afla deja in meniu
     * @param name
     * @return
     */
    public boolean isInMenu(String name) {
        for(MenuItem m : this.menu)
            if(m != null)
                if(name.equals(m.getName()))
                    return true;
        return false;
    }

    /**
     * Metoda pentru returnarea unui anumit element din meniu
     * @param name
     * @return
     */
    public MenuItem returnItem(String name) {
        for(MenuItem m : this.menu)
            if(m != null)
                if(name.equals(m.getName()))
                    return m;
        return null;
    }

    /**
     * Metoda pentru adaugare component in CommpositeProduct
     * @param name
     * @param name1
     */
    public void addComponent(String name, String name1) {
        MenuItem x = returnItem(name);
        if(x != null)
            for(MenuItem m : this.menu)
                if(name1.equals(m.getName()))
                    if(m.isHere(x.getName()) == false)
                        m.addItem(x);
    }

    /**
     * Metoda pentru scoatere component din CompositeProduct
     * @param name
     * @param name1
     */
    public void removeComponent(String name, String name1) {
        MenuItem x = returnItem(name);
        if(x != null)
            for(MenuItem m : this.menu)
                if(name1.equals(m.getName()))
                    if(m.isHere(x.getName()) == true) {
                        m.removeItem(x);
                        m.computePrice();
                    }
    }

    /**
     * Metoda pentru afisare tabel meniu
     * @param table
     */
    public void showTable(JTable table) {
        List<Object[]> list = new ArrayList<Object[]>();
        for(MenuItem m : this.menu) {
            if(m.getClass().getName().equals("bll.BaseProduct"))
                list.add(new Object[] {m.getName(), String.valueOf(m.getPrice()), "Base"});
            else if(m.getClass().getName().equals("bll.CompositeProduct"))
                list.add(new Object[] {m.getName(), String.valueOf(m.getPrice()), "Composite"});
        }
        table.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), new String[] {"Name", "Price", "Type"}));
    }

    /**
     * Metoda pentru afisare MenuItems din comanda
     * @param menu
     * @return
     */
    public String displayString(ArrayList<MenuItem> menu) {
        String items = "";
        for(MenuItem m : menu)
            items += m.getName() + ",";
        return items;
    }

    /**
     * Metoda pentru afisare tabel comenzi
     * @param table
     */
    public void showOrders(JTable table) {
        List<Object[]> list = new ArrayList<Object[]>();
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
            Order key = entry.getKey();
            ArrayList<MenuItem> value = entry.getValue();
            if (key != null)
                list.add(new Object[]{key.getOrderId(), key.getDate().toString(), key.getTable(), displayString(value), computePrice(key)});
        }
        table.setModel(new DefaultTableModel(list.toArray(new Object[][] {}), new String[] {"ID", "Date", "Table", "Items", "Price"}));
    }

    /**
     * Metoda pentru cautare comanda dupa numar masa
     * @param table
     * @return
     */
    public Order returnOrder(int table) {
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
            Order key = entry.getKey();
            ArrayList<MenuItem> value = entry.getValue();
            if(key.getTable() == table)
                return key;
        }
        return null;
    }

    public void registerObserver(Observer observer) {
        if(observer != null)
            this.observers.add(observer);
    }

    public void notifyObservers() {
        for(Observer observer : this.observers)
            observer.update(this);
    }
    public void removeObserver(Observer observer) {
        if(observer != null)
            this.observers.remove(observer);
    }

    /**
     * Metoda pentru creare MenuItem de tip Base
     * @param name
     * @param price
     */
    @Override
    public void createMenuItemBase(String name, double price) {
        menu.add(new BaseProduct(name, price));
    }

    /**
     * Metoda pentru creare MenuItem de tip Composite
     * @param name
     */
    @Override
    public void createMenuItemComposite(String name) {
        menu.add(new CompositeProduct(name));
    }

    /**
     * Metoda pentru stergere MenuItem din meniu
     * @param name
     */
    @Override
    public void deleteMenuItem(String name) {
        MenuItem x = null;
        for(MenuItem m : this.menu)
            if(m != null) {
                if (name.equals(m.getName()))
                    x = m;
                MenuItem y = null;
                if (m.getItems() != null)
                    for (MenuItem n : m.getItems())
                        if (n != null)
                            if (name.equals(n.getName())) {
                                y = n;
                                m.getItems().remove(y);
                                m.computePrice();
                            }
            }
        this.menu.remove(x);
    }

    /**
     * Metoda pentru editare nume sau pret MenuItem
     * @param nameold
     * @param name
     * @param price
     */
    @Override
    public void editMenuItem(String nameold, String name, double price) {
        if (price != 0 && name == null) {
            for (MenuItem m : this.menu) //edit
                if(m != null)
                    if (nameold.equals(m.getName()) && m.getClass().getName().equals("bll.BaseProduct")) {
                        m.setPrice(price);
                        for(MenuItem n : this.menu)
                            if(n.getItems() != null)
                                for(MenuItem p : n.getItems())
                                    if(m.equals(p))
                                        n.computePrice();
                    }
        }
        else if(price == 0 && name != null && nameold != null) {
            for (MenuItem m : this.menu) //edit
                if(m != null)
                    if (nameold.equals(m.getName()))
                        m.setName(name);
        }
    }

    /**
     * Metoda pentru creare comanda
     * @param id
     * @param table
     */
    @Override
    public void createOrder(int id, int table) {
        ArrayList<MenuItem> items = new ArrayList<MenuItem>();
        Order o = new Order(id, new Date(), table);
        orders.put(o, items);
    }

    /**
     * Metoda pentru adaugare MenuItem la comanda
     * @param name
     * @param o
     */
    public void addItemsOrder(String name, Order o) {
        if(returnItem(name) != null) {
            for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
                Order key = entry.getKey();
                ArrayList<MenuItem> value = entry.getValue();
                if(key.equals(o))
                    value.add(returnItem(name));
            }
        }
    }

    /**
     * Metoda pentru calculare pret comanda
     * @param o
     * @return
     */
    @Override
    public double computePrice(Order o) {
        double price = 0;
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
            Order key = entry.getKey();
            ArrayList<MenuItem> value = entry.getValue();
            if(key.equals(o))
                for(MenuItem m : value)
                    price += m.computePrice();
        }
        return price;
    }

    /**
     * Metoda pentru generare nota de plata in format text
     * @param o
     * @throws IOException
     */
    @Override
    public void generateBill(Order o) throws IOException {
        for (Map.Entry<Order, ArrayList<MenuItem>> entry : orders.entrySet()) {
            Order key = entry.getKey();
            ArrayList<MenuItem> value = entry.getValue();
            if(key.equals(o)) {
                FileWorker.writeFile(key, value, this);
                orders.remove(o);
            }
        }
    }
}