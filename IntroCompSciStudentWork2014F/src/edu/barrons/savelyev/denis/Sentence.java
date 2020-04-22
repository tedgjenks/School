package edu.barrons.savelyev.denis;
import java.util.List;
import java.util.ArrayList;
import edu.jenks.dist.barrons.AbstractSentence;

public class Sentence extends AbstractSentence{

	public static void main(String[] args) {
		Sentence test = new Sentence("!i! love! you!");
		stringPrint(test.getWords());
		//System.out.println(test.getWords());
	}
	
	private static void stringPrint(String[] arr) {
		System.out.print("[ ");
		for(int x = 0; x < arr.length; x++) {
			System.out.print(arr[x] + ", ");
		}
		System.out.print(" ]");
	}
	
	public Sentence() {
		setSentence("");
	}
	
	public Sentence(String sentence) {
		setSentence(sentence);
	}
	
	public int countWords() {
		int count = 0;
		int[] blankPositions = new int[getBlankPositions().size()];//creates a int array with getBlankPositions
		for(int x = 0; x < blankPositions.length + 1; x++) {
			count++;
		}
		return count;
	}

	public List<Integer> getBlankPositions() {
		List<Integer> positions = new ArrayList<Integer>();
        int indexPos = 0;
        for (char curr : getSentence().toCharArray()) {
            if (Character.toString(curr).equals(" ")) {
            	positions.add(indexPos);
            }
            indexPos++;
        }
        return positions;
	}
	
	public String[] getWords() {
		String[] words = getSentence().split("\\s+");
		return words;
	}

}
