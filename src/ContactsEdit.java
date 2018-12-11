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

public class ContactsEdit extends JFrame implements ActionListener {
	JPanel panel, ptitle, pcontacts, pbtnsave;
	JLabel lbtitle, lbname, lbphone, lbemail;
	JButton btnsave;
	JTextField tfname, tfphone, tfemail;

	int modify_index;

	public ContactsEdit(int edit_index) {
		modify_index = edit_index;
		setTitle("Contacts Edit");
		setSize(450, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ptitle = new JPanel();
		ptitle.setLayout(new BoxLayout(ptitle, BoxLayout.Y_AXIS));

		lbtitle = new JLabel("연락처 수정");
		lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		ptitle.add(lbtitle);

		panel.add(ptitle, BorderLayout.PAGE_START);

		pcontacts = new JPanel(new GridLayout(3, 2));
		lbname = new JLabel("이름");
		lbphone = new JLabel("전화번호");
		lbemail = new JLabel("이메일");
		tfname = new JTextField(30);
		tfname.setText(ContactsMain.getContactsName(edit_index));
		tfphone = new JTextField(30);
		tfphone.setText(ContactsMain.getContactsPhone(edit_index));
		tfemail = new JTextField(30);
		tfemail.setText(ContactsMain.getContactsEmail(edit_index));
		pcontacts.add(lbname);
		pcontacts.add(tfname);
		pcontacts.add(lbphone);
		pcontacts.add(tfphone);
		pcontacts.add(lbemail);
		pcontacts.add(tfemail);
		panel.add(pcontacts, BorderLayout.CENTER);

		pbtnsave = new JPanel();
		btnsave = new JButton("수정");
		btnsave.addActionListener(this);
		pbtnsave.add(btnsave);
		panel.add(pbtnsave, BorderLayout.PAGE_END);

		add(panel);
		setVisible(true);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ContactsCreate();

	}

	public void ContactsNameEdit(String s) {
		try {
			Writer name_writer = new BufferedWriter(new FileWriter("contacts_name.txt"));
			int length = ContactsMain.contacts_name.size();

			for (int i = 1; i < length; i++) {
				if (i != modify_index) {
					name_writer.write(ContactsMain.getContactsName(i) + "\r\n");
				} else {
					name_writer.write(s);
				}
			}
			name_writer.close();
		} catch (IOException e1) {
			System.out.println("오류 발생. 수정되지 않았습니다.");
		}
	}
	
	public void ContactsPhoneEdit(String s) {
		try {
			Writer phone_writer = new BufferedWriter(new FileWriter("contacts_phone.txt"));
			int length = ContactsMain.contacts_phone.size();

			for (int i = 1; i < length; i++) {
				if (i != modify_index) {
					phone_writer.write(ContactsMain.getContactsPhone(i) + "\r\n");
				} else {
					phone_writer.write(s);
				}
			}
			phone_writer.close();
		} catch (IOException e1) {
			System.out.println("오류 발생. 수정되지 않았습니다.");
		}
	}
	
	public void ContactsEmailEdit(String s) {
		try {
			Writer email_writer = new BufferedWriter(new FileWriter("contacts_email.txt"));
			int length = ContactsMain.contacts_email.size();

			for (int i = 1; i < length; i++) {
				if (i != modify_index) {
					email_writer.write(ContactsMain.getContactsEmail(i) + "\r\n");
				} else {
					email_writer.write(s);
				}
			}
			email_writer.close();
		} catch (IOException e1) {
			System.out.println("오류 발생. 수정되지 않았습니다.");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();

		if (source == btnsave) {
			String str_name = tfname.getText() + "\r\n";
			String str_phone = tfphone.getText() + "\r\n";
			String str_email = tfemail.getText() + "\r\n";

			ContactsNameEdit(str_name);
			ContactsPhoneEdit(str_phone);
			ContactsEmailEdit(str_email);

			ContactsMain.dispose();
			new ContactsMain();
			this.dispose();
		}
	}
}