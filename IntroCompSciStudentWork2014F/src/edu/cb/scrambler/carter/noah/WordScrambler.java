package edu.cb.scrambler.carter.noah;
import java.util.ArrayList;
import java.util.List;
import edu.jenks.dist.cb.scrambler.Scrambler;

public class WordScrambler implements Scrambler {

	public WordScrambler() {
		
	}
	
	@Override
	public String scrambleWord(String word) {
		String swapindex = "A";
		StringBuffer dorw = new StringBuffer();
		String[] wordsplit = word.split("");
		for (int i = 0; i < wordsplit.length; i++){	
				try{
					if (wordsplit[i].equals(swapindex)){
						dorw.append(wordsplit[i+1]);
						dorw.append(wordsplit[i]);
						i++;
					}else{
						dorw.append(wordsplit[i]);
					}
				}catch (ArrayIndexOutOfBoundsException e){
					dorw.append(wordsplit[i]);
				}
		}
		return (dorw.toString());
	}
	
	@Override
	public void scrambleOrRemove(List<String> wordlist) {
		List<String> scramlist = new ArrayList<String>(wordlist);
		String oldword = null;
		int cnt = 0;
		for (int i = 0; i < wordlist.size(); i++){
			oldword = scrambleWord(wordlist.get(i));
			wordlist.set(i, oldword);
		}
		for (int i = 0; i < wordlist.size(); i++){
			if (scramlist.get(i).equals(wordlist.get(i))){
				wordlist.set(i, "");
				cnt++;
			}
		}
		for (;cnt >= 0; cnt--){
			wordlist.remove("");
		}
	}
	
	}

