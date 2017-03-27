import javax.swing.JFrame;

public class Mainwindow extends JFrame{
	 public  final static int Width=900;
	 public final static int Heigh=900;
	 ChessBoard chessBoard;
	 public Mainwindow() {
		// TODO Auto-generated constructor stub
		 this.setTitle("INTERNETIONAL CHESS");
		 this.setSize(Width, Heigh);
		 this.setVisible(true);
		 chessBoard=new ChessBoard();
		 this.add(chessBoard);
		  
	 }
	 public static void main(String[] args) {
		new Mainwindow();
	}
}
