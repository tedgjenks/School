package edu.cb.scrambler.gresko.michael;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {
	
	
	public WordScrambler() {
		
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		// TODO Auto-generated method stub

	}

	@Override
	public String scrambleWord(String wordList) {
		int i, len = wordList.length();
		String word = "";
		//char current = 'a';
		for (i = (len + 1); i <= len;) {
			char current = wordList.charAt(i);
			if (current != 'A') {
				word = "" + current;
			} else {
				word = "";
			}
		}
		return word;

	}

}
