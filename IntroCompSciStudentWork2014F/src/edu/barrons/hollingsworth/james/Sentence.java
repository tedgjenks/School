package edu.barrons.hollingsworth.james;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.barrons.AbstractSentence;

public class Sentence extends AbstractSentence {

	public Sentence() {
		super();
	}
	
	public Sentence(String sentence) {
		super(sentence);
	}
	
	@Override
	public int countWords() {
		int numWords = 0;
		if(getSentence() != null) {
			for(int i = 0; i < getSentence().length(); i++) {
				if(i == getSentence().length() - 1 || getSentence().substring(i, i + 1).equalsIgnoreCase(" "))
					numWords++;
			}
		}
		return numWords;
	}

	@Override
	public List<Integer> getBlankPositions() {
		String tmp = getSentence();
		List<Integer> indexes = new ArrayList<Integer>();
		if(tmp != null) {
			while(tmp.indexOf(" ") > 0) {
				indexes.add(tmp.indexOf(" ") + indexes.size());
				tmp = tmp.substring(0, tmp.indexOf(" ")) + tmp.substring(tmp.indexOf(" ") + 1);
			}
		}
		return indexes;
	}

	@Override
	public String[] getWords() {
		if(getSentence() == null) return new String[0];
		
		String[] words = new String[countWords()];
		List<Integer> indexes = getBlankPositions();

		
		if(indexes.size() == 0) return new String[] {getSentence()};
		else if(indexes.size() == 1) return new String[] {getSentence().substring(0, indexes.get(0)), getSentence().substring(indexes.get(indexes.size() - 1) + 1)};
		else {
			words[0] = getSentence().substring(0, indexes.get(0));
			for(int i = 0; i < indexes.size() - 1; i++)
				words[i + 1] = getSentence().substring(indexes.get(i) + 1, indexes.get(i + 1));
			words[words.length - 1] = getSentence().substring(indexes.get(indexes.size() - 1) + 1);
		}
		
		return words;
	}
	
	public static void main(String...strings) {
		Sentence s = new Sentence("a b c d e");
		System.out.println(s.countWords());
		System.out.println(s.getBlankPositions());
		String[] tmp = s.getWords();
		for(String s2 : tmp) {
			System.out.print(s2 + ", ");
		}
	}

}
