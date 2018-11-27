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

public class AppointMain extends JFrame {
	JLabel lbtitle, check;
	JButton btncreate;
	JButton[] contacts_modify_button;
	JButton[] contacts_delete_button;
	JPanel panel, ptop, pcenter, pbottom;
	JLabel[][] appoint_table_label;

	public static Vector<String> appoint_date;
	public static Vector<String> appoint_persons;
	public static Vector<String> appoint_location;
	
	static int delete_index;
	int appoints_cnt = 1;

	public static String getAppointDate(int edit_index) {
		return appoint_date.elementAt(edit_index);
	}

	public static void setAppointDate(int edit_index, String edit_content) {
		appoint_date.setElementAt(edit_content, edit_index);
	}

	public static void setAppointDate(String edit_content) {
		appoint_date.addElement(edit_content);
	}
	

	public static String getAppointPersons(int edit_index) {
		return appoint_persons.elementAt(edit_index);
	}

	public static void setAppointPersons(int edit_index, String edit_content) {
		appoint_persons.setElementAt(edit_content, edit_index);
	}

	public static void setAppointPersons(String edit_content) {
		appoint_persons.addElement(edit_content);
	}


	public static String getAppointLocation(int edit_index) {
		return appoint_location.elementAt(edit_index);
	}

	public static void setAppointLocation(int edit_index, String edit_content) {
		appoint_location.setElementAt(edit_content, edit_index);
	}

	public static void setAppointLocation(String edit_content) {
		appoint_location.addElement(edit_content);
	}


	public AppointMain() {

		setTitle("Manage Appointment");

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ptop = new JPanel();
		lbtitle = new JLabel("Manage Appointment");
		lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		ptop.add(lbtitle);
		panel.add(ptop, BorderLayout.PAGE_START);

		pcenter = new JPanel();
		pcenter.setLayout(new GridLayout(0, 5));
		appoint_date = new Vector<String>();
		appoint_date.add(0, " ");
		appoint_table_label = new JLabel[20][5];

		appoint_table_label[0][0] = new JLabel("약속 날짜");
		appoint_table_label[0][1] = new JLabel("약속 人");
		appoint_table_label[0][2] = new JLabel("약속 장소");
		appoint_table_label[0][3] = new JLabel("수정");
		appoint_table_label[0][4] = new JLabel("삭제");
		
		panel.add(pcenter, BorderLayout.CENTER);

		pbottom = new JPanel();
		btncreate = new JButton("추가");
		btncreate.setAlignmentX(Component.RIGHT_ALIGNMENT);

		 btncreate.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 new ContactsCreate();
				 }
			 });

		setBounds(400, 400, 100 * 5 + 100, 38 * appoints_cnt + 60 + 20);
		panel.setSize(100 * 5, 35 * appoints_cnt + 50);

		for (int i = 0; i < appoints_cnt; i++) {
			for (int j = 0; j < 5; j++) {
				appoint_table_label[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK));
				appoint_table_label[i][j].setVerticalAlignment(SwingConstants.CENTER);
				appoint_table_label[i][j].setHorizontalAlignment(SwingConstants.CENTER);

			}
		}

		pbottom.add(btncreate);
		panel.add(pbottom, BorderLayout.PAGE_END);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(panel);
		setVisible(true);
	}

	public void dispose() {
		dispose();
	}

	public static void main(String[] args) {
		new ContactsMain();

	}

}
