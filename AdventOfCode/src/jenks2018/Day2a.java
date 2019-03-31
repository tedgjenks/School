package jenks2018;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day2a {
	
	private static final int CAPACITY = 26*4/3 + 1;
	private static String TEST_INPUT = "abcdef,bababc,abbcde,abcccd,aabcdd,abcdee,ababab";

	public static void main(String[] args) throws IOException {
		long testResult = product2And3(TEST_INPUT.split(","));
		assert testResult == 12;
		String input = loadInput();
		System.out.println(product2And3(input.split(",")));
	}
	
	public static String loadInput() throws IOException {
		StringBuilder input = new StringBuilder(1000);
		Path path = FileSystems.getDefault().getPath("Input2018", "Day2.txt");
	    BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
	    String line = null;
	    while((line = reader.readLine()) != null)
	    	input.append(line).append(",");
	    reader.close();
		return input.toString();
	}
	
	private static long product2And3(String[] inputs) {
		long count2 = 0;
		long count3 = 0;
		for(String input: inputs) {
			Repeats repeats = count2And3(input);
			if(repeats.two)
				count2++;
			if(repeats.three)
				count3++;
		}
		return count2 * count3;
	}
	
	private static Repeats count2And3(String letters) {
		Map<Character, Integer> charCounts = new HashMap<>(CAPACITY);
		for(int index = letters.length() - 1; index >= 0; index--) {
			char letter = letters.charAt(index);
			if(!charCounts.containsKey(letter))
				charCounts.put(letter, 0);
			int value = charCounts.get(letter);
			value++;
			charCounts.put(letter, value);
		}
		Repeats repeats = new Repeats();
		Iterator<Character> keys = charCounts.keySet().iterator();
		while(keys.hasNext() && !repeats.hasBoth()) {
			int value = charCounts.get(keys.next());
			if(value == 2)
				repeats.two = true;
			else if(value == 3)
				repeats.three = true;
		}
		return repeats;
	}
	
	private static class Repeats {
		boolean two;
		boolean three;
		
		boolean hasBoth() {
			return two && three;
		}
	}

}
