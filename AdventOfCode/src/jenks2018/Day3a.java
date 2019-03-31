package jenks2018;

import edu.jenks.util.Grid;
import java.util.*;
import jenks2018.io.FileHelper;
import static java.lang.System.out;

public class Day3a {
	
	private static final String[] testInput = {"#1 @ 1,3: 4x4",
		"#2 @ 3,1: 4x4",
		"#3 @ 5,5: 2x2"};
	
	private static final Set<String> NO_OVERLAP_ID = new HashSet<>(50);
	private static final String OVERLAP_MARKER = "X";

	public static void main(String[] args) {
		Grid<String> testGrid = populateGrid(testInput);
		//printGrid(testGrid);
		out.println(countOverlaps(testGrid));
		out.println(NO_OVERLAP_ID);
		String[] input = FileHelper.loadInput("Input2018", "Day3.txt");
		out.println(countOverlaps(populateGrid(input)));
		out.println(NO_OVERLAP_ID);
	}
	
	private static void printGrid(Grid<String> grid) {
		for(int rowIndex = 0; rowIndex < grid.getNumRows(); rowIndex++) {
			for(int colIndex = 0; colIndex < grid.getNumCols(); colIndex++) {
				out.print(grid.get(rowIndex, colIndex) + ", ");
			}
			out.println();
		}
	}
	
	private static int countOverlaps(Grid<String> grid) {
		int overlaps = 0;
		for(int rowIndex = grid.getNumRows() - 1; rowIndex >= 0; rowIndex--) {
			for(int colIndex = grid.getNumCols() - 1; colIndex >= 0; colIndex--) {
				if(OVERLAP_MARKER.equals(grid.get(rowIndex, colIndex)))
					overlaps++;
			}
		}
		return overlaps;
	}
	
	private static Grid<String> populateGrid(String[] inputs) {
		Grid<String> grid = new Grid(100, 100, null);
		for(String input : inputs) {
			boolean hasOverlap = false;
			Claim claim = new Claim(input);
			for(int bottomCorner = claim.topOffset + claim.height - 1; bottomCorner >= claim.topOffset; bottomCorner--) {
				for(int rightCorner = claim.leftOffset + claim.width - 1; rightCorner >= claim.leftOffset; rightCorner--) {
					String curId = grid.get(bottomCorner, rightCorner);
					if(curId != null) {
						hasOverlap = true;
						NO_OVERLAP_ID.remove(curId);
					}
					grid.set(bottomCorner, rightCorner, curId == null ? claim.id : OVERLAP_MARKER);
				}
			}
			if(!hasOverlap) {
				NO_OVERLAP_ID.add(claim.id);
			}
		}
		return grid;
	}
	
	private static class Claim {
		String id;
		int leftOffset, topOffset, width, height;
		
		Claim(String input) {
			String[] idFirst = input.split("@");
			id = idFirst[0].substring(1).trim();
			String[] offsetFirst = idFirst[1].trim().split(":");
			String[] offsets = offsetFirst[0].split(",");
			leftOffset = Integer.parseInt(offsets[0]);
			topOffset = Integer.parseInt(offsets[1]);
			String[] dims = offsetFirst[1].trim().split("x");
			width = Integer.parseInt(dims[0]);
			height = Integer.parseInt(dims[1]);
		}
	}

}
