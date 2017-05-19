package Network;
import java.awt.TextField;
import java.awt.TexturePaint;
//不能连续同一方走棋按照count和getname来解决
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;

import javax.swing.JLabel;


//处理不同棋子的算法
//兵，后，王，车，象，马
//兵变身，兵吃过路兵，王车短易位和长易位
//行棋权 白棋先走，双方轮流走棋
//一着棋，除参与易位的车以外，任何棋子都不能越过被其他棋子占据的格子
//一旦吃掉，必须从棋盘上拿走
//让不同类比的棋子各司其职（执行各自的方法）
public class PiecesMove2 {
	public JLabel[][] pieces;
	public int start[][];
	public int end[][];
	public int side=85;
	public final static int gap=35;//实际棋盘和边框的间距
	public boolean hasExisted;
	public Point_Operation2 findPoint=new Point_Operation2();
	public  boolean rightmove;
	public  boolean  haseaten;
	public boolean hascasting;
//	public FIRThread netthread;
//	public Socket chessSocket;
//	public DataInputStream inputData;
//	public DataOutputStream outputData;
//	public FIRThread firThread;
//	public TextField statusText;
//	public String chessSelfName = null;
//	public String chessPeerName = null;
//	public String host = null;
//	public int port = 4331;
	public PiecesMove2(Point_Operation2 check) {
		// TODO Auto-generated constructor stub
		//总共32个棋子
		start=new int[8][8];
		end=new int[8][8];
//		pieces =new JLabel[4][8];
		findPoint=check;
//		netthread=new FIRThread(this);
	}
	//move rule 
	public void Pawn(JLabel pieces, int x, int y, ChessPoint2 point){
	//其第一步可以向前走一或两格，以后每次只可向前走一步，不可往后走
	//吃对方的棋子则是向前打斜来吃
		//黑棋
		int d=(point.col()-y);
		int d1=Math.abs(d);
		int d2=point.row()-x;
		char value=pieces.getName().charAt(0);
		if ((d2==0&&(d1==1||d1==2))) {
			switch (value) {
			//黑棋
			case '1':
				if (point.col()==1&&d1==2) {
					pieces.setLocation(pieces.getLocation().x, pieces.getLocation().y+2*side);
					findPoint.isexisted[point.row()][point.col()]=0;
					findPoint.isexisted[x][y]=1;
					rightmove=true;
					
					
				}//the piece can not come back
				else if(d==-1){
					pieces.setLocation(pieces.getLocation().x, pieces.getLocation().y+side);
					findPoint.isexisted[point.row()][point.col()]=0;
					findPoint.isexisted[x][y]=1;
					rightmove=true;
				}
				break;
				//白棋
			case '2':
				if (point.col()==6&&d1==2) {
					pieces.setLocation(pieces.getLocation().x, pieces.getLocation().y-2*side);
					findPoint.isexisted[point.row()][point.col()]=0;
					findPoint.isexisted[x][y]=1;
//					netthread.sendMessage(sndMessage);
					rightmove=true;
				}//the piece can not come back
				else if (d==1) {
					pieces.setLocation(pieces.getLocation().x, pieces.getLocation().y-side);
					findPoint.isexisted[point.row()][point.col()]=0;
					findPoint.isexisted[x][y]=1;
					rightmove=true;
				}
				break;
			}
			
			}
	}

