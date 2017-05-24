package Client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.JFrame;

import Network.ChessBoard2;
import UI2.UserChatPad;
import UI2.UserControlPad;
import UI2.UserInputPad;
import UI2.UserListPad;

//chess client
public class MainClient extends JFrame implements ActionListener, KeyListener
{
	// client socket
	Socket clientSocket;
	// data input stream
	DataInputStream inputStream;
	// data output stream
	DataOutputStream outputStream;
	// name of user
	String chessClientName = null;
	// ip of host
	String host = null;
	// host port
	int port = 4331;
	//  whether chatting
	boolean isOnChat = false;
    // whether move
	boolean isOnChess = false;
	// whether the game is going
	boolean isGameConnected = false;
	// whether it is the creator of the game
	boolean isCreator = false; 

	boolean isParticipant = false;
	//  the area of user list
	UserListPad userListPad = new UserListPad();
	// the area of user chat
	UserChatPad userChatPad = new UserChatPad();
	// the area of user operation
	UserControlPad userControlPad = new UserControlPad();
	// the area of input
	UserInputPad userInputPad = new UserInputPad();
	//move
	public ChessBoard2 jpanel=new ChessBoard2();
	// board
	Panel southPanel = new Panel();
	Panel northPanel = new Panel();
	Panel centerPanel = new Panel();
	Panel eastPanel = new Panel();

	// create the frame
	public MainClient()
	{
		super("Chess Client");
		setLayout(new BorderLayout());
		host = userControlPad.ipInputted.getText();
		eastPanel.setLayout(new BorderLayout());
		eastPanel.add(userListPad, BorderLayout.NORTH);
//		eastPanel.add(userChatPad, BorderLayout.CENTER);
		eastPanel.add(userControlPad, BorderLayout.CENTER);
//		eastPanel.setBackground(Color.LIGHT_GRAY);
		userInputPad.contentInputted.addKeyListener(this);
//		System.out.println(userControlPad.ipInputted.getText());
		jpanel.host = userControlPad.ipInputted.getText();
		
		centerPanel.add(jpanel, BorderLayout.CENTER);
//		centerPanel.add(userInputPad, BorderLayout.SOUTH);
//		centerPanel.setBackground(Color.LIGHT_GRAY);
		userControlPad.connectButton.addActionListener(this);
		userControlPad.createButton.addActionListener(this);
		userControlPad.joinButton.addActionListener(this);
		userControlPad.cancelButton.addActionListener(this);
		userControlPad.exitButton.addActionListener(this);
		userControlPad.createButton.setEnabled(false);
		userControlPad.joinButton.setEnabled(false);
		userControlPad.cancelButton.setEnabled(false);
		
//		southPanel.add(userControlPad, BorderLayout.CENTER);
//		southPanel.setBackground(Color.LIGHT_GRAY);

		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				if (isOnChat)
				{ // chatting
					try
					{  // close port
						clientSocket.close();
					}
					catch (Exception ed){}
				}
				if (isOnChess || isGameConnected)
				{ 
					try
					{   // close port
						jpanel.chessSocket.close();
					}
					catch (Exception ee){}
				}
				System.exit(0);
			}
		});

		add(eastPanel, BorderLayout.EAST);
		add(centerPanel, BorderLayout.CENTER);
