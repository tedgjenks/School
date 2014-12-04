package edu.cb.scrambler.guareschi.alex;
import java.util.*;

import edu.jenks.dist.cb.scrambler.Scrambler;
public class WordScrambler extends java.lang.Object implements Scrambler{
	
	private static final char TOKEN = 'A';
	public WordScrambler() {
		
	}
	public String scrambleWord(String word){
		StringBuilder sb = new StringBuilder(word.length());
		for (int i = 0; i <word.length()-1; i++) {
			char currentChar = word.charAt(i);
			char nextChar = word.charAt(i+1);
			if (currentChar == TOKEN && nextChar != TOKEN) {
				sb.append(nextChar).append(currentChar);
				i++;
			}else{
				sb.append(currentChar);
			}
		}
		if (word.length() > sb.length())
			sb.append(word.charAt(word.length()-1));
		return sb.toString();
	}	
		
		@Override
	public void scrambleOrRemove(List<String> wordList) {
		for (int i = 0; i< wordList.size(); i++) {
			if (wordList.get(i).equals(scrambleWord(wordList.get(i)))){
				wordList.remove(i);
				i-=1;
			}else{
				wordList.set(i, scrambleWord(wordList.get(i)));
			}
		}
			
		}
	}