import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessBoard extends JPanel implements MouseListener,CallBack{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int width=800;
	public final static int height=800;
	public final static int place = 50;
	public final static int fixgap = 560;
	public final static int gap=275;//瀹為檯妫嬬洏鍜岃竟妗嗙殑闂磋窛
	public  final static int side=85;//灏忔鏂瑰舰鐨勮竟妗�
	public int row=8,column=8;
	public int gap2=10;
	public JLabel pieces[][]=new JLabel[4][8];
	public ChessPoint Point;
	public  PiecesMove rule;
	public JLabel text;
	public JLabel label=null;
	public Check_isexisted check=new Check_isexisted();
	public int count=0;
	/*鍗曞嚮妫嬪瓙**********************************/
	/*chessManClick = true 闂儊妫嬪瓙 骞剁粰绾跨▼鍝嶅簲*/
	/*chessManClick = false 鍚冩瀛� 鍋滄闂儊  骞剁粰绾跨▼鍝嶅簲*/
	public boolean chessmanclick;
	/*鎺у埗鐜╁璧版****************************/
	/*chessPlayClick=1 榛戞璧版*/
	/*chessPlayClick=2 鐧芥璧版 榛樿鐧芥鍏堣蛋*/
	/*chessPlayClick=3 鍙屾柟閮戒笉鑳借蛋妫�*/	
	int chessPlayClick=2;
	//鎺у埗妫嬪瓙闂儊鐨勭嚎绋�
		thread hThread;
		//鎶婄涓�娆＄殑鍗曞嚮妫嬪瓙缁欑嚎绋嬪搷搴�
	//鎺у埗妫嬪瓙娑堝け鐨勭嚎绋�
		Thread disappear;
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
			pieces[1][i].setBounds(place+gap2+i*side, side,65, 80);
			pieces[2][i].setBounds(place+gap2+i*side, 6*side,65, 80);
		}
		//kings
		pieces[0][4].setBounds(place+4*side, 0, side, side);
		pieces[3][4].setBounds(place+4*side, 7*side, side, side);
		//queens
		pieces[0][3].setBounds(place+3*side, 0, side, side);
		pieces[3][3].setBounds(place+3*side, 7*side, side, side);
		//knights
		pieces[0][1].setBounds(place+side, 0, side, side);
		pieces[0][6].setBounds(place+6*side, 0, side, side);
		pieces[3][1].setBounds(place+side, 7*side,side, side);
		pieces[3][6].setBounds(place+6*side,7*side,side, side);
		//rooks
		pieces[0][0].setBounds(place, 0, side, side);
		pieces[0][7].setBounds(place+7*side, 0, side, side);
		pieces[3][0].setBounds(place, 7*side, side, side);
		pieces[3][7].setBounds(place+7*side, 7*side,side, side);
		//Bishops
		pieces[0][2].setBounds(place+2*side, 0, side, side);
		pieces[0][5].setBounds(place+5*side, 0, side, side);
		pieces[3][2].setBounds(place+2*side, 7*side, side, side);
		pieces[3][5].setBounds(place+5*side, 7*side, side, side);
	}
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(place, 0,row*side,column*side);
		for (int i = 0; i < row; i=i+1) {//row
			for (int j = 0; j < column; j=j+2) {//column
				g.setColor(Color.white);
				g.fillRect(place+j*side+(i%2)*side,i*side, side, side);//鍒╃敤浣欐暟鏉ラ敊浣�
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int x,y;
		chessmanclick=false;

		
//		 move pieces
//      acquire the location		
			x=(e.getXOnScreen()-fixgap)/side;
			y=(e.getYOnScreen()-46)/side;
		//select  pieces
		// select pawns
		for (int j = 0; j <8; j++) {
			if (e.getSource().equals(pieces[1][j])||e.getSource().equals(pieces[2][j])) {
				if (e.getSource()!=label) {
					count=0;
				}
				label=(JLabel) e.getSource();
					if (hThread!=null) {
						hThread.end();
					}
				hThread=new thread(label);
				hThread.start();
				}
			}
		//select queens 
		if (e.getSource().equals(pieces[0][3])||e.getSource().equals(pieces[3][3])) {
			label=(JLabel) e.getSource();
			if (hThread!=null) {
				hThread.end();
			}
			hThread=new thread(label);
			hThread.start();
		}
		//select kings
		if (e.getSource().equals(pieces[0][4])||e.getSource().equals(pieces[3][4])) {
			label=(JLabel) e.getSource();
			if (hThread!=null) {
				hThread.end();
			}
			hThread=new thread(label);
			hThread.start();
		}
		//select rooks
		if (e.getSource().equals(pieces[0][0])||e.getSource().equals(pieces[0][7])||e.getSource().equals(pieces[3][0])||e.getSource().equals(pieces[3][7])) {
			label=(JLabel) e.getSource();
			if (hThread!=null) {
				hThread.end();
			}
			hThread=new thread(label);
			hThread.start();
		}
		// select knights
		if (e.getSource().equals(pieces[0][1])||e.getSource().equals(pieces[0][6])||e.getSource().equals(pieces[3][1])||e.getSource().equals(pieces[3][6])) {
			label=(JLabel) e.getSource();
			if (hThread!=null) {
				hThread.end();
			}
			hThread=new thread(label);
			hThread.start();
		}
		//select bishops
		if (e.getSource().equals(pieces[0][2])||e.getSource().equals(pieces[0][5])||e.getSource().equals(pieces[3][2])||e.getSource().equals(pieces[3][5])) {
			label=(JLabel) e.getSource();
			if (hThread!=null) {
				hThread.end();
			}
			hThread=new thread(label);
			hThread.start();
		}
		
		//move pieces
		if (label!=null&&e.getSource().equals(this)) {
			//move white pawn pieces
			if (chessPlayClick==2) {
				for (int i = 0; i <8; i++) {
					if (label.equals(pieces[2][i])) {
						check.findChessPoint(label);
						count++;
						rule.Pawn(label, e,x,y,check.Point,count,check);
						hThread.end();
						chessPlayClick=3;
					}
				}
			//move white queens
			if (label.equals(pieces[3][3])) {
				check.findChessPoint(label);
				rule.Queen(label,x, y,check.Point);
				hThread.end();
				chessPlayClick=3;
					}
			//move white king
			if (label.equals(pieces[3][4])) {
				check.findChessPoint(label);
				rule.King(label,x,y,check.Point);
				hThread.end();
				chessPlayClick=3;
				}
			//move white rooks
			if (label.equals(pieces[3][0])||label.equals(pieces[3][7])) {
				check.findChessPoint(label);
				rule.Rook(label,x,y,check.Point);
				hThread.end();
				chessPlayClick=3;
			}
			// move white knights
			if (label.equals(pieces[3][1])||label.equals(pieces[3][6])) {
				check.findChessPoint(label);
				rule.knight(label,x,y,check.Point);
				hThread.end();
				chessPlayClick=3;
			}
			//move white bishops
			if (label.equals(pieces[3][2])||label.equals(pieces[3][5])) {
				check.findChessPoint(label);
				rule.Bisshop(label, x, y, check.Point);
				hThread.end();
				chessPlayClick=3;
			}

			}	
			//move black pieces
			else if (chessPlayClick==3) {
				// move black pawns
				for (int i = 0; i <8; i++) {
					if (label.equals(pieces[1][i])) {
						check.findChessPoint(label);
						count++;
						rule.Pawn(label, e,x,y,check.Point,count,check);
						hThread.end();
						chessPlayClick=2;
			
					}
				}
				//move black queens
				if (label.equals(pieces[0][3])) {
					check.findChessPoint(label);
					rule.Queen(label,x, y,check.Point);
					hThread.end();
					chessPlayClick=2;
					}
				//move black kings
					if (label.equals(pieces[0][4])) {
						check.findChessPoint(label);
						rule.King(label,x,y,check.Point);
						hThread.end();
						chessPlayClick=2;
					}
				//move black rooks
					if (label.equals(pieces[0][0])||label.equals(pieces[0][7])) {
						check.findChessPoint(label);
						rule.Rook(label,x,y,check.Point);
						hThread.end();
						chessPlayClick=2;
					}
				//move black knights
					if (label.equals(pieces[0][1])||label.equals(pieces[0][6])) {
						check.findChessPoint(label);
						rule.knight(label,x,y,check.Point);
						hThread.end();
						chessPlayClick=2;
					}
				//move black bishops
					if (label.equals(pieces[0][2])||label.equals(pieces[0][5])) {
						check.findChessPoint(label);
						rule.Bisshop(label, x, y, check.Point);
						hThread.end();
						chessPlayClick=2;
					}
					
				}
			System.out.println(chessPlayClick);
			}
		//eat pieces
				for (int i = 0; i <4; i++) {
					for (int j = 0; j <8; j++) {
						
					}
				}
				if (e.getSource().equals(pieces)) {
					
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

