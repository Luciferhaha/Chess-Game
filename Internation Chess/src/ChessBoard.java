import java.awt.Color;
import java.awt.Graphics;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessBoard extends JPanel{
	public final static int width=800;
	public final static int height=800;
	public final static int gap=50;//实际棋盘和边框的间距
	public  final static int side=85;//小正方形的边框
	public int row=8,column=8;
	public int gap2=10;
	public JLabel pieces[][]=new JLabel[4][8];
	public ChessBoard() {
		// TODO Auto-generated constructor stub
	insertimage();
	setlocation();
	for (int i = 0; i <4; i++) {
		for (int j= 0; j<8; j++) {
			this.add(pieces[i][j]);
		}
	}
    this.setLayout(null);
	}
	public void insertimage() {
		//pawns
		for (int i = 0; i <8; i++) {
			pieces[1][i]=new JLabel(new ImageIcon("src/Graph/Pawn.png"));
			pieces[2][i]=new JLabel(new ImageIcon("src/Graph/BPawn.png"));
		}
		//kings
		pieces[0][4]=new JLabel(new ImageIcon("src/Graph/King.png"));
		pieces[3][4]=new JLabel(new ImageIcon("src/Graph/BKing.png"));
		//queens
		pieces[0][3]=new JLabel(new ImageIcon("src/Graph/Queen.png"));
		pieces[3][3]=new JLabel(new ImageIcon("src/Graph/BQueen.png"));
		//Knights
		pieces[0][1]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
		pieces[0][6]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
		pieces[3][6]=new JLabel(new ImageIcon("src/Graph/BKnight.png"));
		pieces[3][1]=new JLabel(new ImageIcon("src/Graph/BKnight.png"));
		
		//rooks
		pieces[0][0]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[0][7]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][0]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[3][7]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		//Bishops
		pieces[0][2]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[0][5]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][2]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[3][5]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
	}
	public void setlocation() {
		//initial location of pawn
		for (int i = 0; i <8; i++) {
			pieces[1][i].setBounds(gap+gap2+i*side, side,65, 80);
			pieces[2][i].setBounds(gap+gap2+i*side, 6*side,65, 80);
		}
		//kings
		pieces[0][4].setBounds(gap+4*side, 0, side, side);
		pieces[3][4].setBounds(gap+4*side, 7*side, side, side);
		//queens
		pieces[0][3].setBounds(gap+3*side, 0, side, side);
		pieces[3][3].setBounds(gap+3*side, 7*side, side, side);
		//knights
		pieces[0][1].setBounds(gap+side, 0, side, side);
		pieces[0][6].setBounds(gap+6*side, 0, side, side);
		pieces[3][1].setBounds(gap+side, 7*side,side, side);
		pieces[3][6].setBounds(gap+6*side,7*side,side, side);
		//rooks
		pieces[0][0].setBounds(gap, 0, side, side);
		pieces[0][7].setBounds(gap+7*side, 0, side, side);
		pieces[3][0].setBounds(gap, 7*side, side, side);
		pieces[3][7].setBounds(gap+7*side, 7*side,side, side);
		//Bishops
		pieces[0][2].setBounds(gap+2*side, 0, side, side);
		pieces[0][5].setBounds(gap+5*side, 0, side, side);
		pieces[3][2].setBounds(gap+2*side, 7*side, side, side);
		pieces[3][5].setBounds(gap+5*side, 7*side, side, side);
	}
	public  void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(gap, 0,row*side,column*side);
		for (int i = 0; i < row; i=i+1) {//row
			for (int j = 0; j < column; j=j+2) {//column
				g.setColor(Color.black);
				g.fillRect(gap+j*side+(i%2)*side,i*side, side, side);//利用余数来错位
			}
		}
	}

}
