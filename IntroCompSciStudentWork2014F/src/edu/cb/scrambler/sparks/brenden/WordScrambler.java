package edu.cb.scrambler.sparks.brenden;

import java.util.*;    

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {
	
	private static final char TOKEN = 'A';

	public WordScrambler() {
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		// TODO Auto-generated method stub
		for(int index = 0; index < wordList.size(); index++){
			if (wordList.get(index).equals(scrambleWord(wordList.get(index)))){
				wordList.remove(index);
				index -= 1;
			}else{
				wordList.set(index, scrambleWord(wordList.get(index)));
			}
		}
	}
	
	@Override
	public String scrambleWord(String word) {
		StringBuilder sb = new StringBuilder(word.length());
		for(int index= 0; index < word.length() -1 ; index++) {
			char curChar = word.charAt(index);
			char nextChar = word.charAt(index + 1);
			if(curChar == TOKEN && nextChar != TOKEN){
				sb.append(nextChar).append(curChar);
				index++;
			}else{
				
				sb.append(curChar);
			}
		}
		if(word.length()> sb.length()){
			sb.append(word.charAt(word.length() -1 ));
		}
 

		return sb.toString();
	}
}