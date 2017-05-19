package LoginSystem;
import javax.swing.*;


import UI.MainJFrame;

import java.awt.*;
import java.awt.event.*;

public class Login implements ActionListener{

	JFrame mainJFrame;
	Container con;
	JLabel labName,labPass,labID,labTitle,labEmpty;
	JTextField txtName,txtID;
	JPasswordField txtPass;
	JButton login1,register,cancel;
	public int width ,height;

public Login(){
	 width = Toolkit.getDefaultToolkit().getScreenSize().width;
     height = Toolkit.getDefaultToolkit().getScreenSize().height;
	mainJFrame=new JFrame("User log in");
	con=mainJFrame.getContentPane();
	con.setLayout(new FlowLayout());
	labTitle=new JLabel("<html><body><h1> Welcome to Chess Game ! \n <br> </h1> </body>  </html>");
	
	labID=new JLabel("Email address:");
	txtID=new JTextField();
	txtID.setColumns(20);
	
	labName=new JLabel(" User name:     ");
	txtName=new JTextField();
	txtName.setColumns(20);
	
	labPass=new JLabel(" password:      ");
	txtPass=new JPasswordField();
	txtPass.setColumns(20);
	
	login1=new JButton("Log in");
	login1.addActionListener(this);
	
	register=new JButton("Register");
	register.addActionListener(this);
	
	cancel=new JButton("Cancel");
	cancel.addActionListener(this);
	
	
	con.add(labTitle);
	con.add(Box.createHorizontalStrut(30000));//��������
	
	con.add(labID);
	con.add(txtID);
	con.add(Box.createHorizontalStrut(30000));
	
	con.add(labName);
	con.add(txtName);
	con.add(Box.createHorizontalStrut(30000));
	
	con.add(labPass);
	con.add(txtPass);
	con.add(Box.createHorizontalStrut(30000));
	
	con.add(login1);
	con.add(register);
	con.add(cancel);
	
	mainJFrame.setSize(350, 350);
	mainJFrame.setBounds((width - 350) / 2,
            (height - 350) / 2, 350, 350);
	mainJFrame.setVisible(true);
	mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public void actionPerformed(ActionEvent e){
	if(e.getSource()==login1){
		new MainJFrame();
	}
	if(e.getSource()==register){
		mainJFrame.setVisible(false);
		Registry reg=new Registry();
		reg.mainJFrame.setVisible(true);
	}
	if(e.getSource()==cancel){
		txtID.setText(null);
		txtName.setText(null);
		txtPass.setText(null);
		mainJFrame.setVisible(false);
	}
}
public static void main(String[] args){
	new Login();
  }
}