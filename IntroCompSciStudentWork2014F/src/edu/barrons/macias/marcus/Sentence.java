package edu.barrons.macias.marcus;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.barrons.AbstractSentence;

public class Sentence extends AbstractSentence {
	//private String sentence;

	public Sentence() {
		
	}

	public Sentence(String sentence) {
		super(sentence);
		//this.sentence = sentence;
	}

	public int countWords() {
		int count = 1;
		List<Integer> a = getBlankPositions();
		if (getBlankPositions().size() == 0) {
			return count;
		} else {
			return getBlankPositions().size() + 1;
		}

	}

	public static void main(String[] args) {
		Sentence a = new Sentence("I love you!");
		Sentence b = new Sentence("The cat sat on the mat.");
		Sentence c = new Sentence("Why?");
		System.out.println(a.getBlankPositions());
		System.out.println(b.getBlankPositions());
		System.out.println(c.getBlankPositions());
		a.test(a.getWords());
		b.test(b.getWords());
		c.test(c.getWords());
		
		System.out.println(a.countWords());
		System.out.println(b.countWords());
		System.out.println(c.countWords());
		
		Sentence d = new Sentence("");
		System.out.println(d.getBlankPositions());
		System.out.println(d.countWords());
		
	}

	public void test(String[] a) {
		for (String b : a) {
			System.out.print(b + " ");
		}
		System.out.println();
	}

	public List<Integer> getBlankPositions() {
		List<Integer> a = new ArrayList<Integer>();
		
		for (int i = 0; i < super.getSentence().length(); i++) {
			String b = super.getSentence().substring(i, i + 1);
			if (b.equals(" ")) {
				a.add(i);
			}
		}
		return a;
	}

	public String[] getWords() {
		String[] a = new String[countWords()];
		
		
		if(countWords() == 1) {
			a[0] = super.getSentence();
			return a;
		}
		List<Integer> b = getBlankPositions();
		int count = 0;
		int y = 0;
		for(int i = 0 ; i < a.length;i++) {
			if(i==b.size()) {
				break;
			}
			String thing = super.getSentence().substring(count,b.get(i));
			
			a[i] = thing;
			count+= thing.length()+1;
			
			y++;
		}
		a[y] = super.getSentence().substring(count,super.getSentence().length());
		return a;
	}

}
