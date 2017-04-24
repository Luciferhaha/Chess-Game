// the class about the position of the pieces

	public class ChessPoint {
		
		// initial the position of the pieces
		private int row = 0;
		private int col = 0;
		
		// constructor takes the coordinates
		public ChessPoint(int r, int c) { 
			this.row = r;
			this.col = c;
		}
		
        public int col() {
        	return col;
        }
        
		public int row() {
			return row;
		}

		// constructor takes the coordinates
		public boolean ispiece(ChessPoint l) {
			
			return ((row == l.row()) && (col == l.col()));
			
		}
		public void removepiece() {
			
		}
		public void getpiece(){
			
		}
		public void setpiece() {
			
		}
	}

