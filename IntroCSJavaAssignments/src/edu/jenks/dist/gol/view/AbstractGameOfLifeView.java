package edu.jenks.dist.gol.view;

import edu.princeton.cs.introcs.StdDraw;

public abstract class AbstractGameOfLifeView implements GameOfLifeView {
 protected final int WIDTH;
 protected final double HALF_WIDTH;
 protected final int HEIGHT;
 protected final int ROWS;
 protected final int COLS;
 protected final int GENERATION_COUNT_HEIGHT_DIM = 50;
 protected final double HALF_GENERATION_COUNT_HEIGHT_DIM = 25.0D;
 protected final double GENERATION_COUNT_Y;
 
 public AbstractGameOfLifeView(int width, int height, int rows, int cols) {
   WIDTH = width;
   HALF_WIDTH = (WIDTH / 2.0D);
   HEIGHT = height;
   ROWS = rows;
   COLS = cols;
   GENERATION_COUNT_Y = (HEIGHT + 25.0D);
   initStdDraw();
 }
 
 protected void initStdDraw() {
   StdDraw.setCanvasSize(WIDTH, HEIGHT + 50);
   StdDraw.setYscale(0.0D, HEIGHT + 50);
   StdDraw.setXscale(0.0D, WIDTH);
 }
 
 public int getWidth()
 {
   return WIDTH;
 }
 
 public int getHeight()
 {
   return HEIGHT;
 }
 
 public int getRows()
 {
   return ROWS;
 }
 
 public int getCols()
 {
   return COLS;
 }
 
 public void drawGenerationCounter(long generationCount)
 {
   StdDraw.setPenColor(StdDraw.WHITE);
   StdDraw.filledRectangle(HALF_WIDTH, GENERATION_COUNT_Y, HALF_WIDTH, 25.0D);
   StdDraw.setPenColor(StdDraw.BLACK);
   StdDraw.text(HALF_WIDTH, GENERATION_COUNT_Y, "Generation: " + generationCount);
 }
 
 public void show() {}
 
 public boolean mousePressed()
 {
   return StdDraw.isMousePressed();
 }
 
 public double mouseX()
 {
   return StdDraw.mouseX();
 }
 
 public double mouseY()
 {
   return StdDraw.mouseY();
 }
 
 public void enableDoubleBuffering() {}
 
 public void disableDoubleBuffering() {}
 
 public void clear() {}
}