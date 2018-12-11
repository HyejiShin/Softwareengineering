import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.*;

public class AppointEdit extends JFrame implements ActionListener {
	JPanel panel, ptitle, pappoints, pbtnsave;
	JLabel lbtitle, lbdate, lbpersons, lblocation;
	JButton btnsave;
	JTextField tfdate, tfpersons, tflocation;

	int modify_index;

	public AppointEdit(int edit_index) {
		modify_index = edit_index;
		setTitle("Appointments Edit");
		setSize(450, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());

		ptitle = new JPanel();
		lbtitle = new JLabel("약속 수정");
		ptitle.add(lbtitle);
		panel.add(ptitle, BorderLayout.PAGE_START);

		pappoints = new JPanel(new GridLayout(3, 2));
		lbdate = new JLabel("날짜");
		lbpersons = new JLabel("구성인");
		lblocation = new JLabel("장소");
		tfdate = new JTextField(30);
		tfdate.setText(AppointMain.getAppointDate(edit_index));
		tfpersons = new JTextField(30);
		tfpersons.setText(AppointMain.getAppointPersons(edit_index));
		tflocation = new JTextField(30);
		tflocation.setText(AppointMain.getAppointLocation(edit_index));
		pappoints.add(lbdate);
		pappoints.add(tfdate);
		pappoints.add(lbpersons);
		pappoints.add(tfpersons);
		pappoints.add(lblocation);
		pappoints.add(tflocation);
		panel.add(pappoints, BorderLayout.CENTER);

		pbtnsave = new JPanel();
		btnsave = new JButton("수정");
		btnsave.addActionListener(this);
		pbtnsave.add(btnsave);
		panel.add(pbtnsave, BorderLayout.PAGE_END);
		
		add(panel);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AppointCreate();
	}

	public void AppointDateEdit(String s) {
		try {
			Writer date_writer = new BufferedWriter(new FileWriter("appoint_date.txt"));
			int length = AppointMain.appoint_date.size();
			for (int i = 1; i < length; i++)
				if (i != modify_index)
					date_writer.write(AppointMain.getAppointDate(i) + "\r\n");
				else
					date_writer.write(s);
			date_writer.close();
		} catch (IOException e1) {
			System.out.println("오류 발생. 수정되지 않았습니다.");
		}
	}
	
	public void AppointPersonsEdit(String s) {
		try {
			Writer persons_writer = new BufferedWriter(new FileWriter("appoint_persons.txt"));
			int length = AppointMain.appoint_persons.size();
			for (int i = 1; i < length; i++)
				if (i != modify_index)
					persons_writer.write(AppointMain.getAppointPersons(i) + "\r\n");
				else
					persons_writer.write(s);
			persons_writer.close();
		} catch (IOException e1) {
			System.out.println("오류 발생. 수정되지 않았습니다.");
		}
	}
	
	public void AppointLocationEdit(String s) {
		try {
			Writer location_writer = new BufferedWriter(new FileWriter("appoint_location.txt"));
			int length = AppointMain.appoint_location.size();
			for (int i = 1; i < length; i++)
				if (i != modify_index)
					location_writer.write(AppointMain.getAppointLocation(i) + "\r\n");
				else
					location_writer.write(s);
			location_writer.close();
		} catch (IOException e1) {
			System.out.println("오류 발생. 수정되지 않았습니다.");
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnsave) {
			String str_date = tfdate.getText() + "\r\n";
			String str_persons = tfpersons.getText() + "\r\n";
			String str_location = tflocation.getText() + "\r\n";

			AppointDateEdit(str_date);
			AppointPersonsEdit(str_persons);
			AppointLocationEdit(str_location);

			AppointMain.dispose();
			new AppointMain();
			this.dispose();
		}
	}
}