

import javax.swing.JLabel;
public class Check_isexisted {
	public final static int gap=275;//瀹為檯妫嬬洏鍜岃竟妗嗙殑闂磋窛
	public  final static int side=85;//灏忔鏂瑰舰鐨勮竟妗�
	public ChessPoint Point;
	public int x,y;
	public int isexisted[][]=new int[8][8];
	public Check_isexisted() {
		// TODO Auto-generated constructor stub
		//鍒濆鍖栦綅缃�
		for (int i = 0; i <8; i++) {
			for (int j = 0; j <8; j++) {
				isexisted[i][j]=0;
			}
		}
	}
	public void SetChessPoint(JLabel label){
		//pawn
		System.out.println(label.getLocation().x);
		 x=(label.getLocation().x)/side;
		 y=(label.getLocation().y)/side;
		Point=new ChessPoint(x, y);
		if (label.getName().charAt(0)=='1') {
			isexisted[x][y]=1;
		}else if(label.getName().charAt(0)=='2'){
			isexisted[x][y]=2;
		}
	}
	public void findChessPoint (JLabel label) {
		 x=(label.getLocation().x)/side;
		 y=(label.getLocation().y)/side;
		Point=new ChessPoint(x, y);
	}

}
