//位置class

	public class Location {
		private int row = 0;
		private int col = 0;

		public Location(int r, int c) { // constructor takes the coordinates
			this.row = r;
			this.col = c;
		}

		public int row() {
			return row;
		}

		public int col() {
			return col;
		}

		public boolean equal(Location l) {
			return ((row == l.row()) && (col == l.col()));
		}
	};

