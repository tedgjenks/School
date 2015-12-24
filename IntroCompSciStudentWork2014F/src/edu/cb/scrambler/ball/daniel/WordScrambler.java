package edu.cb.scrambler.ball.daniel;

import java.util.List;

import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler 
{

	public WordScrambler() 
	{
		// TODO Auto-generated constructor stub
	}

	@Override
	public void scrambleOrRemove(List<String> list) 
	{
		for (int i = 0; i < list.size(); i++)
		{
			String unScramb = list.get(i);
			String scramb = scrambleWord(list.get(i));
			if (unScramb.equals(scramb))
			{
				list.remove(i);
			}
			else
			{
				list.set(i, scramb);
			}
		}
	}

	@Override
	public String scrambleWord(String word) 
	{
		String buildingString = ("");
		for (int i = 0; i < word.length(); i++)
		{
			if (word.charAt(i) != ('A'))
			{
				buildingString += word.charAt(i);
			}
			else
			{
				if (i != word.length()-1)
				{
					if (word.charAt(i+1) != ('A'))
					{
						buildingString += word.charAt(i+1);
						buildingString += ('A');
						i++;
					}
					else
					{
						// A thing that was changed.
						buildingString += word.charAt(i);
					}
				}
				else
				{
					buildingString += word.charAt(i);
				}
			}
		}
		System.out.println(buildingString);
		return buildingString;
	}

}
