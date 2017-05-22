package UI2;

import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.TextField;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

<<<<<<< HEAD
//鐢ㄦ埛杈撳叆鍖�
=======

>>>>>>> origin/master
public class UserInputPad extends JPanel
{
	public JTextField contentInputted = new JTextField("", 26);
	public JComboBox userChoice = new JComboBox();

	public UserInputPad()
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		for (int i = 0; i < 50; i++)
		{
<<<<<<< HEAD
			userChoice.addItem(i + "." + "No User");
=======
			userChoice.addItem(i + "." + "no user");
>>>>>>> origin/master
		}
		userChoice.setSize(60, 24);
		add(userChoice);
		add(contentInputted);
	}
}
