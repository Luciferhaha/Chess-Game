package Network;
// class about position

	public class ChessPoint2 {
		private int row = 0;
		private int col = 0;
		
		public ChessPoint2(int r, int c) { // constructor takes the coordinates
			this.row = r;
			this.col = c;
		}

		public int row() {
			return row;
		}

		public int col() {
			return col;
		}

		public boolean ispiece(ChessPoint2 l) {
			return ((row == l.row()) && (col == l.col()));
		}
		
		}
	
