package bll;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class MenuItem implements Serializable {
    private static final long serialVersionUID = 6529685098267757691L;

    private String name;
    private double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    public abstract double computePrice();

    public abstract ArrayList<MenuItem> getItems();

    public abstract boolean isHere(String name);

    public abstract void addItem(MenuItem m);

    public abstract void removeItem(MenuItem m);
}