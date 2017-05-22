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

// 五子棋客户端
public class MainClient extends JFrame implements ActionListener, KeyListener
{
	// 客户端套接口
	Socket clientSocket;
	// 数据输入流
	DataInputStream inputStream;
	// 数据输出流
	DataOutputStream outputStream;
	// 用户名
	String chessClientName = null;
	// 主机地址
	String host = null;
	// 主机端口
	int port = 4331;
	// 是否在聊天
	boolean isOnChat = false;
    // 是否在下棋
	boolean isOnChess = false;
	// 游戏是否进行中
	boolean isGameConnected = false;
	// 是否为游戏创建者
	boolean isCreator = false; 
	// 是否为游戏加入者
	boolean isParticipant = false;
	// 用户列表区
	UserListPad userListPad = new UserListPad();
	// 用户聊天区
	UserChatPad userChatPad = new UserChatPad();
	// 用户操作区
	UserControlPad userControlPad = new UserControlPad();
	// 用户输入区
	UserInputPad userInputPad = new UserInputPad();
	// 下棋区
	public ChessBoard2 jpanel=new ChessBoard2();
	// 面板区
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
				{ // 聊天中
					try
					{  // 关闭客户端套接口
						clientSocket.close();
					}
					catch (Exception ed){}
				}
				if (isOnChess || isGameConnected)
				{ // 下棋中
					try
					{   // 关闭下棋端口
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

	// 按指定的IP地址和端口连接到服务器
	public boolean connectToServer(String serverIP, int serverPort) throws Exception
	{
		try
		{
			// 创建客户端套接口
			clientSocket = new Socket(serverIP, serverPort);
			// 创建输入流
			inputStream = new DataInputStream(clientSocket.getInputStream());
			// 创建输出流
			outputStream = new DataOutputStream(clientSocket.getOutputStream());
			// 创建客户端线程
			ClientThread clientthread = new ClientThread(this);
			// 启动线程，等待聊天信息
			clientthread.start();
			isOnChat = true;
			return true;
		}
		catch (IOException ex)
		{
			this.userControlPad.tipsField.setText("不能连接!\n");
		}
		return false;
	}

	// 客户端事件处理
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == userControlPad.connectButton)
		{ // 连接到主机按钮单击事件
			host = jpanel.host = userControlPad.ipInputted.getText(); // 取得主机地址
			try
			{
				if (connectToServer(host, port))
				{   // 成功连接到主机时，设置客户端相应的界面状态
					userChatPad.chatTextArea.setText("");
					userControlPad.connectButton.setEnabled(false);
					userControlPad.createButton.setEnabled(true);
					userControlPad.joinButton.setEnabled(true);
					this.userControlPad.tipsField.setText("连接成功，请等待!");
				}
			}
			catch (Exception ei)
			{
				this.userControlPad.tipsField.setText("不能连接!\n");
			}
		}
		if (e.getSource() == userControlPad.exitButton)
		{ // 离开游戏按钮单击事件
			if (isOnChat)
			{  // 若用户处于聊天状态中
				try
				{ // 关闭客户端套接口
					clientSocket.close();
				}
				catch (Exception ed){}
			}
			if (isOnChess || isGameConnected)
			{ // 若用户处于游戏状态中
				try
				{ // 关闭游戏端口
					jpanel.chessSocket.close();
				}
				catch (Exception ee){}
			}
			System.exit(0);
		}
		if (e.getSource() == userControlPad.joinButton)
		{ // 加入游戏按钮单击事件
			String selectedUser = (String)userListPad.userList.getSelectedItem(); // 取得要加入的游戏
			if (selectedUser == null || selectedUser.startsWith("[inchess]") ||
					selectedUser.equals(chessClientName))
			{ // 若未选中要加入的用户，或选中的用户已经在游戏，则给出提示信息
				this.userControlPad.tipsField.setText("必须选择一个用户!");
			}
			else
			{ // 执行加入游戏的操作
				try
				{
					if (!isGameConnected)
					{ // 若游戏套接口未连接
						if (jpanel.connectServer(jpanel.host, jpanel.port))
						{ // 若连接到主机成功
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
					{ // 若游戏端口连接中
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
					this.userControlPad.tipsField.setText("不能连接: \n" + ee);
				}
			}
		}
		if (e.getSource() == userControlPad.createButton)
		{ // 创建游戏按钮单击事件
			try
			{
				if (!isGameConnected)
				{ // 若游戏端口未连接
					if (jpanel.connectServer(jpanel.host, jpanel.port))
					{ // 若连接到主机成功
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
				{ // 若游戏端口连接中
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
				this.userControlPad.tipsField.setText("不能连接: \n"
						+ ec);
			}
		}
		if (e.getSource() == userControlPad.cancelButton)
		{ // 退出游戏按钮单击事件
			if (isOnChess)
			{ // 游戏中
				jpanel.firThread.sendMessage("/giveup " + chessClientName);
//				jpanel.setVicStatus(-1 * jpanel.chessColor);
				userControlPad.createButton.setEnabled(true);
				userControlPad.joinButton.setEnabled(true);
				userControlPad.cancelButton.setEnabled(false);
				this.userControlPad.tipsField.setText("请创建或加入游戏!");
			}
			if (!isOnChess)
			{ // 非游戏中
				userControlPad.createButton.setEnabled(true);
				userControlPad.joinButton.setEnabled(true);
				userControlPad.cancelButton.setEnabled(false);
				this.userControlPad.tipsField.setText("请创建或加入游戏!");
			}
			isParticipant = isCreator = false;
		}
	}

	public void keyPressed(KeyEvent e)
	{
		TextField inputwords = (TextField) e.getSource();
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
		{ // 处理回车按键事件
			if (userInputPad.userChoice.getSelectedItem().equals("所有用户"))
			{ // 给所有人发信息
				try
				{
					// 发送信息
					outputStream.writeUTF(inputwords.getText());
					inputwords.setText("");
				}
				catch (Exception ea)
				{
					this.userControlPad.tipsField.setText("不能连接到服务器!\n");
					userListPad.userList.removeAll();
					userInputPad.userChoice.removeAll();
					inputwords.setText("");
					userControlPad.connectButton.setEnabled(true);
				}
			}
			else
			{ // 给指定人发信息
				try
				{
					outputStream.writeUTF("/" + userInputPad.userChoice.getSelectedItem()
							+ " " + inputwords.getText());
					inputwords.setText("");
				}
				catch (Exception ea)
				{
					this.userControlPad.tipsField.setText("不能连接到服务器!\n");
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
