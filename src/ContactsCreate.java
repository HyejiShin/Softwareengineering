import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import javax.swing.text.AttributeSet;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class ContactsCreate extends JFrame implements ActionListener{
   
   JPanel panel, ptitle, pcontacts, pbtnsave;
   JLabel lbtitle, lbname, lbphone, lbemail;
   JButton btnsave;
   JTextField tfname, tfphone, tfemail;
   
   public ContactsCreate() {
      setTitle("Contacts Create");
      setSize(450,200);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      panel = new JPanel();
      panel.setLayout(new BorderLayout());
      
      ptitle = new JPanel();
      ptitle.setLayout(new BoxLayout(ptitle, BoxLayout.Y_AXIS));
      
      lbtitle = new JLabel("연락처 추가");
      lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      ptitle.add(lbtitle);
    
      panel.add(ptitle, BorderLayout.PAGE_START);
      
      pcontacts = new JPanel(new GridLayout(3,2));
      lbname = new JLabel("이름");
      lbphone = new JLabel("전화번호");
      lbemail = new JLabel("이메일");
      tfname = new JTextField(30);      
      tfphone = new JTextField(30);      
      tfemail = new JTextField(30);
      pcontacts.add(lbname);
      pcontacts.add(tfname);
      pcontacts.add(lbphone);
      pcontacts.add(tfphone);
      pcontacts.add(lbemail);
      pcontacts.add(tfemail);
      panel.add(pcontacts,BorderLayout.CENTER);
      
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
      new ContactsCreate();
      
   }
   
   // 입출력 부분
   public void ContactsNameWrite(String data) {
      try {
         Writer name_create = new BufferedWriter(new FileWriter("contacts_name.txt",true));
         name_create.append(data);
         name_create.close();
      }catch(IOException ex) {
         System.out.println("오류 발생. 추가되지 않았습니다.");
      }
   }
   public void ContactsPhoneWrite(String data) {
	      try {
	         Writer phone_create = new BufferedWriter(new FileWriter("contacts_phone.txt",true));
	         phone_create.append(data);
	         phone_create.close();
	      }catch(IOException ex) {
	         System.out.println("오류 발생. 추가되지 않았습니다.");
	      }
	   }
   public void ContactsEmailWrite(String data) {
	      try {
	         Writer email_create = new BufferedWriter(new FileWriter("contacts_email.txt",true));
	         email_create.append(data);
	         email_create.close();
	      }catch(IOException ex) {
	         System.out.println("오류 발생. 추가되지 않았습니다.");
	      }
	   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      Object source = e.getSource();
      
      
      if(source == btnsave) {
         String str_name = tfname.getText()+"\r\n";
         String str_phone = tfphone.getText()+"\r\n";
         String str_email = tfemail.getText()+"\r\n";
         
         ContactsNameWrite(str_name);
         ContactsPhoneWrite(str_phone);
         ContactsEmailWrite(str_email);
         
         ContactsMain.dispose();
         new ContactsMain();
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