import java.awt.Label;

//处理不同棋子的算法
//兵，后，王，车，象，马
//兵变身，王车短易位和长易位
//让不同类比的棋子各司其职（执行各自的方法）
public class Chess {
	public Label[][] chess;
	public Chess() {
		// TODO Auto-generated constructor stub
		//总共32个棋子
		chess =new Label[3][7];
		JobDivision();
	}
	//职责划分
	public void JobDivision() {
		//两列士兵8*2
		for (int i = 0; i < 8; i++) {
			Soilder(chess[1][i]);
			Soilder(chess[2][i]);
		}
		//双王
		King(chess[0][4]);
		King(chess[3][4]);
		//双后
		Queen(chess[0][3]);
		Queen(chess[3][3]);
		//四车
		Rook(chess[0][0]);
		Rook(chess[0][7]);
		Rook(chess[3][0]);
		Rook(chess[3][7]);
		//四象
		BIsshop(chess[0][2]);
		BIsshop(chess[0][5]);
		BIsshop(chess[3][2]);
		BIsshop(chess[3][5]);
		//四马
		Knight(chess[0][1]);
		Knight(chess[0][6]);
		Knight(chess[3][1]);
		Knight(chess[3][6]);
		
		
	}
	public void Soilder(Label chess){
		
	} 
	public void Queen(Label chess) {
		
	}
	public void King(Label chess) {
		
	}
	public void Rook(Label chess) {
		
	}
	public void Knight(Label chess){
		
	}
	public void BIsshop(Label chess) {
		
	}
	
}
