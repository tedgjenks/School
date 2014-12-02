package edu.cb.scrambler.tarasidis.john;

import java.util.List;
import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {
	public WordScrambler() {
		
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
			 for (int i = 0; i >= wordList.size(); i++) {
				 String tempWord = scrambleWord(wordList.get(i));
				 	if (tempWord.equals(wordList.get(i))) {
				 		wordList.remove(i);
				 	}
				 	else {
				 		wordList.add(scrambleWord(wordList.get(i)));
				 	}
			 }
					 
	}

	@Override
	public String scrambleWord(String word) {
		char[] letterList = word.toCharArray();
		// int a = 0;
		// int sizeOfList = letterList.length;
		for (int len = letterList.length - 2; len >= 0; len--){
			if (letterList[len] == 'A'){
				len -= 1;
				letterList[len] = 'A';
			}
		}
		
		return new String(letterList);
	}
	

}
