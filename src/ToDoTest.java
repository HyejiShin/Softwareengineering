import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;

class ToDoTest {

   @Test
   void testToDoCreate() {
      int todo_cnt = 0;
      int start_date_cnt = 0;
      int due_date_cnt = 0;
      int description_cnt = 0;
      
      ToDoCreate todocreate = new ToDoCreate();
      
      Vector<String> start_date_test = new Vector<String>();
      Vector<String> due_date_test = new Vector<String>();
      Vector<String> description_test = new Vector<String>();
      
      String start_date = "2019/02/02";
      String due_date = "2019/03/01";
      String description = "토익";

      todocreate.StartDateWrite(start_date + "\r\n");
      todocreate.DueDateWrite(due_date + "\r\n");
      todocreate.DescriptionWrite(description + "\r\n");

      try {
         BufferedReader start_date_reader = new BufferedReader(new FileReader("todo_start_date.txt"));
         BufferedReader due_date_reader = new BufferedReader(new FileReader("todo_due_date.txt"));
         BufferedReader description_reader = new BufferedReader(new FileReader("todo_description.txt"));

         String todo_start_date_test = "";
         String todo_due_date_test = "";
         String todo_description_test = "";

         while ((todo_start_date_test = start_date_reader.readLine()) != null) {
            start_date_test.add(start_date_cnt, todo_start_date_test);
            start_date_cnt++;
            todo_cnt = start_date_cnt;
         }

         while ((todo_due_date_test = due_date_reader.readLine()) != null) {
            due_date_test.add(due_date_cnt, todo_due_date_test);
            due_date_cnt++;
         }

         while ((todo_description_test = description_reader.readLine()) != null) {
            description_test.add(description_cnt, todo_description_test);
            description_cnt++;
         }
         start_date_reader.close();
         due_date_reader.close();
         description_reader.close();
      } catch (IOException e) {
      }
      assertTrue(start_date_test.elementAt(start_date_cnt - 1).equals(start_date));
      assertTrue(due_date_test.elementAt(due_date_cnt - 1).equals(due_date));
      assertTrue(description_test.elementAt(description_cnt - 1).equals(description));
   }

   @Test
   void testToDoEdit() {
      int modify_index = 1;
      ToDoMain todomain = new ToDoMain();
      ToDoEdit todoedit = new ToDoEdit(modify_index);
      
      Vector<String> edit_start_date = new Vector<String>();
      Vector<String> edit_due_date = new Vector<String>();
      Vector<String> edit_description = new Vector<String>();
      
      String start_date = "2019/02/01";
      String due_date = "2019/03/01";
      String description = "토플";

      todoedit.StartDateEdit(start_date + "\r\n");
      todoedit.DueDateEdit(due_date + "\r\n");
      todoedit.DescriptionEdit(description + "\r\n");

      try {
         BufferedReader start_date_reader = new BufferedReader(new FileReader("todo_start_date.txt"));
         BufferedReader due_date_reader = new BufferedReader(new FileReader("todo_due_date.txt"));
         BufferedReader description_reader = new BufferedReader(new FileReader("todo_description.txt"));

         String todo_start_date_edit = "";
         String todo_due_date_edit = "";
         String todo_description_edit = "";

         while ((todo_start_date_edit = start_date_reader.readLine()) != null) {
            edit_start_date.add(todo_start_date_edit);
         }
         while ((todo_due_date_edit = due_date_reader.readLine()) != null) {
            edit_due_date.add(todo_due_date_edit);
         }
         while ((todo_description_edit = description_reader.readLine()) != null) {
            edit_description.add(todo_description_edit);
         }
         start_date_reader.close();
         due_date_reader.close();
         description_reader.close();
      }

      catch (IOException e) {
      }
      assertTrue(edit_start_date.elementAt(modify_index - 1).equals(start_date));
      assertTrue(edit_due_date.elementAt(modify_index - 1).equals(due_date));
      assertTrue(edit_description.elementAt(modify_index - 1).equals(description));
   }

}