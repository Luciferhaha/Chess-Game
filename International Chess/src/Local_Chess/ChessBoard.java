package Local_Chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import UI.bVictory;
import UI.wVictory;

import java.io.*;
import java.net.Socket;

public class ChessBoard extends JPanel implements MouseListener,CallBack,Runnable{

	public final static int gap=35;//实际棋盘和边框的间距
	public final static int gap2=0;
	public  final static int side=85;//小正方形的边框
	public int row=8,column=8;
	public JLabel pieces[][]=new JLabel[6][8];
	public ChessPoint Point;
	public  PiecesMove rule;
	public JLabel text;
	public JLabel label=null;
	public JLabel label2=null;
	// label3 for casting
	public JLabel label3=null;
	public JLabel label4=null;
	public Point_Operation check=new Point_Operation();
	public int count=0;
	public int nq,nq2;
	public  boolean win;
	/*chessPlayClick=3黑棋走棋*/
	/*chessPlayClick=2 白棋走棋 默认白棋先走*/
	/*chessPlayClick=1 双方都不能走棋*/	
	public int chessPlayClick=2;
	//The thread which could control the chess to flash
		thread hThread;
	public ChessBoard() {
		// TODO Auto-generated constructor stub
		insertimage();
		setlocation();
		for (int i = 0; i <4; i++) {
			for (int j= 0; j<8; j++) {
				this.add(pieces[i][j]);
				check.SetChessPoint(pieces[i][j]);
			pieces[i][j].addMouseListener(this);
			}
		}
//		this.setSize(row*side, column);
		this.addMouseListener(this);
		this.setLayout(null);
		rule=new PiecesMove(check);
	}
	public void insertimage() {
		//pawns
		for (int i = 0; i <8; i++) {
			pieces[1][i]=new JLabel(new ImageIcon("src/Graph/BPawn.png"));
			pieces[1][i].setName("1Pawn");
			pieces[2][i]=new JLabel(new ImageIcon("src/Graph/Pawn.png"));
			pieces[2][i].setName("2Pawn");
		}
		//kings
		pieces[0][4]=new JLabel(new ImageIcon("src/Graph/BKing.png"));
		pieces[0][4].setName("1King");
		pieces[3][4]=new JLabel(new ImageIcon("src/Graph/King.png"));
		pieces[3][4].setName("2King");
		//queens
		pieces[0][3]=new JLabel(new ImageIcon("src/Graph/BQueen.png"));
		pieces[0][3].setName("1Queen");
		pieces[3][3]=new JLabel(new ImageIcon("src/Graph/Queen.png"));
		pieces[3][3].setName("2Queen");
		//Knights
		pieces[0][1]=new JLabel(new ImageIcon("src/Graph/BKnight.png"));
		pieces[0][1].setName("1Knighht");
		pieces[0][6]=new JLabel(new ImageIcon("src/Graph/BKnight.png"));
		pieces[0][6].setName("1Knighht");
		pieces[3][6]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
		pieces[3][6].setName("2Knighht");
		pieces[3][1]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
		pieces[3][1].setName("2Knighht");
		
		//rooks
		pieces[0][0]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[0][0].setName("1Rook");
		pieces[0][7]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[0][7].setName("1Rook");
		pieces[3][0]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][0].setName("2Rook");
		pieces[3][7]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][7].setName("2Rook");
		//Bishops
		pieces[0][2]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[0][2].setName("1Bishop");
		pieces[0][5]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[0][5].setName("1Bishop");
		pieces[3][2]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][2].setName("2Bishop");
		pieces[3][5]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][5].setName("2Bishop");
	}
	public void setlocation() {
		//initial location of pawn
		for (int i = 0; i <8; i++) {
			pieces[1][i].setBounds(gap+i*side, side,side, side);
			pieces[2][i].setBounds(gap+i*side, 6*side,side, side);
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
		if (win==false) {
			
		
		int x,y;
		//acquire location
		x=(e.getX()-gap)/side;
		y=(e.getY())/side;
		
		if (x>=0&&x<8&&y>=0&&y<8) {
			//move pieces
			//move white pawn pieces
			if (chessPlayClick==2) {
				selectPiece(e,chessPlayClick);
				if (label!=null&&e.getSource().equals(this)) {
					
						for (int i = 0; i <8; i++) {
							if (label.equals(pieces[2][i])&&pieces[2][i].getName()!="3Queen") {
								check.findChessPoint(label);
								rule.Pawn(label, e,x,y,check.Point,check);
								if (rule.rightmove==true) {
									hThread.end();
									PawnPromotion();
									chessPlayClick=3;
									rule.rightmove=false;
								}
							}
						}
						
					
			//move white queens
				if (label.equals(pieces[3][3])||(label.equals(pieces[2][nq])&&pieces[2][nq].getName()=="3Queen")) {
					check.findChessPoint(label);
					rule.Queen(label,x, y,check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=3;
							rule.rightmove=false;
						}
					}
			//move white king
					if (label.equals(pieces[3][4])) {
						check.findChessPoint(label);
						rule.King(label,x,y,check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=3;
							rule.rightmove=false;
						}
					}
			//move white rooks
					if (label.equals(pieces[3][0])||label.equals(pieces[3][7])) {
						check.findChessPoint(label);
						rule.Rook(label,x,y,check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=3;
							rule.rightmove=false;
						}
					}
			// move white knights
					if (label.equals(pieces[3][1])||label.equals(pieces[3][6])) {
						check.findChessPoint(label);
						rule.knight(label,x,y,check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=3;
							rule.rightmove=false;
						}
					}
			//move white bishops
					if (label.equals(pieces[3][2])||label.equals(pieces[3][5])) {
						check.findChessPoint(label);
						rule.Bisshop(label, x, y, check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=3;
							rule.rightmove=false;
						}
					}
				}
			}	
			//move black pieces
			else if (chessPlayClick==3) {
				//select black pieces
				selectPiece(e,chessPlayClick);
				if (label!=null&&e.getSource().equals(this)) {
					// move black pawns
					
					for (int i = 0; i <8; i++) {
						if (label.equals(pieces[1][i])&&pieces[1][i].getName()!="4Queen") {
							check.findChessPoint(label);
							rule.Pawn(label, e,x,y,check.Point,check);
							if (rule.rightmove==true) {
								hThread.end();
								PawnPromotion();
								chessPlayClick=2;
								rule.rightmove=false;
							}
						}
					}
				
					//move black queens
					if (label.equals(pieces[0][3])||(label.equals(pieces[1][nq2])&&pieces[1][nq2].getName()=="4Queen")) {
						check.findChessPoint(label);
						rule.Queen(label,x, y,check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=2;
							rule.rightmove=false;
						}
					}
					//move black kings
					if (label.equals(pieces[0][4])) {
						check.findChessPoint(label);
						rule.King(label,x,y,check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=2;
							rule.rightmove=false;
						}
					}
					//move black rooks
					if (label.equals(pieces[0][0])||label.equals(pieces[0][7])) {
						check.findChessPoint(label);
						rule.Rook(label,x,y,check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=2;
							rule.rightmove=false;
						}
					}
					//move black knights
					if (label.equals(pieces[0][1])||label.equals(pieces[0][6])) {
						check.findChessPoint(label);
						rule.knight(label,x,y,check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=2;
							rule.rightmove=false;
						}
					}
					//move black bishops
					if (label.equals(pieces[0][2])||label.equals(pieces[0][5])) {
						check.findChessPoint(label);
						rule.Bisshop(label, x, y, check.Point);
						if (rule.rightmove==true) {
							hThread.end();
							chessPlayClick=2;
							rule.rightmove=false;
						}
					}
					
				}
			}
			//find eat pieces
			if (label!=null&&!e.getSource().equals(this)) {
				for (int i = 0; i <4; i++) {
					for (int j = 0; j <8; j++) {
						if (e.getSource().equals(pieces[i][j])) {
							if (pieces[i][j].getName()!=label.getName()) {
								label2=pieces[i][j];
								if (label2.getName().charAt(0)==label.getName().charAt(0)) {
									// casting
										label2=null;
									}
							}
						}
					}
				}
			}
//			System.out.println(label.getName());
//			System.out.println(label2);
			// make a judgment 
			//judge the piece is  pawn or not 

			if (label2!=null&&label2.getName().charAt(0)!=label.getName().charAt(0)) {
				
				if (chessPlayClick==3) {
					//black pawn eat pieces
						for (int i = 0; i <8; i++) {
							if (label.equals(pieces[1][i])&&pieces[1][i].getName()!="4Queen") {
								rule.PawnEatRule(label, label2);
								if (rule.haseaten) {
									remove(label2);
									victory();
									label2=null;
									PawnPromotion();
									hThread.end();
									rule.haseaten=false;
									chessPlayClick=2;
								}
							}
						}
					//black queen eat pieces
					if (label.equals(pieces[0][3])||(label.equals(pieces[1][nq2])&&pieces[1][nq2].getName()=="4Queen")) {
						rule.QueenEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=2;
						}
					}
					//black king
					if (label.equals(pieces[0][4])) {
						rule.KingEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=2;
						}
					}
					//black knight eat
					if (label.equals(pieces[0][1])||label.equals(pieces[0][6])) {
						rule.knightEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=2;
						}
					}
					//black bishop 
					if (label.equals(pieces[0][2])||label.equals(pieces[0][5])) {
						rule.BisshopEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=2;
						}
					}
					//black rook eat 
					if (label.equals(pieces[0][0])||label.equals(pieces[0][7])) {
						rule.RookEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=2;
						}
					}
				}
				if (chessPlayClick==2) {
					//white pawn eat pieces
					for (int i = 0; i <8; i++) {
						if (label.equals(pieces[2][i])&&pieces[2][i].getName()!="3Queen") {
							
							rule.PawnEatRule(label, label2);
							if (rule.haseaten) {
								remove(label2);
								victory();
								label2=null;
								PawnPromotion();
								hThread.end();
								rule.haseaten=false;
								chessPlayClick=3;
							}
						}
					}
					// white queen eat pieces
					if (label.equals(pieces[3][3])||(label.equals(pieces[2][nq])&&pieces[2][nq].getName()=="3Queen")) {
						rule.QueenEatRule(label, label2);
						
						if (rule.haseaten==true) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=3;
						}
					}
					//white king eat pieces
					if (label.equals(pieces[3][4])) {
						rule.KingEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=3;
						}
					}
					//white rook eat 
					if (label.equals(pieces[3][0])||label.equals(pieces[3][7])) {
						rule.RookEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=3;
						}
					}
					//white knight eat
					if (label.equals(pieces[3][1])||label.equals(pieces[3][6])) {
						rule.knightEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=3;
						}
					}
					// white bishop eat 
					if (label.equals(pieces[3][2])||label.equals(pieces[3][5])) {
						rule.BisshopEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							victory();
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=3;
						}
					}
					
				}
				
				
			}
		}
		}
	}
	public void selectPiece(MouseEvent e,int i) {
		if (i==2) {
			//select pawns
			
				for (int j = 0; j <8; j++) {
					if (e.getSource().equals(pieces[2][j])&&pieces[2][j].getName()!="3Queen") {
						label=(JLabel) e.getSource();
						if (hThread!=null) {
							hThread.end();
						}
						hThread=new thread(label);
						hThread.start();
					}
				}
				
			
			//select queens 
			if (e.getSource().equals(pieces[3][3])||(e.getSource().equals(pieces[2][nq])&&pieces[2][nq].getName()=="3Queen")) {
				try {
					label=(JLabel) e.getSource();
					if (hThread!=null) {
						hThread.end();
					}
					hThread=new thread(label);
					hThread.start();
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			//select kings
			if (e.getSource().equals(pieces[3][4])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
			}
			//select rooks
			if (e.getSource().equals(pieces[3][0])||e.getSource().equals(pieces[3][7])) {
				
				if (label.getName()=="2King") {
					label3=(JLabel) e.getSource();
						rule.Castling(label,label3);
						System.out.println(rule.hascasting);
						if (!rule.hascasting) {
							label=label3;
						}else {
							chessPlayClick=3;
						}
				}else {
					label=(JLabel) e.getSource();
				}
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
			}
			// select knights
			if (e.getSource().equals(pieces[3][1])||e.getSource().equals(pieces[3][6])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
			}
			//select bishops
			if (e.getSource().equals(pieces[3][2])||e.getSource().equals(pieces[3][5])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
			}
		}else if (i==3) {
			
				for (int j = 0; j <8; j++) {
					if (e.getSource().equals(pieces[1][j])&&pieces[1][j].getName()!="4Queen") {
						label=(JLabel) e.getSource();
						if (hThread!=null) {
							hThread.end();
						}
						hThread=new thread(label);
						hThread.start();
					}
				}
				
			
			//select queens 
			if (e.getSource().equals(pieces[0][3])||(e.getSource().equals(pieces[1][nq2])&&pieces[1][nq2].getName()=="4Queen")) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
			}
			//select kings
			if (e.getSource().equals(pieces[0][4])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
			}
			//select rooks
			if (e.getSource().equals(pieces[0][0])||e.getSource().equals(pieces[0][7])) {
				if (label.getName()=="1King") {
					label3=(JLabel) e.getSource();
						rule.Castling(label,label3);
						if (!rule.hascasting) {
							label=label3;
						}else {
							chessPlayClick=2;
						}
				}else {
					label=(JLabel) e.getSource();
				}
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
			}
			// select knights
			if (e.getSource().equals(pieces[0][1])||e.getSource().equals(pieces[0][6])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
			}
			//select bishops
			if (e.getSource().equals(pieces[0][2])||e.getSource().equals(pieces[0][5])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
			}
		}
		
		
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
	public void deliver() {
		// TODO Auto-generated method stub
		
	}
	public void PawnPromotion() {
		//when a pawn moving to the last line ,it will be turn to queen.
		for (int i = 0; i <8; i++) {
			int record =pieces[1][i].getLocation().y/side;
			int record2=pieces[2][i].getLocation().y/side;
				if (record==7) {
					pieces[1][i].setIcon(new ImageIcon("src/Graph/BQueen.png"));
					pieces[1][i].setName("4Queen");
					nq2=i;
				}
				if (record2==0) {
					pieces[2][i].setIcon(new ImageIcon("src/Graph/Queen.png"));
					pieces[2][i].setName("3Queen");
					nq=i;
				}
			}
	}
	public void victory() {
		
		if (label2.getName()=="1King") {
			System.out.println("The white side succeed!");
			new wVictory();
			win=true;
		}else if (label2.getName()=="2King") {
			System.out.println("The black side succeed!");
			new bVictory();
			win=true;
		}
	
	}
	// 连接到主机
	
	@Override
	public void run() {
		// TODO Auto-generated method stu
	
	}
	
}

