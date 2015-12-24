package edu.cb.scrambler.hines.jonathan;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler 
{

	public WordScrambler() 
	{
	}

	@Override
	public void scrambleOrRemove(List<String> wordList) 
	{
		for(int i = 0; i< wordList.size(); i++)
		{
			String word = wordList.get(i);
			String scramWord = scrambleWord(word);
			if (word.equals(scramWord))
			{
				wordList.remove(i);
			}
			else if(word.equals(scramWord) == false)
			{
				wordList.set(i, scramWord);
			}
		}
	}

	@Override
	public String scrambleWord(String word) 
	{
		char[] c= word.toCharArray();
		for(int i= 0; i < word.length()-1; i++)
		{
			if (c[i]=='A' && c[i+1] != 'A')
			{
				c[i] = c[i+1];
				c[i+1] =  'A';
				i++;
			}
		}
		return new String(c);
	}
}
