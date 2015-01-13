package edu.cb.scrambler.patterson.andrew;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler extends java.lang.Object implements Scrambler {

	private static final char TOKEN = 'A';
	
	
	public WordScrambler() {
		
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for (int i=0; i<wordList.size(); i++){
			if(wordList.get(i).equals(scrambleWord(wordList.get(i)))){
				wordList.remove(i);
				i-=1;
			}else{
				wordList.set(i,  scrambleWord(wordList.get(i)));
			}	
		}
	}


	@Override
	public String scrambleWord(String word) {
		StringBuilder sb = new StringBuilder(word.length());
		for(int index =0; index < word.length() - 1; index ++){
			char curChar=word.charAt(index);
			char nextChar=word.charAt(index +1);
			if(curChar==TOKEN && nextChar != TOKEN) {
				sb.append(nextChar).append(curChar);
				index++;
			}else{
				sb.append(curChar);
			}
			
		}
	
		if (word.length() > sb.length())
			sb.append(word.charAt(word.length()-1));
		return sb.toString();
		
	}
}
