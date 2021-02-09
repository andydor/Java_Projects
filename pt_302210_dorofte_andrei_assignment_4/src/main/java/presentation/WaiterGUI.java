package presentation;

import bll.Restaurant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

public class WaiterGUI extends JPanel {
    private JLabel jcomp1;
    private JTextField id;
    private JLabel jcomp3;
    private JLabel jcomp4;
    private JTextField table;
    private JLabel jcomp6;
    private JTextField itemname;
    private JButton add;
    private JButton addorder;
    private JLabel jcomp10;
    private JTextField table1;
    private JLabel jcomp12;
    private JButton price;
    private JLabel price1;
    private JLabel jcomp14;
    private JLabel jcomp15;
    private JTextField table2;
    private JButton bill;
    private JButton back;
    private JButton BMenuItems;
    private JLabel items;

    public WaiterGUI(Restaurant restaurant, JFrame frame) {
        //construct components
        jcomp1 = new JLabel ("Create Order");
        id = new JTextField (5);
        jcomp3 = new JLabel ("ID");
        jcomp4 = new JLabel ("Table");
        table = new JTextField (5);
        jcomp6 = new JLabel ("Add items");
        itemname = new JTextField (5);
        add = new JButton ("Add");
        addorder = new JButton ("Add Order");
        jcomp10 = new JLabel ("Compute Price");
        table1 = new JTextField (5);
        jcomp12 = new JLabel ("Table");
        price = new JButton ("Compute");
        price1 = new JLabel ();
        jcomp14 = new JLabel ("Generate Bill");
        jcomp15 = new JLabel ("Table");
        table2 = new JTextField (5);
        bill = new JButton ("Generate");
        back = new JButton ("Back");
        BMenuItems = new JButton ("View Orders");
        items = new JLabel ();

        //adjust size and set layout
        setPreferredSize (new Dimension (800, 351));
        setLayout (null);

        //add components
        add (jcomp1);
        add (id);
        add (jcomp3);
        add (jcomp4);
        add (table);
        add (jcomp6);
        add (itemname);
        add (add);
        add (addorder);
        add (jcomp10);
        add (table1);
        add (jcomp12);
        add (price);
        add (price1);
        add (jcomp14);
        add (jcomp15);
        add (table2);
        add (bill);
        add (back);
        add(BMenuItems);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (35, 35, 100, 25);
        id.setBounds (45, 65, 45, 25);
        jcomp3.setBounds (25, 65, 20, 25);
        jcomp4.setBounds (20, 105, 40, 25);
        table.setBounds (60, 105, 50, 25);
        jcomp6.setBounds (40, 140, 100, 25);
        itemname.setBounds (20, 170, 100, 25);
        add.setBounds (30, 205, 80, 25);
        addorder.setBounds (20, 255, 100, 25);
        jcomp10.setBounds (190, 35, 100, 25);
        table1.setBounds (230, 70, 45, 25);
        jcomp12.setBounds (190, 70, 40, 25);
        price.setBounds (190, 105, 85, 30);
        price1.setBounds (190, 145, 50, 25);
        jcomp14.setBounds (325, 35, 85, 25);
        jcomp15.setBounds (325, 70, 35, 25);
        table2.setBounds (365, 70, 40, 25);
        bill.setBounds (320, 105, 90, 30);
        back.setBounds (330, 310, 100, 25);
        BMenuItems.setBounds (320, 140, 100, 25);
        items.setBounds (420, 20, 300, 25);

        add.addActionListener(new ActionListener() { //edit name base
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurant.addItemsOrder(itemname.getText(), restaurant.returnOrder(Integer.parseInt(table.getText())));
                System.out.println("MenuItem added to order");
            }
        });

        addorder.addActionListener(new ActionListener() { //edit name base
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurant.createOrder(Integer.parseInt(id.getText()), Integer.parseInt(table.getText()));
                System.out.println("Order created");
            }
        });

        price.addActionListener(new ActionListener() { //edit name base
            @Override
            public void actionPerformed(ActionEvent e) {
                price1.setText(String.valueOf(restaurant.computePrice(restaurant.returnOrder(Integer.parseInt(table1.getText())))));
            }
        });

        bill.addActionListener(new ActionListener() { //edit name base
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    restaurant.generateBill(restaurant.returnOrder(Integer.parseInt(table2.getText())));
                    System.out.println("Bill generated. Check folder");
                } catch (IOException ioException) {
                    ioException.getMessage();
                }
            }
        });

        BMenuItems.addActionListener(new ActionListener() { //create
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = new JTable();
                restaurant.showOrders(table);
                frame.add(table);
                items.setText("ID      " + "Date      " + "Table      " + "Items");
                table.setBounds(420, 35, 400, 550);
            }
        });
    }

    public JButton getBack() {
        return this.back;
    }
}