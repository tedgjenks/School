package edu.cb.scrambler.gottlieb.matthew;

import java.util.*;
import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler extends java.lang.Object implements Scrambler{

	public String scrambleWord(String word) {
		String retWord="";
		for(int i=0; i<word.length();i++){
			String compWord= word.substring(i, i+1);
			String compWord2= word.substring(i+1,i+2);
			if (compWord== "A"){
				if (compWord2 != "A"){
					retWord= compWord2 +compWord;
				}
			}
			retWord= retWord + compWord + compWord2;
		}
		
		return retWord;
	}
		
	public void scrambleOrRemove(List <String> wordlist){
	}
}
