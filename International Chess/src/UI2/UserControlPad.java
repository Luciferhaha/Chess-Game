package UI2;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class UserControlPad extends JPanel
{
	public JLabel ipLabel = new JLabel("IP", JLabel.LEFT);
	public JTextField ipInputted = new JTextField("localhost", 10);

	public JTextArea tipsField=new JTextArea("", 10, 10);
	public JButton connectButton = new JButton("Connect to the server");
	public JButton createButton = new JButton("New Game");
	public JButton joinButton = new JButton("Join Game");
	public JButton cancelButton = new JButton("Aband Game");
	public JButton exitButton = new JButton("Exit");

	public JButton button[]=new JButton [5];
	public  JPanel panel1=new JPanel();
	
	public UserControlPad()
	{

		this.setLayout(new GridLayout(7,1,5,5));
//		this.setBackground(Color.red);
		panel1.setLayout(new FlowLayout());
		panel1.add(ipLabel);
		panel1.add(ipInputted);
		this.add(panel1);
		this.add(tipsField);
		this.add(connectButton);
		this.add(createButton);
		this.add(joinButton);
		this.add(cancelButton);
		this.add(exitButton);

		


	}
}