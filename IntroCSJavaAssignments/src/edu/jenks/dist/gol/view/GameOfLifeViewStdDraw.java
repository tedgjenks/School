/**
 * 
 */
package edu.jenks.dist.gol.view;

/**
 * @author tedgj
 *
 */
import edu.princeton.cs.introcs.StdDraw;







public class GameOfLifeViewStdDraw
  extends AbstractGameOfLifeView
{
  private final int CELL_SIDE_LENGTH;
  private final double FILLED_SQUARE_HALF_LENGTH;
  private final double PEN_RADIUS_GRID = 0.002D;
  private final double PEN_RADIUS_CELLS = 0.0D;
  private final double CELL_CENTER;
  
  public GameOfLifeViewStdDraw(int width, int height, int cellSideLength)
  {
    super(width - width % cellSideLength, height - height % cellSideLength, height / cellSideLength, width / cellSideLength);
    CELL_SIDE_LENGTH = cellSideLength;
    CELL_CENTER = (CELL_SIDE_LENGTH / 2.0D);
    FILLED_SQUARE_HALF_LENGTH = (0.9D * CELL_SIDE_LENGTH / 2.0D);
    StdDraw.setPenRadius(0.0D);
  }
  
  public void drawCell(int row, int col, boolean alive)
  {
    if (alive) {
      StdDraw.setPenColor(StdDraw.GREEN);
    } else
      StdDraw.setPenColor(StdDraw.WHITE);
    double x = col * CELL_SIDE_LENGTH + CELL_CENTER;
    double y = row * CELL_SIDE_LENGTH + CELL_CENTER;
    StdDraw.filledSquare(x, y, FILLED_SQUARE_HALF_LENGTH);
  }
  
  public void drawGrid()
  {
    StdDraw.clear();
    StdDraw.setPenRadius(0.002D);
    StdDraw.setPenColor(StdDraw.BLACK);
    for (int horiz = HEIGHT; horiz > 0; horiz -= CELL_SIDE_LENGTH)
      StdDraw.line(0.0D, horiz, WIDTH, horiz);
    for (int vert = WIDTH - CELL_SIDE_LENGTH; vert > 0; vert -= CELL_SIDE_LENGTH)
      StdDraw.line(vert, 0.0D, vert, HEIGHT);
    StdDraw.setPenRadius(0.0D);
  }
  
  public int calculateRowFromMouse()
  {
    return (int)(mouseY() / CELL_SIDE_LENGTH);
  }
  
  public int calculateColumnFromMouse()
  {
    return (int)(mouseX() / CELL_SIDE_LENGTH);
  }
}