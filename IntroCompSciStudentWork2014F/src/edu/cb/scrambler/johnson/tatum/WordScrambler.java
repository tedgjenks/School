package edu.cb.scrambler.johnson.tatum;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for(int i = 0; i <wordList.size(); i++){
			String word = wordList.get(i);
			String scrambledWord = scrambleWord(word);
			if (word.equals(scrambledWord));{
				wordList.remove(i);
			}
			if(!word.equals(scrambledWord)){
				wordList.set(i, scrambledWord);
			}
		}

	}

	@Override
	public String scrambleWord(String word) {
		char[] array = word.toCharArray();
		if(word != ""){
			for (int i = 0; i < array.length-1; i++){
				char c = array[i];
				char a = 'A';
				if(c==(a)){
					char temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
				}	
			}
		}
		return new String(array); 
	}
}	


