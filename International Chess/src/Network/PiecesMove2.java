package Network;
import java.awt.TextField;
import java.awt.TexturePaint;

import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalDate;

import javax.swing.JLabel;


//process different pieces about the algorithms
public class PiecesMove2 {
	public JLabel[][] pieces;
	public int start[][];
	public int end[][];
	public int side=85;
	public final static int gap=30;
	public final static int gap2=30;
	public boolean hasExisted;
	public Point_Operation2 findPoint=new Point_Operation2();
	public  boolean rightmove;
	public  boolean  haseaten;
	public boolean hascasting;
	public boolean inthreat;

	public PiecesMove2(Point_Operation2 check) {
		// TODO Auto-generated constructor stub
		//总共32个棋子
		start=new int[8][8];
		end=new int[8][8];
		findPoint=check;

	}
	//move rule 
	public void Pawn(JLabel pieces, int x, int y, ChessPoint2 point){
		//first it can move 2
		
		//black peice
		int d=(point.col()-y);
		int d1=Math.abs(d);
		int d2=point.row()-x;
		char value=pieces.getName().charAt(0);
		if ((d2==0&&(d1==1||d1==2))) {
			switch (value) {
		//black
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
				//white
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
			// how to restrict the eating must be on time ?
			for (int i = 0; i < 8; i++) {
				String recordString ="2"+i+"Pawn";
				if (y2==4&&y1==4&&Math.abs(d)==1&&label2.getName().equals(recordString)) {
					label.setLocation(label2.getLocation().x, label2.getLocation().y+side);
					findPoint.isexisted[x1][y1]=0;
					findPoint.isexisted[x2][y2+1]=1;
					findPoint.isexisted[x2][y2]=0;
					haseaten=true;
				}
			}
			break;

		case '2':
			if (d1==-1&&(d==-1||d==1)) {
				label.setLocation(label2.getLocation().x, label2.getLocation().y);
				findPoint.isexisted[x1][y1]=0;
				findPoint.isexisted[x2][y2]=1;
				haseaten=true;
			}
			// how to restrict the eating must be on time ?
			for (int i = 0; i < 8; i++) {
				String recordString2 ="1"+i+"Pawn";
			if (y2==3&&y1==3&&Math.abs(d)==1&&label2.getName().equals(recordString2)) {
				label.setLocation(label2.getLocation().x, label2.getLocation().y-side);
				findPoint.isexisted[x1][y1]=0;
				findPoint.isexisted[x2][y2-1]=1;
				findPoint.isexisted[x2][y2]=0;
				haseaten=true;
			}}
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
	public boolean PawnThreat(JLabel label,JLabel king){
		char c=label.getName().charAt(0);
		findPoint.SetChessPoint(king);
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
				inthreat=true;
				return true;
			}
			break;

		case '2':
			if (d1==-1&&(d==-1||d==1)) {
				haseaten=true;
				return true;
			}
			break;
		}
		return false;
		}
	public boolean queenThreat(JLabel label,JLabel king){
		findPoint.SetChessPoint(king);
		int x2=findPoint.x;
		int y2=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x2-x1;
		int d1=y2-y1;
		if (d==0||d1==0||Math.abs(d)==Math.abs(d1)) {
			if (!Judgehaspieces(x1,y1,x2,y2)) {
				inthreat=true;
				return true;
			}
		}
		return false;
	}
	public boolean kingThreat(JLabel label,JLabel king){
		findPoint.SetChessPoint(king);
		int x2=findPoint.x;
		int y2=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x2-x1;
		int d1=y2-y1;
		if ((Math.abs(d)==0&&Math.abs(d1)==1)||(Math.abs(d)==1&&Math.abs(d1)==0)||((Math.abs(d)==Math.abs(d1)&&(Math.abs(d)==1&&(Math.abs(d1)==1))))) {
			 if (!Judgehaspieces(x1,y1,x2,y2)) {
				 inthreat=true;
				 return true;
			  }
			}
		return false;
	}
	public boolean RookThreat(JLabel label,JLabel king){
		findPoint.SetChessPoint(king);
		int x=findPoint.x;
		int y=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x-x1;
		int d1=y-y1;
		if (d==0||d1==0) {
			 if (!Judgehaspieces(x1,y1,x,y)) {
				 inthreat=true;
				 return true;
			 }
		}
		return false;
	}
	public boolean KnightThreat(JLabel label,JLabel king){
		findPoint.SetChessPoint(king);
		int x2=findPoint.x;
		int y2=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x2-x1;
		int d1=y2-y1;
		if ((Math.abs(d)==0&&Math.abs(d1)==1)||(Math.abs(d)==1&&Math.abs(d1)==0)||((Math.abs(d)==Math.abs(d1)&&(Math.abs(d)==1&&(Math.abs(d1)==1))))) {
			 if ((Math.abs(d)==1&&Math.abs(d1)==2)||(Math.abs(d)==2&&Math.abs(d1)==1)) {
				 inthreat=true;
				 return true;
			}
		}
		return false;
	}
	public boolean bishopThreat(JLabel label ,JLabel king) {
		findPoint.SetChessPoint(king);
		int x2=findPoint.x;
		int y2=findPoint.y;
		findPoint.SetChessPoint(label);
		int x1=findPoint.x;
		int y1=findPoint.y;
		int d=x2-x1;
		int d1=y2-y1;
		if (Math.abs(d)==Math.abs(d1)) {
			 if (!Judgehaspieces(x1,y1,x2,y2)) {
			inthreat=true;
			return true;
			 }
		}
		return false;
	}
	public void Castling(JLabel label, JLabel label2) {//this part l haven't done

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
			for (int i = -1; i > d1; i--) {
				if (findPoint.isexisted[x1+i][y1]!=0) {
					return true;
				}
			}
			return false;
		}else {
			return false;
		}
	}

	public void justmove(int Sx, int Sy,int Dx, int Dy, JLabel pieces){
		System.out.println("l made it");
		pieces.setLocation(Dx, Dy);
		findPoint.isexisted[Sx][Sy]=0;
		
		findPoint.isexisted[(Dx-gap)/side][(Dy-gap2)/side]=1;
		
		
	}

}
