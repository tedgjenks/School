package edu.cb.scrambler.slimmer.ben;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler
extends java.lang.Object
implements Scrambler {

	@Override
	public void scrambleOrRemove(List<String> wordlist) {
		for(int i=0;i<wordlist.size();i++){
			String orig=wordlist.get(i);
			String changed= scrambleWord(wordlist.get(i));
			if(orig.equals(changed)){
				wordlist.remove(i);
				i--;
			}
			else{
				wordlist.set(i, changed);
			}
		}
		
	}

	@Override
	public String scrambleWord(String word) {
		char[] letters=word.toCharArray();
		if(letters.length<=1)
			return word;
		for(int i=0;i<letters.length-1;i++)
			if (letters[i]=="A".charAt(0)||letters[i]=="a".charAt(0)){
				char temp=letters[i+1];
				letters[i+1]=letters[i];
				letters[i]=temp;
				i++;
				if(letters[i-1]=="A".charAt(0)||letters[i-1]=="a".charAt(0))
					i--;
			}
		String returnword="";
		for (int i=0;i<letters.length;i++){
			returnword= returnword+letters[i];
		}
		return returnword;
	}

}
