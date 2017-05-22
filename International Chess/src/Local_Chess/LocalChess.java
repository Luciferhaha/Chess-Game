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
	
	public final static int gap=50;//实际棋盘和边框的间距
	public  final static int side=85;//小正方形的边框
	public int row=8,column=8;
	public ChessBoard jpanel;
	Container con;

	//工具栏
	JToolBar jmain;
	JButton anew;
	JButton repent;
	JButton showOpen;
	JButton showSave;
	JButton exit;
	//当前信息
	JLabel text;
	JMenuBar jBar;
	JTextArea jArea;
	JScrollPane jsp;
	//保存当前操作
	Vector fileVar;
	Vector Var;
	public LocalChess() {
		// TODO Auto-generated constructor stub
	
		
		jmain = new JToolBar();
		text = new JLabel("       WElCOME");
		text.setToolTipText("提示信息");
		anew = new JButton(" New Game "	);
		anew.setToolTipText("重新开始新的一局");
		exit = new JButton("Exit");
		exit.setToolTipText("退出本程序");
		repent = new JButton("Undo ");
		repent.setToolTipText("返回到上次走棋的位置");				
		showOpen = new JButton("Back");
		showOpen.setToolTipText("打开以前棋局");		
		showSave = new JButton("Record");
		showSave.setToolTipText("保存当前棋局");
		jArea=new JTextArea();
		jsp=new JScrollPane(jArea);
		jsp.setBounds(720, 200, 200, 400);
		this.add(jsp);
		//把组件添加到工具栏
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
//		this.setLayout(new BorderLayout());
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
