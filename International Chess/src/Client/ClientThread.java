package Client;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

public class ClientThread extends Thread
{
	public MainClient firClient;

	public ClientThread(MainClient firClient)
	{
		this.firClient = firClient;
	}

	public void dealWithMsg(String msgReceived)
	{
		if (msgReceived.startsWith("/userlist "))
		{ //  get the information of the user list
			StringTokenizer userToken = new StringTokenizer(msgReceived, " ");
			int userNumber = 0;
			// clear the user list
			firClient.userListPad.userList.removeAll();
			
			firClient.userInputPad.userChoice.removeAll();
			
			firClient.userInputPad.userChoice.addItem("all users");
			while (userToken.hasMoreTokens())
			{ // the user is here
				String user = (String) userToken.nextToken(" "); //get the user information
				if (userNumber > 0 && !user.startsWith("[inchess]"))
				{ // the information of user has time
					firClient.userListPad.userList.add(user);// add the user information to the list
					firClient.userInputPad.userChoice.addItem(user); 
				}
				userNumber++;
			}
			firClient.userInputPad.userChoice.setSelectedIndex(0);
		}
		else if (msgReceived.startsWith("/yourname "))
		{ //  get the information is the name of the user
			firClient.chessClientName = msgReceived.substring(10); // get the user name
			firClient.setTitle("Chess Game" + "Username:"
					+ firClient.chessClientName); 
		}
		else if (msgReceived.equals("/reject"))
		{ // get the message is that refuse
			try
			{
				firClient.userControlPad.tipsField.setText("can't join the game!");
				firClient.userControlPad.cancelButton.setEnabled(false);
				firClient.userControlPad.joinButton.setEnabled(true);
				firClient.userControlPad.createButton.setEnabled(true);
			}
			catch (Exception ef)
			{
				firClient.userChatPad.chatTextArea
						.setText("Cannot close!");
			}
			firClient.userControlPad.joinButton.setEnabled(true);
		}
		else if (msgReceived.startsWith("/peer "))
		{ 
			firClient.jpanel.chessPeerName = msgReceived.substring(6);
			if (firClient.isCreator)
			{ //if user create the game
				firClient.jpanel.chessPlayClick=2;// let white move firstly
//				firClient.jpanel.isMouseEnabled = true;
				firClient.jpanel.whoismaster="Master";
				firClient.userControlPad.tipsField.setText("white...");
			}
			else if (firClient.isParticipant)
			{ // if user is the joined player
				firClient.jpanel.chessPlayClick = 1; // let black move later
				firClient.jpanel.whoismaster="Guest";
				firClient.userControlPad.tipsField.setText("game join wait other.");
			}
		}
		else if (msgReceived.equals("/youwin"))
		{ // get the message is that victory
			firClient.isOnChess = false;
//			firClient.jpanel.setVicStatus(firClient.jpanel.chessColor);
			firClient.userControlPad.tipsField.setText("rival exit");
//			firClient.jpanel.isMouseEnabled = false;
		}
		else if (msgReceived.equals("/OK"))
		{ // get the message is that creating game successfully
			firClient.userControlPad.tipsField.setText("game created,waiting rivail");
		}

		else if (msgReceived.equals("/error"))
		{ // get the wrong message
			firClient.userChatPad.chatTextArea.append("error,exit.\n");
		}
		else
		{
			firClient.userChatPad.chatTextArea.append(msgReceived + "\n");
			firClient.userChatPad.chatTextArea.setCaretPosition(
			firClient.userChatPad.chatTextArea.getText().length());
		}
	}

	public void run()
	{
		String message = "";
		try
		{
			while (true)
			{
			
				message = firClient.inputStream.readUTF();
				dealWithMsg(message);
			}
		}
		catch (IOException es){}
	}
}
