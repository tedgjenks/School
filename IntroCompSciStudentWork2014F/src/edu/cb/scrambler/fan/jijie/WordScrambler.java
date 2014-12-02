package edu.cb.scrambler.fan.jijie;

import java.util.*;  

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) {
		// TODO Auto-generated method stub

	}

	@Override
	public String scrambleWord(String word) {
		// TODO Auto-generated method stub
		Random r = new Random();
	    char a[] = word.toCharArray();
	    for( int i=0 ; i<a.length-1 ; i++ ) {
	        int j = r.nextInt(a.length-1);
	        char temp = a[i]; a[i] = a[j];  a[j] = temp;
	    }      
	    
		/*String vowelA = "A";
		char a[] = word.toCharArray();
		for(int i=0;i<a.length-1;i=i+2) {
             char temp= a[i];
             a[i]=a[i+1];
             a[i+1]=temp; 
         }
		*/
	    return new String(a);
	}
	
	/*public static void main(String [] args){
		WordScrambler sw= new WordScrambler();
		String cat = "ANIMALS";
		System.out.println(sw.scrambleWord(cat));
	}
	*/
	
}