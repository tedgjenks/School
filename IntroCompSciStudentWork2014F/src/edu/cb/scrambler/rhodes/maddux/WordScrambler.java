package edu.cb.scrambler.rhodes.maddux;

import java.util.*;

import edu.jenks.dist.cb.Scrambler;

public class WordScrambler implements Scrambler {

	public static void main(String[] args) {
		WordScrambler test = new WordScrambler();
		System.out.println(test.scrambleWord("TAN"));
		List<String> list = new ArrayList<String>();
		list.add("TAN");
		list.add("ABRACADABRA");
		list.add("WHOA");
		list.add("APPLE");
		list.add("EGGS");
		//list.add("A");
		//list.add("");
		System.out.println("List before: " + list);
		test.scrambleOrRemove(list);
		System.out.println("List after: " + list);
	}
	
	public void scrambleOrRemove(List<String> wordList) {
		for(int i = 0; i < wordList.size(); i++) {
			if(wordList.get(i).equals(scrambleWord(wordList.get(i)))) {
				wordList.remove(i);
				i--;
			}
		}
		for(int i = 0; i < wordList.size(); i++) {
			wordList.set(i, scrambleWord(wordList.get(i)));
		}
	}

	public String scrambleWord(String word) {
		StringBuilder wordS = new StringBuilder(word);
		for(int i = 0; i < wordS.length()-1; i++) {
			if(wordS.charAt(i) == 'A' && wordS.charAt(i+1) != 'A') {
				wordS.setCharAt(i, wordS.charAt(i+1));
				wordS.setCharAt(i+1, 'A');
				i++;
			}
		}
		return wordS.toString();
	}
}
