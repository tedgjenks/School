package edu.cb.scrambler.higginbotham.andrew;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler{

	public WordScrambler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for(String word :wordList){
			String scrambled = scrambleWord(word);
			if(word.equals(scrambled))
				wordList.remove(word);
			else
				scrambleWord(word);
		}
			
			

	}

	@Override
	public String scrambleWord(String word) {
		char[] wordArray = word.toCharArray();
		if (word == "")
			return word;
		else{
			for(int j = 0; j < word.length() - 1; j++)
				if(wordArray[j] == 'A' && wordArray[j+1] != 'A'){
					char temp = wordArray[j];
					char temp2 = wordArray[j+1];
					wordArray[j] = temp2;
					wordArray[j+1] = temp;
					j++;
				}
			}
		return new String(wordArray);
		}			

}
