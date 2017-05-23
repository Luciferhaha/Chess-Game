import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import javax.swing.ImageIcon;  
import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JPanel;  


public class Register {
	private JFrame jf;
	private Container con;
	private JPanel userJPanel, passJPanel, spassJPanel, regJPanel, msgJPanel, emailJPanel,img;
	private JLabel userJLabel, passJLabel, spassJLabel, emailJLabel, titleJLabel;
	private JLabel msg;
	private JTextField userJtf;
	private JTextField emailJtf;
	private JPasswordField passJtf, spassJtf;
	private JButton reset, register;
	
	
	
	
	public void init() {
		
		
		
		
		jf = new JFrame("Register");
		con = jf.getContentPane();
		con.setLayout(new GridLayout(5, 1));
		titleJLabel=new JLabel("<html><body><h1> Welcome to Chess Game ! \n <br> </h1> </body>  </html>");
		
		
		userJLabel = new JLabel("User name：                      ");
		userJtf = new JTextField(10); 
		userJPanel = new JPanel();
		userJPanel.add(userJLabel);
		userJPanel.add(userJtf);
		
		passJLabel = new JLabel("  Pass word：                   ");
		passJtf = new JPasswordField(10);
		passJPanel = new JPanel();
		passJPanel.add(passJLabel);
		passJPanel.add(passJtf);
		
		emailJLabel = new JLabel("Email address：              ");
		emailJtf = new JTextField(10);
		emailJPanel = new JPanel();
		emailJPanel.add(emailJLabel);
		emailJPanel.add(emailJtf);
		
		spassJLabel = new JLabel("Confirm your password：");
		spassJtf = new JPasswordField(10);
		spassJPanel = new JPanel();
		spassJPanel.add(spassJLabel);
		spassJPanel.add(spassJtf);
		
		
		
		msg = new JLabel();
		msgJPanel = new JPanel();
		msgJPanel.add(msg);
		
		reset = new JButton("Clear all");
		reset.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
						userJtf.setText("");
						passJtf.setText("");
						spassJtf.setText("");
					}
			});
		register = new JButton("Register");
		register.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					String pass = new String(passJtf.getPassword());
					String spass= new String(spassJtf.getPassword());
					if(!pass.equals(spass)) {
						msg.setText("Your passwords do not match");
						return ;
					} 
					
					String userName = userJtf.getText();
					User user = new User();
					user.setUserName(userName);
					user.setPwd(pass);
					RegisterService service = new RegisterService();
					service.register(user);
					msg.setText("Register success");
					jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//这里有一个没解决的bug， 我无法关闭页面
					  Login login = new Login();
				        login.init(); 
				}
			});
		regJPanel = new JPanel();
		regJPanel.add(reset);
		regJPanel.add(register);
		
		
		
		con.add(titleJLabel);

		con.add(userJPanel);
		con.add(Box.createHorizontalStrut(30000));//用来换行
		con.add(passJPanel);
		con.add(Box.createHorizontalStrut(30000));//用来换行
		con.add(spassJPanel);
		con.add(spassJPanel);
		con.add(Box.createHorizontalStrut(30000));//用来换行
		con.add(emailJPanel);
		con.add(Box.createHorizontalStrut(30000));//用来换行
		
		con.add(msgJPanel);
		con.add(regJPanel);

		
		jf.setSize(600, 400);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
        Register ref = new Register();
        ref.init(); 
        
    }
	
}



