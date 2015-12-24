package edu.cb.scrambler.piland.will;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		if(wordList != null){
			for(String word : wordList){
				int n= 0;
				wordList.set(n, scrambleWord(word));
				n++;
			}
		}
	}

	@Override
	public String scrambleWord(String word) {
		word.toUpperCase();
		String replaceWord = "";
		for(int n= 0; word.length() > n; n++){
					char ifA = word.charAt(n);
					if(ifA == 'A'){
						if(word.length() <= n + 1)
						replaceWord += word.charAt(n +1);
						replaceWord += 'A';
						n++;
					}
					if(ifA != word.charAt(n)){
						replaceWord += word.charAt(n);
					}
				}
		return replaceWord;
	}

}
