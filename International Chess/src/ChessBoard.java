import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.tree.TreeNode;
import javax.xml.soap.Text;

public class ChessBoard extends JPanel implements MouseListener,Runnable{
	public final static int width=800;
	public final static int height=800;
	public final static int gap=50;//实际棋盘和边框的间距
	public  final static int side=85;//小正方形的边框
	public int row=8,column=8;
	public int gap2=10;
	public JLabel pieces[][]=new JLabel[4][8];
	public ChessPoint Point[][]=new ChessPoint[8][8];
	public boolean excited[][]=new boolean[8][8];
	public  PiecesMove rule;
	public JLabel text;
	public JLabel label;
	/*单击棋子**********************************/
	/*chessManClick = true 闪烁棋子 并给线程响应*/
	/*chessManClick = false 吃棋子 停止闪烁  并给线程响应*/
	public boolean chessmanclick=true;
	/*控制玩家走棋****************************/
	/*chessPlayClick=1 黑棋走棋*/
	/*chessPlayClick=2 白棋走棋 默认白棋先走*/
	/*chessPlayClick=3 双方都不能走棋*/	
	int chessPlayClick=2;
	//控制棋子闪烁的线程
		Thread tmain;
		//把第一次的单击棋子给线程响应
	static int left,right;
	public ChessBoard() {
		// TODO Auto-generated constructor stub
		insertimage();
		setlocation();
		for (int i = 0; i <4; i++) {
			for (int j= 0; j<8; j++) {
			pieces[i][j].addMouseListener(this);
			this.add(pieces[i][j]);
			
			}
		}
		this.setLayout(null);
		
	}
	public void insertimage() {
		//pawns
		for (int i = 0; i <8; i++) {
			pieces[1][i]=new JLabel(new ImageIcon("src/Graph/BPawn.png"));
			pieces[2][i]=new JLabel(new ImageIcon("src/Graph/Pawn.png"));
		}
		//kings
		pieces[0][4]=new JLabel(new ImageIcon("src/Graph/BKing.png"));
		pieces[3][4]=new JLabel(new ImageIcon("src/Graph/King.png"));
		//queens
		pieces[0][3]=new JLabel(new ImageIcon("src/Graph/BQueen.png"));
		pieces[3][3]=new JLabel(new ImageIcon("src/Graph/Queen.png"));
		//Knights
		pieces[0][1]=new JLabel(new ImageIcon("src/Graph/BKnight.png"));
		pieces[0][6]=new JLabel(new ImageIcon("src/Graph/BKnight.png"));
		pieces[3][6]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
		pieces[3][1]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
		
		//rooks
		pieces[0][0]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[0][7]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[3][0]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][7]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		//Bishops
		pieces[0][2]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[0][5]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[3][2]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][5]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
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
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(gap, 0,row*side,column*side);
		for (int i = 0; i < row; i=i+1) {//row
			for (int j = 0; j < column; j=j+2) {//column
				g.setColor(Color.white);
				g.fillRect(gap+j*side+(i%2)*side,i*side, side, side);//利用余数来错位
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x,y;
		chessmanclick=false;
		 label=(JLabel) e.getSource();
		//当前坐标
		//启动线程
		
//		label.setVisible(false);
//		while(chessmanclick==false){
//		try {
//			Thread.sleep(500);
//			System.out.println("hha");
//			label.setVisible(true);
//		} catch (Exception e2) {
//			// TODO: handle exception
//			}
//		}
          if (tmain==null) {
			tmain=new Thread(this);
			tmain.start();
          }
		//白棋走棋时
//		if (chessmanclick==false) {
//				x=(e.getXOnScreen()-gap)/side;
//				y=(e.getYOnScreen()-46)/side;
//				System.out.println(x);
//				System.out.println(y);
//			
//				//move pawns
//					for (int j = 0; j <8; j++) {
//						if (label.equals(pieces[1][j])||label.equals(pieces[2][j])) {
////							label.setLocation(gap+2*side+gap2,5*side);
//							
//						}
//					}
//				//move queens 
//					if (label.equals(pieces[0][3])||label.equals(pieces[3][3])) {
////						rule.Queen();
//					}
//				//move Kings
//					if (label.equals(pieces[0][4])||label.equals(pieces[3][4])) {
//						//move
//					}
//				//move rooks
//					if (label.equals(pieces[0][0])||label.equals(pieces[0][7])||label.equals(pieces[3][0])||equals(pieces[3][7])) {
//						//move
//					}// move knights
//					if (label.equals(pieces[0][1])||label.equals(pieces[0][6])||label.equals(pieces[3][1])||label.equals(pieces[3][6])) {
//						//move
//					}//move bishops
//					if (label.equals(pieces[0][2])||label.equals(pieces[0][5])||label.equals(pieces[3][2])||label.equals(pieces[3][5])) {
//						//move
//					}
//				}
			}
		

		
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			//单击棋子第一下开始闪烁
			if (chessmanclick==false) {
				label.setVisible(false);
				try {
					tmain.sleep(500);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			label.setVisible(true);
			try {
				tmain.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

