package edu.cb.scrambler.ruhoff.brooke;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {
	public WordScrambler(){
		
	}
	
	@Override
	public void scrambleOrRemove(List<String> wordList) {
		for(int count=0; count<wordList.size(); count++){
			String word=wordList.get(count);
			String scrambledWord=scrambleWord(word);
			if(word.equals(scrambledWord)){
				wordList.remove(word);
				count-=1;
			}
			else wordList.set(count, scrambledWord);
		}

	}

	@Override
	public String scrambleWord(String word) {
		String scrambledWord = null;
		int count=0;	
		for(int count2=0; count<word.length()-1; count++){
				if(word.charAt(count)=='A'){
					scrambledWord+=word.charAt(count+1);
					scrambledWord+='A';
					count++;
				}
				else scrambledWord+=word.charAt(count);
			}
		if(count<word.length()){
			scrambledWord+=word.charAt(word.length()-1);
		}
		word=scrambledWord;
	return word;
	}
}
