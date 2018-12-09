import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame {
	public Main() {
		setTitle("Manage Program");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);

		JLabel la = new JLabel("Manage Program");

		la.setLocation(200, 30);
		la.setSize(100, 60);
		c.add(la);

		JButton btncontacts = new JButton("1.Manage Contacts");
		btncontacts.setLocation(100, 150);
		btncontacts.setSize(300, 60);
		JButton btntodo = new JButton("2.Manage To-Do list");
		btntodo.setLocation(100, 250);
		btntodo.setSize(300, 60);
		JButton btnappoint = new JButton("3.Manage Appointments");
		btnappoint.setLocation(100, 350);
		btnappoint.setSize(300, 60);

		btncontacts.addActionListener(new MainActionListener());
		btntodo.addActionListener(new MainActionListener());
		btnappoint.addActionListener(new MainActionListener());

		c.add(btncontacts);
		c.add(btntodo);
		c.add(btnappoint);

		setSize(500, 500);
		setLocation(300, 150);
		setVisible(true);

	}

	public static void main(String[] args) {
		new Main();
	}
}

class MainActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) e.getSource();
		if (b.getText().equals("1.Manage Contacts"))
			new ContactsMain();

		if (b.getText().equals("2.Manage To-Do list"))
			new ToDoMain();

		if (b.getText().equals("3.Manage Appointments"))
			new AppointMain();
	}
}
