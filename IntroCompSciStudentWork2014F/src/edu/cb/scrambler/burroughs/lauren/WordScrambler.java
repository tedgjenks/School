package edu.cb.scrambler.burroughs.lauren;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
	}


	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for(String word : wordList){
			String scrambled = scrambleWord(word);
			if(word.equals(scrambled));
			else{
				wordList.remove(word);
			}
		}
	}

	@Override
	public String scrambleWord(String word) {
		String words = "";
		for(int a = 0 ; a < word.length()-1 ; a++){
			char letter = word.charAt(a);
			char next = word.charAt(a + 1);
			if(letter  == 'A' && letter!= word.length()-1){
				words += next;
				words += letter;
			}
			else{
				words += letter;
			}
		}

		return words;



	}

}
