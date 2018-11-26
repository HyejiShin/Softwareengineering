import java.awt.BorderLayout;
import java.awt.Component;
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
   
   JPanel panel, ptitle, ptfname, pbtnsave;
   JLabel lbtitle;
   JButton btnsave;
   JTextField tfname;
   
   public ContactsCreate() {
      setTitle("Contacts Create");
      setSize(500,300);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      
      panel = new JPanel();
      panel.setLayout(new BorderLayout());
      
      ptitle = new JPanel();
      ptitle.setLayout(new BoxLayout(ptitle, BoxLayout.Y_AXIS));
      
      lbtitle = new JLabel("연락처 추가");
      lbtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
      
      ptitle.add(lbtitle);
    
      panel.add(ptitle, BorderLayout.PAGE_START);
      
      ptfname = new JPanel();
      tfname = new JTextField(30);
      //tfmemo.setDocument(new JTextFieldLimit(20));
      ptfname.add(tfname);
      panel.add(ptfname,BorderLayout.CENTER);
      
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
   public void memoFileWrite(String data) {
      try {
         Writer name_create = new BufferedWriter(new FileWriter("contacts_name.txt",true));
         name_create.append(data);
         name_create.close();
      }catch(IOException ex) {
         System.out.println("오류 발생. 추가되지 않았습니다.");
      }
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      // TODO Auto-generated method stub
      Object source = e.getSource();
      
      
      if(source == btnsave) {
         String data = tfname.getText()+"\r\n";
         
         memoFileWrite(data);
         
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