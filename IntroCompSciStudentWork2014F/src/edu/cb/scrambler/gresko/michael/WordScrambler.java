package edu.cb.scrambler.gresko.michael;

import java.util.ArrayList;
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

		String word = "";
		int i, len = wordList.length();
		String test = "A";
		for (i = 0; i < len; i++){
			if (i+1 > len) {
				String temp = wordList.substring(i);
				word = word + temp;
			}
			String temp = wordList.substring(i, i+1);
			if (temp.equals(test)) {
				
			}
		}
		return word;

	}
	

}
