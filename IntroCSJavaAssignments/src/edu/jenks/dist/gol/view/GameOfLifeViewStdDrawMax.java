/**
 * 
 */
package edu.jenks.dist.gol.view;

import edu.princeton.cs.introcs.StdDraw;

/**
 * @author tedgj
 *
 */
public class GameOfLifeViewStdDrawMax
  extends AbstractGameOfLifeView
{
  public GameOfLifeViewStdDrawMax(int width, int height)
  {
    super(width, height, height, width);
    StdDraw.setPenRadius(0.0D);
  }
  
  public void drawGrid() {}
  
  public int calculateRowFromMouse()
  {
    return (int)mouseY();
  }
  
  public int calculateColumnFromMouse()
  {
    return (int)mouseX();
  }
  
  public void drawCell(int row, int col, boolean alive)
  {
    if (alive) {
      StdDraw.setPenColor(StdDraw.GREEN);
    } else
      StdDraw.setPenColor(StdDraw.WHITE);
    StdDraw.point(col, row);
  }
}