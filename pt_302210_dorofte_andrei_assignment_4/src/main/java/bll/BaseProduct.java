package bll;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseProduct extends MenuItem implements Serializable {
    private static final long serialVersionUID = 6529685098267757693L;

    private String name;
    private double price;

    public BaseProduct(String name, double price) {
        super(name, price);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }

    public void setPrice(double price) {
        super.setPrice(price);
    }

    public double getPrice() {
        return super.getPrice();
    }

    @Override
    public double computePrice() {
        return getPrice();
    }

    @Override
    public ArrayList<MenuItem> getItems() {
        return null;
    }

    @Override
    public boolean isHere(String name) {
        return false;
    }

    @Override
    public void addItem(MenuItem m) {

    }

    @Override
    public void removeItem(MenuItem m) {

    }
}
