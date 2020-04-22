package edu.barrons.page.javin;
import java.util.*;

import edu.jenks.dist.barrons.*;
public class Sentence extends AbstractSentence{
	public Sentence() {
		super();
	}
	public Sentence(String sentence) {
		super(sentence);
	}
	@Override
	public int countWords() {
		int words = 1;
		String theWord = sentence;
		while(theWord.indexOf(" ") != -1) {
			words++;
			theWord = theWord.substring(theWord.indexOf(" ") + 1);
		}
		return words;
	}

	@Override
	public List<Integer> getBlankPositions() {
		List<Integer> pos = new ArrayList<>();
		for(int i = 0; i < sentence.length(); i++) {
			if(sentence.indexOf(" ", i) == -1) break;
			pos.add(sentence.indexOf(" ", i));
			i = sentence.indexOf(" ", i);
		}
		return pos;
	}

	@Override
	public String[] getWords() {
		List<String> words = new ArrayList<>();
		String theWords = sentence;
		while(theWords.indexOf(" ") != -1) {
			words.add(theWords.substring(0, theWords.indexOf(" ")));
			theWords = theWords.substring(theWords.indexOf(" ") + 1);
		}
		words.add(theWords);
		return words.toArray(new String[0]);
	}
	 public static void main(String[] args) {
		 Sentence kevinhart = new Sentence("Hello World You Suck Crack");
		 System.out.println(kevinhart.getBlankPositions().toString());
	 }
}
