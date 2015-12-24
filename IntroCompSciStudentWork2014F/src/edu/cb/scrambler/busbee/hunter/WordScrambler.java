package edu.cb.scrambler.busbee.hunter;

import java.util.List;
import edu.jenks.dist.cb.scrambler.Scrambler;
public class WordScrambler {

	public WordScrambler() {

	}
	public void scrambleOrRemove(List<String> wordList){
		for(String unscrambledWords: wordList){
			for(int i = 0; i < unscrambledWords.length(); i++){
				if(unscrambledWords.contains("A")){

				}
			}
		}
	}
	public String scrambleWord(String word){
		String newWord = word;
		if(word.contains("A")){
			if(word.endsWith("A") == false){
				for(int i = 0; i < word.length(); i++){
					char letters = word.charAt(i);
					if(letters == 'A'){
						
						if(word.charAt(i) != 'A'){
							
						}
					}
				}
			}
		}
		return word;
	}
}
