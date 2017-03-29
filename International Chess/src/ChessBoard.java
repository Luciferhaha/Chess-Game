import java.awt.Color;
import java.awt.Graphics;


import javax.swing.JPanel;

public class ChessBoard  extends JPanel{
	//一些常用参数
	public final static int width=800;
	public final static int height=800;
	public final static int gap=50;//实际棋盘和边框的间距
	public  final static int side=100;//小正方形的边框
	//位置参数
	public  Location[][] grid;
	//棋子的对象
	public Chess pieces;
	//行款和列宽
	public int row,column;
	public ChessBoard() {
		// TODO Auto-generated constructor stub
		//初始化坐标和棋子，行列宽
		grid=new Location [7][7];
		pieces=new Chess();
		row=column=8;
	}
	//设定初始位置
	public void SetInitialLocation(){
		//所有的位置都是从后往前设定
		//两行士兵的初始位置
		for (int i = 0; i <column; i++) {
			for (int j = 0; j <column; j++) {
				pieces.chess[1][i].setLocation(j*100, 100);
				pieces.chess[3][i].setLocation(j*100, 100);
				
			}
		}
		//双王的初始位置
		pieces.chess[0][4].setLocation(400, 700);
		pieces.chess[3][4].setLocation(400,0);
		//双后的初始位置
		pieces.chess[0][3].setLocation(300, 700);
		pieces.chess[3][3].setLocation(300,0);
		//四车的初始位置
		pieces.chess[0][0].setLocation(0,700);
		pieces.chess[0][7].setLocation(700,700);
		pieces.chess[3][0].setLocation(0,0);
		pieces.chess[3][7].setLocation(0,700);
		//四象的初始位置
		pieces.chess[0][2].setLocation(200, 700);
		pieces.chess[0][5].setLocation(500, 700);
		pieces.chess[3][2].setLocation(200, 0);
		pieces.chess[3][5].setLocation(500, 0);
		//四马的初始位置
		pieces.chess[0][1].setLocation(100, 700);
		pieces.chess[0][6].setLocation(600, 700);
		pieces.chess[3][1].setLocation(100, 0);
		pieces.chess[3][6].setLocation(600, 0);
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
