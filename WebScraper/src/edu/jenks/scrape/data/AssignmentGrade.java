package edu.jenks.scrape.data;

public class AssignmentGrade implements Comparable<AssignmentGrade> {

	private final byte SECTION;
	private final String STUDENT_NAME;
	private byte bestScore;
	
	public AssignmentGrade(byte section, String studentName, byte bestScore) {
		SECTION = section;
		STUDENT_NAME = studentName;
		this.bestScore = bestScore;
	}

	public String getStudentName() {
		return STUDENT_NAME;
	}
	
	public void setBestScore(byte bestScore) {
		this.bestScore = bestScore;
	}
	
	public byte getSection() {
		return SECTION;
	}

	public byte getBestScore() {
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
