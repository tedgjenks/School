package edu.frstats.jenks.dist;

/**
 * Encapsulates a score and the number of students who made that score.
 * 
 * @author Ted Jenks
 *
 */
public class ScoreInfo {
	private int score;
	private int numStudents;
	
	public ScoreInfo(int aScore) {
		score = aScore;
		numStudents = 1;
	}
	
	public void increment() {
		numStudents++;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getFrequency() {
		return numStudents;
	}
	
	@Override
	public String toString() {
		return score + " score; " + numStudents + " students";
	}
}
