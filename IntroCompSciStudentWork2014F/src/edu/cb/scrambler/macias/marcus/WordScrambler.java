package edu.cb.scrambler.macias.marcus;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.Scrambler;

public class WordScrambler implements Scrambler {

	public static void main(String[] args) {
		WordScrambler run = new WordScrambler();
		
		System.out.println(run.scrambleWord("ABRACADABRA"));
		System.out.println("BARCADABARA");
		System.out.println(run.scrambleWord("TAN"));
		System.out.println("TNA");
		System.out.println(run.scrambleWord("WHOA"));
		System.out.println("WHOA");
		System.out.println(run.scrambleWord("AARDVARK"));
		System.out.println("ARADVRAK");
		System.out.println(run.scrambleWord("EGGS"));
		System.out.println("EGGS");
		System.out.println(run.scrambleWord("A"));
		System.out.println("A");
		System.out.println(run.scrambleWord(""));
		System.out.println("");
		System.out.println(run.scrambleWord("AFRICAN"));
		System.out.println("FARICNA");
		System.out.println(run.scrambleWord("AMAZON"));
		System.out.println("MAZAON");
		System.out.println(run.scrambleWord("AMERICAN"));
		System.out.println("MAERICNA");
		List<String> thing = new ArrayList<>();
		thing.add("TAN");
		thing.add("ABRACADABRA");
		thing.add("WHOA");
		thing.add("APPLE");
		thing.add("EGGS");
		System.out.println(thing);
		run.scrambleOrRemove(thing);
		System.out.println(thing);
	}
	
	public void scrambleOrRemove(List<String> wordList) {
		//List<String> answer = new ArrayList<>();
		for(int i = 0 ; i < wordList.size();i++) {
			String scramble = scrambleWord(wordList.get(i));
			if(scramble.equals(wordList.get(i))) {
				wordList.remove(i);
				i--;
			}else {
				wordList.set(i, scramble);
			}
		}
		
	}

	public String scrambleWord(String word) {
		if(word.length() <= 1) {
			return word;
		}
		String answer1 = "";
		for (int i = 0; i < word.length() - 1; i++) {
			String curChar = word.substring(i,i+1);
			String nextChar = word.substring(i+1,i+2);
			
			if(curChar.equals("A") && !(nextChar.equals("A"))){
				
				answer1 += nextChar;
				answer1 += curChar;
				i+=1;
				if(i == word.length() - 2 && answer1.length() != word.length()) {
					answer1 += word.substring(i+1,i+2);
				}
			}else {
				answer1 += curChar;
				if(i == word.length() - 2 && answer1.length() != word.length()) {
					answer1 += nextChar;
				}
			}
		}
		return answer1;
	}

}
