import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.*;
import java.util.Vector;

public class ToDoMain {
	public static JFrame frame;
	JLabel lbtitle, check;
	JButton btnwrite;
	JButton[] todolist_modify_button;
	JButton[] todolist_delete_button;
	JPanel panel, ptop, pcontacts, pbottom;
	JPanel pcenter;
	JLabel[][] todolist_table_label;
	
	public static Vector<String> todolist_vec;
	static int delete_index;
	int todolist_cnt = 1;

	public static String getStartDate(int edit_index) {
		return todolist_vec.elementAt(edit_index);
	}
	
	public static void setStartDate(int edit_index, String edit_date) {
		todolist_vec.setElementAt(edit_date, edit_index);
	}
	
	public static void setContactsContent(String edit_index) {
		todolist_vec.addElement(edit_index);
	}

	public ToDoMain() {

		frame = new JFrame();
		frame.setTitle("Manage To-Do list");

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ptop = new JPanel();
		lbtitle = new JLabel("Manage To-Do list");
		lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		ptop.add(lbtitle);
		panel.add(ptop, BorderLayout.PAGE_START);

		pcenter = new JPanel();
		pcenter.setLayout(new GridLayout(0, 3));
		todolist_vec = new Vector<String>();
		todolist_vec.add(0, " ");
		todolist_table_label = new JLabel[20][5];

		todolist_table_label[0][0] = new JLabel("시작날짜");
		todolist_table_label[0][1] = new JLabel("완료날짜");
		todolist_table_label[0][2] = new JLabel("할일");
		todolist_table_label[0][3] = new JLabel("");
		todolist_table_label[0][4] = new JLabel("");

		// 파일 입출력 부분
		try {
			BufferedReader reader = new BufferedReader(new FileReader("todolist.txt"));
			String todolist_name_str = "";

			while ((todolist_name_str = reader.readLine()) != null) {
				todolist_vec.add(todolist_cnt, todolist_name_str);
				todolist_cnt++;
			}

			reader.close();

			todolist_modify_button = new JButton[todolist_cnt];
			todolist_delete_button = new JButton[todolist_cnt];

			for (int i = 0; i < 5; i++)
				pcenter.add(todolist_table_label[0][i]);

			panel.add(pcenter);

			for (int i = 1; i < todolist_cnt; i++) {

				todolist_table_label[i][0] = new JLabel(todolist_vec.elementAt(i));
				todolist_table_label[i][0].setAlignmentY(Component.LEFT_ALIGNMENT);
				todolist_table_label[i][0].setSize(300, 20);

				todolist_modify_button[i] = new JButton("수정");
				todolist_modify_button[i].setSize(80, 17);
				todolist_table_label[i][1] = new JLabel();
				todolist_table_label[i][1] = new JLabel("                                ");
				todolist_table_label[i][1].setSize(100, 20);
				todolist_table_label[i][1].add(todolist_modify_button[i]);

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

				todolist_delete_button[i] = new JButton("삭제");
				todolist_delete_button[i].setSize(80, 17);
				todolist_table_label[i][2] = new JLabel();
				todolist_table_label[i][2] = new JLabel("                                ");
				todolist_table_label[i][2].setSize(100, 20);
				todolist_table_label[i][2].add(todolist_delete_button[i]);
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
					pcenter.add(todolist_table_label[i][j]);

				panel.add(pcenter);
			}

		} catch (IOException e) {
			for (int j = 0; j < 3; j++)
				pcenter.add(todolist_table_label[0][j]);
		}

		panel.add(pcenter, BorderLayout.CENTER);

		pbottom = new JPanel();
		btnwrite = new JButton("Add");
		btnwrite.setAlignmentX(Component.RIGHT_ALIGNMENT);

		// Add 버튼 눌렀을 때
		
		 
		 

		frame.setBounds(200, 200, 100 * 3 + 100, 38 * todolist_cnt + 60 + 20);
		panel.setSize(100 * 3, 35 * todolist_cnt + 50);

		for (int i = 0; i < todolist_cnt; i++) {
			for (int j = 0; j < 3; j++) {
				todolist_table_label[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				todolist_table_label[i][j].setVerticalAlignment(SwingConstants.CENTER);
				todolist_table_label[i][j].setHorizontalAlignment(SwingConstants.CENTER);

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
