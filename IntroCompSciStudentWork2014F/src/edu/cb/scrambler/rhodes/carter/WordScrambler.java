package edu.cb.scrambler.rhodes.carter;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scrambleOrRemove(List<String> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String scrambleWord(String word) {
		String newWord = "";
		for(int i = word.length()-1; i>=0;i--){
			newWord = newWord + word.charAt(i+1); 
			}
		
		return newWord;
	}

}
