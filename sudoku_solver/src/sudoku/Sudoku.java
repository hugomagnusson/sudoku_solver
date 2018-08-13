package sudoku;

public class Sudoku {

	private static int times = 0;
	// solvable
	// private static int[][] sudoku = { { 3, 0, 6, 5, 0, 8, 4, 0, 0 }, //
	// { 5, 2, 0, 0, 0, 0, 0, 0, 0 }, //
	// { 0, 8, 7, 0, 0, 0, 0, 3, 1 }, //
	// { 0, 0, 3, 0, 1, 0, 0, 8, 0 }, //
	// { 9, 0, 0, 8, 6, 3, 0, 0, 5 }, //
	// { 0, 5, 0, 0, 9, 0, 6, 0, 0 }, //
	// { 1, 3, 0, 0, 0, 0, 2, 5, 0 }, //
	// { 0, 0, 0, 0, 0, 0, 0, 7, 4 }, //
	// { 0, 0, 5, 2, 0, 6, 3, 0, 0 } };;

	// remove 7 to make solvable
	// private static int[][] sudoku = { { 1, 2, 3, 0, 0, 0, 0, 0, 0 }, //
	// { 4, 5, 6, 0, 0, 0, 0, 0, 0 }, //
	// { 0, 0, 0, 7, 0, 0, 0, 0, 0 }, //
	// { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
	// { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
	// { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
	// { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
	// { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
	// { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };;

	// 5 in same row unsolvable
	private static int[][] sudoku = { { 2, 2, 0, 0, 0, 0, 0, 0, 0 }, //
									  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
									  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
									  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
									  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
									  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
									  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
									  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
									  { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };;

	// en lösning
	// private static int[][] sudoku = { { 0, 0, 8, 0, 0, 9, 0, 6, 2 }, //
	// { 0, 0, 0, 0, 0, 0, 0, 0, 5 }, //
	// { 1, 0, 2, 5, 0, 0, 0, 0, 0 }, //
	// { 0, 0, 0, 2, 1, 0, 0, 9, 0 }, //
	// { 0, 5, 0, 0, 0, 0, 6, 0, 0 }, //
	// { 6, 0, 0, 0, 0, 0, 0, 2, 8 }, //
	// { 4, 1, 0, 6, 0, 8, 0, 0, 0 }, //
	// { 8, 6, 0, 0, 3, 0, 1, 0, 0 }, //
	// { 0, 0, 0, 0, 0, 0, 4, 0, 0 } };;

	public static void main(String[] args) {
		// Sudoku sud = new Sudoku();
		boolean solved = Sudoku.solve();
		if (solved) {
			System.out.println("Sudoku solved!");
		} else {
			System.out.println("Sudoku could not be solved");
		}
		Sudoku.printSudoku();
		System.out.println(times);
	}

	public static boolean solve() {
//		if (checkProper() == false) {
//			System.out.println("Duplicate number");
//			return false;
//		}
//		System.out.println("Solvable!");
		
		return solve(0, 0);
	}

	
	private static boolean solve(int i, int j) {
		times++;
		//System.out.println("Recursions: " + times);
		//System.out.println("i = " + i + "  j = " + j);
		if (i > 8)
			return true;

		if (sudoku[i][j] != 0) {
			if (j + 1 > 8) {
				return solve(i + 1, 0);
			} else {
				return solve(i, j + 1);
			}
		}

		for (int n = 1; n <= 9; n++) {
			if (checkValue(i, j, n) == true) {
				sudoku[i][j] = n;

				boolean solved;
				if (j + 1 > 8) {
					solved = solve(i + 1, 0);
				} else {
					solved = solve(i, j + 1);
				}

				if (solved == true) {
					return true;
				} else {
					sudoku[i][j] = 0;
				}
			}
		}
		return false;
	}
	

	private static boolean checkValue(int i, int j, int value) {

		// kolla om rad, kollumn eller region innehåller varje n
		// om något n inte hittas i någon, return nästa solve, annars return false

		// check row
		for (int r = 0; r < 9; r++) {
			if (sudoku[r][j] == value) {
				return false;
			}
		}
		// check column
		for (int c = 0; c < 9; c++) {
			if (sudoku[i][c] == value) {
				return false;
			}
		}

		// kolla om regionen innehåller n
		for (int x = 3 * (i / 3); x <= 3 * (i / 3) + 2; x++) {
			for (int y = 3 * (j / 3); y <= 3 * (j / 3) + 2; y++) {
				if (sudoku[x][y] == value) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean checkProper() {
		int[] row;
		for (int i = 0; i < 9; i++) {
			row = new int[10];
			for (int j = 0; j < 9; j++) {
				row[sudoku[i][j]]++;
			}

			for (int t = 1; t <= 9; t++) {
				if (row[t] > 1) {
					System.out.println("row");
					return false;
				}
			}
		}
		int[] col;
		for (int j = 0; j < 9; j++) {
			col = new int[10];
			for (int i = 0; i < 9; i++) {
				col[sudoku[i][j]]++;
			}

			for (int t = 1; t <= 9; t++) {
				if (col[t] > 1) {
					System.out.println("col");
					return false;
				}
			}
		}

		// add check for duplicate in region using nested for loop
		int[] reg;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				 reg = new int[10];
				for (int x = 3 * i; x <= 3 * i + 2; x++) {
					for (int y = 3 * j; y <= 3 * j + 2; y++) {
						reg[sudoku[i][j]]++;
					}
				}
				for (int t = 1; t <= 9; t++) {
					if (reg[t] > 1) {
						System.out.println("reg: " + reg[t] + " index: " + t);
						return false;
					}
				}
			}
		}

		return true;
	}

	public static void inputMatrix(int[][] matrix) {
		sudoku = matrix;
	}

	public static void setCell(int i, int j, int value) {
		sudoku[i][j] = value;
	}

	public static int getCell(int i, int j) {
		return sudoku[i][j];
	}

	public static void printSudoku() {
		System.out.println("Recursions" + times);
		for (int i = 0; i < sudoku.length; i++) {
			for (int j = 0; j < sudoku[i].length; j++) {
				System.out.print(sudoku[i][j] + " ");
			}
			System.out.println();
		}
	}
}
