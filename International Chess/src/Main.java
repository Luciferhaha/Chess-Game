import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

// this is the main class that implement the chess
public class Main extends JFrame implements ActionListener{
	public final static int width=800;
	public final static int height=800;
	
	// the chesserboard and the frame
	public final static int gap=50;
	// the frame of the square
	public  final static int side=85;
	// initial the row and the column
	public int row=8,column=8;
	public ChessBoard jpanel;
	
	Container con;

	// the tools
	JToolBar jmain;
	JButton anew;
	JButton repent;
	JButton showOpen;
	JButton showSave;
	JButton exit;
	
	//present information
	JLabel text;
	
	// save the present operations
	Vector fileVar;
	Vector Var;
	public Main() {
		// TODO Auto-generated constructor stub
//		con = this.getContentPane();
//		con.setLayout(null);
//
//		jmain = new JToolBar();
//		text = new JLabel("  Welcome");
//		text.setToolTipText(" Prompt information");
//		anew = new JButton("  New Game ");
		
//		anew.setToolTipText("A new Game again");
//		exit = new JButton(" exist ");
//		exit.setToolTipText("exist the application");
//		repent = new JButton(" retract a false move ");
//		repent.setToolTipText("return last position");				
//		showOpen = new JButton("Open");
//		showOpen.setToolTipText("open last chess game");		
//		showSave = new JButton("Save");
//		showSave.setToolTipText("save present chess");
//		
//		
		//Add the component to the toolbar
//		jmain.setLayout(new GridLayout(0,6));
//		jmain.add(anew);
//		jmain.add(repent);		
//		jmain.add(showOpen);
//		jmain.add(showSave);
//		jmain.add(exit);
//		jmain.add(text);
//		jmain.setBounds(0,500,450,30);
//		con.add(jmain);
//
//		jmain.setLayout(new GridLayout(0,6));
//		jmain.add(anew);
//		jmain.add(repent);		
//		jmain.add(showOpen);
//		jmain.add(showSave);
//		jmain.add(exit);
//		jmain.add(text);
//		jmain.setBounds(0,0,450,30);
//		con.add(jmain);
//
//		anew.addActionListener(this);
//		repent.addActionListener(this);
//		exit.addActionListener(this);		
//		showOpen.addActionListener(this);
//		showSave.addActionListener(this);

		this.setTitle("INTERNETIONAL CHESS");
		this.setSize(900, 900);
		jpanel=new ChessBoard();
		this.add(jpanel);
		this.setVisible(true);
		//System out
		this.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent we) {
				System.exit(0);
				
			}
		});
		
	}

	public static void main(String[] args) {
		
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
