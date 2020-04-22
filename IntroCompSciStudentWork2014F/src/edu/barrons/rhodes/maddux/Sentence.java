package edu.barrons.rhodes.maddux;

import java.util.ArrayList;
import java.util.List;
import edu.jenks.dist.barrons.AbstractSentence;

public class Sentence extends AbstractSentence{
	
	public Sentence() {
		super();
	}
	
	public Sentence(String sentence) {
		super(sentence);
	}
	
	public static void main(String[] args) {
		Sentence test = new Sentence("Why?");
		System.out.println(test.countWords());
		test.getWords();
	}
	
	public int countWords() {
		return getBlankPositions().size() + 1;
	}

	public List<Integer> getBlankPositions() {
		List<Integer> list = new ArrayList<Integer>();
		if(getSentence()==null) {
			return list;
		}else {
			for(int i = 0; i < getSentence().length(); i++) {
				if(getSentence().substring(i, i+1).equals(" ")) {
					list.add(i);
				}
			}
		}
		return list;
	}

	public String[] getWords() {
		String[] strArr = new String[this.countWords()];
		if(getSentence()!=null) {
			int start = 0;
			int end = 0;
			for(int i = 0; i < getBlankPositions().size(); i++) {
				end = getBlankPositions().get(i);
				strArr[i] = getSentence().substring(start, end);
				start = end+1;
			}
			strArr[strArr.length - 1] = getSentence().substring(start);
		}
		return strArr;
	}

	public void toArray(String[] arr) {
		System.out.print("[");
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + ", ");
		}
		System.out.print("]");
	}
	
}
