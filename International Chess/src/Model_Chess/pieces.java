package Model_Chess;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class pieces  extends JLabel{
	public JLabel pieces[][]=new JLabel[4][8];
	public pieces() {
		// TODO Auto-generated constructor stub
		this.Pieces();
		this.Queen();
		this.KIng();
		this.Bishop();
		this.Knight();
		this.Rook();
		
	}

	public void Pieces() {
		//pawns
		for (int i = 0; i <8; i++) {
			pieces[1][i]=new JLabel(new ImageIcon("src/Graph/BPawn.png"));
			pieces[1][i].setName("1Pawn");
			pieces[2][i]=new JLabel(new ImageIcon("src/Graph/Pawn.png"));
			pieces[2][i].setName("2Pawn");
		}
	}
	public void Queen() {
		//queens
		pieces[0][3]=new JLabel(new ImageIcon("src/Graph/BQueen.png"));
		pieces[0][3].setName("1Queen");
		pieces[3][3]=new JLabel(new ImageIcon("src/Graph/Queen.png"));
		pieces[3][3].setName("2Queen");
	}
	public void KIng() {
		//kings
		pieces[0][4]=new JLabel(new ImageIcon("src/Graph/BKing.png"));
		pieces[0][4].setName("1King");
		pieces[3][4]=new JLabel(new ImageIcon("src/Graph/King.png"));
		pieces[3][4].setName("2KIng");
	}
	public void Bishop() {
		pieces[0][2]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[0][2].setName("1Bishop");
		pieces[0][5]=new JLabel(new ImageIcon("src/Graph/BBishop.png"));
		pieces[0][5].setName("1Bishop");
		pieces[3][2]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][2].setName("2Bishop");
		pieces[3][5]=new JLabel(new ImageIcon("src/Graph/Bishop.png"));
		pieces[3][5].setName("2Bishop");
	}
	public void Knight() {
		//Knights
				pieces[0][1]=new JLabel(new ImageIcon("src/Graph/BKnight.png"));
				pieces[0][1].setName("1Knighht");
				pieces[0][6]=new JLabel(new ImageIcon("src/Graph/BKnight.png"));
				pieces[0][6].setName("1Knighht");
				pieces[3][6]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
				pieces[3][6].setName("2Knighht");
				pieces[3][1]=new JLabel(new ImageIcon("src/Graph/Knight.png"));
				pieces[3][1].setName("2Knighht");
	}
	public void Rook() {
		//rooks
		pieces[0][0]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[0][0].setName("1Rook");
		pieces[0][7]=new JLabel(new ImageIcon("src/Graph/BRook.Png"));
		pieces[0][7].setName("1Rook");
		pieces[3][0]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][0].setName("2Rook");
		pieces[3][7]=new JLabel(new ImageIcon("src/Graph/Rook.Png"));
		pieces[3][7].setName("2Rook");
	}

	
}
