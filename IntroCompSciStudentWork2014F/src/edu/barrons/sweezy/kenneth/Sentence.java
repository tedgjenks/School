package edu.barrons.sweezy.kenneth;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.barrons.AbstractSentence;

public class Sentence extends AbstractSentence {

	public static void main(String[] args) {
		Sentence testing = new Sentence("The cat sat on the mat.");
		System.out.println(testing.getWords().toString());
		for (String s : testing.getWords()) {
			System.out.println(s);
		}
	}

	public Sentence() {
		setSentence(new String());
	}

	public Sentence(String sent) {
		setSentence(sent);
	}

	public int countWords() {
		return getBlankPositions().size() + 1;
	}

	public List<Integer> getBlankPositions() {
		List<Integer> temp = new ArrayList<Integer>();
		int count = 0;
		for (char c : getSentence().toCharArray()) {
			if (Character.toString(c).equals(" ")) {
				temp.add(count);
			}
			count++;
		}
		return temp;
	}

	public String[] getWords() {
		String[] words = getSentence().split("\\s+");
		return words;
	}

}
