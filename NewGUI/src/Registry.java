
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Registry implements ActionListener {

	JFrame mainJFrame;
	Container con;
	JLabel labName,labPass,labRPass,labTitle,labEmpty1,labEmpty,labSex,labAge,labClass;
	JTextField txtName,txtID,txtAge;
	JPasswordField txtPass,txtRPass;
	JButton resetBtn,regBtn,cancel;
	JRadioButton mRadio,fRadio;
	ButtonGroup sexBtnGroup;
	static final String msg1[]={"153721","153722","I do not want to tell you"};
	JComboBox Jcombobox1;
	
public Registry(){
	mainJFrame=new JFrame("Register");
	con=mainJFrame.getContentPane();
	con.setLayout(new FlowLayout());
	
	labTitle=new JLabel("<html><body><h1>User register     </h1> </body>  </html> ");
	con.add(labTitle);
	con.add(Box.createHorizontalStrut(30000));//用来换行
	
	labName=new JLabel("     Choose a name:");
	txtName=new JTextField();
	txtName.setColumns(20);
	con.add(labName);
	con.add(txtName);
	con.add(Box.createHorizontalStrut(30000));//用来换行
	
	labPass=new JLabel("      New password:");
	txtPass=new JPasswordField();
	txtPass.setColumns(20);
	con.add(labPass);
	con.add(txtPass);
	con.add(Box.createHorizontalStrut(30000));//用来换行
	
	labRPass=new JLabel("Confirm password:");
	txtRPass=new JPasswordField();
	txtRPass.setColumns(20);
	con.add(labRPass);
	con.add(txtRPass);
	con.add(Box.createHorizontalStrut(30000));//用来换行
	
	 
	labSex=new JLabel("Gender:");// 这里用两个按钮来处理
	mRadio=new JRadioButton("I am a boy",true);
	mRadio.addActionListener(this);
	fRadio=new JRadioButton("I am a lady",false);
	fRadio.addActionListener(this);
	sexBtnGroup=new ButtonGroup();
	sexBtnGroup.add(mRadio);
	sexBtnGroup.add(fRadio);
	con.add(labSex);
	con.add(mRadio);
	con.add(fRadio);
	con.add(Box.createHorizontalStrut(30000));//用来换行
	
	labAge=new JLabel("Age:");
	txtAge=new JTextField();
	txtAge.setColumns(5);
	con.add(labAge);
	con.add(txtAge);
	con.add(Box.createHorizontalStrut(30000));//用来换行
	
	labClass=new JLabel("Your class:");//这里使用下拉选项框来显示选项
	Jcombobox1=new JComboBox(msg1);
	//Jcombobox1.addItemListener(this);
	con.add(labClass);
	con.add(Jcombobox1);
	con.add(Box.createHorizontalStrut(30000));//用来换行
	
	regBtn=new JButton("Register");
	regBtn.addActionListener(this); 
	con.add(regBtn);
	
	resetBtn=new JButton("Reset");
	resetBtn.addActionListener(this);
	con.add(resetBtn);
	
	cancel=new JButton("Cancel");
	cancel.addActionListener(this);
	con.add(cancel);
	
	mainJFrame.setSize(400,400);
	mainJFrame.setVisible(true);
	mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public void actionPerformed(ActionEvent e){
	if(e.getSource()==regBtn){
		
	}
	if(e.getSource()==resetBtn){
		txtName.setText(null);
		txtPass.setText(null);
		txtRPass.setText(null);
		mRadio.setSelected(true);
		txtAge.setText(null);
		Jcombobox1.setSelectedIndex(0);
		}
	if(e.getSource()==cancel){
		mainJFrame.setVisible(false);
		Login login=new Login();
		login.mainJFrame.setVisible(true);
	}
}
public static void main(String[] args){
	new Registry();
}
}

