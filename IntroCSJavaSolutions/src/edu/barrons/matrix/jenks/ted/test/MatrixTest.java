package edu.barrons.matrix.jenks.ted.test;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.barrons.matrix.jenks.ted.Matrix;

public class MatrixTest {

	@Test
	public void testReverseAllRows() {
		int[][] act = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
		int[][] exp = {{3, 2, 1}, {6, 5, 4}, {9, 8, 7}, {12, 11, 10}};
		Matrix matrix = new Matrix(act);
		matrix.reverseAllRows();
		assertArrayEquals(exp, act);
	}

	@Test
	public void testReverseMatrix() {
		int[][] act = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
		int[][] exp = {{12, 11, 10}, {9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
		Matrix matrix = new Matrix(act);
		matrix.reverseMatrix();
		assertArrayEquals(exp, act);
		
		int[][] act2 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
		int[][] exp2 = {{9, 8, 7}, {6, 5, 4}, {3, 2, 1}};
		matrix = new Matrix(act2);
		matrix.reverseMatrix();
		assertArrayEquals(exp2, act2);
		
		int[][] act3 = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
		int[][] exp3 = {{12, 11, 10, 9}, {8, 7, 6, 5}, {4, 3, 2, 1}};
		matrix = new Matrix(act3);
		matrix.reverseMatrix();
		assertArrayEquals(exp3, act3);
	}

}