//		add(southPanel, BorderLayout.SOUTH);
		repaint();

		this.setTitle("INTERNETIONAL CHESS");
		//acquire the size of screen.
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		this.setBounds((width - 900) / 2,
	            (height - 780) / 2, 900, 780);
		
		jpanel=new ChessBoard2();
		this.add(jpanel);
		this.setVisible(true);
		// fix the size first
		this.setResizable(false);
		//System out
		this.validate();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	public boolean connectToServer(String serverIP, int serverPort) throws Exception
	{
		try
		{
			// create client socket
			clientSocket = new Socket(serverIP, serverPort);
			// create input stream
			inputStream = new DataInputStream(clientSocket.getInputStream());
			// create output stream
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			// create the thread of the client
			ClientThread clientthread = new ClientThread(this);
			// start client
			clientthread.start();
			isOnChat = true;
			return true;
		}
		catch (IOException ex)
		{
			this.userControlPad.tipsField.setText("Cannot connect!\n");
		}
		return false;
	}

	// process the client event
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == userControlPad.connectButton)
		{ 
			host = jpanel.host = userControlPad.ipInputted.getText(); //get the host ip
			try
			{
				if (connectToServer(host, port))
				{   
					userChatPad.chatTextArea.setText("");
					userControlPad.connectButton.setEnabled(false);
					userControlPad.createButton.setEnabled(true);
					userControlPad.joinButton.setEnabled(true);
					this.userControlPad.tipsField.setText("connet well!");
				}
			}
			catch (Exception ei)
			{
				this.userControlPad.tipsField.setText("Cannot Connect!\n");
			}
		}
		if (e.getSource() == userControlPad.exitButton)
		{ 
			if (isOnChat)
			{  // if chatting
				try
				{ // close client port
					clientSocket.close();
				}
				catch (Exception ed){}
			}
			if (isOnChess || isGameConnected)
			{ // if the game is going
				try
				{ // close the game port
					jpanel.chessSocket.close();
				}
				catch (Exception ee){}
			}
			System.exit(0);
		}
		if (e.getSource() == userControlPad.joinButton)
		{ //join game event
			String selectedUser = (String)userListPad.userList.getSelectedItem(); // get the game
			if (selectedUser == null || selectedUser.startsWith("[inchess]") ||
					selectedUser.equals(chessClientName))
			{ 
				this.userControlPad.tipsField.setText("choose a player!");
			}
			else
			{ 
				try
				{
					if (!isGameConnected)
					{ // if the socket is not connected
						if (jpanel.connectServer(jpanel.host, jpanel.port))
						{ //if connect to host successfully
							isGameConnected = true;
							isOnChess = true;
							isParticipant = true;
							userControlPad.createButton.setEnabled(false);
							userControlPad.joinButton.setEnabled(false);
							userControlPad.cancelButton.setEnabled(true);
							jpanel.firThread.sendMessage("/joingame "
									+ (String)userListPad.userList.getSelectedItem() + " "
									+ chessClientName);
							System.out.println("connected2");
						}
					}
					else
					{ 
						isOnChess = true;
						isParticipant = true;
						userControlPad.createButton.setEnabled(false);
						userControlPad.joinButton.setEnabled(false);
						userControlPad.cancelButton.setEnabled(true);
						jpanel.firThread.sendMessage("/joingame "
								+ (String)userListPad.userList.getSelectedItem() + " "
								+ chessClientName);
					}
				}
				catch (Exception ee)
				{
					isGameConnected = false;
					isOnChess = false;
					isParticipant = false;
					userControlPad.createButton.setEnabled(true);
					userControlPad.joinButton.setEnabled(true);
					userControlPad.cancelButton.setEnabled(false);
					this.userControlPad.tipsField.setText("can't connect: \n" + ee);
				}
			}
		}
		if (e.getSource() == userControlPad.createButton)
		{ 
			try
			{
				if (!isGameConnected)
				{ 
					if (jpanel.connectServer(jpanel.host, jpanel.port))
					{ // if connect successfully
						isGameConnected = true;
						isOnChess = true;
						isCreator = true;
						userControlPad.createButton.setEnabled(false);
						userControlPad.joinButton.setEnabled(false);
						userControlPad.cancelButton.setEnabled(true);
						jpanel.firThread.sendMessage("/creatgame "
								+ "[inchess]" + chessClientName);
					}
				}
				else
				{ // if the game is connecting
					isOnChess = true;
					isCreator = true;
					userControlPad.createButton.setEnabled(false);
					userControlPad.joinButton.setEnabled(false);
					userControlPad.cancelButton.setEnabled(true);
					jpanel.firThread.sendMessage("/creatgame "
							+ "[inchess]" + chessClientName);
				}
			}
			catch (Exception ec)
			{
				isGameConnected = false;
				isOnChess = false;
				isCreator = false;
				userControlPad.createButton.setEnabled(true);
				userControlPad.joinButton.setEnabled(true);
				userControlPad.cancelButton.setEnabled(false);
				ec.printStackTrace();
				this.userControlPad.tipsField.setText("can't connect: \n"
						+ ec);
			}
		}
		if (e.getSource() == userControlPad.cancelButton)
		{ 
			if (isOnChess)
			{ // gaming
				jpanel.firThread.sendMessage("/giveup " + chessClientName);
//				jpanel.setVicStatus(-1 * jpanel.chessColor);
				userControlPad.createButton.setEnabled(true);
				userControlPad.joinButton.setEnabled(true);
				userControlPad.cancelButton.setEnabled(false);
				this.userControlPad.tipsField.setText("create or join!");
			}
			if (!isOnChess)
			{ // not gaming
				userControlPad.createButton.setEnabled(true);
				userControlPad.joinButton.setEnabled(true);
				userControlPad.cancelButton.setEnabled(false);
				this.userControlPad.tipsField.setText("create or gaming!");
			}
			isParticipant = isCreator = false;
		}
	}

	public void keyPressed(KeyEvent e)
	{
		TextField inputwords = (TextField) e.getSource();
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{ 
			if (userInputPad.userChoice.getSelectedItem().equals("all users"))
			{ 
				try
				{
					// send message
					outputStream.writeUTF(inputwords.getText());
					inputwords.setText("");
				}
				catch (Exception ea)
				{
					this.userControlPad.tipsField.setText("can't connect to server!\n");
					userListPad.userList.removeAll();
					userInputPad.userChoice.removeAll();
					inputwords.setText("");
					userControlPad.connectButton.setEnabled(true);
				}
			}
			else
			{ 
				try
				{
					outputStream.writeUTF("/" + userInputPad.userChoice.getSelectedItem()
							+ " " + inputwords.getText());
					inputwords.setText("");
				}
				catch (Exception ea)
				{
					this.userControlPad.tipsField.setText("can't connect to server!\n");
					userListPad.userList.removeAll();
					userInputPad.userChoice.removeAll();
					inputwords.setText("");
					userControlPad.connectButton.setEnabled(true);
				}
			}
		}
	}

	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	
	public static void main(String args[])
	{
		MainClient chessClient = new MainClient();
	}
}
