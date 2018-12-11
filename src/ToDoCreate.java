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

      pbtnsave = new JPanel();
      btnsave = new JButton("저장");
      btnsave.addActionListener(this);
      pbtnsave.add(btnsave);
      panel.add(pbtnsave, BorderLayout.PAGE_END);
      
      add(panel);
      setVisible(true);
      
   }
   
   public static void main(String[] args) {

      new ToDoCreate();
      
   }
   
   public void StartDateWrite(String data) {
      try {
         Writer start_date_create = new BufferedWriter(new FileWriter("todo_start_date.txt",true));
         start_date_create.append(data);
         start_date_create.close();
      }catch(IOException ex) {
         System.out.println("오류 발생. 추가되지 않았습니다.");
      }
   }
   public void DueDateWrite(String data) {
	      try {
	         Writer due_date_create = new BufferedWriter(new FileWriter("todo_due_date.txt",true));
	         due_date_create.append(data);
	         due_date_create.close();
	      }catch(IOException ex) {
	         System.out.println("오류 발생. 추가되지 않았습니다.");
	      }
	   }
   public void DescriptionWrite(String data) {
	      try {
	         Writer description_create = new BufferedWriter(new FileWriter("todo_description.txt",true));
	         description_create.append(data);
	         description_create.close();
	      }catch(IOException ex) {
	         System.out.println("오류 발생. 추가되지 않았습니다.");
	      }
	   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
     
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

}