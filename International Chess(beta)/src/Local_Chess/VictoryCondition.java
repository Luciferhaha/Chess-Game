package Local_Chess;
//1.将军触发保护机制即是强迫被将军方做出脱离将军状态的行动
//2.将死判定直接一方胜利
//3.和棋的几种判定方法
	//1. 其中一方无棋可走，其“王”自身没有被对方“将军”但周围的格均有威胁。
	//2.·任何一方都无法将死对方。
	//3.其中一方提出和棋要求，并得到另一方的同意。
	//4.在五十回合内没有棋子被吃掉，也没有兵移动过。
	//5.同一局面被三次重复
	//必然导致和棋的残局有
	//1.王对王单马或王单象
	//2.王对王双马
public class VictoryCondition {
	public VictoryCondition() {
		// TODO Auto-generated constructor stub
	}
	//判断将军
	  public boolean Check(){
		  
		  return (Boolean) null;
	  }
	 //判断将死
	  public boolean Checkmate() {
		return true;
	}
	  //判断和棋
	  public void DrawmGame() {
		
	}
}
