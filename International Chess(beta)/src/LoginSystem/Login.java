package LoginSystem;
import java.awt.*;
import javax.swing.*;

import Server.Server;
import UI.MainJFrame;

import java.awt.event.*;
public class Login {
	private JFrame jf;
	private Container con;
	private JPanel userJPanel, passJPanel, regJPanel, emailJPanel,loginJPanel;
	private JLabel userJLabel, passJLabel, emailJLabel;
	
	private JTextField emailJtf;
	private JTextField userJtf;
	private JPasswordField passJtf;
	private JButton login, register;
	
	public void init() {
		jf = new JFrame("Login");
		con = jf.getContentPane();
		con.setLayout(new FlowLayout());
		 
		userJLabel = new JLabel("User name:     ");
		userJtf = new JTextField(10);
		userJPanel = new JPanel();
		userJPanel.add(userJLabel);
		userJPanel.add(userJtf);
		
		emailJLabel = new JLabel("Email address:");
		emailJtf = new JTextField(10);
		emailJPanel = new JPanel();
		emailJPanel.add(emailJLabel);
		emailJPanel.add(emailJtf);
		
		
		passJLabel = new JLabel(" Password:     ");
		passJtf = new JPasswordField(10);
		passJPanel = new JPanel();
		passJPanel.add(passJLabel);
		passJPanel.add(passJtf);
		
	
	
		login = new JButton("Log in ");
		login.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					String userName = userJtf.getText();
					String pwd = new String(passJtf.getPassword());
					User user = new User();
					user.setUserName(userName);
					user.setPwd(pwd);
					LoginService service = new LoginService();
					boolean b = service.login(user);
					if(b) {
						JOptionPane.showMessageDialog(jf, "Log in success!");
						new MainJFrame();
						
					} else {
						JOptionPane.showMessageDialog(jf, "Log in fail!");
					}
				}
			});
		
		
		register = new JButton("Register");
		register.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
			
			Register reg = new Register();
	       reg.init();
					}
		});
					
					
					
		
		
		
		loginJPanel = new JPanel();
		regJPanel = new JPanel();
		loginJPanel.add(register);
		regJPanel.add(login);//��ʾ
		
		con.add(userJPanel);
		con.add(Box.createHorizontalStrut(30000));//��������
		con.add(passJPanel);
		con.add(Box.createHorizontalStrut(30000));//��������
		con.add(emailJPanel);
		con.add(Box.createHorizontalStrut(30000));//��������
		con.add(regJPanel);
		con.add(Box.createHorizontalStrut(30000));//��������
		con.add(loginJPanel);
		
		
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		jf.setBounds((width - 400) / 2,
	            (height - 400) / 2, 400, 400);
		jf.setVisible(true);
	}
	public static void main(String[] args) {
        Login login = new Login();
        login.init(); 
        new Server();
    }
	
}