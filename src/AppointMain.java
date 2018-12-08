import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

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

public class AppointMain {
	public static JFrame frame;
	JLabel lbtitle, check;
	JButton btncreate;
	JButton[] appoint_modify_button;
	JButton[] appoint_delete_button;
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
		frame = new JFrame();
		frame.setTitle("Manage Appointment");

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
		appoint_persons = new Vector<String>();
		appoint_persons.add(0, " ");
		appoint_location = new Vector<String>();
		appoint_location.add(0, " ");
		appoint_table_label = new JLabel[20][5];

		appoint_table_label[0][0] = new JLabel("약속 날짜");
		appoint_table_label[0][1] = new JLabel("참여자");
		appoint_table_label[0][2] = new JLabel("약속 장소");
		appoint_table_label[0][3] = new JLabel("");
		appoint_table_label[0][4] = new JLabel("");

		try {
			BufferedReader date_reader = new BufferedReader(new FileReader("appoint_date.txt"));
			BufferedReader persons_reader = new BufferedReader(new FileReader("appoint_persons.txt"));
			BufferedReader location_reader = new BufferedReader(new FileReader("appoint_location.txt"));
			
			String appoint_date_str = "";
			String appoint_persons_str = "";
			String appoint_location_str = "";
			
			int date_cnt =1, persons_cnt = 1, location_cnt = 1;
			while ((appoint_date_str = date_reader.readLine()) != null) {
				appoint_date.add(date_cnt, appoint_date_str);
				date_cnt++;
				appoints_cnt = date_cnt;
			}
			
			while ((appoint_persons_str = persons_reader.readLine()) != null) {
				appoint_persons.add(persons_cnt, appoint_persons_str);
				persons_cnt++;
			}
			
			while ((appoint_location_str = location_reader.readLine()) != null) {
				appoint_location.add(location_cnt, appoint_location_str);
				location_cnt++;	
			}
			
			date_reader.close();
			persons_reader.close();
			location_reader.close();

			appoint_modify_button = new JButton[appoints_cnt];
			appoint_delete_button = new JButton[appoints_cnt];

			for (int i = 0; i < 5; i++)
				pcenter.add(appoint_table_label[0][i]);
			panel.add(pcenter);

			
			for (int i = 1; i < appoints_cnt; i++) {

				appoint_table_label[i][0] = new JLabel(appoint_date.elementAt(i));
				appoint_table_label[i][1] = new JLabel(appoint_persons.elementAt(i));
				appoint_table_label[i][2] = new JLabel(appoint_location.elementAt(i));
				
				appoint_modify_button[i] = new JButton("수정");
				appoint_modify_button[i].setSize(80, 17);
				appoint_table_label[i][3] = new JLabel();
				appoint_table_label[i][3].add(appoint_modify_button[i]);

				appoint_delete_button[i] = new JButton("삭제");
				appoint_delete_button[i].setSize(80, 17);
				appoint_table_label[i][4] = new JLabel();
				appoint_table_label[i][4].add(appoint_delete_button[i]);
				delete_index = i;
				
				for (int j = 0; j < 5; j++)
					pcenter.add(appoint_table_label[i][j]);
				panel.add(pcenter);
			}

		} catch (IOException e) {
			for (int j = 0; j < 5; j++)
				pcenter.add(appoint_table_label[0][j]);
		}
		panel.add(pcenter, BorderLayout.CENTER);
		pbottom = new JPanel();
		btncreate = new JButton("추가");
		btncreate.setAlignmentX(Component.RIGHT_ALIGNMENT);

		 btncreate.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 new AppointCreate();
			}
		});

		frame.setBounds(400, 400, 100 * 5 + 100, 38 * appoints_cnt + 60 + 20);
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
	}

	public static void dispose() {
		frame.dispose();
	}

	public static void main(String[] args) {
		new AppointMain();
	}

}