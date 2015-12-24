package edu.cb.scrambler.mariscal.juan;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	@Override
	public void scrambleOrRemove(List<String> list) {
		// TODO Auto-generated method stub
		for(int n = 0; n < list.size(); n++){
			String a = scrambleWord(list.get(n));
			System.out.println(a);
			if(a.equals(list.get(n))){
				list.remove(n);
				n-=1;
			}
			else{
				list.set(n, a);
			}
			
		}
	}

	@Override
	public String scrambleWord(String word) {
		// TODO Auto-generated method stub
		String nword = ""; 
		for(int n = 0; n < word.length(); n++){
			String first = Character.toString(word.charAt(n));
			if(n != word.length()-1){
				
			
			String second = Character.toString(word.charAt(n+1));
			
			if(first.equals("A") && !(second.equals("A"))){
				String temp =first;
				nword += "" + second + first;
				n+=1;
				
			}
			else{
				nword += first;
			}}
			else{nword+=first;}
		
		}
		
		return nword;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
