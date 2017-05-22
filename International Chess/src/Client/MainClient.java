package Client;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Network.ChessBoard2;
import Server.Server;
import UI2.UserChatPad;
import UI2.UserControlPad;
import UI2.UserInputPad;
import UI2.UserListPad;


// chess client
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
	// host ip
	String host = null;
	// host port
	int port = 4331;
	// whether chating
	boolean isOnChat = false;
    // whether move
	boolean isOnChess = false;
	// whether the game is going
	boolean isGameConnected = false;
	// whether it is the creator of thr game
	boolean isCreator = false; 
	
	boolean isParticipant = false;
	// the area of user list
	UserListPad userListPad = new UserListPad();
	// the area of user chat
	UserChatPad userChatPad = new UserChatPad();
	// the area of user operation
	UserControlPad userControlPad = new UserControlPad();
	// the area of input
	UserInputPad userInputPad = new UserInputPad();
	// move
	public ChessBoard2 jpanel=new ChessBoard2();
	// board
>>>>>>> origin/master
	Panel southPanel = new Panel();
	Panel northPanel = new Panel();
	Panel centerPanel = new Panel();
	Panel eastPanel = new Panel();
	

	// create the frame
	public MainClient()
	{
		super("Chess Client");

		//new Background();
        //setVisible(true);
        
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
		
		String path0 = "icon.png";
        ImageIcon icon = new ImageIcon(path0);
        setIconImage(icon.getImage());
		
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
<<<<<<< HEAD
				{ // 鑱婂ぉ涓�
					try
					{  // 鍏抽棴瀹㈡埛绔鎺ュ彛
=======
				{ // chatting
					try
					{  // close client socket
>>>>>>> origin/master
						clientSocket.close();
					}
					catch (Exception ed){}
				}
				if (isOnChess || isGameConnected)
<<<<<<< HEAD
				{ // 涓嬫涓�
					try
					{   // 鍏抽棴涓嬫绔彛
=======
				{ //move
					try
					{   // close
>>>>>>> origin/master
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

<<<<<<< HEAD
	// 鎸夋寚瀹氱殑IP鍦板潃鍜岀鍙ｈ繛鎺ュ埌鏈嶅姟鍣�
=======
	// special ip and port to the server
>>>>>>> origin/master
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
			// create client thread
			ClientThread clientthread = new ClientThread(this);
			// start thread
>>>>>>> origin/master
			clientthread.start();
			isOnChat = true;
			return true;
		}
		catch (IOException ex)
		{
<<<<<<< HEAD
			this.userControlPad.tipsField.setText("Connection Disabled!\n");
=======
			this.userControlPad.tipsField.setText("can not connect!\n");
>>>>>>> origin/master
		}
		return false;
	}

<<<<<<< HEAD
	// 瀹㈡埛绔簨浠跺鐞�
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == userControlPad.connectButton)
		{ // 杩炴帴鍒颁富鏈烘寜閽崟鍑讳簨浠�
			host = jpanel.host = userControlPad.ipInputted.getText(); // 鍙栧緱涓绘満鍦板潃
			try
			{
				if (connectToServer(host, port))
				{   // 鎴愬姛杩炴帴鍒颁富鏈烘椂锛岃缃鎴风鐩稿簲鐨勭晫闈㈢姸鎬�
=======
	// process the event of client
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == userControlPad.connectButton)
		{ // connect the host
			host = jpanel.host = userControlPad.ipInputted.getText(); // get ip of host
			try
			{
				if (connectToServer(host, port))
				{   
>>>>>>> origin/master
					userChatPad.chatTextArea.setText("");
					userControlPad.connectButton.setEnabled(false);
					userControlPad.createButton.setEnabled(true);
					userControlPad.joinButton.setEnabled(true);
<<<<<<< HEAD
					this.userControlPad.tipsField.setText("Connect success, please wait.");
=======
					this.userControlPad.tipsField.setText("connect success!");
>>>>>>> origin/master
				}
			}
			catch (Exception ei)
			{
<<<<<<< HEAD
				this.userControlPad.tipsField.setText("Connection disabled!\n");
			}
		}
		if (e.getSource() == userControlPad.exitButton)
		{ // 绂诲紑娓告垙鎸夐挳鍗曞嚮浜嬩欢
			if (isOnChat)
			{  // 鑻ョ敤鎴峰浜庤亰澶╃姸鎬佷腑
				try
				{ // 鍏抽棴瀹㈡埛绔鎺ュ彛
=======
				this.userControlPad.tipsField.setText("can't connect!\n");
			}
		}
		if (e.getSource() == userControlPad.exitButton)
		{ // exit the game
			if (isOnChat)
			{  // chatting
				try
				{ // close client socket

					clientSocket.close();
				}
				catch (Exception ed){}
			}
			if (isOnChess || isGameConnected)

				try
				{ // close the port

					jpanel.chessSocket.close();
				}
				catch (Exception ee){}
			}
			System.exit(0);
		}
		if (e.getSource() == userControlPad.joinButton)

		{ 
			String selectedUser = (String)userListPad.userList.getSelectedItem(); // get the game that want to join
			if (selectedUser == null || selectedUser.startsWith("[inchess]") ||
					selectedUser.equals(chessClientName))
			{ 
				this.userControlPad.tipsField.setText("必须选择一个用户!");
			}
			else
			{ // join the game
				try
				{
					if (!isGameConnected)
					{ // if the socket can not connect
						if (jpanel.connectServer(jpanel.host, jpanel.port))
						{ // if connect the host succsessfully

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

					{ // if the port is connecting

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
		
					
		 // create button hit event
			try
			{
				if (!isGameConnected)
				{ 
					if (jpanel.connectServer(jpanel.host, jpanel.port))
					{ // if the game connect the host successfully

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

				{ // if the port is connecting

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
				this.userControlPad.tipsField.setText("Please create or join a game!");
			}
			if (!isOnChess)
			{ // 闈炴父鎴忎腑
				userControlPad.createButton.setEnabled(true);
				userControlPad.joinButton.setEnabled(true);
				userControlPad.cancelButton.setEnabled(false);
				this.userControlPad.tipsField.setText("Please create or join a game!");
				this.userControlPad.tipsField.setText("create or join !");
			}
			if (!isOnChess)
			{ // not gaming
				userControlPad.createButton.setEnabled(true);
				userControlPad.joinButton.setEnabled(true);
				userControlPad.cancelButton.setEnabled(false);
				this.userControlPad.tipsField.setText("create or join!");

			}
			isParticipant = isCreator = false;
		}
	}

	public void keyPressed(KeyEvent e)
	{
		TextField inputwords = (TextField) e.getSource();
		if (e.getKeyCode() == KeyEvent.VK_ENTER)

		{ 
			if (userInputPad.userChoice.getSelectedItem().equals("All Users"))
			{ 
				try
				{
					// send message

					outputStream.writeUTF(inputwords.getText());
					inputwords.setText("");
				}
				catch (Exception ea)
				{

					this.userControlPad.tipsField.setText("Cannot connect to the server.\n");

					userListPad.userList.removeAll();
					userInputPad.userChoice.removeAll();
					inputwords.setText("");
					userControlPad.connectButton.setEnabled(true);
				}
			}
			else
			{ // send message

				try
				{
					outputStream.writeUTF("/" + userInputPad.userChoice.getSelectedItem()
							+ " " + inputwords.getText());
					inputwords.setText("");
				}
				catch (Exception ea)
				{

					this.userControlPad.tipsField.setText("Cannot connect to the server.\n");

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
	public static void main(String[] args) {
		new MainClient();
	}

}
