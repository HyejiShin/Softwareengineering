import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.*;


public class ToDoCreate extends JFrame implements ActionListener{
   
   JPanel panel, ptitle, ptodolist, pbtnsave;
   JLabel lbtitle, lb_start_date, lb_due_date, lb_description;
   JButton btnsave;
   JTextField tf_start_date, tf_due_date, tf_description;
   
   public ToDoCreate() {
      setTitle("To Do List Create");
      setSize(450,200);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      panel = new JPanel();
      panel.setLayout(new BorderLayout());
      
      ptitle = new JPanel();
      ptitle.setLayout(new BoxLayout(ptitle, BoxLayout.Y_AXIS));
      
      lbtitle = new JLabel("할일 추가");
      lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      ptitle.add(lbtitle);
    
      panel.add(ptitle, BorderLayout.PAGE_START);
      
      ptodolist = new JPanel(new GridLayout(3,2));
      lb_start_date = new JLabel("생성날짜");
      lb_due_date = new JLabel("마감날짜");
      lb_description = new JLabel("설명");
      tf_start_date = new JTextField(30);      
      tf_due_date = new JTextField(30);      
      tf_description = new JTextField(30);
      ptodolist.add(lb_start_date);
      ptodolist.add(tf_start_date);
      ptodolist.add(lb_due_date);
      ptodolist.add(tf_due_date);
      ptodolist.add(lb_description);
      ptodolist.add(tf_description);
      panel.add(ptodolist,BorderLayout.CENTER);
      
      //plbcontacts = new JPanel(new GridLayout(3,1));
      /*
      plbcontacts = new JPanel();
      
      plbcontacts.setLayout(new BoxLayout(plbcontacts, BoxLayout.Y_AXIS));
      lbname = new JLabel("이름");
      lbphone = new JLabel("전화번호");
      lbemail = new JLabel("이메일");
      plbcontacts.add(lbname);
      plbcontacts.add(lbphone);
      plbcontacts.add(lbemail);
      panel.add(plbcontacts,BorderLayout.WEST);
      
      ptfcontacts = new JPanel();
      tfname = new JTextField(30);      
      tfphone = new JTextField(30);      
      tfemail = new JTextField(30);
      ptfcontacts.add(tfname);
      ptfcontacts.add(tfphone);
      ptfcontacts.add(tfemail);
      panel.add(ptfcontacts,BorderLayout.CENTER);
      */
      
      //tfmemo.setDocument(new JTextFieldLimit(20));
      
      
      pbtnsave = new JPanel();
      btnsave = new JButton("저장");
      btnsave.addActionListener(this);
      pbtnsave.add(btnsave);
      panel.add(pbtnsave, BorderLayout.PAGE_END);
      
      add(panel);
      setVisible(true);
      
   }
   
   public static void main(String[] args) {
      // TODO Auto-generated method stub
      new ToDoCreate();
      
   }
   
   // 입출력 부분
   public void StartDateWrite(String data) {
      try {
         Writer start_date_create = new BufferedWriter(new FileWriter("start_date.txt",true));
         start_date_create.append(data);
         start_date_create.close();
      }catch(IOException ex) {
         System.out.println("오류 발생. 추가되지 않았습니다.");
      }
   }
   public void DueDateWrite(String data) {
	      try {
	         Writer due_date_create = new BufferedWriter(new FileWriter("due_date.txt",true));
	         due_date_create.append(data);
	         due_date_create.close();
	      }catch(IOException ex) {
	         System.out.println("오류 발생. 추가되지 않았습니다.");
	      }
	   }
   public void DescriptionWrite(String data) {
	      try {
	         Writer description_create = new BufferedWriter(new FileWriter("description.txt",true));
	         description_create.append(data);
	         description_create.close();
	      }catch(IOException ex) {
	         System.out.println("오류 발생. 추가되지 않았습니다.");
	      }
	   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      Object source = e.getSource();
      
      
      if(source == btnsave) {
         String str_start_date = tf_start_date.getText()+"\r\n";
         String str_due_date = tf_due_date.getText()+"\r\n";
         String str_description = tf_description.getText()+"\r\n";
         
         StartDateWrite(str_start_date);
         DueDateWrite(str_due_date);
         DescriptionWrite(str_description);
         
         ToDoMain.dispose();
         new ToDoMain();
         this.dispose();
         
      }
      
   }

   /*
   class JTextFieldLimit extends PlainDocument {
       private int limit;
       JTextFieldLimit(int limit) {
         super();
         this.limit = limit;
       }

       JTextFieldLimit(int limit, boolean upper) {
         super();
         this.limit = limit;
       }

       public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
         if (str == null)
           return;

         if ((getLength() + str.length()) <= limit) {
           super.insertString(offset, str, attr);
         }
       }
     }
     */
}