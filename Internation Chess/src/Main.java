import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;

public class Main extends JFrame {
	public final static int width=800;
	public final static int height=800;
	public final static int gap=50;//实际棋盘和边框的间距
	public  final static int side=85;//小正方形的边框
	public int row=8,column=8;
	ChessBoard jpanel;
	public Main() {
		// TODO Auto-generated constructor stub
		this.setTitle("INTERNETIONAL CHESS");
		this.setSize(900, 900);
		jpanel=new ChessBoard();
		this.add(jpanel);
		this.setVisible(true);
		
	}

	public static void main(String[] args) {
		new Main();
	}
}
