import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

public class ToDoEdit extends JFrame implements ActionListener {
	JPanel panel, ptitle, ptodolist, pbtnsave;
	JLabel lbtitle, lbstartdate, lbduedate, lbdescription;
	JButton btnsave;
	JTextField tfstartdate, tfduedate, tfdescription;

	int modify_index;

	public ToDoEdit(int edit_index) {
		modify_index = edit_index;
		setTitle("ToDoList Edit");
		setSize(450, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ptitle = new JPanel();
		ptitle.setLayout(new BoxLayout(ptitle, BoxLayout.Y_AXIS));

		lbtitle = new JLabel("할일 수정");
		lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

		ptitle.add(lbtitle);

		panel.add(ptitle, BorderLayout.PAGE_START);

		ptodolist = new JPanel(new GridLayout(3, 2));
		lbstartdate = new JLabel("생성날짜");
		lbduedate = new JLabel("마감날짜");
		lbdescription = new JLabel("설명");
		tfstartdate = new JTextField(30);
		tfstartdate.setText(ToDoMain.getStartDate(edit_index));
		tfduedate = new JTextField(30);
		tfduedate.setText(ToDoMain.getDueDate(edit_index));
		tfdescription = new JTextField(30);
		tfdescription.setText(ToDoMain.getDescription(edit_index));
		ptodolist.add(lbstartdate);
		ptodolist.add(tfstartdate);
		ptodolist.add(lbduedate);
		ptodolist.add(tfduedate);
		ptodolist.add(lbdescription);
		ptodolist.add(tfdescription);
		panel.add(ptodolist, BorderLayout.CENTER);

		pbtnsave = new JPanel();
		btnsave = new JButton("수정");
		btnsave.addActionListener(this);
		pbtnsave.add(btnsave);
		panel.add(pbtnsave, BorderLayout.PAGE_END);

		add(panel);
		setVisible(true);
	}

	public static void main(String[] args) {
		
		new ToDoCreate();

	}

	public void StartDateEdit(String s) {
		try {
			Writer start_date_writer = new BufferedWriter(new FileWriter("todo_start_date.txt"));
			int length = ToDoMain.start_date.size();

			for (int i = 1; i < length; i++) {
				if (i != modify_index) {
					start_date_writer.write(ToDoMain.getStartDate(i) + "\r\n");
				} else {
					start_date_writer.write(s);
				}
			}
			start_date_writer.close();
		} catch (IOException e1) {
			System.out.println("오류 발생. 수정되지 않았습니다.");
		}
	}
	
	public void DueDateEdit(String s) {
		try {
			Writer due_date_writer = new BufferedWriter(new FileWriter("todo_due_date.txt"));
			int length = ToDoMain.due_date.size();

			for (int i = 1; i < length; i++) {
				if (i != modify_index) {
					due_date_writer.write(ToDoMain.getDueDate(i) + "\r\n");
				} else {
					due_date_writer.write(s);
				}
			}
			due_date_writer.close();
		} catch (IOException e1) {
			System.out.println("오류 발생. 수정되지 않았습니다.");
		}
	}
	
	public void DescriptionEdit(String s) {
		try {
			Writer description_writer = new BufferedWriter(new FileWriter("todo_description.txt"));
			int length = ToDoMain.description.size();

			for (int i = 1; i < length; i++) {
				if (i != modify_index) {
					description_writer.write(ToDoMain.getDescription(i) + "\r\n");
				} else {
					description_writer.write(s);
				}
			}
			description_writer.close();
		} catch (IOException e1) {
			System.out.println("오류 발생. 수정되지 않았습니다.");
		}
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Object source = e.getSource();

		if (source == btnsave) {
			String str_start_date = tfstartdate.getText() + "\r\n";
			String str_due_date = tfduedate.getText() + "\r\n";
			String str_description = tfdescription.getText() + "\r\n";

			StartDateEdit(str_start_date);
			DueDateEdit(str_due_date);
			DescriptionEdit(str_description);

			ToDoMain.dispose();
			new ToDoMain();
			this.dispose();
		}
	}
}