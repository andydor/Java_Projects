import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JPanel{
	
	public View() {

	JFrame frame = new JFrame ("Polynomial Calculator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 400);

    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();
    JPanel panel3 = new JPanel();
    JPanel panel4 = new JPanel();
    JPanel panel5 = new JPanel();
    JPanel panel6 = new JPanel();
    JPanel panel7 = new JPanel();

    JLabel l = new JLabel ("Polynom 1");
    final JTextField tf = new JTextField(20);

    JLabel l1 = new JLabel ("Polynom 2");
    final JTextField tf1 = new JTextField(20);
    
    JLabel l2 = new JLabel ("Result");
    final JTextField tf2 = new JTextField(40);

    panel1.add(l);
    panel2.add(tf);
    panel3.add(l1);
    panel4.add(tf1);
    panel6.add(l2);
    panel7.add(tf2);

    JButton b1 = new JButton("+");
    b1.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	Operations out = new Operations();
        	Regexda ba = new Regexda();
        	Polinomial a = new Polinomial();
        	a = ba.rez(tf.getText());
        	Polinomial b = new Polinomial();
        	b = ba.rez(tf1.getText());
        	tf2.setText(out.adauga(a, b).toString());
        }
    });

    JButton b2 = new JButton("-");
    b2.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	Operations out = new Operations();
        	Regexda ba = new Regexda();
        	Polinomial a = new Polinomial();
        	a = ba.rez(tf.getText());
        	Polinomial b = new Polinomial();
        	b = ba.rez(tf1.getText());
        	tf2.setText(out.scade(a, b).toString());
        }
    });

    JButton b3 = new JButton("*");
    b3.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	Operations out = new Operations();
        	Regexda ba = new Regexda();
        	Polinomial a = new Polinomial();
        	a = ba.rez(tf.getText());
        	Polinomial b = new Polinomial();
        	b = ba.rez(tf1.getText());
        	tf2.setText(out.multiply(a, b).toString());
        }
    });

    JButton b4 = new JButton("df/dx");
    b4.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	Operations out = new Operations();
        	Regexda ba = new Regexda();
        	Polinomial a = new Polinomial();
        	a = ba.rez(tf.getText());
        	tf2.setText(out.derivat(a).toString());
        }
    });
    
    JButton b5 = new JButton("integrate");
    b5.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
        	Operations out = new Operations();
        	Regexda ba = new Regexda();
        	Polinomial a = new Polinomial();
        	a = ba.rez(tf.getText());
        	tf2.setText(out.integr(a).toString());
        }
    });

    panel5.add(b1);
    panel5.add(b2);
    panel5.add(b3);
    panel5.add(b4);
    panel5.add(b5);

    JPanel p = new JPanel();

    p.add(panel1);
    p.add(panel2);
    p.add(panel3);
    p.add(panel4);
    p.add(panel6);
    p.add(panel7);
    p.add(panel5);
    p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

    frame.setContentPane(p);
    frame.setVisible(true);
	}
}