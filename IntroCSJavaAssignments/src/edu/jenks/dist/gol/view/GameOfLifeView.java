/**
 * 
 */
package edu.jenks.dist.gol.view;

/**
 * @author tedgj
 *
 */
public interface GameOfLifeView {
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract int getRows();
	public abstract int getCols();
	public abstract void drawGrid();
	public abstract void drawCell(int paramInt1, int paramInt2, boolean paramBoolean);
	public abstract void drawGenerationCounter(long paramLong);
	public abstract void show();
	public abstract boolean mousePressed();
	public abstract double mouseX();
	public abstract double mouseY();
	public abstract void enableDoubleBuffering();
	public abstract void disableDoubleBuffering();
	public abstract void clear();
	public abstract int calculateRowFromMouse();
	public abstract int calculateColumnFromMouse();
}