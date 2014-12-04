package edu.cb.scrambler.wilbanks.tanner;

import java.util.*;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		String scrambled = null;
		String word = null;
		for (int i = 0; i <= wordList.size(); i++){
			word = wordList.get(i);
			scrambled = scrambleWord(word);
			if (!word.equals(scrambled)){
				wordList.set(i , scrambled);
			}else{
				wordList.remove(word);
				i--;
			}
		 }
	}

	@Override
	public String scrambleWord(String word) {
		StringBuilder scramble= new StringBuilder();
		char one;
		char two;
		for (int i = 0; i < (word.length()-1); i++){
			one = word.charAt(i);
			two = word.charAt(i+1);
			if (one == ('A') && two  != 'A'){
				scramble.append(two);
				scramble.append(one);
				i++;
			}else{
				scramble.append(one);
			}
		}
        if (word.length() != scramble.length()){
        	scramble.append(word.charAt(word.length()-1));
        }
        return scramble.toString();
    }
		

}
