package sudoku;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestSudoku {

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testEmpty() {
		int[][] matrix =	{ { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
						  	  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };;
				   
		Sudoku.inputMatrix(matrix);
		assertTrue(Sudoku.solve(), "Failed to solve empty sudoku");
	}
	
	@Test
	void testExample() {
		int[][] matrix = 	{ { 0, 0, 8, 0, 0, 9, 0, 6, 2 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 5 }, //
							  { 1, 0, 2, 5, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 2, 1, 0, 0, 9, 0 }, //
							  { 0, 5, 0, 0, 0, 0, 6, 0, 0 }, //
							  { 6, 0, 0, 0, 0, 0, 0, 2, 8 }, //
							  { 4, 1, 0, 6, 0, 8, 0, 0, 0 }, //
							  { 8, 6, 0, 0, 3, 0, 1, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 4, 0, 0 } };;
				   
		Sudoku.inputMatrix(matrix);
		assertTrue(Sudoku.solve(), "Failed to solve example sudoku");
	}
	
	
	void testTwoFives() {
		int[][] matrix =	{ { 5, 5, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
						  	  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };;
				   
		Sudoku.inputMatrix(matrix);
		assertFalse(Sudoku.solve(), "Solved unsolvable sudoku with 2 fives in same row");
	}
	
	@Test
	void testUnsolvableSeven() {
		int[][] matrix = 	{ { 1, 2, 3, 0, 0, 0, 0, 0, 0 }, //
							  { 4, 5, 6, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 7, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };;
				   
		Sudoku.inputMatrix(matrix);
		assertFalse(Sudoku.solve(), "Solved unsolvable sudoku");
	}
	
	@Test
	void testFromWebsite() {
		int[][] matrix =	{ { 9, 8, 0, 7, 0, 0, 6, 0, 0 }, //
							  { 7, 5, 0, 0, 0, 0, 0, 9, 0 }, //
							  { 0, 0, 6, 0, 0, 0, 0, 0, 0 }, //
						  	  { 0, 4, 0, 0, 9, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 6, 0, 0, 0, 0, 0 }, //
							  { 0, 0, 0, 0, 0, 3, 0, 0, 9 }, //
							  { 0, 0, 7, 9, 0, 0, 0, 8, 2 }, //
							  { 0, 0, 5, 8, 0, 0, 0, 6, 0 }, //
							  { 0, 9, 0, 0, 2, 0, 0, 0, 1 } };;
				   
		Sudoku.inputMatrix(matrix);
		assertTrue(Sudoku.solve(), "Failed to solve sudoku from website");
	}

}
