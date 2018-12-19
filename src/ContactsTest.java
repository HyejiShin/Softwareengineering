import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

class ContactsTest {

	@Test
	void testContactsCreate() {
		int contacts_cnt = 0;
		int name_cnt = 0;
		int phone_cnt = 0;
		int email_cnt = 0;
		
		ContactsCreate contactscreate = new ContactsCreate();
		
		Vector<String> name_test = new Vector<String>();
		Vector<String> phone_test = new Vector<String>();
		Vector<String> email_test = new Vector<String>();
		
		String name = "Sooksook";
		String phone = "010-7777-7777";
		String email = "sookmyung@naver.com";

		contactscreate.ContactsNameWrite(name + "\r\n");
		contactscreate.ContactsPhoneWrite(phone + "\r\n");
		contactscreate.ContactsEmailWrite(email + "\r\n");

		try {
			BufferedReader name_reader = new BufferedReader(new FileReader("contacts_name.txt"));
			BufferedReader phone_reader = new BufferedReader(new FileReader("contacts_phone.txt"));
			BufferedReader email_reader = new BufferedReader(new FileReader("contacts_email.txt"));

			String contacts_name_test = "";
			String contacts_phone_test = "";
			String contacts_email_test = "";

			while ((contacts_name_test = name_reader.readLine()) != null) {
				name_test.add(name_cnt, contacts_name_test);
				name_cnt++;
				contacts_cnt = name_cnt;
			}

			while ((contacts_phone_test = phone_reader.readLine()) != null) {
				phone_test.add(phone_cnt, contacts_phone_test);
				phone_cnt++;
			}

			while ((contacts_email_test = email_reader.readLine()) != null) {
				email_test.add(email_cnt, contacts_email_test);
				email_cnt++;
			}
			name_reader.close();
			phone_reader.close();
			email_reader.close();
		} catch (IOException e) {
		}
		assertTrue(name_test.elementAt(name_cnt - 1).equals(name));
		assertTrue(phone_test.elementAt(phone_cnt - 1).equals(phone));
		assertTrue(email_test.elementAt(email_cnt - 1).equals(email));
	}

	@Test
	void testContactsEdit() {
		int modify_index = 1;
		ContactsMain contactsmain = new ContactsMain();
		ContactsEdit contactsedit = new ContactsEdit(modify_index);
		
		Vector<String> edit_name = new Vector<String>();
		Vector<String> edit_phone = new Vector<String>();
		Vector<String> edit_email = new Vector<String>();
		
		String name = "Sook";
		String phone = "010-5555-5555";
		String email = "sooksook@naver.com";

		contactsedit.ContactsNameEdit(name + "\r\n");
		contactsedit.ContactsPhoneEdit(phone + "\r\n");
		contactsedit.ContactsEmailEdit(email + "\r\n");

		try {
			BufferedReader name_reader = new BufferedReader(new FileReader("contacts_name.txt"));
			BufferedReader phone_reader = new BufferedReader(new FileReader("contacts_phone.txt"));
			BufferedReader email_reader = new BufferedReader(new FileReader("contacts_email.txt"));

			String contacts_name_edit = "";
			String contacts_phone_edit = "";
			String contacts_email_edit = "";

			while ((contacts_name_edit = name_reader.readLine()) != null) {
				edit_name.add(contacts_name_edit);
			}
			while ((contacts_phone_edit = phone_reader.readLine()) != null) {
				edit_phone.add(contacts_phone_edit);
			}
			while ((contacts_email_edit = email_reader.readLine()) != null) {
				edit_email.add(contacts_email_edit);
			}
			name_reader.close();
			phone_reader.close();
			email_reader.close();
		}

		catch (IOException e) {
		}
		assertTrue(edit_name.elementAt(modify_index - 1).equals(name));
		assertTrue(edit_phone.elementAt(modify_index - 1).equals(phone));
		assertTrue(edit_email.elementAt(modify_index - 1).equals(email));
	}

}
