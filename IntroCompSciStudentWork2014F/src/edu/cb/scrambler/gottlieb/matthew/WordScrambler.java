package edu.cb.scrambler.gottlieb.matthew;

import java.util.*;
import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler extends java.lang.Object implements Scrambler{
	private static final char TOKEN = 'A';

	public String scrambleWord(String word) {
		StringBuilder sb= new StringBuilder(word.length());
		for(int i=0; i < word.length(); i++){
			char curChar = word.charAt(i);
			char nextChar= word.charAt(i+1);
			if (curChar== TOKEN && nextChar !=TOKEN){
				sb.append(nextChar).append(curChar);
				i++;
			} else {
				sb.append(curChar);
			}
		}
		if (word.length() > sb.length())
			sb.append(word.charAt(word.length()-1));
		return sb.toString();
	}
	public void scrambleOrRemove(List <String> wordlist){
		for(int i=0; i<wordlist.size(); i++){
			if (wordlist.get(i).equals(scrambleWord(wordlist.get(i)))){
				wordlist.remove(i);
				i-=1;
			} else{
				wordlist.set(i,scrambleWord(wordlist.get(i)));
			}
			
			}
		}
	}
