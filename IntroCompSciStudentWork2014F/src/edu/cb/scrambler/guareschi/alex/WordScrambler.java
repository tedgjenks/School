package edu.cb.scrambler.guareschi.alex;
import java.util.*;

import edu.jenks.dist.cb.scrambler.Scrambler;
public class WordScrambler extends java.lang.Object implements Scrambler{
	public WordScrambler() {
		
	}
	public String scrambleWord(String word){
		String switchindex = "A";
		StringBuffer dorw = new StringBuffer();
		String [] wordsplit = word.split("");
		for (int i=0; i< wordsplit.length; i++)
			try{
				if(wordsplit[i].equals(switchindex)){
					dorw.append(wordsplit[i+1]);
					dorw.append(wordsplit[i]);
					i++;
				}else{
					dorw.append(wordsplit[i]);
				}
				}catch(ArrayIndexOutOfBoundsException ex){
					dorw.append(wordsplit[i]);
				}
				return dorw.toString();
			}
		
		
		
		
		@Override
	public void scrambleOrRemove(List<String> arg0) {
			// TODO Auto-generated method stub
			
		}
	}