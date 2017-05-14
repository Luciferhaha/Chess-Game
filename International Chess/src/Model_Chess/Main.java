package Model_Chess;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class Main extends JFrame implements ActionListener{
	public final static int width=800;
	public final static int height=800;
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
	//保存当前操作
	Vector fileVar;
	Vector Var;
	public Main() {
		// TODO Auto-generated constructor stub
//		con = this.getContentPane();
//		con.setLayout(null);
//
//		jmain = new JToolBar();
//		text = new JLabel("  热烈欢迎");
//		text.setToolTipText("提示信息");
//		anew = new JButton(" 新 游 戏 "	);
//		anew.setToolTipText("重新开始新的一局");
//		exit = new JButton(" 退  出 ");
//		exit.setToolTipText("退出本程序");
//		repent = new JButton(" 悔  棋 ");
//		repent.setToolTipText("返回到上次走棋的位置");				
//		showOpen = new JButton("打开");
//		showOpen.setToolTipText("打开以前棋局");		
//		showSave = new JButton("保存");
//		showSave.setToolTipText("保存当前棋局");
//		
//		//把组件添加到工具栏
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
		this.setBounds(0, 0,900,900);
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
