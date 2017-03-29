import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//处理不同棋子的算法
//兵，后，王，车，象，马
//兵变身，兵吃过路兵，王车短易位和长易位
//行棋权 白棋先走，双方轮流走棋
//一着棋，除参与易位的车以外，任何棋子都不能越过被其他棋子占据的格子
//一旦吃掉，必须从棋盘上拿走
//让不同类比的棋子各司其职（执行各自的方法）
public class Chess implements MouseListener{
	public Label[][] chess;
	public Chess() {
		// TODO Auto-generated constructor stub
		//总共32个棋子
		chess =new Label[4][8];
		JobDivision();
	}
	//职责划分
	public void JobDivision() {
		//两列士兵8*2
		for (int i = 0; i < 8; i++) {
			Pawn(chess[1][i]);
			Pawn(chess[2][i]);
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
		Bisshop(chess[0][2]);
		Bisshop(chess[0][5]);
		Bisshop(chess[3][2]);
		Bisshop(chess[3][5]);
		//四马
		Knight(chess[0][1]);
		Knight(chess[0][6]);
		Knight(chess[3][1]);
		Knight(chess[3][6]);
		
		
	}
	public void Pawn(Label chess){
	//其第一步可以向前走一或两格，以后每次只可向前走一步，不可往后走
	//吃对方的棋子则是向前打斜来吃
	
	} 
	public void Queen(Label chess) {
		//上下左右，四个方向斜
	}
	public void King(Label chess) {
		//上下左右斜每次移动一步
	}
	public void Rook(Label chess) {
		//上下左右
	}
	public void Knight(Label chess){
		//日子格
	}
	public void Bisshop(Label chess) {
		//上下左右斜
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
	public void Removechess(){
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
}
