package edu.jenks.scrape.data;

import java.util.*;

public class Assignment {
	
	private final String NAME;
	private final Map<String, AssignmentGrade> ASSIGNMENT_GRADES = new HashMap<>(22);

	public Assignment(String name) {
		NAME = name;
	}
	
	public String getName() {
		return NAME;
	}
	
	public Iterator<AssignmentGrade> getAssignmentGrades() {
		return ASSIGNMENT_GRADES.values().iterator();
	}
	
	public void addAssignment(byte section, String studentName, byte score) {
		if(ASSIGNMENT_GRADES.containsKey(studentName)) {
			AssignmentGrade ag = ASSIGNMENT_GRADES.get(studentName);
			if(score > ag.getBestScore())
				ag.setBestScore(score);
		} else
			ASSIGNMENT_GRADES.put(studentName, new AssignmentGrade(section, studentName, score));
	}
}
