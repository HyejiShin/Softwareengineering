import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import javax.swing.*;
//얍!!
public class AppointCreate extends JFrame implements ActionListener {

	JPanel panel, ptitle, pappoint, pbtnsave;
	JLabel lbtitle, lbdate, lbpersons, lblocation;
	JButton btnsave;
	JTextField tfdate, tfpersons, tflocation;

	public AppointCreate() {
		setTitle("Appoint Create");
		setSize(450, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		ptitle = new JPanel();
		ptitle.setLayout(new BoxLayout(ptitle, BoxLayout.Y_AXIS));
		lbtitle = new JLabel("약속 추가");
		lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		ptitle.add(lbtitle);
		panel.add(ptitle, BorderLayout.PAGE_START);
		pappoint = new JPanel(new GridLayout(3, 2));
		lbdate = new JLabel("날짜");
		lbpersons = new JLabel("참여자");
		lblocation = new JLabel("장소");
		tfdate = new JTextField(30);
		tfpersons = new JTextField(30);
		tflocation = new JTextField(30);
		pappoint.add(lbdate);
		pappoint.add(tfdate);
		pappoint.add(lbpersons);
		pappoint.add(tfpersons);
		pappoint.add(lblocation);
		pappoint.add(tflocation);
		panel.add(pappoint, BorderLayout.CENTER);
		pbtnsave = new JPanel();
		btnsave = new JButton("저장");
		btnsave.addActionListener(this);
		pbtnsave.add(btnsave);
		panel.add(pbtnsave, BorderLayout.PAGE_END);
		add(panel);
		setVisible(true);
	}

	public static void main(String[] args) {
		new AppointCreate();
	}

	public void AppointDateWrite(String data) {
		try {
			Writer date_create = new BufferedWriter(new FileWriter("appoint_date.txt", true));
			date_create.append(data);
			date_create.close();
		} catch (IOException ex) {
			System.out.println("오류 발생. 추가되지 않았습니다.");
		}
	}

	public void AppointPersonsWrite(String data) {
		try {
			Writer persons_create = new BufferedWriter(new FileWriter("appoint_persons.txt", true));
			persons_create.append(data);
			persons_create.close();
		} catch (IOException ex) {
			System.out.println("오류 발생. 추가되지 않았습니다.");
		}
	}

	public void AppointLocationWrite(String data) {
		try {
			Writer location_create = new BufferedWriter(new FileWriter("appoint_location.txt", true));
			location_create.append(data);
			location_create.close();
		} catch (IOException ex) {
			System.out.println("오류 발생. 추가되지 않았습니다.");
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == btnsave) {
			String str_date = tfdate.getText() + "\r\n";
			String str_persons = tfpersons.getText() + "\r\n";
			String str_location = tflocation.getText() + "\r\n";

			AppointDateWrite(str_date);
			AppointPersonsWrite(str_persons);
			AppointLocationWrite(str_location);

			AppointMain.dispose();
			new AppointMain();
			this.dispose();
		}
	}
}