package edu.cb.scrambler.gresko.michael;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {
	
	
	public WordScrambler() {
		
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		String scrambledWord = null;
		for (int index = 0; index < wordList.size(); index++) {
			scrambledWord = scrambleWord(wordList.get(index));
			if (wordList.get(index).equals(scrambledWord)) {
				wordList.remove(wordList.get(index));
				index--;
			
			}else{
				wordList.set(index, scrambledWord);
			}
		}
	}

	@Override
	public String scrambleWord(String wordList) {
		int i, len = wordList.length();
		StringBuilder word = new StringBuilder(len);
		String test = "A";
		for (i = 0; i < len - 1; i++){
			String temp = wordList.substring(i, i + 1);
			String tem = wordList.substring(i + 1, i + 2);
			if (temp.equals(test) && !tem.equals(test)) {
				word.append(tem).append(temp);
				i = i + 1;
			} else { 
				word.append(temp);
			}
		}
		if (len > word.length()) {
			word.append(wordList.substring(wordList.length() - 1));
		}
		return word.toString();

	}
}
