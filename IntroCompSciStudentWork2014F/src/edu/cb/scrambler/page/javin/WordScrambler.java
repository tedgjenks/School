package edu.cb.scrambler.page.javin;
import java.util.List;

import edu.jenks.dist.cb.*;
public class WordScrambler extends Object implements Scrambler{

	@Override
	public void scrambleOrRemove(List<String> words) {
		for(int i = 0; i < words.size(); i++) {
			String word = scrambleWord(words.get(i));
			if(word.equals(words.get(i))) {
				words.remove(i);
				i--;
			}else {
				words.set(i, word);
			}
		}
	}

	@Override
	public String scrambleWord(String word) {
		for(int i = 0; i < word.length() - 1; i++) {
			if(word.substring(i, i+1).equals("A")) {
				if(!word.substring(i, i+1).equals(word.substring(i+1, i+2))) {
					String temp1 = word.substring(i, i+1);
					String temp2 = word.substring(i+1,i+2);
					word = word.substring(0,i) + temp2 + temp1 + word.substring(i+2);
					i++;
				}
			}
		}
		return word;
	}
	public static void main(String[] args) {
		WordScrambler hi = new WordScrambler();
		System.out.println(hi.scrambleWord("ABRACADABRA").toString());
	}
}
