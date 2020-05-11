package edu.cb.scrambler.tran.don;

import java.util.*;
import edu.jenks.dist.cb.*;

public class WordScrambler implements Scrambler{

	public static void main(String args[]) {
		WordScrambler s = new WordScrambler();
		List<String> l = new ArrayList<String>();
		l.add("TAN");
		l.add("ABRACADABRA");
		l.add("WHOA");
		l.add("AARDVARK");
		l.add("EGGS");
		l.add("A");
		l.add("");
		l.add("ABABABABABABABAB");
		System.out.println("TAN - " + s.scrambleWord("TAN"));
		System.out.println("ABRACADABRA - " + s.scrambleWord("ABRACADABRA"));
		System.out.println("WHOA - " + s.scrambleWord("WHOA"));
		System.out.println("AARDVARK - " + s.scrambleWord("AARDVARK"));
		System.out.println("EGGS - " + s.scrambleWord("EGGS"));
		System.out.println("A - " + s.scrambleWord("A"));
		System.out.println(" - " + s.scrambleWord(""));
		System.out.println("ABABABABABABABAB - " + s.scrambleWord("ABABABABABABABAB"));
		System.out.println("APPLE - " + s.scrambleWord("APPLE"));
		for(String str : l) {
			System.out.print(str + ", ");
		}
		System.out.println("");
		s.scrambleOrRemove(l);
		for(String str : l) {
			System.out.print(str + ", ");
		}
	}
	
	public WordScrambler() {
		
	}
	
	public void scrambleOrRemove(List<String> wordList) {
		for(int i = 0; i < wordList.size(); i++) {
			if(scrambleWord(wordList.get(i)).equals(wordList.get(i))) {
				wordList.remove(i);
				i--;
			} else {
				wordList.set(i, scrambleWord(wordList.get(i)));
			}
			
		}
		
	}

	@Override
	public String scrambleWord(String str) {
		StringBuilder s = new StringBuilder(str);
		//System.out.println(s);
		for(int i = 0; i < s.length() - 1; i++) {
			if(s.charAt(i) == 'A' && s.charAt(i+1) != 'A') {
				s.setCharAt(i, s.charAt(i+1));
				s.setCharAt(i + 1, 'A');
				i++;
				//System.out.println("pee");
			}
		}
		//System.out.println(s);
		return s.toString();
	}

}
