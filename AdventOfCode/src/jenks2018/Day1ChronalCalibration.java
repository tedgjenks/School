package jenks2018;

import java.util.*;
import static java.lang.System.out;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
public class Day1ChronalCalibration {
	
	private final static Map<String, Integer> TEST_CASES = new HashMap<>();
	
	static {
		TEST_CASES.put("+1, +1, +1", 3);
		TEST_CASES.put("+1, +1, -2", 0);
		TEST_CASES.put("-1, -2, -3", -6);
	}

	public static void main(String[] args) throws IOException {
		for(String input : TEST_CASES.keySet()) {
			assert TEST_CASES.get(input).equals(calibrate(input));
		}
		out.println(calibrate(loadPuzzleInput("Day1.txt")));
	}
	
	private static int calibrate(String input) {
		int sum = 0;
		String[] addends = input.split(", ");
		for(String addend: addends)
			sum += Integer.parseInt(addend);
		return sum;
	}
	
	public static String loadPuzzleInput(String fileName) throws IOException {
		StringBuilder input = new StringBuilder(1000);
		Path path = FileSystems.getDefault().getPath("Input2018", fileName);
	    BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
	    String line = null;
	    while((line = reader.readLine()) != null)
	    	input.append(line).append(", ");
	    reader.close();
		return input.toString();
	}

}
