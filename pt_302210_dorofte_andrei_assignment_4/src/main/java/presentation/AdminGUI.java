package presentation;

import bll.BaseProduct;
import bll.CompositeProduct;
import bll.MenuItem;
import bll.Restaurant;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AdminGUI extends JPanel {
    private JTextField basename;
    private JTextField baseprice;
    private JTextField compositename;
    private JLabel jcomp4;
    private JLabel jcomp5;
    private JLabel jcomp6;
    private JLabel jcomp7;
    private JLabel jcomp8;
    private JLabel jcomp9;
    private JButton addcomposite;
    private JButton addbase;
    private JButton addcomponent;
    private JLabel jcomp13;
    private JTextField deletename;
    private JLabel jcomp15;
    private JButton deletemenuitem;
    private JLabel jcomp17;
    private JLabel jcomp18;
    private JTextField oldname;
    private JLabel jcomp20;
    private JTextField newname;
    private JButton editname;
    private JTextField addcomponentname;
    private JLabel jcomp24;
    private JTextField removecommponentname;
    private JButton removecomponent;
    private JButton jcomp27;
    private JButton BMenuItems;

    public AdminGUI(Restaurant restaurant, JFrame frame) {
        //construct components
        basename = new JTextField(5);
        baseprice = new JTextField(5);
        compositename = new JTextField(5);
        jcomp4 = new JLabel("Base add / Modify price");
        jcomp5 = new JLabel("Composite add");
        jcomp6 = new JLabel("Name");
        jcomp7 = new JLabel("Price");
        jcomp8 = new JLabel("Name");
        jcomp9 = new JLabel("Add component");
        addcomposite = new JButton("Add");
        addbase = new JButton("Add");
        addcomponent = new JButton("Add");
        jcomp13 = new JLabel("Delete menu item");
        deletename = new JTextField(5);
        jcomp15 = new JLabel("Name");
        deletemenuitem = new JButton("Delete");
        jcomp17 = new JLabel("Edit name");
        jcomp18 = new JLabel("Old name");
        oldname = new JTextField(5);
        jcomp20 = new JLabel("New name");
        newname = new JTextField(5);
        editname = new JButton("Edit");
        addcomponentname = new JTextField(5);
        jcomp24 = new JLabel("Remove component");
        removecommponentname = new JTextField(5);
        removecomponent = new JButton("Remove");
        jcomp27 = new JButton ("Back");
        BMenuItems = new JButton ("View Menu");

        //adjust size and set layout
        setPreferredSize(new Dimension(944, 574));
        setLayout(null);

        //add components
        add(basename);
        add(baseprice);
        add(compositename);
        add(jcomp4);
        add(jcomp5);
        add(jcomp6);
        add(jcomp7);
        add(jcomp8);
        add(jcomp9);
        add(addcomposite);
        add(addbase);
        add(addcomponent);
        add(jcomp13);
        add(deletename);
        add(jcomp15);
        add(deletemenuitem);
        add(jcomp17);
        add(jcomp18);
        add(oldname);
        add(jcomp20);
        add(newname);
        add(editname);
        add(addcomponentname);
        add(jcomp24);
        add(removecommponentname);
        add(removecomponent);
        add(jcomp27);
        add(BMenuItems);

        //set component bounds (only needed by Absolute Positioning)
        basename.setBounds(20, 85, 180, 25);
        baseprice.setBounds(20, 140, 115, 25);
        compositename.setBounds(230, 85, 180, 25);
        jcomp4.setBounds(40, 25, 140, 25);
        jcomp5.setBounds(280, 25, 150, 25);
        jcomp6.setBounds(20, 60, 100, 25);
        jcomp7.setBounds(20, 115, 100, 25);
        jcomp8.setBounds(230, 60, 100, 25);
        jcomp9.setBounds(225, 165, 100, 25);
        addcomposite.setBounds(275, 120, 100, 25);
        addbase.setBounds(60, 180, 100, 25);
        addcomponent.setBounds(345, 200, 75, 25);
        jcomp13.setBounds(455, 25, 100, 25);
        deletename.setBounds(455, 85, 100, 25);
        jcomp15.setBounds(455, 60, 100, 25);
        deletemenuitem.setBounds(455, 130, 100, 25);
        jcomp17.setBounds(20, 220, 125, 25);
        jcomp18.setBounds(20, 250, 100, 25);
        oldname.setBounds(20, 285, 100, 25);
        jcomp20.setBounds(20, 315, 100, 25);
        newname.setBounds(20, 345, 100, 25);
        editname.setBounds(20, 385, 100, 25);
        addcomponentname.setBounds(225, 200, 100, 25);
        jcomp24.setBounds(225, 240, 120, 25);
        removecommponentname.setBounds(225, 270, 100, 25);
        removecomponent.setBounds(345, 270, 100, 25);
        jcomp27.setBounds (790, 505, 130, 35);
        BMenuItems.setBounds (345, 300, 100, 25);

        addbase.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(restaurant.isInMenu(basename.getText()) == false) { //create
                    restaurant.createMenuItemBase(basename.getText(), Double.parseDouble(baseprice.getText()));
                    System.out.println("BaseProduct created");
                }
                else if(restaurant.isInMenu(basename.getText()) == true) { //edit name
                    restaurant.editMenuItem(basename.getText(), null, Double.parseDouble(baseprice.getText()));
                    System.out.println("BaseProduct price updated");
                }
            }
        });

        addcomposite.addActionListener(new ActionListener() { //create
            @Override
            public void actionPerformed(ActionEvent e) {
                if(restaurant.isInMenu(compositename.getText()) == false) { //create
                    restaurant.createMenuItemComposite(compositename.getText());
                    System.out.println("CompositeProduct created");
                }
            }
        });

        addcomponent.addActionListener(new ActionListener() { //add component
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurant.addComponent(addcomponentname.getText(), compositename.getText());
                System.out.println("Component added to CompositeProduct");
            }
        });

        removecomponent.addActionListener(new ActionListener() { //delete component
            @Override
            public void actionPerformed(ActionEvent e) {
                restaurant.removeComponent(removecommponentname.getText(), compositename.getText());
                System.out.println("Component removed from CompositeProduct");
            }
        });

        editname.addActionListener(new ActionListener() { //edit name base
            @Override
            public void actionPerformed(ActionEvent e) {
                if(restaurant.isInMenu(oldname.getText()) == true) {
                    restaurant.editMenuItem(oldname.getText(), newname.getText(), 0);
                    System.out.println("BaseProduct name updated");
                }
            }
        });

        deletemenuitem.addActionListener(new ActionListener() { //delete
            @Override
            public void actionPerformed(ActionEvent e) {
                if(restaurant.isInMenu(deletename.getText()) == true) {
                    restaurant.deleteMenuItem(deletename.getText());
                    System.out.println("MenuItem deleted");
                }
            }
        });

        BMenuItems.addActionListener(new ActionListener() { //create
            @Override
            public void actionPerformed(ActionEvent e) {
                JTable table = new JTable();
                restaurant.showTable(table);
                frame.add(table);
                table.setBounds(560, 25, 200, 550);
            }
        });
    }

    public JButton getBack() {
        return this.jcomp27;
    }
}