package edu.cb.scrambler.whitt.rose;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.Scrambler;

public class WordScrambler extends Object implements Scrambler {

	public static void main(String[] args) {
		WordScrambler w = new WordScrambler();
		String a = "";
		a += "hello";
		System.out.println(a);
		System.out.println(w.swap("ROSE", 0, 1));
		System.out.println(w.scrambleWord("TAN"));
		System.out.println(w.scrambleWord("ABRACADABRA"));
		System.out.println(w.scrambleWord("WHOA"));
		System.out.println(w.scrambleWord("AARDVARK"));
		System.out.println(w.scrambleWord("EGGS"));
		System.out.println(w.scrambleWord("A"));
		System.out.println(w.scrambleWord(""));
		
		List<String> wordList = new ArrayList<String>();
		wordList.add("TAN");
		wordList.add("ABRACADABRA");
		wordList.add("WHOA");
		wordList.add("AARDVARK");
		wordList.add("EGGS");
		wordList.add("A");
		wordList.add("");
		
		System.out.println(wordList);
		w.scrambleOrRemove(wordList);
		System.out.println(wordList);

	}
	
	public WordScrambler() {
		
	}
	
	//- all words unchanged by scrambling have been removed from wordList
	//- each of the remaining words has been replaced by its scrambled version
	//- the relative ordering of the entries in wordList is the same as it was before the method was called
	@Override
	public void scrambleOrRemove(List<String> wordList) {
		// TODO Auto-generated method stub
		for (int i = 0; i < wordList.size(); i++) {
			//- all words unchanged by scrambling have been removed from wordList
			if (scrambleWord(wordList.get(i)).equals(wordList.get(i))) {
				wordList.remove(i);
			} else {
				wordList.set(i, scrambleWord(wordList.get(i)));
			}
		}
	}

	//- the word was scrambled, beginning at the first letter and continuing from left to right
	//- two consecutive letters consisting of "A" followed by a letter that was not "A" were swapped
	//- letters were swapped at most once
	@Override
	public String scrambleWord(String word) {
		// TODO Auto-generated method stub
//		String end = "";
		for (int i = 0; i < word.length() - 1; i++) {
			if (word.substring(i, i + 1).contentEquals("A") && (word.substring(i + 1, i + 2).contentEquals("A") == false)) {
				word = swap(word, i , i + 1);
				i += 1;
			}
		}
		return word;
	}
	
	public String swap(String str, int a, int b) {
		if (b == str.length() - 1)  {
            return str.substring(0, a) + str.charAt(b) + str.substring(a + 1, b) + str.charAt(a); 
		} else {
			return str.substring(0, a) + str.charAt(b) + str.substring(a + 1, b) + str.charAt(a) + str.substring(b + 1, str.length());
		}
	}

}
