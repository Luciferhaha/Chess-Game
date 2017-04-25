

import javax.swing.JLabel;
public class Check_isexisted {
	public final static int gap=50;//实际棋盘和边框的间距
	public  final static int side=85;//小正方形的边框
	public ChessPoint Point;
	public int x,y;
	public boolean isexisted[][]=new boolean[8][8];
	public void SetChessPoint(JLabel label){
		//pawn
		 x=(label.getLocation().x-gap)/side;
		 y=(label.getLocation().y)/side;
		Point=new ChessPoint(x, y);
		isexisted[x][y]=true;
	}
	public void findChessPoint (JLabel label) {
		 x=(label.getLocation().x-gap)/side;
		 y=(label.getLocation().y)/side;
		Point=new ChessPoint(x, y);
	}

}
