package Model_Chess;


import javax.swing.JLabel;
public class Point_Operation {
	public final static int gap=50;//实际棋盘和边框的间距
	public  final static int side=85;//小正方形的边框
	public ChessPoint Point;
	public int x,y;
	public static int isexisted[][]=new int[8][8];
	public Point_Operation() {
		// TODO Auto-generated constructor stub
		//初始化位置
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
		 y=(label.getLocation().y)/side;
		 Point=new ChessPoint(x, y);
			isexisted[x][y]=1;
	}
	public void findChessPoint (JLabel label) {
		 x=(label.getLocation().x-gap)/side;
		 y=(label.getLocation().y)/side;
		 Point=new ChessPoint(x, y);
	}
	public void findlocation(int x1,int y2) {
		x=(x1*side)+gap;
		y=y2*side;
		Point =new ChessPoint(x, y);
	}
}
