package edu.jenks.scrape.data.ka;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
	
	private final String KA_NAME;
	private final List<AssignmentGrade> ASSIGNMENT_GRADES = new ArrayList<>(22);

	public Assignment(String kaName) {
		KA_NAME = kaName;
	}
	
	public String getKaName() {
		return KA_NAME;
	}
	
	public List<AssignmentGrade> getAssignmentGrades() {
		return ASSIGNMENT_GRADES;
	}
	
	public void addAssignment(String name, byte score) {
		ASSIGNMENT_GRADES.add(new AssignmentGrade(name, score));
	}
}
