package Model_Chess;
//位置class

	public class ChessPoint {
		private int row = 0;
		private int col = 0;
		
		public ChessPoint(int r, int c) { // constructor takes the coordinates
			this.row = r;
			this.col = c;
		}

		public int row() {
			return row;
		}

		public int col() {
			return col;
		}

		public boolean ispiece(ChessPoint l) {
			return ((row == l.row()) && (col == l.col()));
		}
		
		}
	
