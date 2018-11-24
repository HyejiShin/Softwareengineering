import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainView extends JFrame{
	public MainView() {
		setTitle("Manage Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null);
		
		JLabel la = new JLabel("Manage Program");
		
		la.setLocation(200,30);
		la.setSize(100,60);
		c.add(la);
		
		JButton btn1 = new JButton("1.Manage Contacts");
		btn1.setLocation(100,150);
		btn1.setSize(300,60);
		JButton btn2 = new JButton("2.Manage To-Do list");
		btn2.setLocation(100,250);
		btn2.setSize(300,60);
		JButton btn3 = new JButton("3.Manage appointments");
		btn3.setLocation(100,350);
		btn3.setSize(300,60);
		
		
		btn1.addActionListener(new MainActionListener());
		btn2.addActionListener(new MainActionListener());
		btn3.addActionListener(new MainActionListener());
		
		c.add(btn1);
		c.add(btn2);
		c.add(btn3);
		
		setSize(500,500);
		setLocation(300,150);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new MainView();
	}
	
}

// 버튼 누르면 해당 창이 나타남
class MainActionListener implements ActionListener{
	public void actionPerformed(ActionEvent e) {
		
		JButton b = (JButton)e.getSource();
		if(b.getText().equals("1.Manage Contacts")) {
			final Frame ContactsMain = new Frame("Manage Contacts");
			ContactsMain.setVisible(true);
			ContactsMain.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					ContactsMain.setVisible(false);
					ContactsMain.dispose();
				}
			});
			ContactsMain.setSize(500, 500);
			ContactsMain.setLocation(800, 150);
		}
		
		if(b.getText().equals("2.Manage To-Do list")) {
			final Frame ContactsMain = new Frame("Manage To-Do list");
			ContactsMain.setVisible(true);
			ContactsMain.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					ContactsMain.setVisible(false);
					ContactsMain.dispose();
				}
			});
			ContactsMain.setSize(500, 500);
			ContactsMain.setLocation(800, 150);
		}
		
		if(b.getText().equals("3.Manage appointments")) {
			final Frame ContactsMain = new Frame("Manage appointments");
			ContactsMain.setVisible(true);
			ContactsMain.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					ContactsMain.setVisible(false);
					ContactsMain.dispose();
				}
			});
			ContactsMain.setSize(500, 500);
			ContactsMain.setLocation(800, 150);
		}
			
	}
}
