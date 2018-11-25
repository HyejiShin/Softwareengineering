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
	JLabel lbtitle, check;
	JButton btnwrite;
	JButton[] contacts_modify_button;
	JButton[] contacts_delete_button;
	JPanel panel, ptop, pcontacts, pbottom;
	JPanel pcenter;
	JLabel[][] contacts_table_label;

	public static Vector<String> contacts_vec;
	static int delete_index;
	int contacts_cnt = 1;

	public static String getContactsContent(int edit_index) {
		return contacts_vec.elementAt(edit_index);
	}

	public static void setContactsContent(int edit_index, String edit_content) {
		contacts_vec.setElementAt(edit_content, edit_index);
	}

	public static void setContactsContent(String edit_content) {
		contacts_vec.addElement(edit_content);
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
		pcenter.setLayout(new GridLayout(0, 3));
		contacts_vec = new Vector<String>();
		contacts_vec.add(0, " ");
		contacts_table_label = new JLabel[20][5];

		contacts_table_label[0][0] = new JLabel("이름");
		contacts_table_label[0][1] = new JLabel("전화번호");
		contacts_table_label[0][2] = new JLabel("이메일");
		contacts_table_label[0][3] = new JLabel("");
		contacts_table_label[0][4] = new JLabel("");

		// 파일 입출력 부분
		try {
			BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"));
			String contacts_name_str = "";

			while ((contacts_name_str = reader.readLine()) != null) {
				contacts_vec.add(contacts_cnt, contacts_name_str);
				contacts_cnt++;
			}

			reader.close();

			contacts_modify_button = new JButton[contacts_cnt];
			contacts_delete_button = new JButton[contacts_cnt];

			for (int i = 0; i < 5; i++)
				pcenter.add(contacts_table_label[0][i]);

			panel.add(pcenter);

			for (int i = 1; i < contacts_cnt; i++) {

				contacts_table_label[i][0] = new JLabel(contacts_vec.elementAt(i));
				contacts_table_label[i][0].setAlignmentY(Component.LEFT_ALIGNMENT);
				contacts_table_label[i][0].setSize(300, 20);

				contacts_modify_button[i] = new JButton("수정");
				contacts_modify_button[i].setSize(80, 17);
				contacts_table_label[i][1] = new JLabel();
				contacts_table_label[i][1] = new JLabel("                                ");
				contacts_table_label[i][1].setSize(100, 20);
				contacts_table_label[i][1].add(contacts_modify_button[i]);

				// 수정 버튼 눌러졌을 때 _ 수정 필요
				/*
				 * contacts_modify_button[i].addActionListener(new
				 * ActionListener() {
				 * 
				 * 
				 * public void actionPerformed(ActionEvent e) { // TODO
				 * Auto-generated method stub int modify_index=0; for(int
				 * j=1;j<contacts_cnt;j++) { if(e.getSource()==
				 * contacts_modify_button[j]) { modify_index = j; } } new
				 * MemoEdit(modify_index); } });
				 */

				contacts_delete_button[i] = new JButton("삭제");
				contacts_delete_button[i].setSize(80, 17);
				contacts_table_label[i][2] = new JLabel();
				contacts_table_label[i][2] = new JLabel("                                ");
				contacts_table_label[i][2].setSize(100, 20);
				contacts_table_label[i][2].add(contacts_delete_button[i]);
				delete_index = i;

				// 삭제 버튼 눌렀을 때 _ 수정 필요
				/*
				 * contacts_delete_button[i].addActionListener(new
				 * ActionListener() { public void actionPerformed(ActionEvent e)
				 * {
				 * 
				 * // TODO Auto-generated method stub try {
				 * 
				 * @SuppressWarnings("resource") Writer memo_writer = new
				 * BufferedWriter(new FileWriter("memo.txt"));
				 * 
				 * 
				 * for(int j=1;j<memo_cnt;j++) {
				 * if(e.getSource()!=memo_delete_button[j]) {
				 * memo_writer.write(memo_vec.elementAt(j)+"\r\n"); } }
				 * memo_writer.close(); } catch(IOException e1) {
				 * 
				 * } ContactsMain.dispose(); new ContactsMain(); } });
				 * 
				 */

				for (int j = 0; j < 3; j++)
					pcenter.add(contacts_table_label[i][j]);

				panel.add(pcenter);
			}

		} catch (IOException e) {
			for (int j = 0; j < 3; j++)
				pcenter.add(contacts_table_label[0][j]);
		}

		panel.add(pcenter, BorderLayout.CENTER);

		pbottom = new JPanel();
		btnwrite = new JButton("Add");
		btnwrite.setAlignmentX(Component.RIGHT_ALIGNMENT);

		// Add 버튼 눌렀을 때
		
		 btnwrite.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 new ContactsCreate();
				 }
			 });
		 

		frame.setBounds(200, 200, 100 * 3 + 100, 38 * contacts_cnt + 60 + 20);
		panel.setSize(100 * 3, 35 * contacts_cnt + 50);

		for (int i = 0; i < contacts_cnt; i++) {
			for (int j = 0; j < 3; j++) {
				contacts_table_label[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				contacts_table_label[i][j].setVerticalAlignment(SwingConstants.CENTER);
				contacts_table_label[i][j].setHorizontalAlignment(SwingConstants.CENTER);

			}
		}

		pbottom.add(btnwrite);
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
