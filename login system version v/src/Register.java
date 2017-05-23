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
	private JButton reset, register, login;
	
	
	
	
	public void init() {
		
		
		
		
		jf = new JFrame("Register");
	
		
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();   
        jf.setSize(400,400);   
        Dimension   framesize   =   jf.getSize();   
        int   x   =   (int)screensize.getWidth()/2   -   (int)framesize.getWidth()/2;   
        int   y   =   (int)screensize.getHeight()/2   -   (int)framesize.getHeight()/2;   
        jf.setLocation(x,y);   
        jf.setVisible(true);   
		
    	con = jf.getContentPane();
    	con.setLayout(new FlowLayout());
		

		
		
		titleJLabel=new JLabel("<html><body><h1>                Welcome to Chess Game !              \n <br> </h2> </body>  </html>");
		
		
		userJLabel = new JLabel( "                           User name:                       ");
		userJtf = new JTextField(15); 
		userJPanel = new JPanel();
		userJPanel.add(userJLabel);
		userJPanel.add(userJtf);
		
		passJLabel = new JLabel("                           Pass word:                        ");
		passJtf = new JPasswordField(15); 
		passJPanel = new JPanel();
		passJPanel.add(passJLabel);
		passJPanel.add(passJtf);
		
		emailJLabel = new JLabel("                         Email address:                  ");
		emailJtf = new JTextField(15);
		emailJPanel = new JPanel();
		emailJPanel.add(emailJLabel);
		emailJPanel.add(emailJtf);
		
		spassJLabel = new JLabel("                Confirm your password:          ");
		spassJtf = new JPasswordField(15);
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
					
					
					
		login = new JButton("login");
		login.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			}
			});
		        	
			
					
					String userName = userJtf.getText();
					User user = new User();
					user.setUserName(userName);
					user.setPwd(pass);
					RegisterService service = new RegisterService();
					service.register(user);
					msg.setText("<html><body><h1> Register success \n <br> </h2> </body>  </html>");
					jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//这里有一个没解决的bug， 我无法关闭页面
					  Login login = new Login();
				        login.init(); 
				}
			});
		regJPanel = new JPanel();
		regJPanel.add(reset);
		regJPanel.add(register);

		
		con.add(titleJLabel);
		con.add(Box.createHorizontalStrut(30000));//用来换行
		con.add(userJPanel);
		con.add(Box.createHorizontalStrut(30000));//用来换行
		con.add(passJPanel);
		con.add(Box.createHorizontalStrut(30000));//用来换行
		con.add(spassJPanel);
		con.add(spassJPanel);
		con.add(Box.createHorizontalStrut(30000));//用来换行
		con.add(emailJPanel);
		con.add(Box.createHorizontalStrut(30000));//用来换行
		con.add(Box.createHorizontalStrut(30000));//用来换行
		con.add(msgJPanel);
		con.add(regJPanel);

		
		jf.setSize(500, 400);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
        Register ref = new Register();
        ref.init(); 
        
    }
	
}



