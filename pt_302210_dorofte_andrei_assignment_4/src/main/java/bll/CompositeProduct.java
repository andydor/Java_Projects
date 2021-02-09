package bll;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class CompositeProduct extends MenuItem implements Serializable {
    private static final long serialVersionUID = 6529685098267757692L;

    private String name;
    private double price;

    private ArrayList<MenuItem> items;

    public CompositeProduct(String name) {
        super(name, 0);
        this.items = new ArrayList<MenuItem> ();
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
        double t = 0;
        for(MenuItem m : items) {
            t += m.getPrice();
        }
        setPrice(t);
        return t;
    }

    @Override
    public ArrayList<MenuItem> getItems() {
        return this.items;
    }

    @Override
    public boolean isHere(String name) {
        for(MenuItem m : this.items)
            if(name.equals(m.getName()))
                return true;
        return false;
    }

    @Override
    public void addItem(MenuItem m) {
        items.add(m);
        setPrice(this.computePrice());
    }

    @Override
    public void removeItem(MenuItem m) {
        items.remove(m);
        this.price = this.computePrice();
    }
}