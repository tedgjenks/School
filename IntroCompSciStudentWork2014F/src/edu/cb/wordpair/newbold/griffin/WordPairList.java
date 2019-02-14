package edu.cb.wordpair.newbold.griffin;
import edu.jenks.dist.cb.wordpair.*;
import java.util.*;

public class WordPairList extends AbstractWordPairList{
	
	
	public WordPairList(String[] words){
		super();
		allPairs = new ArrayList<>();
		for(int i = 0; i < words.length-1; i++){
			for(int j = (i+1); j < words.length; j++){
				String first = words[i];
				String last = words[j];
				WordPair newPair = new WordPair(first, last); 
				allPairs.add(newPair);
			}
		}
		
	}
	
	public int numMatches(){
		int count = 0; 
		for(int k = 0; k < allPairs.size(); k++){
			if(allPairs.get(k) != null){
				WordPair testPair = allPairs.get(k);
				if(testPair.getFirst() != null && testPair.getLast() != null){
					if(testPair.getFirst().equals(testPair.getLast())){
						count++;
					}
				}
			}
		}
		System.out.println(count);
		return count;
	}
	
	public static void main(String[] args){
		
		String[] testWords = {"one", "two", "three"};
		String[] testWords2 = {"the", "more", "the", "merrier"};
		String[] testWords3 = {"the", "red", "fox", "the", "red"};
		WordPairList test = new WordPairList(testWords);
		WordPairList test2 = new WordPairList(testWords2);
		WordPairList test3 = new WordPairList(testWords3);
		System.out.println(test.allPairs);
		System.out.println(test2.allPairs);
		System.out.println(test3.allPairs);
		test.numMatches();
		test2.numMatches();
		test3.numMatches();
		
		
		
		
	}
}
