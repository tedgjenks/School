package edu.cb.scrambler.smith.rod;

import java.util.List;
import java.util.Scanner;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
	}

	public static void scrambleOrRemove(String words2) {
		Scanner sc = new Scanner (System.in);
	    String words = sc.nextLine();
	    System.out.println(words);
	    WordScrambler w = new WordScrambler();
		WordScrambler.scrambleOrRemove(words);
	}

	public String scrambleWord(String word) {
		String[] word1 = word.trim().split(" ");
		for (int i = 0; i < word1.length; i++) {
			System.out.println(word1[i]);		
	    }
		return word;
		
	}

	public void scrambleOrRemove(List<String> arg0) {
		// TODO Auto-generated method stub
		
	}

}
	
