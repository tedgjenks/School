package edu.jenks.scrape.data;

public class AssignmentGrade implements Comparable<AssignmentGrade> {
	
	private static final byte ZERO_SCORE = 0;

	private final byte SECTION;
	private final String STUDENT_NAME;
	private int bestScore;
	
	public AssignmentGrade(byte section, String studentName, int bestScore) {
		SECTION = section;
		STUDENT_NAME = studentName;
		this.bestScore = bestScore;
	}
	
	public AssignmentGrade(byte section, String studentName) {
		this(section, studentName, ZERO_SCORE);
	}
	
	public void increaseScore(int increase) {
		bestScore += increase;
	}

	public String getStudentName() {
		return STUDENT_NAME;
	}
	
	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}
	
	public byte getSection() {
		return SECTION;
	}

	public int getBestScore() {
		return bestScore;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(AssignmentGrade arg0) {
		return STUDENT_NAME.compareTo(arg0.getStudentName());
	}
	
	
}
