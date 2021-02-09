package bll;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int orderId;
    private Date date;
    private int table;

    public Order(int id, Date date, int table) {
        this.orderId = id;
        this.date = date;
        this.table = table;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                table == order.table &&
                date.equals(order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, date, table);
    }

    public int getOrderId() {
        return orderId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}