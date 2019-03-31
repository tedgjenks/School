package csp.common.robot;

public class Grid {

	public static final byte OPEN = 0;
	public static final byte CLOSED = 1;
	public static final byte TARGET = 2;
	
	private int[][] rectGrid;
	
	public Grid(int rows, int cols) {
		rectGrid = new int[rows][cols];
	}
	
	public void close(int row, int col) {
		rectGrid[row][col] = CLOSED;
	}
	
	public void close(Location location) {
		rectGrid[location.getRow()][location.getCol()] = CLOSED;
	}
	
	public void setTarget(int row, int col) {
		rectGrid[row][col] = TARGET;
	}
	
	public void setTarget(Location location) {
		rectGrid[location.getRow()][location.getCol()] = TARGET;
	}
	
	public boolean isLocationInGrid(Location location) {
		int row = location.getRow(), col = location.getCol();
		return row >= 0 && col >= 0 && row < rectGrid.length && col < rectGrid[0].length;
	}
	
	public boolean isLocationOpen(Location location) {
		return isLocationInGrid(location) && rectGrid[location.getRow()][location.getCol()] != CLOSED;
	}
	
	public boolean isTarget(Location location) {
		return isLocationInGrid(location) && TARGET == rectGrid[location.getRow()][location.getCol()];
	}
}
