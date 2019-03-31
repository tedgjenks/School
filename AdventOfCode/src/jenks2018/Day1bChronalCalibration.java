package jenks2018;

import static java.lang.System.out;

import java.io.IOException;
import java.util.*;

public class Day1bChronalCalibration {
	
private final static Map<String, Integer> TEST_CASES = new HashMap<>();
	
	static {
		TEST_CASES.put("+1, -2, +3, +1", 2);
		TEST_CASES.put("+1, -1", 0);
		TEST_CASES.put("+3, +3, +4, -2, -4", 10);
		TEST_CASES.put("-6, +3, +8, +5, -6", 5);
		TEST_CASES.put("+7, +7, -2, -7, -4", 14);
	}
	
	private static int calibrate(String input) {
		int curFreq = 0;
		Set<Integer> prevFreqs = new HashSet<>(50);
		String[] freqs = input.split(", ");
		int index = 0;
		while(!prevFreqs.contains(curFreq)) {
			prevFreqs.add(curFreq);
			curFreq += Integer.parseInt(freqs[index]);
			index = index >= freqs.length - 1 ? 0 : index + 1;
		}
		return curFreq;
	}

	public static void main(String[] args) throws IOException {
		for(String input : TEST_CASES.keySet()) {
			assert TEST_CASES.get(input).equals(calibrate(input));
		}
		out.println(calibrate(Day1ChronalCalibration.loadPuzzleInput("Day1.txt")));
	}

}
