import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

class AppointTest {

	@Test
	void testAppointCreate() {
		int date_cnt = 0;
		int persons_cnt = 0;
		int location_cnt = 0;
		
		AppointCreate create = new AppointCreate();
		
		Vector<String> date_test = new Vector<String>();
		Vector<String> persons_test = new Vector<String>();
		Vector<String> location_test = new Vector<String>();
		String inDate = "2018/12/19";
		String inPersons = "경현, 소연, 혜지";
		String inLocation = "과방";

		create.AppointDateWrite(inDate + "\r\n");
		create.AppointPersonsWrite(inPersons + "\r\n");
		create.AppointLocationWrite(inLocation + "\r\n");

		try {
			BufferedReader date_reader = new BufferedReader(new FileReader("appoint_date.txt"));
			BufferedReader persons_reader = new BufferedReader(new FileReader("appoint_persons.txt"));
			BufferedReader location_reader = new BufferedReader(new FileReader("appoint_location.txt"));

			String appoint_date_test = "";
			String appoint_persons_test = "";
			String appoint_location_test = "";

			while ((appoint_date_test = date_reader.readLine()) != null) {
				date_test.add(date_cnt, appoint_date_test);
				date_cnt++;
			}

			while ((appoint_persons_test = persons_reader.readLine()) != null) {
				persons_test.add(persons_cnt, appoint_persons_test);
				persons_cnt++;
			}

			while ((appoint_location_test = location_reader.readLine()) != null) {
				location_test.add(location_cnt, appoint_location_test);
				location_cnt++;
			}
			date_reader.close();
			persons_reader.close();
			location_reader.close();
		} catch (IOException e) {}
		assertTrue(date_test.elementAt(date_cnt - 1).equals(inDate));
		assertTrue(persons_test.elementAt(persons_cnt - 1).equals(inPersons));
		assertTrue(location_test.elementAt(location_cnt - 1).equals(inLocation));
	}

	@Test
	void testContactsEdit() {
		int modify_index = 1;
		AppointEdit appointedit = new AppointEdit(modify_index);

		Vector<String> date_edit = new Vector<String>();
		Vector<String> persons_edit = new Vector<String>();
		Vector<String> location_edit = new Vector<String>();

		String newDate = "2018/12/25";
		String newPersons = "김소연, 이수진";
		String newLocation = "종로";

		appointedit.AppointDateEdit(newDate+ "\r\n");
		appointedit.AppointPersonsEdit(newPersons + "\r\n");
		appointedit.AppointLocationEdit(newLocation + "\r\n");

		try {
			BufferedReader date_reader = new BufferedReader(new FileReader("appoint_date.txt"));
			BufferedReader persons_reader = new BufferedReader(new FileReader("appoint_persons.txt"));
			BufferedReader location_reader = new BufferedReader(new FileReader("appoint_location.txt"));

			String appoint_date_edit = "";
			String appoint_persons_edit = "";
			String appoint_location_edit = "";

			while ((appoint_date_edit = date_reader.readLine()) != null) {
				date_edit.add(appoint_date_edit);
			}
			while ((appoint_persons_edit = persons_reader.readLine()) != null) {
				persons_edit.add(appoint_persons_edit);
			}
			while ((appoint_location_edit = location_reader.readLine()) != null) {
				location_edit.add(appoint_location_edit);
			}
			date_reader.close();
			persons_reader.close();
			location_reader.close();
		} catch (IOException e) {}
		assertTrue(date_edit.elementAt(modify_index - 1).equals(newDate));
		assertTrue(persons_edit.elementAt(modify_index - 1).equals(newPersons));
		assertTrue(location_edit.elementAt(modify_index - 1).equals(newLocation));
	}

}