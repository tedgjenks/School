package edu.cb.scrambler.tarasidis.john;

import java.util.List;
import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {
	public WordScrambler() {
		
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {

	}

	@Override
	public String scrambleWord(String word) {
		char[] letterList = word.toCharArray();
		// int a = 0;
		// int sizeOfList = letterList.length;
		for (int len = letterList.length - 2; len >= 0; len--){
			if (letterList[len] == 'A'){
				len += 1;
				letterList[len] = 'A';
			}
		}
		return letterList.toString();
	}

}
