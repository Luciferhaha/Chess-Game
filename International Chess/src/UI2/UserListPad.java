package UI2;

import java.awt.BorderLayout;
import java.awt.List;
import java.awt.Panel;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

public class UserListPad extends Panel
{
	public List userList = new List(10);
	
	public UserListPad()
	{
		setLayout(null);
		for (int i = 0; i < 10; i++)
		{
			userList.add(i + "." + "no user");
		}
		userList.setSize(185, 200);
		this.add(userList);
		this.setBounds(185, 0, 185, 200);
		
		//		add(userList, BorderLayout.EAST);
	}
}
