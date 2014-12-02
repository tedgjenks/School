package edu.cb.scrambler.wilbanks.tanner;

import java.util.*;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		List<String> wordList2 = new ArrayList<String>();
		for (int i = 0; i < wordList.size(); i++){
			String word = wordList.get(i);
			String scrambled = scrambleWord(word);
			if (!word.equals(scrambled)){
				wordList2.add(scrambled);
			}
			
		 }
		
		 
		  
    
		

	}

	@Override
	public String scrambleWord(String word) {
		StringBuilder scramble= new StringBuilder();
		char one;
		char two;
		for (int i = 0; i < word.length(); i++){
			one = word.charAt(i);
			two = word.charAt(i+1);
			if (one == ('A')){
				scramble.append(two);
				scramble.append(one);
				i += 1;
				
			}
			else {
				scramble.append(one);
			}
		}
        
       
        return scramble.toString();
    }
		

}
