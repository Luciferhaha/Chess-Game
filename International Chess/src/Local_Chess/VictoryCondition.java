package Local_Chess;
//1.The king protection mechanism is forced to be the military to make the state from the  killed action
//2.king die to determine the direct victory
//3.There are several ways to determine the chess
	//1. One of them has no chess to go, its "king" itself has not been the other "general" but the surrounding grid are threatened.
	//2.·No one can die each other.
	//3.One of them cites a chess request and gets the consent of the other party.
	//4.There were no pieces in the fifty rounds, and no soldiers were moved.
	//5.The same situation was repeated three times
	//dead king inevitably lead to the remnants of the chess
	
public class VictoryCondition {
	public VictoryCondition() {
		// TODO Auto-generated constructor stub
	}
	//tell the eating king
	  public boolean Check(){
		  
		  return (Boolean) null;
	  }
	 //tell the king is dead
	  public boolean Checkmate() {
		return true;
	}
	  //tell both not dead
	  public void DrawmGame() {
		
	}
}
