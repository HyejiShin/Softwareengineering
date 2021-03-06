import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

public class ContactsMain {
	public static JFrame frame;
	JLabel lbtitle;
	JButton btncreate;
	JButton[] contacts_modify_button;
	JButton[] contacts_delete_button;
	JPanel panel, ptop, pcenter, pbottom;
	JLabel[][] contacts_table_label;
	public static Vector<String> contacts_name;
	public static Vector<String> contacts_phone;
	public static Vector<String> contacts_email;
	static int delete_index;
	int contacts_cnt = 1;
	int name_cnt = 1, phone_cnt = 1, email_cnt = 1;

	public static String getContactsName(int edit_index) {
		return contacts_name.elementAt(edit_index);
	}

	public static void setContactsName(int edit_index, String edit_content) {
		contacts_name.setElementAt(edit_content, edit_index);
	}

	public static void setContactsName(String edit_content) {
		contacts_name.addElement(edit_content);
	}

	public static String getContactsPhone(int edit_index) {
		return contacts_phone.elementAt(edit_index);
	}

	public static void setContactsPhone(int edit_index, String edit_content) {
		contacts_phone.setElementAt(edit_content, edit_index);
	}

	public static void setContactsPhone(String edit_content) {
		contacts_phone.addElement(edit_content);
	}

	public static String getContactsEmail(int edit_index) {
		return contacts_email.elementAt(edit_index);
	}

	public static void setContactsEmail(int edit_index, String edit_content) {
		contacts_email.setElementAt(edit_content, edit_index);
	}

	public static void setContactsEmail(String edit_content) {
		contacts_email.addElement(edit_content);
	}

	public ContactsMain() {
		frame = new JFrame();
		frame.setTitle("Manage Contacts");

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ptop = new JPanel();
		lbtitle = new JLabel("Manage Contacts");
		lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		ptop.add(lbtitle);
		panel.add(ptop, BorderLayout.PAGE_START);

		pcenter = new JPanel();
		pcenter.setLayout(new GridLayout(0, 5));
		contacts_name = new Vector<String>();
		contacts_name.add(0, " ");
		contacts_phone = new Vector<String>();
		contacts_phone.add(0, " ");
		contacts_email = new Vector<String>();
		contacts_email.add(0, " ");
		contacts_table_label = new JLabel[20][5];

		contacts_table_label[0][0] = new JLabel("이름");
		contacts_table_label[0][1] = new JLabel("전화번호");
		contacts_table_label[0][2] = new JLabel("이메일");
		contacts_table_label[0][3] = new JLabel("수정");
		contacts_table_label[0][4] = new JLabel("삭제");
		try {
			BufferedReader name_reader = new BufferedReader(new FileReader("contacts_name.txt"));
			BufferedReader phone_reader = new BufferedReader(new FileReader("contacts_phone.txt"));
			BufferedReader email_reader = new BufferedReader(new FileReader("contacts_email.txt"));

			String contacts_name_str = "";
			String contacts_phone_str = "";
			String contacts_email_str = "";

			while ((contacts_name_str = name_reader.readLine()) != null) {
				contacts_name.add(name_cnt, contacts_name_str);
				name_cnt++;
				contacts_cnt = name_cnt;
			}

			while ((contacts_phone_str = phone_reader.readLine()) != null) {
				contacts_phone.add(phone_cnt, contacts_phone_str);
				phone_cnt++;
			}

			while ((contacts_email_str = email_reader.readLine()) != null) {
				contacts_email.add(email_cnt, contacts_email_str);
				email_cnt++;	
			}
			
			name_reader.close();
			phone_reader.close();
			email_reader.close();

			contacts_modify_button = new JButton[contacts_cnt];
			contacts_delete_button = new JButton[contacts_cnt];

			for (int i = 0; i < 5; i++)
				pcenter.add(contacts_table_label[0][i]);

			panel.add(pcenter);

			for (int i = 1; i < contacts_cnt; i++) {
				contacts_table_label[i][0] = new JLabel(contacts_name.elementAt(i));
				contacts_table_label[i][1] = new JLabel(contacts_phone.elementAt(i));
				contacts_table_label[i][2] = new JLabel(contacts_email.elementAt(i));
				contacts_modify_button[i] = new JButton("수정");
				contacts_modify_button[i].setSize(80, 17);
				contacts_table_label[i][3] = new JLabel();
				contacts_table_label[i][3].add(contacts_modify_button[i]);

				contacts_modify_button[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						int modify_index = 0;
						for (int j = 1; j < contacts_cnt; j++) {
							if (e.getSource() == contacts_modify_button[j]) {
								modify_index = j;
							}
						}
						new ContactsEdit(modify_index);
					}
				});

				contacts_delete_button[i] = new JButton("삭제");
				contacts_delete_button[i].setSize(80, 17);
				contacts_table_label[i][4] = new JLabel();
				contacts_table_label[i][4].add(contacts_delete_button[i]);
				delete_index = i;

				contacts_delete_button[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							@SuppressWarnings("resource")
							Writer name_writer = new BufferedWriter(new FileWriter("contacts_name.txt"));
							Writer phone_writer = new BufferedWriter(new FileWriter("contacts_phone.txt"));
							Writer email_writer = new BufferedWriter(new FileWriter("contacts_email.txt"));

							for (int j = 1; j < contacts_cnt; j++) {
								if (e.getSource() != contacts_delete_button[j]) {
									name_writer.write(contacts_name.elementAt(j) + "\r\n");
									phone_writer.write(contacts_phone.elementAt(j) + "\r\n");
									email_writer.write(contacts_email.elementAt(j) + "\r\n");
								}
							}
							name_writer.close();
							phone_writer.close();
							email_writer.close();
						} catch (IOException e1) {

						}
						ContactsMain.dispose();
						new ContactsMain();
					}
				});

				for (int j = 0; j < 5; j++)
					pcenter.add(contacts_table_label[i][j]);
				panel.add(pcenter);
			}
		} catch (IOException e) {
			for (int j = 0; j < 5; j++)
				pcenter.add(contacts_table_label[0][j]);
		}

		panel.add(pcenter, BorderLayout.CENTER);

		pbottom = new JPanel();
		btncreate = new JButton("추가");

		btncreate.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 new ContactsCreate();
				 }
			 });

		frame.setBounds(400, 400, 100 * 5 + 100, 38 * contacts_cnt + 60 + 20);
		panel.setSize(100 * 5, 35 * contacts_cnt + 50);

		for (int i = 0; i < contacts_cnt; i++) {
			for (int j = 0; j < 5; j++) {
				contacts_table_label[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				contacts_table_label[i][j].setVerticalAlignment(SwingConstants.CENTER);
				contacts_table_label[i][j].setHorizontalAlignment(SwingConstants.CENTER);
			}
		}

		pbottom.add(btncreate);
		panel.add(pbottom, BorderLayout.PAGE_END);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
	}

	public static void dispose() {
		frame.dispose();
	}

	public static void main(String[] args) {
		new ContactsMain();
	}
}