import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;

import javax.swing.JPanel;

public class ChessBoard  extends JPanel{
	public final static int width=800;
	public final static int height=800;
	public final static int gap=50;
	public  final static int side=100;
	public  int[][] grid; 
	public Label[][] chess;
	public ChessBoard() {
		// TODO Auto-generated constructor stub
		grid=new int [7][7];
		chess=new Label[4][7];
	}
	public  void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.fillRect(gap, 0,width ,height);
		for (int i = 0; i < grid.length; i=i+1) {//row
			for (int j = 0; j < grid.length; j=j+2) {//column
				g.setColor(Color.black);
				g.fillRect(gap+j*side+(i%2)*side,i*side, side, side);//利用余数来错位
			}
		}
	}

}
