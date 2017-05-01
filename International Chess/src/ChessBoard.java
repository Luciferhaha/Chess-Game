import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessBoard extends JPanel implements MouseListener,CallBack{
	public final static int width=800;
	public final static int height=800;
	public final static int gap=50;//实际棋盘和边框的间距
	public  final static int side=85;//小正方形的边框
	public int row=8,column=8;
	public JLabel pieces[][]=new JLabel[4][8];
	public ChessPoint Point;
	public  PiecesMove rule;
	public JLabel text;
	public JLabel label=null;
	public JLabel label2=null;
	public Point_Operation check=new Point_Operation();
	public int count=0;
	/*单击棋子**********************************/
	/*chessManClick = true 闪烁棋子 并给线程响应*/
	/*chessManClick = false 吃棋子 停止闪烁  并给线程响应*/
	public boolean chessmanclick;
	/*控制玩家走棋****************************/
	/*chessPlayClick=1 黑棋走棋*/
	/*chessPlayClick=2 白棋走棋 默认白棋先走*/
	/*chessPlayClick=3 双方都不能走棋*/	
	int chessPlayClick=2;
	//控制棋子闪烁的线程
		thread hThread;
		//把第一次的单击棋子给线程响应
	static int left,right;
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
		pieces[3][4].setName("2KIng");
		//queens
		pieces[0][3]=new JLabel(new ImageIcon("src/Graph/BQueen.png"));
		pieces[0][3].setName("2Queen");
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
		int x,y;

		//acquire location
		x=(e.getXOnScreen()-gap)/side;
		y=(e.getYOnScreen()-46)/side;
		
		
		if (x>=0&&x<8&&y>=0&&y<8) {
			//move pieces
			//move white pawn pieces
			if (chessPlayClick==2) {
				selectPiece(e,chessPlayClick);
				if (label!=null&&e.getSource().equals(this)) {
					for (int i = 0; i <8; i++) {
						if (label.equals(pieces[2][i])) {
							check.findChessPoint(label);
							rule.Pawn(label, e,x,y,check.Point,check);
							if (rule.rightmove==true) {
								hThread.end();
								chessPlayClick=3;
								rule.rightmove=false;
							}
						}
					}
					//move white queens
					if (label.equals(pieces[3][3])) {
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
						if (label.equals(pieces[1][i])) {
							check.findChessPoint(label);
							rule.Pawn(label, e,x,y,check.Point,check);
							if (rule.rightmove==true) {
								hThread.end();
								chessPlayClick=2;
								rule.rightmove=false;
							}
						}
					}
					//move black queens
					if (label.equals(pieces[0][3])) {
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
			if (label!=null&&!e.getSource().equals(label)&&!e.getSource().equals(this)) {
				for (int i = 0; i <4; i++) {
					for (int j = 0; j <8; j++) {
						if (e.getSource().equals(pieces[i][j])) {
							label2=pieces[i][j];
						}
					}
				}
			}
			// make a judgment 
			//judge the piece is  pawn or not 
			if (label2!=null&&label2.getName().charAt(0)!=label.getName().charAt(0)) {
				//white pawn eat pieces
				for (int i = 0; i <8; i++) {
					if (label.equals(pieces[2][i])) {
						rule.PawnEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=3;
						}
					}
				}
				//black pawn eat pieces
				for (int i = 0; i <8; i++) {
					if (label.equals(pieces[1][i])) {
						rule.PawnEatRule(label, label2);
						if (rule.haseaten) {
							remove(label2);
							label2=null;
							hThread.end();
							rule.haseaten=false;
							chessPlayClick=2;
						}
					}
				}
				// white queen eat pieces
				if (label.equals(pieces[3][3])) {
					rule.QueenEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=3;
					}
				}
				//black queen eat pieces
				if (label.equals(pieces[0][3])) {
					rule.QueenEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=2;
					}
				}
				//white king eat pieces
				if (label.equals(pieces[3][4])) {
					rule.KingEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=3;
					}
				}//black
				if (label.equals(pieces[0][4])) {
					rule.KingEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=2;
					}
				}//white rook eat 
				if (label.equals(pieces[3][0])||label.equals(pieces[3][7])) {
					rule.RookEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=3;
					}
				}//black
				if (label.equals(pieces[0][0])||label.equals(pieces[0][7])) {
					rule.RookEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=2;
					}
				}	//white knight eat
				if (label.equals(pieces[3][1])||label.equals(pieces[3][6])) {
					rule.knightEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=3;
					}
				}//black knight eat
				if (label.equals(pieces[0][1])||label.equals(pieces[0][6])) {
					rule.knightEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=2;
					}
				}// white bishop eat 
				if (label.equals(pieces[3][2])||label.equals(pieces[3][5])) {
					rule.BisshopEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=3;
					}
				}//black bishop 
				if (label.equals(pieces[3][2])||label.equals(pieces[3][5])) {
					rule.BisshopEatRule(label, label2);
					if (rule.haseaten) {
						remove(label2);
						label2=null;
						hThread.end();
						rule.haseaten=false;
						chessPlayClick=2;
					}
				}
			}
		}
	
			

	}
	public void selectPiece(MouseEvent e,int i) {
		if (i==2) {
			//select pawns
			for (int j = 0; j <8; j++) {
				if (e.getSource().equals(pieces[2][j])) {
					label=(JLabel) e.getSource();
					if (hThread!=null) {
						hThread.end();
					}
					hThread=new thread(label);
					hThread.start();
				}
			}
			//select queens 
			if (e.getSource().equals(pieces[3][3])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
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
				label=(JLabel) e.getSource();
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
				if (e.getSource().equals(pieces[1][j])) {
					label=(JLabel) e.getSource();
					if (hThread!=null) {
						hThread.end();
					}
					hThread=new thread(label);
					hThread.start();
				}
			}
			//select queens 
			if (e.getSource().equals(pieces[0][3])) {
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
				label=(JLabel) e.getSource();
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
	
	
}

