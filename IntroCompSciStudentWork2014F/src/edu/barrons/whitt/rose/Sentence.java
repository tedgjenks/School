package edu.barrons.whitt.rose;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.barrons.AbstractSentence;

public class Sentence extends AbstractSentence  {
	private String sent;
	
	public static void main(String[] args) {
		Sentence s = new Sentence("Why?");
		System.out.println(s.countWords());
		s.getWords();
		System.out.println(s.getBlankPositions());
	}
	
	public Sentence() {
		
	}
	
	public Sentence(String Sentence) {
		super(Sentence);
	}
	
	@Override
	public int countWords() {
		// TODO Auto-generated method stub
		
		int count = 0;
		for (int i = 0; i < getSentence().length(); i++) {
			if (getSentence().substring(i, i + 1).contentEquals(" ")) {
				count++;
			}
		}
		count++;
		return count;
	}

	@Override
	public List<Integer> getBlankPositions() {
		// TODO Auto-generated method stub
		List<Integer> spaces = new ArrayList<Integer>();
		if (countWords() == 0) {
			return spaces;
		}
		for (int i = 0; i < getSentence().length(); i++) {
			if (getSentence().substring(i, i + 1) != null) {
				if (getSentence().substring(i, i + 1).contentEquals(" ")) {
					spaces.add(i);
				}
			}
		}
		return spaces;
	}

	@Override
	public String[] getWords() {
		// TODO Auto-generated method stub
		String[] words = new String[countWords()];
		if (getSentence().length() == 1) {
			words[0] = getSentence();
			System.out.println(words[0]);
			return words;
		}
		if (getSentence().indexOf(" ") == -1) {
			words[0] = getSentence();
			System.out.println(words[0]);
			return words;
		}
		for (int i = 0; i < words.length; i++) {
			if (i == 0) {
				words[i] = getSentence().substring(indexSpace().get(i), indexSpace().get(i + 1));
			} else if (i == words.length - 1) {
				words[i] = getSentence().substring(indexSpace().get(i) + 1);
			} else {
				words[i] = getSentence().substring(indexSpace().get(i) + 1, indexSpace().get(i + 1));
			}
			
		}
		for (int i = 0; i < words.length; i++) {
			System.out.println(words[i]);
		}
		return words;
	}
	
	//helper
	public List<Integer> indexSpace() {
		List<Integer> spaces = new ArrayList<Integer>();
		spaces.add(0);
		for (int i = 0; i < getSentence().length(); i++) {
			if (getSentence().substring(i, i + 1).contentEquals(" ")) {
				spaces.add(i);
			}
		}
		return spaces;
	}

}
