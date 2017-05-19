package UI2;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UserControlPad extends JPanel
{
	public JLabel ipLabel = new JLabel("IP", JLabel.LEFT);
	public JTextField ipInputted = new JTextField("localhost", 10);
	public JButton connectButton = new JButton("连接到服务器");
	public JButton createButton = new JButton("创建游戏");
	public JButton joinButton = new JButton("加入游戏");
	public JButton cancelButton = new JButton("放弃游戏");
	public JButton exitButton = new JButton("退出程序");
	public JButton button[]=new JButton [5];
	public  JPanel panel1=new JPanel();
	
	public UserControlPad()
	{

		this.setLayout(new GridLayout(6,1,4,4));
//		this.setBackground(Color.red);
		panel1.setLayout(new FlowLayout());
		panel1.add(ipLabel);
		panel1.add(ipInputted);
	
		this.add(panel1);
		this.add(connectButton);
		this.add(createButton);
		this.add(joinButton);
		this.add(cancelButton);
		this.add(exitButton);

		


	}
}