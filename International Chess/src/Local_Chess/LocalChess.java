package Local_Chess;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import UI.Modechoose;

public class LocalChess extends JFrame implements ActionListener{
	
	private  final static int gap=50;//the chessboard and frame
	private   final static int side=85;//frame of little square
	private  int row=8,column=8;
	private  ChessBoard jpanel;
	Container con;

	//tool
	private JToolBar jmain;
	private JButton anew;
	private JButton repent;
	private JButton showOpen;
	private JButton showSave;
	private JButton exit;
	//current information
	private JLabel text;
	private JMenuBar jBar;
	private JTextArea jArea;
	private JScrollPane jsp;
	//save the current operation
	public LocalChess() {
		// TODO Auto-generated constructor stub
	
		
		jmain = new JToolBar();
		text = new JLabel("       WElCOME");
		text.setToolTipText("note");
		anew = new JButton(" New Game "	);
		anew.setToolTipText("new again");
		exit = new JButton("Exit");
		exit.setToolTipText("exist");
		
		repent = new JButton("Undo ");
		repent.setToolTipText("return last position");				
		showOpen = new JButton("Back");
		showOpen.setToolTipText("open last game");		
		showSave = new JButton("Record");
		showSave.setToolTipText("save current game");
		jArea=new JTextArea();
		jsp=new JScrollPane(jArea);
		jsp.setBounds(720, 200, 200, 400);
		this.add(jsp);
		
		jmain.setLayout(new GridLayout(0,6));
		jmain.add(anew);
		jmain.add(repent);		
		jmain.add(showOpen);
		jmain.add(showSave);
		jmain.add(exit);
		jmain.add(text);
		jmain.setBounds(25,0,600,30);
		this.add(jmain);
		//insert a text area into JFrame
		anew.addActionListener(this);
		repent.addActionListener(this);
		exit.addActionListener(this);		
		showOpen.addActionListener(this);
		showSave.addActionListener(this);
		
		this.setTitle("INTERNETIONAL CHESS");
		//acquire the size of screen.
		Dimension   screensize   =   Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screensize.getWidth();
		int height = (int)screensize.getHeight();
		this.setBounds((width - 900) / 2,
	            (height - 750) / 2, 900, 750);
		jpanel=new ChessBoard();
		this.add(jpanel);
		this.setVisible(true);
		// fix the size first
		repaint();
		this.setResizable(false);
		//System out
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		new LocalChess();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==anew) {
			dispose();
			new LocalChess();
		}else if (e.getSource()==exit) {
			System.exit(0);
		}else if (e.getSource()==showOpen) {
			dispose();
			new Modechoose();
		}
	}


}
