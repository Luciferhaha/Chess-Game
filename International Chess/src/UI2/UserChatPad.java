package UI2;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.TextArea;

import javax.swing.JPanel;
import javax.swing.JTextArea;

//用户聊天面板
public class UserChatPad extends JPanel
{
	public JTextArea chatTextArea = new JTextArea("command area", 18, 20);
	public UserChatPad()
	{
		
//		setLayout(new BorderLayout());
		chatTextArea.setAutoscrolls(true);
		chatTextArea.setLineWrap(true);
//		add(chatTextArea, BorderLayout.WEST);
		setLayout(null);
		chatTextArea.setSize(185, 200);
		add(chatTextArea);
		this.setBounds(715, 200, 185, 200);
		
	}
}