	public void Queen(JLabel pieces, int x,int y,ChessPoint2 point) {
		//上下左右，四个方向斜
		int dx=(point.row()-x);
		int dy=(point.col()-y);
			if (!(dy==0&&dx==0)) {//prevent it haven't move
				if ((dx==0||dy==0||Math.abs(dx)==Math.abs(dy))) {
					if (!Judgehaspieces(point.row(), point.col(), x, y)) {
						pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
						findPoint.isexisted[point.row()][point.col()]=0;
						findPoint.isexisted[x][y]=1;
						rightmove=true;
					}
					
				}
			}
		}
	public void King(JLabel pieces, int x, int y, ChessPoint2 point) {
		int dx=(point.row()-x);
		int dy=(point.col()-y);
		if (!(dx==0&&dy==0)) {
			if ((dx==0&&Math.abs(dy)==1)||(dy==0&&Math.abs(dx)==1)||((Math.abs(dx)==Math.abs(dy)&&(Math.abs(dx)==1&&(Math.abs(dy)==1))))) {
				
				if (!Judgehaspieces(point.row(), point.col(), x, y)) {
					pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
					findPoint.isexisted[point.row()][point.col()]=0;
					findPoint.isexisted[x][y]=1;
					rightmove=true;
				}
			}
		}
	}
	public void Rook(JLabel pieces, int x, int y, ChessPoint2 point) {
		//up and down and left and right
		int dx=(point.row()-x);
		int dy=(point.col()-y);
		if (!(dx==0&&dy==0)) {
		if ((dx==0||dy==0)&&(!(dx==0&&dy==0))) {
			if (!Judgehaspieces(point.row(), point.col(), x, y)) {
				pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
				findPoint.isexisted[point.row()][point.col()]=0;
				findPoint.isexisted[x][y]=1;
				rightmove=true;
				}
			}
		}
	}
	public void knight(JLabel pieces, int x, int y, ChessPoint2 point) {
		// TODO Auto-generated method stub
		int dx=(point.row()-x);
		int dy=(point.col()-y);
		
		if ((Math.abs(dx)==1&&Math.abs(dy)==2)||(Math.abs(dx)==2&&Math.abs(dy)==1)) {
				pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
				findPoint.isexisted[point.row()][point.col()]=0;
				findPoint.isexisted[x][y]=1;
				rightmove=true;
				
		
		}
	}
	public void Bisshop(JLabel pieces,int x,int y,ChessPoint2 point) {
		int dx=(point.row()-x);
		int dy=(point.col()-y);
		if (!(dx==0&&dy==0)) {
		if ((Math.abs(dx)==Math.abs(dy))&&(!(dx==0&&dy==0))) {
			if (!Judgehaspieces(point.row(), point.col(), x, y)) {
				pieces.setLocation(pieces.getLocation().x-dx*side, pieces.getLocation().y-dy*side);
				findPoint.isexisted[point.row()][point.col()]=0;
				findPoint.isexisted[x][y]=1;
				rightmove=true;
				}
			}
		}
		}
    //	eat rule 
	public void PawnEatRule(JLabel label, JLabel label2){
		char c=label.getName().charAt(0);
		findPoint.SetChessPoint(label2);
		int x2=findPoint.x;
		int y2=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x2-x1;
		int d1=y2-y1;
		switch (c) {
		case '1':
			if (d1==1&&(d==1||d==-1)) {
				label.setLocation(label2.getLocation().x, label2.getLocation().y);
				findPoint.isexisted[x1][y1]=0;
				findPoint.isexisted[x2][y2]=1;
				haseaten=true;
			}
			break;

		case '2':
			if (d1==-1&&(d==-1||d==1)) {
				label.setLocation(label2.getLocation().x, label2.getLocation().y);
				findPoint.isexisted[x1][y1]=0;
				findPoint.isexisted[x2][y2]=1;
				haseaten=true;
			}
			break;
		}
	}
	public void QueenEatRule(JLabel label, JLabel label2) {
		
		findPoint.SetChessPoint(label2);
		int x2=findPoint.x;
		int y2=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x2-x1;
		int d1=y2-y1;
		if (d==0||d1==0||Math.abs(d)==Math.abs(d1)) {
			if (!Judgehaspieces(x1,y1,x2,y2)) {

				label.setLocation(label2.getLocation().x, label2.getLocation().y);
				haseaten=true;
			}
		}
		
	}
	public void KingEatRule(JLabel label, JLabel label2) {
		findPoint.SetChessPoint(label2);
		int x2=findPoint.x;
		int y2=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x2-x1;
		int d1=y2-y1;
		if ((Math.abs(d)==0&&Math.abs(d1)==1)||(Math.abs(d)==1&&Math.abs(d1)==0)||((Math.abs(d)==Math.abs(d1)&&(Math.abs(d)==1&&(Math.abs(d1)==1))))) {
			 if (!Judgehaspieces(x1,y1,x2,y2)) {
			label.setLocation(label2.getLocation().x, label2.getLocation().y);
			haseaten=true;
			  }
			}
		
	}
	public void RookEatRule(JLabel label, JLabel label2) {
		findPoint.SetChessPoint(label2);
		int x=findPoint.x;
		int y=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x-x1;
		int d1=y-y1;
		if (d==0||d1==0) {
			 if (!Judgehaspieces(x1,y1,x,y)) {
			label.setLocation(label2.getLocation().x, label2.getLocation().y);
			haseaten=true;
			 }
		}
	}
	public void knightEatRule(JLabel label, JLabel label2) {
		findPoint.SetChessPoint(label2);
		int x2=findPoint.x;
		int y2=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x2-x1;
		int d1=y2-y1;
		if ((Math.abs(d)==1&&Math.abs(d1)==2)||(Math.abs(d)==2&&Math.abs(d1)==1)) {
			
			label.setLocation(label2.getLocation().x, label2.getLocation().y);
			haseaten=true;
		}
	}
	public void BisshopEatRule(JLabel label, JLabel label2) {
		findPoint.SetChessPoint(label2);
		int x2=findPoint.x;
		int y2=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x2-x1;
		int d1=y2-y1;
		if (Math.abs(d)==Math.abs(d1)) {
			 if (!Judgehaspieces(x1,y1,x2,y2)) {
			label.setLocation(label2.getLocation().x, label2.getLocation().y);
			haseaten=true;
			 }
		}
	}
	public void En_Passant() {
		//吃路过兵
		//1.对方的兵必须是在原位第一次移动且直进两格
		//2.形成本方有兵与其横向紧贴并列
		//3.吃的方式为斜进，并拿掉对方棋子
	}
	public void Castling(JLabel label, JLabel label2) {//this part l haven't done
		//王车长短易位
		//1.王车从来没有移位
		//2.王和车之间没有棋子
		//3.王的原始格子或者将要越过的格子或者将要占据的格子正受到对方棋子的攻击
		//王往右两个或者往左两个，车换到王的里侧
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		findPoint.SetChessPoint(label2);
		int x2=findPoint.x;
		int y2=findPoint.y;
		int d1=x2-x1;
		if (d1==3) {
			//short distance casting
			if (!Judgehaspieces(x1, y1, x2, y2)) {
				int x3=label.getLocation().x;
				int y3=label.getLocation().y;
				label.setLocation(label2.getLocation().x-side, label2.getLocation().y);
				label2.setLocation(x3+side, y3);
				hascasting=true;
			}
		}else if(d1==-4){// long distance casting
			if (!Judgehaspieces(x1, y1, x2, y2)) {
				
				label.setLocation(label.getLocation().x-2*side, label.getLocation().y);
				label2.setLocation(label2.getLocation().x+3*side, label2.getLocation().y);
				hascasting=true;
			}
		}
		
	}
	boolean checkPawnPosition(JLabel label,int y){
		if (label.getName().charAt(0)==1) {
			if (y==7) {
				return true;
			}
		}else {
			if (y==0) {
				return true;
			}
		}
		return false;
	}
	public boolean Judgehaspieces(int x1,int y1, int x2, int y2) {
		int d1=x2-x1;
		int d2=y2-y1;
		if (Math.abs(d1)>1&&Math.abs(d2)>1) {
			if(d1>0&&d2<0){
				for (int i = 1; i < d1; i++) {
						if (findPoint.isexisted[x1+i][y1-i]!=0) {
							return true;
					
				}
			   }
			return false;
			}else if (d1>0&&d2>0) {
				for (int i = 1; i < d1; ++i) {
					
						if (findPoint.isexisted[x1+i][y1+i]!=0) {
							return true;
						}
					
				}
				return false;
			}else if(d1<0&&d2<0){
				for (int i = -1; i >d1; --i) {
					
						if (findPoint.isexisted[x1+i][y1+i]!=0) {
							return true;
						}
					
				}
				return false;
			}else if (d1<0&&d2>0) {
				for (int i = -1; i > d1; --i) {
				
						if (findPoint.isexisted[x1+i][y1-i]!=0) {
							
							return true;
						}
					
				}
			}
			return false;
		}else if((Math.abs(d2)==0&&Math.abs(d1)==1)||(Math.abs(d2)==1&&Math.abs(d1)==0)||((Math.abs(d2)==Math.abs(d1)&&(Math.abs(d2)==1&&(Math.abs(d1)==1))))){
			return false;
		}else if (d1==0&&d2>0) {
			for (int i = 1; i <d2; i++) {
				if (findPoint.isexisted[x1][y1+i]!=0) {
					return true;
				}
			}
			return false;
		}else if (d1==0&&d2<0) {
			for (int i = -1; i > d2; i--) {
				if (findPoint.isexisted[x1][y1+i]!=0) {
					return true;
				}
			}
			return false;
		}else if (d1>0&&d2==0) {

			for (int i = 1; i <d1; i++) {
				if (findPoint.isexisted[x1+i][y1]!=0) {
					return true;
				}
			}
			return false;
		}else if (d1<0&&d2==0) {
			for (int i = -1; i > d2; i--) {
				if (findPoint.isexisted[x1+i][y1]!=0) {
					return true;
				}
			}
			return false;
		}else {
			return false;
		}
	}
	public void Kingprotections(){
		
		}
	public void justmove(int Sx, int Sy,int Dx, int Dy, JLabel pieces){
		System.out.println("l made it");
		pieces.setLocation(Dx, Dy);
		findPoint.isexisted[Sx][Sy]=0;
		findPoint.isexisted[(Dx-gap)/side][Dy/side]=1;
		
		
	}
	// 连接到主机
//	public boolean connectServer(String ServerIP, int ServerPort) throws Exception
//		{
//			try
//			{
//				// 取得主机端口
//				chessSocket = new Socket(ServerIP, ServerPort);
//				// 取得输入流
//				inputData = new DataInputStream(chessSocket.getInputStream());
//				// 取得输出流
//				outputData = new DataOutputStream(chessSocket.getOutputStream());
//				firThread.start();// where the thread start 
//				return true;
//			}
//			catch (IOException ex)
//			{
//				statusText.setText("连接失败! \n");
//			}
//			return false;
//		}
	
}
