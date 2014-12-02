package edu.cb.scrambler.carter.noah;
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
		for (int i = 0; i < wordsplit.length - 1; i++){	
					if (wordsplit[i].equals(swapindex) && !wordsplit[i+1].equals(swapindex)){
						dorw.append(wordsplit[i+1]);
						dorw.append(wordsplit[i]);
						i++;
					}else{
						dorw.append(wordsplit[i]);
					}
		}
		if(wordsplit.length > dorw.length()){
			dorw.append(wordsplit[wordsplit.length - 1]);
		}
		return (dorw.toString());
	}
	
	@Override
	public void scrambleOrRemove(List<String> wordlist) {
		String oldword = null;
		int cnt = 0;
		for (int i = 0; i < wordlist.size(); i++){
			oldword = scrambleWord(wordlist.get(i));
			if (oldword.equals(wordlist.get(i))){
				wordlist.set(i, "");
				cnt ++;
			}else{
				wordlist.set(i, oldword);
			}
		}
		for (;cnt >= 0; cnt--){
			wordlist.remove("");
		}
	}
	
}