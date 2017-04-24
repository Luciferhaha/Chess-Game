import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

//handling the algorithm of different pieces

//bishop king knight pawn_black pawn_white queen rook
//兵变身，兵吃过路兵，王车短易位和长易位
//行棋权 白棋先走，双方轮流走棋
//一着棋，除参与易位的车以外，任何棋子都不能越过被其他棋子占据的格子
//一旦吃掉，必须从棋盘上拿走
//让不同类比的棋子各司其职（执行各自的方法）
public class PiecesMove {
	public JLabel[][] pieces;
	public int start[][];
	public int end[][];
	public int side=85;
	public PiecesMove() {
		// TODO Auto-generated constructor stub
		//总共32个棋子
		start=new int[8][8];
		end=new int[8][8];
		pieces =new JLabel[4][8];
	}
	
	public void Pawn(JLabel pieces,MouseEvent e, int x, int y, ChessPoint point){
	//其第一步可以向前走一或两格，以后每次只可向前走一步，不可往后走
	//吃对方的棋子则是向前打斜来吃
		//黑棋
		int d=Math.abs(point.col()-y);
		int d2=point.row()-x;
		
		int count =e.getClickCount();
		char value=pieces.getName().charAt(0);
		int values=value-'0';
		if (d2==0&&(d==1||d==2)) {
			switch (values) {
			//white piece
			case 1:
				if (count==1&&d==2) {
					pieces.setLocation(pieces.getLocation().x, pieces.getLocation().y-2*side);
				}else {
					pieces.setLocation(pieces.getLocation().x, pieces.getLocation().y-side);
				}
				break;
				// black piece
			case 2:
				if (count==1&&d==2) {
					pieces.setLocation(pieces.getLocation().x, pieces.getLocation().y+2*side);
				}else {
					pieces.setLocation(pieces.getLocation().x, pieces.getLocation().y+side);
				}
				break;
			
			
			}
			
		}
			
		}
	
		
	

	public void Queen(JLabel pieces, int x,int y,ChessPoint point) {
		//上下左右，四个方向斜
		int dx=(point.row()-x);
		int dy=(point.col()-y);
		char c=pieces.getName().charAt(0);
		int value=c-'0';
		if (dx==0||dy==0||Math.abs(dx)==Math.abs(dy)) {
			switch (value) {
			case 1:
					pieces.setLocation(pieces.getLocation().x+dx*side, pieces.getLocation().y+dy*side);
				break;
			case 2:
					pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
				break;
			}
			
		}
	}
	public void King(JLabel pieces, int x, int y, ChessPoint point) {
		int dx=(point.row()-x);
		int dy=(point.col()-y);
		char c=pieces.getName().charAt(0);
		int value=c-'0';
		if (dx==0||dy==0||((Math.abs(dx)==Math.abs(dy)&&(Math.abs(dx)==1&&(Math.abs(dy)==1))))) {
			switch (value) {
			case 1:
				pieces.setLocation(pieces.getLocation().x+dx*side, pieces.getLocation().y+dy*side);
				break;

			case 2:
				pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
				break;
			}
		}
	}
	public void Rook(JLabel pieces, int x, int y, ChessPoint point) {
		//up and down and left and right
		int dx=(point.row()-x);
		int dy=(point.col()-y);
		char c=pieces.getName().charAt(0);
		int value=c-'0';
		if (dx==0||dy==0) {
			switch (value) {
			case 1:
				pieces.setLocation(pieces.getLocation().x+dx*side, pieces.getLocation().y+dy*side);
				break;

			case 2:
				pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
				break;
			}
		}
		
	}
	
	public void knight(JLabel pieces, int x, int y, ChessPoint point) {
		// TODO Auto-generated method stub
		int dx=(point.row()-x);
		int dy=(point.col()-y);
		char c=pieces.getName().charAt(0);
		int value=c-'0';
		if ((Math.abs(dx)==1&&Math.abs(dy)==2)||(Math.abs(dx)==2&&Math.abs(dy)==1)) {
			switch (value) {
			case 1:
				pieces.setLocation(pieces.getLocation().x+dx*side, pieces.getLocation().y+dy*side);
				break;
			case 2:
				pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
				break;
			}
		}
	}
	public void Bisshop(JLabel pieces,int x,int y,ChessPoint point) {
		int dx=(point.row()-x);
		int dy=(point.col()-y);
		char c=pieces.getName().charAt(0);
		int value=c-'0';
		if (Math.abs(dx)==Math.abs(dy)) {
			switch (value) {
			case 1:
				pieces.setLocation(pieces.getLocation().x+dx*side, pieces.getLocation().y+dy*side);
				break;
			case 2:
				pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
				break;
			}
		}
	}
	//兵的升变
	public void promotion() {
		//当兵子走至对方底线，玩家可选择把该兵升级成车，马，象或后。
	}
	public void En_Passant() {
		//吃路过兵
		//1.对方的兵必须是在原位第一次移动且直进两格
		//2.形成本方有兵与其横向紧贴并列
		//3.吃的方式为斜进，并拿掉对方棋子
	}
	public void Castling() {
		//王车长短易位
		//1.王车从来没有移位
		//2.王和车之间没有棋子
		//3.王的原始格子或者将要越过的格子或者将要占据的格子正受到对方棋子的攻击
		//王往右两个或者往左两个，车换到王的里侧
	}
	public void Removepieces(){
		//易掉被吃掉的棋子
		//包括路过吃兵的情况
	}

	
}
