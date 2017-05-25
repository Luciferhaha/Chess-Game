package Network;


import javax.swing.JLabel;
public class Point_Operation2 {
	public final static int gap=30;
	public final static int gap2=30;
	public  final static int side=85;
	public ChessPoint2 Point;
	public int x,y;
	public static int isexisted[][]=new int[8][8];
	public Point_Operation2() {
		// TODO Auto-generated constructor stub
		//initial the position
		for (int i = 2; i <6; i++) {
			for (int j = 0; j <8; j++) {
				isexisted[i][j]=0;
			}
		}
		for (int i = 0; i <8; i++) {
			isexisted[i][0]=1;
		}
		for (int i = 0; i <8; i++) {
			isexisted[i][1]=1;
		}
		for (int i = 0; i <8; i++) {
			isexisted[i][6]=1;
		}
		for (int i = 0; i <8; i++) {
			isexisted[i][7]=1;
		}
	
	}
	public void SetChessPoint(JLabel label){
		//pawn
		 x=(label.getLocation().x-gap)/side;
		 y=(label.getLocation().y-gap2)/side;
		 Point=new ChessPoint2(x, y);
			isexisted[x][y]=1;
	}
	public void findChessPoint (JLabel label) {
		 x=(label.getLocation().x-gap)/side;
		 y=(label.getLocation().y-gap2)/side;
		 Point=new ChessPoint2(x, y);
	}
	public void findlocation(int x1,int y2) {
		x=(x1*side)+gap;
		y=y2*side+gap2;
		Point =new ChessPoint2(x, y);
	}
}
