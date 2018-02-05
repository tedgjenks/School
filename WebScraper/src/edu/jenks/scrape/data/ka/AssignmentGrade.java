package edu.jenks.scrape.data.ka;

public class AssignmentGrade {

	private final String KA_STUDENT_NAME;
	private final byte BEST_SCORE;
	
	public AssignmentGrade(String kaStudentName, byte bestScore) {
		KA_STUDENT_NAME = kaStudentName;
		BEST_SCORE = bestScore;
	}

	public String getKaStudentName() {
		return KA_STUDENT_NAME;
	}

	public byte getBestScore() {
		return BEST_SCORE;
	}
}
