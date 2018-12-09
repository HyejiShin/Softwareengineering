import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.swing.text.AttributeSet;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ContactsCreate extends JFrame implements ActionListener {
	JPanel panel, ptitle, pcontacts, pbtnsave;
	JLabel lbtitle, lbname, lbphone, lbemail;
	JButton btnsave;
	JTextField tfname, tfphone, tfemail;

	public ContactsCreate() {
		setTitle("Contacts Create");
		setSize(450, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ptitle = new JPanel();
		ptitle.setLayout(new BoxLayout(ptitle, BoxLayout.Y_AXIS));

		lbtitle = new JLabel("����ó �߰�");
		lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		ptitle.add(lbtitle);

		panel.add(ptitle, BorderLayout.PAGE_START);

		pcontacts = new JPanel(new GridLayout(3, 2));
		lbname = new JLabel("�̸�");
		lbphone = new JLabel("��ȭ��ȣ");
		lbemail = new JLabel("�̸���");
		tfname = new JTextField(30);
		tfphone = new JTextField(30);
		tfemail = new JTextField(30);
		pcontacts.add(lbname);
		pcontacts.add(tfname);
		pcontacts.add(lbphone);
		pcontacts.add(tfphone);
		pcontacts.add(lbemail);
		pcontacts.add(tfemail);
		panel.add(pcontacts, BorderLayout.CENTER);

		pbtnsave = new JPanel();
		btnsave = new JButton("����");
		btnsave.addActionListener(this);
		pbtnsave.add(btnsave);
		panel.add(pbtnsave, BorderLayout.PAGE_END);

		add(panel);
		setVisible(true);
	}

	public static void main(String[] args) {
		new ContactsCreate();
	}

	public void ContactsNameWrite(String data) {
		try {
			Writer name_create = new BufferedWriter(new FileWriter("contacts_name.txt", true));
			name_create.append(data);
			name_create.close();
		} catch (IOException ex) {
			System.out.println("���� �߻�. �߰����� �ʾҽ��ϴ�.");
		}
	}

	public void ContactsPhoneWrite(String data) {
		try {
			Writer phone_create = new BufferedWriter(new FileWriter("contacts_phone.txt", true));
			phone_create.append(data);
			phone_create.close();
		} catch (IOException ex) {
			System.out.println("���� �߻�. �߰����� �ʾҽ��ϴ�.");
		}
	}

	public void ContactsEmailWrite(String data) {
		try {
			Writer email_create = new BufferedWriter(new FileWriter("contacts_email.txt", true));
			email_create.append(data);
			email_create.close();
		} catch (IOException ex) {
			System.out.println("���� �߻�. �߰����� �ʾҽ��ϴ�.");
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == btnsave) {
			String str_name = tfname.getText() + "\r\n";
			String str_phone = tfphone.getText() + "\r\n";
			String str_email = tfemail.getText() + "\r\n";

			ContactsNameWrite(str_name);
			ContactsPhoneWrite(str_phone);
			ContactsEmailWrite(str_email);

			ContactsMain.dispose();
			new ContactsMain();
			this.dispose();
		}
	}
}