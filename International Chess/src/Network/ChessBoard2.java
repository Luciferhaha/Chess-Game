package Network;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import UI.bVictory;
import UI.wVictory;
import java.io.*;
import java.net.Socket;


public class ChessBoard2 extends JPanel implements MouseListener{

	public final static int gap=30;//the chess board
	public final static int gap2=30;
	public  final static int side=85;//frame of little square
	public int row=8,column=8;
	public JLabel pieces[][]=new JLabel[4][8];
	public ChessPoint2 Point;
	public  PiecesMove2 rule;
	public JLabel text;
	public JLabel label=null;
	public JLabel label2=null;
	// label3 for casting
	public JLabel label3=null;
	public JLabel label4=null;
	public Point_Operation2 check=new Point_Operation2();
	public int count=0;
	public int nq,nq2;
	public  boolean win=false;
	public JLabel label5=null;
	public JLabel label6=null;
	/*chessPlayClick=3black is not move*/
	/*chessPlayClick=2 white move firstly*/
	/*chessPlayClick=1 both*/	
	public int chessPlayClick=2;
	//The thread which could control the chess to flash
		thread2 hThread;
		// socket
	public String whoismaster;
	public Socket chessSocket;
	public DataInputStream inputData;
	public DataOutputStream outputData;
	public FIRThread firThread;
	public TextField statusText;
	public String chessSelfName = null;
	public String chessPeerName = null;
	public String host = null;
	public boolean isEnabled ;
	public int port = 4331;
	private boolean win2=false;
	public ChessBoard2() {
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
		rule=new PiecesMove2(check);
		firThread = new FIRThread(this);
		statusText = new TextField("Please contect to server！");
	}
	public void insertimage() {
		//pawns
		for (int i = 0; i <8; i++) {
			pieces[1][i]=new JLabel(new ImageIcon("src/Graph/BPawn.png"));
			pieces[1][i].setName("1"+i+"Pawn");
			pieces[2][i]=new JLabel(new ImageIcon("src/Graph/Pawn.png"));
			pieces[2][i].setName("2"+i+"Pawn");
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
		pieces[0][1].setName("11Knight");
		pieces[0][6]=new JLabel(new ImageIcon("src/Graph/BKnight.png"));
		pieces[0][6].setName("16Knight");
		pieces[3][6]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
		pieces[3][6].setName("26Knight");
		pieces[3][1]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
		pieces[3][1].setName("21Knight");
		
		//rooks
		pieces[0][0]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[0][0].setName("10Rook");
		pieces[0][7]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[0][7].setName("17Rook");
		pieces[3][0]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][0].setName("20Rook");
		pieces[3][7]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][7].setName("27Rook");
		//Bishops
		pieces[0][2]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[0][2].setName("12Bishop");
		pieces[0][5]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[0][5].setName("15Bishop");
		pieces[3][2]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][2].setName("22Bishop");
		pieces[3][5]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][5].setName("25Bishop");
	}
	public void setlocation() {
		//initial location of pawn
		for (int i = 0; i <8; i++) {
			pieces[1][i].setBounds(gap+i*side, side+gap2,side, side);
			pieces[2][i].setBounds(gap+i*side, 6*side+gap2,side, side);
		}
		//kings
		pieces[0][4].setBounds(gap+4*side, 0+gap2, side, side);
		pieces[3][4].setBounds(gap+4*side, 7*side+gap2, side, side);
		//queens
		pieces[0][3].setBounds(gap+3*side, 0+gap2, side, side);
		pieces[3][3].setBounds(gap+3*side, 7*side+gap2, side, side);
		//knights
		pieces[0][1].setBounds(gap+side, 0+gap2, side, side);
		pieces[0][6].setBounds(gap+6*side, 0+gap2, side, side);
		pieces[3][1].setBounds(gap+side, 7*side+gap2,side, side);
		pieces[3][6].setBounds(gap+6*side,7*side+gap2,side, side);
		//rooks
		pieces[0][0].setBounds(gap, gap2, side, side);
		pieces[0][7].setBounds(gap+7*side,gap2, side, side);
		pieces[3][0].setBounds(gap, 7*side+gap2, side, side);
		pieces[3][7].setBounds(gap+7*side, 7*side+gap2,side, side);
		//Bishops
		pieces[0][2].setBounds(gap+2*side, 0+gap2, side, side);
		pieces[0][5].setBounds(gap+5*side, 0+gap2, side, side);
		pieces[3][2].setBounds(gap+2*side, 7*side+gap2, side, side);
		pieces[3][5].setBounds(gap+5*side, 7*side+gap2, side, side);
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(gap, gap2,row*side,column*side);
		for (int i = 0; i < row; i=i+1) {//row
			for (int j = 0; j < column; j=j+2) {//column
				g.setColor(Color.white);
				g.fillRect(gap+j*side+(i%2)*side,i*side+gap2, side, side);//利用余数来错位
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (chessPlayClick!=1) {
			if (win==false) {
				int x,y;
				//acquire location
				x=(e.getX()-gap)/side;
				y=(e.getY()-gap2)/side;
				
				if (x>=0&&x<8&&y>=0&&y<8) {
					//move pieces
					//move white pawn pieces
					if (chessPlayClick==2) {
						selectPiece(e,chessPlayClick);
						if (label!=null&&e.getSource().equals(this)) {
							
							for (int i = 0; i <8; i++) {
								if (label.equals(pieces[2][i])&&pieces[2][i].getName()!="3Queen") {
									check.findChessPoint(label);// 
									rule.Pawn(label, x,y,check.Point);
									String pString=pieces[2][i].getName();
									if (rule.rightmove==true) {
										hThread.end();
										firThread.sendMessage("/" + chessPeerName + " /chess "
												+  check.Point.row()+ " " + check.Point.row() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
										PawnPromotion();
										if (rule.PawnThreat(label, pieces[0][4])) {
											firThread.sendMessage("/" + chessPeerName + " /chess "
												+ "AThreat");
										}
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
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x
											+" "+label.getLocation().y+" "+ label.getName());
									if (rule.queenThreat(label, pieces[0][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
									rule.rightmove=false;
								}
							}
							//move white king
							if (label.equals(pieces[3][4])) {
								check.findChessPoint(label);
								rule.King(label,x,y,check.Point);
								if (rule.rightmove==true) {
									hThread.end();
//							chessPlayClick=3;
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
									if (rule.kingThreat(label, pieces[0][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
									rule.rightmove=false;
								}
							}
							//move white rooks
							if (label.equals(pieces[3][0])||label.equals(pieces[3][7])) {
								check.findChessPoint(label);
								rule.Rook(label,x,y,check.Point);
								if (rule.rightmove==true) {
									hThread.end();
//							chessPlayClick=3;
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
									if (rule.RookThreat(label, pieces[0][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
									rule.rightmove=false;
								}
							}
							// move white knights
							if (label.equals(pieces[3][1])||label.equals(pieces[3][6])) {
								check.findChessPoint(label);
								rule.knight(label,x,y,check.Point);
								if (rule.rightmove==true) {
									hThread.end();
//							
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
									if (rule.KnightThreat(label, pieces[0][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
									rule.rightmove=false;
								}
							}
							//move white bishops
							if (label.equals(pieces[3][2])||label.equals(pieces[3][5])) {
								check.findChessPoint(label);
								rule.Bisshop(label, x, y, check.Point);
								if (rule.rightmove==true) {
									hThread.end();
//							
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
									if (rule.bishopThreat(label, pieces[0][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
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
									rule.Pawn(label, x,y,check.Point);
									String pString=pieces[1][i].getName();
									if (rule.rightmove==true) {
										hThread.end();
										firThread.sendMessage("/" + chessPeerName + " /chess "
												+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
										PawnPromotion();
										if (rule.PawnThreat(label, pieces[3][4])) {
											firThread.sendMessage("/" + chessPeerName + " /chess "
												+ "AThreat");
										}
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
//							chessPlayClick=2;
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
									if (rule.queenThreat(label, pieces[3][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
									rule.rightmove=false;
								}
							}
							//move black kings
							if (label.equals(pieces[0][4])) {
								check.findChessPoint(label);
								rule.King(label,x,y,check.Point);
								if (rule.rightmove==true) {
									hThread.end();
//							chessPlayClick=2;
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
									if (rule.kingThreat(label, pieces[3][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
									rule.rightmove=false;
								}
							}
							//move black rooks
							if (label.equals(pieces[0][0])||label.equals(pieces[0][7])) {
								check.findChessPoint(label);
								rule.Rook(label,x,y,check.Point);
								if (rule.rightmove==true) {
									hThread.end();
//							chessPlayClick=2;
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
									if (rule.RookThreat(label, pieces[3][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
									rule.rightmove=false;
								}
							}
							//move black knights
							if (label.equals(pieces[0][1])||label.equals(pieces[0][6])) {
								check.findChessPoint(label);
								rule.knight(label,x,y,check.Point);
								if (rule.rightmove==true) {
									hThread.end();
//							chessPlayClick=2;
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
									if (rule.KnightThreat(label, pieces[3][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
									rule.rightmove=false;
								}
							}
							//move black bishops
							if (label.equals(pieces[0][2])||label.equals(pieces[0][5])) {
								check.findChessPoint(label);
								rule.Bisshop(label, x, y, check.Point);
								if (rule.rightmove==true) {
									hThread.end();
//							chessPlayClick=2;
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName());
									if (rule.bishopThreat(label, pieces[3][4])) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
											+ "AThreat");
									}
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
									check.findChessPoint(label);
									rule.PawnEatRule(label, label2);
									if (rule.haseaten) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
												+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
										remove(label2);
										victory();
										label2=null;
										String pString=pieces[1][i].getName();
										PawnPromotion();
										hThread.end();
										rule.haseaten=false;
//									chessPlayClick=2;
									}
								}
							}
							//black queen eat pieces
							if (label.equals(pieces[0][3])||(label.equals(pieces[1][nq2])&&pieces[1][nq2].getName()=="4Queen")) {
								check.findChessPoint(label);
								rule.QueenEatRule(label, label2);
								if (rule.haseaten) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=2;
								}
							}
							//black king
							if (label.equals(pieces[0][4])) {
								check.findChessPoint(label);
								rule.KingEatRule(label, label2);
								if (rule.haseaten) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=2;
								}
							}
							//black knight eat
							if (label.equals(pieces[0][1])||label.equals(pieces[0][6])) {
								check.findChessPoint(label);
								rule.knightEatRule(label, label2);
								if (rule.haseaten) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=2;
								}
							}
							//black bishop 
							if (label.equals(pieces[0][2])||label.equals(pieces[0][5])) {
								check.findChessPoint(label);
								rule.BisshopEatRule(label, label2);
								if (rule.haseaten) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=2;
								}
							}
							//black rook eat 
							if (label.equals(pieces[0][0])||label.equals(pieces[0][7])) {
								check.findChessPoint(label);
								rule.RookEatRule(label, label2);
								if (rule.haseaten) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=2;
								}
							}
						}
						if (chessPlayClick==2) {
							//white pawn eat pieces
							for (int i = 0; i <8; i++) {
								if (label.equals(pieces[2][i])&&pieces[2][i].getName()!="3Queen") {
									check.findChessPoint(label);
									rule.PawnEatRule(label, label2);
									if (rule.haseaten) {
										firThread.sendMessage("/" + chessPeerName + " /chess "
												+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
										remove(label2);
										victory();
										label2=null;
										String pString2=pieces[2][i].getName();
										PawnPromotion();
										hThread.end();
										rule.haseaten=false;
//								chessPlayClick=3;
									}
								}
							}
							// white queen eat pieces
							if (label.equals(pieces[3][3])||(label.equals(pieces[2][nq])&&pieces[2][nq].getName()=="3Queen")) {
								check.findChessPoint(label);
								if (label2!=null) {
									rule.QueenEatRule(label, label2);
									
								}
								
								if (rule.haseaten==true) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=3;
								}
							}
							//white king eat pieces
							if (label.equals(pieces[3][4])) {
								check.findChessPoint(label);
								rule.KingEatRule(label, label2);
								if (rule.haseaten) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=3;
								}
							}
							//white rook eat 
							if (label.equals(pieces[3][0])||label.equals(pieces[3][7])) {
								check.findChessPoint(label);
								rule.RookEatRule(label, label2);
								if (rule.haseaten) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=3;
								}
							}
							//white knight eat
							if (label.equals(pieces[3][1])||label.equals(pieces[3][6])) {
								check.findChessPoint(label);
								rule.knightEatRule(label, label2);
								if (rule.haseaten) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=3;
								}
							}
							// white bishop eat 
							if (label.equals(pieces[3][2])||label.equals(pieces[3][5])) {
								check.findChessPoint(label);
								rule.BisshopEatRule(label, label2);
								if (rule.haseaten) {
									firThread.sendMessage("/" + chessPeerName + " /chess "
											+  check.Point.row()+ " " + check.Point.col() + " " +label.getLocation().x+" "+label.getLocation().y+" "+ label.getName()+" "+label2.getName());
									remove(label2);
									victory();
									label2=null;
									hThread.end();
									rule.haseaten=false;
//							chessPlayClick=3;
								}
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
						hThread=new thread2(label);
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
					hThread=new thread2(label);
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
				hThread=new thread2(label);
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
						}else {//send message about new location from both.
							
							firThread.sendMessage("/" + chessPeerName + " /chess "+label.getName()+" "+label3.getName());
						}
				}else {
					label=(JLabel) e.getSource();
				}
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread2(label);
				hThread.start();
			}
			// select knights
			if (e.getSource().equals(pieces[3][1])||e.getSource().equals(pieces[3][6])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread2(label);
				hThread.start();
			}
			//select bishops
			if (e.getSource().equals(pieces[3][2])||e.getSource().equals(pieces[3][5])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread2(label);
				hThread.start();
			}
		}else if (i==3) {
			
				for (int j = 0; j <8; j++) {
					if (e.getSource().equals(pieces[1][j])&&pieces[1][j].getName()!="4Queen") {
						label=(JLabel) e.getSource();
						if (hThread!=null) {
							hThread.end();
						}
						hThread=new thread2(label);
						hThread.start();
					}
				}
				
			
			//select queens 
			if (e.getSource().equals(pieces[0][3])||(e.getSource().equals(pieces[1][nq2])&&pieces[1][nq2].getName()=="4Queen")) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread2(label);
				hThread.start();
			}
			//select kings
			if (e.getSource().equals(pieces[0][4])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread2(label);
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
						
							firThread.sendMessage("/" + chessPeerName + " /chess "+label.getName()+" "+label3.getName());
							
						}
				}else {
					label=(JLabel) e.getSource();
				}
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread2(label);
				hThread.start();
			}
			// select knights
			if (e.getSource().equals(pieces[0][1])||e.getSource().equals(pieces[0][6])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread2(label);
				hThread.start();
			}
			//select bishops
			if (e.getSource().equals(pieces[0][2])||e.getSource().equals(pieces[0][5])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread2(label);
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
	public void PawnPromotion() {
		//when a pawn moving to the last line ,it will be turn to queen.
			for (int i = 0; i <8; i++) {
				int record =pieces[1][i].getLocation().y/side;
				int record2=pieces[2][i].getLocation().y/side;
				if (record==7) {
					String pString1=pieces[1][i].getName();
					pieces[1][i].setIcon(new ImageIcon("src/Graph/BQueen.png"));
					pieces[1][i].setName("4Queen");
					firThread.sendMessage("/" + chessPeerName + " /chess "
							+ "Promotion"+" "+pString1);
					nq2=i;
				}
				if (record2==0) {
					String pString2=pieces[2][i].getName();
					pieces[2][i].setIcon(new ImageIcon("src/Graph/Queen.png"));
					pieces[2][i].setName("3Queen");
						firThread.sendMessage("/" + chessPeerName + " /chess "
								+ "Promotion"+" "+pString2);
					nq=i;
				}
			}
		}
	public void victory() {
		
		if (label2.getName()=="1King") {
			System.out.println("The white side succeed!");
			win=true;
			new wVictory();
				firThread.sendMessage("/" + chessPeerName + " /chess "
										+"Win");
		}else if (label2.getName()=="2King") {
			System.out.println("The black side succeed!");
			win2=true;
			new bVictory();
				firThread.sendMessage("/" + chessPeerName + " /chess "
						+"Win2");
		}
	
	}
	// connect to host
	public boolean connectServer(String ServerIP, int ServerPort) throws Exception
		{
			try
			{
				//get the host port
				chessSocket = new Socket(ServerIP, ServerPort);
				// get input stream
				inputData = new DataInputStream(chessSocket.getInputStream());
				// get output stream
				outputData = new DataOutputStream(chessSocket.getOutputStream());
				firThread.start();// where the thread start 
				
				
				return true;
			}
			catch (IOException ex)
			{
				statusText.setText("连接失败! \n");
			}
			return false;
		}
	public  void arrivemessage(int Sx, int Sy, int Dx, int Dy, String label6) {
		// TODO Auto-generated method stub
		// for pawn 
		try {
			
			for (int i = 0; i < 8; i++) {
				if (label6.equals("1"+i+"Pawn")) {
					rule.justmove(Sx, Sy, Dx,Dy,pieces[1][i]);
					label=pieces[1][i];//keep this 
					isEnabled=true;
				}
				else if (label6.equals("2"+i+"Pawn")) {
					rule.justmove(Sx, Sy,Dx,Dy,pieces[2][i]);
					label=pieces[2][i];
					isEnabled=true;
				}
			}
			//for WQ
			if (label6.equals("2King")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[3][4]);
				isEnabled=true;
			}
			if (label6.equals("1King")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[0][4]);
				isEnabled=true;
			}
			if (label6.equals("2Queen")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[3][3]);
				isEnabled=true;
			}
			if (label6.equals("3Queen")) {
				rule.justmove(Sx, Sy,Dx,Dy,this.label6);
				isEnabled=true;
			}
			if (label6.equals("4Queen")) {
				rule.justmove(Sx, Sy,Dx,Dy,label5);
				isEnabled=true;
			}
			if (label6.equals("1Queen")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[0][3]);
				isEnabled=true;
			}
			if (label6.equals("21Knight")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[3][1]);
				isEnabled=true;
			}
			if (label6.equals("26Knight")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[3][6]);
				isEnabled=true;
			}
			if (label6.equals("11Knight")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[0][1]);
				isEnabled=true;
			}
			if (label6.equals("16Knight")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[0][6]);
				isEnabled=true;
			}
			if (label6.equals("20Rook")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[3][0]);
				isEnabled=true;
			}
			if (label6.equals("27Rook")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[3][7]);
				isEnabled=true;
			}
			if (label6.equals("10Rook")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[0][0]);
				isEnabled=true;
			}
			if (label6.equals("17Rook")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[0][7]);
				isEnabled=true;
			}
			if (label6.equals("22Bishop")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[3][2]);
				isEnabled=true;
			}
			if (label6.equals("25Bishop")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[3][5]);
				isEnabled=true;
			}
			if (label6.equals("12Bishop")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[0][2]);
				isEnabled=true;
			}
			if (label6.equals("15Bishop")) {
				rule.justmove(Sx, Sy,Dx,Dy,pieces[0][5]);
				isEnabled=true;
			}
			
			if (isEnabled&&whoismaster.equals("Master")) {
				chessPlayClick=2;
			}else if(isEnabled&&whoismaster.equals("Guest")){
				chessPlayClick=3;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void arriveEatMessage(int Sx, int Sy, int Dx, int Dy, String label6,
			String label7) {
		// TODO Auto-generated method stub
		System.out.println("arrived2");
		for (int i = 0; i < 8; i++) {
			if (label6.equals("1"+i+"Pawn")) {
				rule.justmove(Sx, Sy, Dx,Dy,pieces[1][i]);
				isEnabled=true;
				}
			else if (label6.equals("2"+i+"Pawn")) {
				System.out.println("move");
				rule.justmove(Sx, Sy,Dx,Dy,pieces[2][i]);
				 isEnabled=true;
				}
		}
		//for WQ
		if (label6.equals("2King")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[3][4]);
			 isEnabled=true;
		}
		if (label6.equals("1King")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[0][4]);
			 isEnabled=true;
		}
		if (label6.equals("3Queen")) {
			rule.justmove(Sx, Sy,Dx,Dy,this.label6);
			isEnabled=true;
		}
		if (label6.equals("4Queen")) {
			rule.justmove(Sx, Sy,Dx,Dy,label5);
			isEnabled=true;
		}
		if (label6.equals("2Queen")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[3][3]);
			 isEnabled=true;
		}
		if (label6.equals("1Queen")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[0][3]);
			 isEnabled=true;
		}
		if (label6.equals("21Knight")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[3][1]);
			 isEnabled=true;
		}
		if (label6.equals("26Knight")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[3][6]);
			 isEnabled=true;
		}
		if (label6.equals("11Knight")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[0][1]);
			 isEnabled=true;
		}
		if (label6.equals("16Knight")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[0][6]);
			 isEnabled=true;
		}
		if (label6.equals("20Rook")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[3][0]);
			 isEnabled=true;
		}
		if (label6.equals("27Rook")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[3][7]);
			 isEnabled=true;
		}
		if (label6.equals("10Rook")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[0][0]);
			 isEnabled=true;
		}
		if (label6.equals("17Rook")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[0][7]);
			 isEnabled=true;
		}
		if (label6.equals("22Bishop")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[3][2]);
			 isEnabled=true;
		}
		if (label6.equals("25Bishop")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[3][5]);
			 isEnabled=true;
		}
		if (label6.equals("12Bishop")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[0][2]);
			 isEnabled=true;
		}
		if (label6.equals("15Bishop")) {
			rule.justmove(Sx, Sy,Dx,Dy,pieces[0][5]);
			 isEnabled=true;
		}
		System.out.println(label7);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j <8; j++) {
				if (pieces[i][j].getName().equals(label7)) {
					label4=pieces[i][j];
					remove(pieces[i][j]);
					pieces[i][j]=null;
					pieces[i][j]=label4;
				}
			}
		}
		if (isEnabled&&whoismaster.equals("Master")) {
			chessPlayClick=2;
		}else if(isEnabled&&whoismaster.equals("Guest")){
			chessPlayClick=3;
		}
	}
	public void arriveCastingmessageAndPromotion(String string, String string2) {
		// TODO Auto-generated method stub
		System.out.println("arrived3");
		int x3=pieces[0][4].getLocation().x;
		int x23=pieces[3][4].getLocation().x;
		int y3=pieces[3][4].getLocation().y;
		if (string.equals("1King")) {
			if (string2.equals("10Rook")) {
				pieces[0][4].setLocation(pieces[0][4].getLocation().x-2*side, pieces[0][4].getLocation().y);
				pieces[0][0].setLocation(pieces[0][0].getLocation().x+3*side, pieces[0][0].getLocation().y);
			}else if (string2.equals("17Rook")) {
				pieces[0][4].setLocation(pieces[0][7].getLocation().x-side, pieces[0][7].getLocation().y);
				pieces[0][7].setLocation(x3+side, 0);
			}
		}else if (string.equals("2King")) {
			if (string2.equals("20Rook")) {
				pieces[3][4].setLocation(pieces[3][4].getLocation().x-2*side, pieces[3][4].getLocation().y);
				pieces[3][0].setLocation(pieces[3][0].getLocation().x+3*side, pieces[3][0].getLocation().y);
			}else if (string2.equals("27Rook")) {
				pieces[3][4].setLocation(pieces[3][7].getLocation().x-side, pieces[3][7].getLocation().y);
				pieces[3][7].setLocation(x23+side, y3);
			}
		}else if (string.equals("Promotion")) {
			System.out.println("in");
			for (int i = 0; i < 8; i++) {
				if (string2.equals("1"+i+"Pawn")) {
					pieces[1][i].setIcon(new ImageIcon("src/Graph/BQueen.png"));
					label5=pieces[1][i];
					pieces[1][i].setName("4Queen");
					System.out.println("4Queen");
					this.repaint();
					if (rule.queenThreat(pieces[1][i], pieces[3][4])) {
					JOptionPane.showMessageDialog(null, "White King Is In A Threat Now","Warning",
								JOptionPane.WARNING_MESSAGE);
					}
					nq2=i;
				}else if(string2.equals("2"+i+"Pawn")) {
					pieces[2][i].setIcon(new ImageIcon("src/Graph/Queen.png"));
					pieces[2][i].setName("3Queen");
					label6=pieces[2][i];
					System.out.println("3Queen");
					this.repaint();
					if (rule.queenThreat(pieces[2][i], pieces[0][4])) {
						JOptionPane.showMessageDialog(null, "Black King Is In A Threat Now","Warning",
								JOptionPane.WARNING_MESSAGE);
					}
					nq=i;
				}
			}
			
		}
			
		
		if (isEnabled&&whoismaster.equals("Master")) {
			chessPlayClick=2;
		}else if(isEnabled&&whoismaster.equals("Guest")){
			chessPlayClick=3;
		}
		
	}
	public void arriveThreat(String  paString) {
		// TODO Auto-generated method stub
		System.out.println("arrived 4");
		System.out.println(paString);
		if (paString.equals("Win")) {
			if (isEnabled&&whoismaster.equals("Guest")) {
				new wVictory();
			}
		}else if (paString.equals("Win2")) {
			 if(isEnabled&&whoismaster.equals("Master")){
				 new bVictory();
			}
		}
		else if (paString.equals("AThreat")) {
			if (isEnabled&&whoismaster.equals("Guest")) {
				JOptionPane.showMessageDialog(null, "Black King Is In AThreat Now","Warning",
						JOptionPane.WARNING_MESSAGE);
			}else if(isEnabled&&whoismaster.equals("Master")){
				JOptionPane.showMessageDialog(null, "White King Is In AThreat Now","Warning",
						JOptionPane.WARNING_MESSAGE);
			}
		}
	
	}
	
}

