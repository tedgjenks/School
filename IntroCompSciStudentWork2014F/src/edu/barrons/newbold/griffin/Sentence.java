package edu.barrons.newbold.griffin;
import edu.jenks.dist.barrons.*;
import java.util.*;


public class Sentence extends AbstractSentence{
	//String sentence = "Hello";
	public Sentence(){
		super();
	}
	
	public Sentence(String sentence){
		super(sentence);
		this.sentence = sentence;
	}
	
	public List<Integer> getBlankPositions(){
		List<Integer> blank = new ArrayList<Integer>();
		for(int i = 0; i < sentence.length(); i++){
			String segment = sentence.substring(i, i+1);
			if(segment.equals(" ")){
				blank.add(i);
			}
		}
		return blank;
	}
	public int countWords(){
		int wordCount = 0;
		for(int i = 0; i < sentence.length(); i++){
			String segment = sentence.substring(i, i+1);
			if(segment.equals(" ")){
				wordCount++;
			}
		}
		return (wordCount+1);
	}
	
	public String[] getWords(){
		String[] words = new String[countWords()];
		int arrayPosition = 0;
		String word = "";
		for(int i = 0; i < sentence.length(); i++){
			String segment = sentence.substring(i, i+1);
			if(segment.equals(" ")){
				words[arrayPosition] = word;
				word = "";
				arrayPosition++;
			}else{
				word += segment;
			}
		}
		words[arrayPosition] = word;
		return words;
	}
	
	public static void main(String[] args){
		
		Sentence test = new Sentence();
		test.countWords();
		test.getBlankPositions();
		test.getWords();
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
}