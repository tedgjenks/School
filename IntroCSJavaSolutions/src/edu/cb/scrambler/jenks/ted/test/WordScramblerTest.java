package edu.cb.scrambler.jenks.ted.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.cb.scrambler.jenks.ted.WordScrambler;

public class WordScramblerTest {
	
	private Map<String, String> expWordScramble = new HashMap<String, String>(14);
	private WordScrambler ws = new WordScrambler();

	@Before
	public void setUp() throws Exception {
		expWordScramble.put("TAN", "TNA");
		expWordScramble.put("ABRACADABRA", "BARCADABARA");
		expWordScramble.put("WHOA", "WHOA");
		expWordScramble.put("AARDVARK", "ARADVRAK");
		expWordScramble.put("EGGS", "EGGS");
		expWordScramble.put("A", "A");
		expWordScramble.put("", "");
	}

	@Test
	public void testScrambleOrRemove() {
		List<String> wordList = new ArrayList<String>(5);
		wordList.addAll(Arrays.asList("TAN", "ABRACADABRA", "WHOA", "APPLE", "EGGS"));
		List<String> expected = new ArrayList<String>(3);
		expected.addAll(Arrays.asList("TNA", "BARCADABARA", "PAPLE"));
		ws.scrambleOrRemove(wordList);
		assertEquals(expected, wordList);
	}

	@Test
	public void testScrambleWord() {
		for(String originalWord : expWordScramble.keySet()) {
			String expected = expWordScramble.get(originalWord);
			String actual = ws.scrambleWord(originalWord);
			assertEquals(expected, actual);
		}
	}

}
