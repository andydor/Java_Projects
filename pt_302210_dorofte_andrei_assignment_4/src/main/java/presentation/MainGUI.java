package presentation;

import bll.Restaurant;
import data.RestaurantSerializator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JPanel {
    private JButton jcomp1;
    private JButton jcomp2;
    private JButton load;
    private JButton save;

    public MainGUI(Restaurant[] restaurant, JFrame frame, String filename) {
        //construct components
        jcomp1 = new JButton ("Admin");
        jcomp2 = new JButton ("Waiter");
        load = new JButton ("Load");
        save = new JButton ("Save");

        //adjust size and set layout
        setPreferredSize (new Dimension(852, 473));
        setLayout (null);

        //add components
        add (jcomp1);
        add (jcomp2);
        add (load);
        add (save);

        //set component bounds (only needed by Absolute Positioning)
        jcomp1.setBounds (200, 180, 145, 55);
        jcomp2.setBounds (525, 180, 145, 55);
        load.setBounds (625, 410, 100, 25);
        save.setBounds (725, 410, 100, 25);

        jcomp1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                frame.setVisible(false);
                JFrame admin = new JFrame ("Admin GUI");
                AdminGUI admin1 = new AdminGUI(restaurant[0], admin);
                admin.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                admin.getContentPane().add(admin1);
                admin.pack();
                admin.setVisible (true);

                admin1.getBack().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        admin.setVisible(false);
                        frame.setVisible(true);
                    }
                });
            }
        });

        jcomp2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                frame.setVisible(false);
                JFrame waiter = new JFrame ("Waiter GUI");
                WaiterGUI waiter1 = new WaiterGUI(restaurant[0], waiter);
                waiter.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                waiter.getContentPane().add(waiter1);
                waiter.pack();
                waiter.setVisible (true);

                waiter1.getBack().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        waiter.setVisible(false);
                        frame.setVisible(true);
                    }
                });
            }
        });

        load.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new RestaurantSerializator().deserialize(restaurant, filename);
            }
        });

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                new RestaurantSerializator().serialize(restaurant, filename);
            }
        });
    }
}
