package sudoku;

public class Sudoku {
	private int[][] sudoku;
	
	public Sudoku() {
		sudoku = new int[9][9];
	}
	
	public void setCell(int i, int j, int value) {
		sudoku[i][j] = value;
	}
	
	public int getCell(int i, int j) {
		return sudoku[i][j];
	}
	
	public boolean solve() {
		return solve(0, 0);
	}
	
	//hur sätter man in värdet i matrisen???
	private boolean solve(int i, int j) {
		if (sudoku[i][j] != 0) {
			if (i + 1 >= 8) {
				return solve(0, j + 1);
			}
			else {
				return solve(i + 1, j);
			}
		}
		
		for (int n  = 1; n <= 9; n++) {
			if (checkValue(i, j, n)) {
				
			}
		}
		return false;
	}
	
	private boolean checkValue(int i, int j, int value) {
		
		//kolla om rad, kollumn eller region innehåller varje n
		//om något n inte hittas i någon, return nästa solve, annars return false
		
		for (int t = 0; t < 9; t++) {
			if (sudoku[t][j] == value) {
				return false;
			}
		}
		
		for (int t = 0; t < 9; t++) {
			if (sudoku[i][t] == value) {
				return false;
			}
		}
		
		//kolla om regionen innehåller n
		for (int x = 3 * (i / 3); x < 3 * (i / 3) + 2; x++) {
			for (int y = 3 * (j / 3); x < 3 * (j / 3) + 3; y++) {
				if (sudoku[x][y] == value) {
					return false;
				}
			}
		}
		return true;
	}

}
