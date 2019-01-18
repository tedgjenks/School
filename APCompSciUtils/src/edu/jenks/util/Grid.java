package edu.jenks.util;

import java.util.*;

public class Grid<E> {

	private final List<List<E>> ROWS; // height
	private int cols; // width
	private final E DEFAULT_ELEMENT;
	
	public Grid(final int numRows, final int numCols, final E defaultElement) {
		DEFAULT_ELEMENT = defaultElement;
		ROWS = new ArrayList<>(numRows);
		for(int rowIndex = numRows - 1; rowIndex >= 0; rowIndex--) {
			List<E> col = new ArrayList<E>(numCols);
			ROWS.add(col);
			for(int colIndex = numCols - 1; colIndex >= 0; colIndex--) {
				col.add(defaultElement);
			}
		}
		cols = numCols;
	}
	
	public E get(final int row, final int col) {
		checkSize(row, col);
		return ROWS.get(row).get(col);
	}
	
	public void set(final int row, final int col, E element) {
		checkSize(row, col);
		ROWS.get(row).set(col, element);
	}
	
	private void checkSize(final int row, final int col) {
		for(int rowsToAdd = row - (ROWS.size() - 1); rowsToAdd > 0; rowsToAdd--) {
			List<E> newCol = new ArrayList<>(cols);
			ROWS.add(newCol);
			for(int colIndex = cols - 1; colIndex >= 0; colIndex--) {
				newCol.add(DEFAULT_ELEMENT);
			}
		}
		final int colsToAdd = col - (cols - 1);
		if(colsToAdd > 0)
			cols += colsToAdd;
		for(int rowIndex = ROWS.size() - 1; rowIndex >= 0; rowIndex--) {
			List<E> column = ROWS.get(rowIndex);
			for(int toAdd = colsToAdd; toAdd > 0; toAdd--) {
				column.add(DEFAULT_ELEMENT);
			}
		}
	}
	
	public int getNumRows() {
		return ROWS.size();
	}
	
	public int getNumCols() {
		return cols;
	}
}
