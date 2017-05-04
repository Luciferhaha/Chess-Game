import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class ServerThread extends Thread
{
	Socket clientSocket; // Save the information of the client socket
	Hashtable clientDataHash; // Save the Hash corresponding to the output port of the client port
	Hashtable clientNameHash; // Save the client socket and client name corresponding to the Hash
	Hashtable chessPeerHash; // Save the game creator and the game participant corresponding to the Hash
	ServerMsgPanel serverMsgPanel;
	boolean isClientClosed = false;
	
	public ServerThread(Socket clientSocket, Hashtable clientDataHash,
			Hashtable clientNameHash, Hashtable chessPeerHash,
			ServerMsgPanel server)
	{
		this.clientSocket = clientSocket;
		this.clientDataHash = clientDataHash;
		this.clientNameHash = clientNameHash;
		this.chessPeerHash = chessPeerHash;
		this.serverMsgPanel = server;
	}

	public void dealWithMsg(String msgReceived)
	{
		String clientName;
		String peerName;
		if (msgReceived.startsWith("/"))
		{
			if (msgReceived.equals("/list"))
			{ // The received information is that update the user list
				Feedback(getUserList());
			}
			else if (msgReceived.startsWith("/creatgame [inchess]"))
			{ //The information received is that creating the game
				String gameCreaterName = msgReceived.substring(20); //Get the server name
				synchronized (clientNameHash)
				{ // Place the user port in the user list
					clientNameHash.put(clientSocket, msgReceived.substring(11));
				}
				synchronized (chessPeerHash)
				{ // Set the host to wait state
					chessPeerHash.put(gameCreaterName, "wait");
				}
				Feedback("/yourname " + clientNameHash.get(clientSocket));
				sendGamePeerMsg(gameCreaterName, "/OK");
				sendPublicMsg(getUserList());
			}
			else if (msgReceived.startsWith("/joingame "))
			{ // Receive the information for the time to join the game
				StringTokenizer userTokens = new StringTokenizer(msgReceived, " ");
				String userToken;
				String gameCreatorName;
				String gamePaticipantName;
				String[] playerNames = { "0", "0" };
				int nameIndex = 0;
				while (userTokens.hasMoreTokens())
				{
					userToken = (String) userTokens.nextToken(" ");
					if (nameIndex >= 1 && nameIndex <= 2)
					{
						playerNames[nameIndex - 1] = userToken; // get the player name
					}
					nameIndex++;
				}
				gameCreatorName = playerNames[0];
				gamePaticipantName = playerNames[1];
				if (chessPeerHash.containsKey(gameCreatorName)
						&& chessPeerHash.get(gameCreatorName).equals("wait"))
				{ // A game was created
					synchronized (clientNameHash)
					{ // Increase the correspondence between the socket and the name of the game participant
						clientNameHash.put(clientSocket,
								("[inchess]" + gamePaticipantName));
					}
					synchronized (chessPeerHash)
					{ // Increase or modify the correspondence between the game creator and the game participant's name
						chessPeerHash.put(gameCreatorName, gamePaticipantName);
					}
					sendPublicMsg(getUserList());
					// Send a message to the game joiner
					sendGamePeerMsg(gamePaticipantName,
							("/peer " + "[inchess]" + gameCreatorName));
					//Send the game to the game creator
					sendGamePeerMsg(gameCreatorName,
							("/peer " + "[inchess]" + gamePaticipantName));
				}
				else
				{ // If the game is not created, it will refuse to join the game
					sendGamePeerMsg(gamePaticipantName, "/reject");
					try
					{
						closeClient();
					}
					catch (Exception ez)
					{
						ez.printStackTrace();
					}
				}
			}
			else if (msgReceived.startsWith("/[inchess]"))
			{ // Receive the information for the game is playing
				int firstLocation = 0, lastLocation;
				lastLocation = msgReceived.indexOf(" ", 0);
				peerName = msgReceived.substring((firstLocation + 1), lastLocation);
				msgReceived = msgReceived.substring((lastLocation + 1));
				if (sendGamePeerMsg(peerName, msgReceived))
				{
					Feedback("/error");
				}
			}
			else if (msgReceived.startsWith("/giveup "))
			{ // Received the information for giving up the game
				String chessClientName = msgReceived.substring(8);
				if (chessPeerHash.containsKey(chessClientName)
						&& !((String) chessPeerHash.get(chessClientName))
								.equals("wait"))
				{ // Victory for the game to join, send victory information
					sendGamePeerMsg((String) chessPeerHash.get(chessClientName),
							"/youwin");
					synchronized (chessPeerHash)
					{ // Delete the user who quit the game
						chessPeerHash.remove(chessClientName);
					}
				}
				if (chessPeerHash.containsValue(chessClientName))
				{ //Victory for the game creator, send victory information
					sendGamePeerMsg((String) getHashKey(chessPeerHash,
							chessClientName), "/youwin");
					synchronized (chessPeerHash)
					{// Delete the user who quit the game
						chessPeerHash.remove((String) getHashKey(chessPeerHash,
								chessClientName));
					}
				}
			}
			else
			{ // When the information is received is that other information
				int lastLocation = msgReceived.indexOf(" ", 0);
				if (lastLocation == -1)
				{
					Feedback("Invalid command");
					return;
				}
			}
		}
		else
		{
			msgReceived = clientNameHash.get(clientSocket) + ">" + msgReceived;
			serverMsgPanel.msgTextArea.append(msgReceived + "\n");
			sendPublicMsg(msgReceived);
			serverMsgPanel.msgTextArea.setCaretPosition(serverMsgPanel.msgTextArea.getText()
					.length());
		}
	}

	// Send public information
	public void sendPublicMsg(String publicMsg)
	{
		synchronized (clientDataHash)
		{
			for (Enumeration enu = clientDataHash.elements(); enu
					.hasMoreElements();)
			{
				DataOutputStream outputData = (DataOutputStream) enu.nextElement();
				try
				{
					outputData.writeUTF(publicMsg);
				}
				catch (IOException es)
				{
					es.printStackTrace();
				}
			}
		}
	}

	// Send a message to a player in the specified game
	public boolean sendGamePeerMsg(String gamePeerTarget, String gamePeerMsg)
	{
		for (Enumeration enu = clientDataHash.keys(); enu.hasMoreElements();)
		{ // Traverse to get the user's socket in the game
			Socket userClient = (Socket) enu.nextElement();
			if (gamePeerTarget.equals((String) clientNameHash.get(userClient))
					&& !gamePeerTarget.equals((String) clientNameHash
							.get(clientSocket)))
			{ //Find the user who wants to send the message
				synchronized (clientDataHash)
				{
					//Create an output stream
					DataOutputStream peerOutData = (DataOutputStream) clientDataHash
							.get(userClient);
					try
					{
						//send message
						peerOutData.writeUTF(gamePeerMsg);
					}
					catch (IOException es)
					{
						es.printStackTrace();
					}
				}
				return false;
			}
		}
		return true;
	}

	// Send feedback to the person connected to the host
	public void Feedback(String feedBackMsg)
	{
		synchronized (clientDataHash)
		{
			DataOutputStream outputData = (DataOutputStream) clientDataHash
					.get(clientSocket);
			try
			{
				outputData.writeUTF(feedBackMsg);
			}
			catch (Exception eb)
			{
				eb.printStackTrace();
			}
		}
	}

	// Get the player list
	public String getUserList()
	{
		String userList = "/userlist";
		for (Enumeration enu = clientNameHash.elements(); enu.hasMoreElements();)
		{
			userList = userList + " " + (String) enu.nextElement();
		}
		return userList;
	}

	// Obtain the corresponding key from the Hashtable based on the value
	public Object getHashKey(Hashtable targetHash, Object hashValue)
	{
		Object hashKey;
		for (Enumeration enu = targetHash.keys(); enu.hasMoreElements();)
		{
			hashKey = (Object) enu.nextElement();
			if (hashValue.equals((Object) targetHash.get(hashKey)))
				return hashKey;
		}
		return null;
	}

	// The method that was executed when you were connected to the host
	public void sendInitMsg()
	{
		sendPublicMsg(getUserList());
		Feedback("/yourname " + (String) clientNameHash.get(clientSocket));
		Feedback("Client of the chess");
		Feedback("/list --update user list");
		Feedback("/<username> <talk> --chat");
		Feedback("Note: The command must be sent to all users");
	}

	public void closeClient()
	{
		serverMsgPanel.msgTextArea.append("The user is disconnected:" + clientSocket + "\n");
		synchronized (chessPeerHash)
		{ //If it is a game client host
			if (chessPeerHash.containsKey(clientNameHash.get(clientSocket)))
			{
				chessPeerHash.remove((String) clientNameHash.get(clientSocket));
			}
			if (chessPeerHash.containsValue(clientNameHash.get(clientSocket)))
			{
				chessPeerHash.put((String) getHashKey(chessPeerHash,
						(String) clientNameHash.get(clientSocket)),
						"tobeclosed");
			}
		}
		synchronized (clientDataHash)
		{ //Delete player data
			clientDataHash.remove(clientSocket);
		}
		synchronized (clientNameHash)
		{ // Delete player data
			clientNameHash.remove(clientSocket);
		}
		sendPublicMsg(getUserList());
		serverMsgPanel.statusLabel.setText("当前连接数:" + clientDataHash.size());
		try
		{
			clientSocket.close();
		}
		catch (IOException exx)
		{
			exx.printStackTrace();
		}
		isClientClosed = true;
	}

	public void run()
	{
		DataInputStream inputData;
		synchronized (clientDataHash)
		{
			serverMsgPanel.statusLabel.setText("The current number of connections:" + clientDataHash.size());
		}
		try
		{	// Waiting for information to connect to the host
			inputData = new DataInputStream(clientSocket.getInputStream());
			sendInitMsg();
			while (true)
			{
				String message = inputData.readUTF();
				dealWithMsg(message);
			}
		}
		catch (IOException esx){}
		finally
		{
			if (!isClientClosed)
			{
				closeClient();
			}
		}
	}
}
