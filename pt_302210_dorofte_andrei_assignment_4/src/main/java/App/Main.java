package App;

import bll.*;
import presentation.MainGUI;
import javax.swing.*;

public class Main extends JPanel {
    public static void main (String[] args) {
        final Restaurant[] restaurant = {new Restaurant()};
        JFrame frame = new JFrame ("Restaurant");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new MainGUI(restaurant, frame, args[0].trim()));
        frame.pack();
        frame.setVisible (true);
    }
}