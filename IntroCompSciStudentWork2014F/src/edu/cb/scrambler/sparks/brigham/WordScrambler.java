package edu.cb.scrambler.sparks.brigham;

import java.util.*;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler extends java.lang.Object implements Scrambler {
	public WordScrambler() {
		
	}
	public String scrambleWord(String word){
		word = word.toLowerCase();
		String ret_value = "";
		for (int i = 0; i < (word.length()-2); i ++){
			String compval = word.substring(i, i+1);
			String compval2 = word. substring(i+1, i+2);
			if(compval == "a "){
				if(compval2 !="a"){
					ret_value = ret_value + compval2 + compval;
				}
			}
			ret_value = ret_value + compval +compval2;
		}
		ret_value = ret_value.toUpperCase();
		return ret_value; 
	}
	public void scrambleOrRemove(List<String> wordList){
		
	}

}