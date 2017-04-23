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

import org.omg.CORBA.portable.ValueBase;

public class ChessBoard extends JPanel implements MouseListener{
	public final static int width=800;
	public final static int height=800;
// implement the checkerboard and frame
	public final static int gap=50;
// the frame of each square
	public  final static int side=85;
	public int row=8,column=8;
	public int gap2=10;
	public JLabel pieces[][]=new JLabel[4][8];
	public ChessPoint Point;
	public boolean excited[][]=new boolean[8][8];
	public  PiecesMove  rule;
	public JLabel text;
	public JLabel label=null;
	/*click the piece*****************
	 when chessManClick = true ,The pieces are flashing,Start thread response
	 chessManClick = false the pieces stop flashing*/
	public boolean chessmanclick;
	/***Control the player to move**************************/
	/*chessPlayClick=1 one side stop move*/
	/*chessPlayClick=2  the other side move
	chessPlayClick=3  both don't move*/	
	int chessPlayClick=2;
	//control the flashing thread of the pieces
		thread hThread;
	static int left,right;
	public ChessBoard() {
		// TODO Auto-generated constructor stub
		insertimage();
		setlocation();
		for (int i = 0; i <= 3; i++) {
			for (int j= 0; j<= 7; j++) {
				this.add(pieces[i][j]);
			pieces[i][j].addMouseListener(this);
			
			}
		 }
		this.addMouseListener(this);
		this.setLayout(null);
		rule=new PiecesMove();
		
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
		
		//Bishops
		pieces[0][2]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[0][2].setName("1Bishop");
		pieces[0][5]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[0][5].setName("1Bishop");
		pieces[3][2]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][2].setName("2Bishop");
		pieces[3][5]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][5].setName("2Bishop");
		//rooks
		pieces[0][0]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[0][0].setName("1Rook");
		pieces[0][7]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[0][7].setName("1Rook");
		pieces[3][0]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][0].setName("2Rook");
		pieces[3][7]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][7].setName("2Rook");
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
		//Bishops
		pieces[0][2].setBounds(gap+2*side, 0, side, side);
		pieces[0][5].setBounds(gap+5*side, 0, side, side);
		pieces[3][2].setBounds(gap+2*side, 7*side, side, side);
		pieces[3][5].setBounds(gap+5*side, 7*side, side, side);
		//rooks
		pieces[0][0].setBounds(gap, 0, side, side);
		pieces[0][7].setBounds(gap+7*side, 0, side, side);
		pieces[3][0].setBounds(gap, 7*side, side, side);
		pieces[3][7].setBounds(gap+7*side, 7*side,side, side);
	}
	public void SetChessPoint(JLabel label){
		//pawn
		int x=(label.getLocation().x-gap)/side;
		int y=(label.getLocation().y)/side;
		Point=new ChessPoint(x, y);
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(gap, 0,row*side,column*side);
		for (int i = 0; i < row; i=i+1) {//row
			for (int j = 0; j < column; j=j+2) {//column
				g.setColor(Color.white);
				g.fillRect(gap+j*side+(i%2)*side,i*side, side, side);//use the remainder to dislocate
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x,y;
   //	int count=0;
		chessmanclick=false;
  //	move pieces
  //    acquire the location		
			x=(e.getXOnScreen()-gap)/side;
			y=(e.getYOnScreen()-46)/side;
	
//move pawns
		for (int j = 0; j <=7; j++) {
		if (e.getSource().equals(pieces[1][j])||e.getSource().equals(pieces[2][j])) {
			label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
//				count++;
				}
			}
	for (int i = 0; i <=7; i++) {
			if ((e.getSource().equals(this))&&((label.equals(pieces[1][i])||(label.equals(pieces[2][i]))))) {
				SetChessPoint(label);
				rule.Pawn(label, e,x,y,Point);
				hThread.end();
			}
			
		}
				
//			}
				//move queens 
			if (e.getSource().equals(pieces[0][3])||e.getSource().equals(pieces[3][3])) {
				label=(JLabel) e.getSource();
				if (hThread!=null) {
					hThread.end();
				}
				hThread=new thread(label);
				hThread.start();
					}
			if (e.getSource().equals(this)&&(label.equals(pieces[0][3])||label.equals(pieces[3][3]))) {
				SetChessPoint(label);
				rule.Queen(label,x, y,Point);
				hThread.end();
			}
	      
				//move Kings
					if (e.getSource().equals(pieces[0][4])||e.getSource().equals(pieces[3][4])) {
						label=(JLabel) e.getSource();
						if (hThread!=null) {
							hThread.end();
						}
						hThread=new thread(label);
						hThread.start();
					}
					if (e.getSource().equals(this)&&(label.equals(pieces[0][4])||label.equals(pieces[3][4]))) {
						SetChessPoint(label);
						rule.King(label,x,y,Point);
						hThread.end();
					}
				//move rooks
					if (e.getSource().equals(pieces[0][0])||e.getSource().equals(pieces[0][7])||e.getSource().equals(pieces[3][0])||e.getSource().equals(pieces[3][7])) {
						label=(JLabel) e.getSource();
						if (hThread!=null) {
							hThread.end();
						}
						hThread=new thread(label);
						hThread.start();
					}
					if (e.getSource().equals(this)&&(label.equals(pieces[0][0])||label.equals(pieces[0][7])||label.equals(pieces[3][0])||label.equals(pieces[3][7]))) {
						SetChessPoint(label);
						rule.Rook(label,x,y,Point);
					}
                  
				// move knights
					if (e.getSource().equals(pieces[0][1])||e.getSource().equals(pieces[0][6])||e.getSource().equals(pieces[3][1])||e.getSource().equals(pieces[3][6])) {
						label=(JLabel) e.getSource();
						if (hThread!=null) {
							hThread.end();
						}
						hThread=new thread(label);
						hThread.start();
					}
					if (e.getSource().equals(this)&&label.equals(pieces[0][1])||label.equals(pieces[0][6])||label.equals(pieces[3][1])||label.equals(pieces[3][6])) {
						SetChessPoint(label);
						rule.knight(label,x,y,Point);
					}
				
				//move bishops
					if (e.getSource().equals(pieces[0][2])||e.getSource().equals(pieces[0][5])||e.getSource().equals(pieces[3][2])||e.getSource().equals(pieces[3][5])) {
						label=(JLabel) e.getSource();
						if (hThread!=null) {
							hThread.end();
						}
						hThread=new thread(label);
						hThread.start();
					}
					if (e.getSource().equals(this)&&(label.equals(pieces[0][2])||label.equals(pieces[0][5])||label.equals(pieces[3][2])||label.equals(pieces[3][5]))) {
						SetChessPoint(label);
						rule.Bisshop(label, x, y, Point);
					}
				}
//				}
//			}
//		}

//	}
	
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
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}

