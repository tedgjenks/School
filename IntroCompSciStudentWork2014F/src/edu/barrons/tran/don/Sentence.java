package edu.barrons.tran.don;
import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.barrons.*;

public class Sentence extends AbstractSentence{
	public static void main(String arg[]) {
		Sentence what = new Sentence(" s");
		System.out.println(what.countWords());
		String[] arr = what.getWords();
		for(int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	}
	public Sentence() {
		super();
	}
	
	public Sentence(String sentence) {
		super(sentence);
		//sent = getSentence();
	}
	
	public int countWords() {
		ArrayList<Integer> temp = (ArrayList<Integer>) getBlankPositions();
		if(getSentence().length() == 0) {
			return 0;
		}
		return temp.size() + 1;
	}

	@Override
	public List<Integer> getBlankPositions() {
		ArrayList<Integer> temp = new ArrayList<>();
		for(int i = 0; i < getSentence().length(); i++) {
			if(getSentence().substring(i, i+1).equals(" ")) {
				//System.out.println("wo");
				temp.add(i);
			}
		}
		return temp;
	}

	@Override
	public String[] getWords() {
		ArrayList<Integer> temp = (ArrayList<Integer>) getBlankPositions();
		String[] temp2 = new String[countWords()];
		if(countWords() == 0)
			return temp2;
		if(countWords() == 1) {
			temp2[0] = getSentence().substring(0, getSentence().length());
			return temp2;
		}
		temp2[0] = getSentence().substring(0, temp.get(0));
		System.out.println(temp.size());
		for(int i = 1; i < temp.size(); i++) {
			System.out.println("woo");
			temp2[i] = getSentence().substring(temp.get(i - 1) + 1, temp.get(i));
		}
		temp2[temp2.length - 1] = getSentence().substring(temp.get(countWords() - 2) + 1, getSentence().length());
		return temp2;
	}

}
