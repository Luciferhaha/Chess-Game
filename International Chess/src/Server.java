
import java.io.*;
import java.net.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.JButton;

// The class about the frame of the server
public class Server extends Frame implements ActionListener
{
	JButton clearMsgButton = new JButton("Clear the list");
	JButton serverStatusButton = new JButton("Server status");
	JButton closeServerButton = new JButton("Close server");
	Panel buttonPanel = new Panel();
	ServerMsgPanel serverMsgPanel = new ServerMsgPanel();
	ServerSocket serverSocket;
	Hashtable clientDataHash = new Hashtable(50); //Bind the client socket to the output stream
	Hashtable clientNameHash = new Hashtable(50); //Bind the client socket to the output stream
	Hashtable chessPeerHash = new Hashtable(50); // Bind the game creator and game joiner
	
	public Server()
	{
		super("The server of chess");
		setBackground(Color.LIGHT_GRAY);
		buttonPanel.setLayout(new FlowLayout());
		clearMsgButton.setSize(60, 25);
		buttonPanel.add(clearMsgButton);
		clearMsgButton.addActionListener(this);
		serverStatusButton.setSize(75, 25);
		buttonPanel.add(serverStatusButton);
		serverStatusButton.addActionListener(this);
		closeServerButton.setSize(75, 25);
		buttonPanel.add(closeServerButton);
		closeServerButton.addActionListener(this);
		add(serverMsgPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		pack();
		setVisible(true);
		setSize(400, 300);
		setResizable(false);
		validate();
		
		try
		{
			createServer(4331, serverMsgPanel);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// Create a server with the specified port and panel
	public void createServer(int port, ServerMsgPanel serverMsgPanel) throws IOException
	{
		Socket clientSocket; // socket of the client
		long clientAccessNumber = 1; // Number of clients connected to the host
		this.serverMsgPanel = serverMsgPanel; // Set the current host
		try
		{
			serverSocket = new ServerSocket(port);
			serverMsgPanel.msgTextArea.setText("The server started from : "
					+ InetAddress.getLocalHost() + ":" //djr
					+ serverSocket.getLocalPort() + "\n");
			while (true)
			{
				// Listen to the information of client socket
				clientSocket = serverSocket.accept();
				serverMsgPanel.msgTextArea.append("Connected user:" + clientSocket + "\n");
				// Create a client output stream
				DataOutputStream outputData = new DataOutputStream(clientSocket
						.getOutputStream());
				// Bind the client socket to the output stream
				clientDataHash.put(clientSocket, outputData);
				// Bind the client socket to the client name
				clientNameHash
						.put(clientSocket, ("new player" + clientAccessNumber++));
				// Create and run server threads
				ServerThread thread = new ServerThread(clientSocket,
						clientDataHash, clientNameHash, chessPeerHash, serverMsgPanel);
				thread.start();
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == clearMsgButton)
		{ // clear the information of the server
			serverMsgPanel.msgTextArea.setText("");
		}
		if (e.getSource() == serverStatusButton)
		{ // show the information of the server
			try
			{
				serverMsgPanel.msgTextArea.append("The information of server:"
						+ InetAddress.getLocalHost() + ":"
						+ serverSocket.getLocalPort() + "\n");
			}
			catch (Exception ee)
			{
				ee.printStackTrace();
			}
		}
		if (e.getSource() == closeServerButton)
		{ // close the server
			System.exit(0);
		}
	}

	public static void main(String args[])
	{
		Server firServer = new Server();
	}
}

