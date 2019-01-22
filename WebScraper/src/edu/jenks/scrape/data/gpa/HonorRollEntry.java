package edu.jenks.scrape.data.gpa;

import java.util.*;

import edu.jenks.util.MathUtil;

public class HonorRollEntry {
	private static int minGradeA, minGradeAB;
	
	public static void setMinGradeAB(int minGradeArg) {
		minGradeAB = minGradeArg;
	}
	
	public static void setMinGradeA(int minGradeArg) {
		minGradeA = minGradeArg;
	}
	
	private boolean noGradesBelowMinGradeAB = true;
	private boolean noGradesBelowMinGradeA = true;
	private boolean atLeastOneGrade = false;
	private List<HonorRollEntryCourse> courses = new ArrayList<>(4);
	
	public HonorRollEntry() {
		if(minGradeA == 0 || minGradeAB == 0)
			throw new IllegalStateException("minGrades not initialized");
	}
	
	public void setNoHonorRoll() {
		noGradesBelowMinGradeA = false;
		noGradesBelowMinGradeAB = false;
	}
	
	public boolean isOnHonorRollA() {
		return noGradesBelowMinGradeA && atLeastOneGrade;
	}
	
	public boolean isOnHonorRollAB() {
		return noGradesBelowMinGradeAB && atLeastOneGrade;
	}
	
	public List<HonorRollEntryCourse> getCourses() {
		return courses;
	}
	
	public void addCourse(String name, String grade) {
		if(MathUtil.isIntegerNumber(grade)) { // numeric grade
			atLeastOneGrade = true;
			int gradeInt = Integer.parseInt(grade);
			if(gradeInt < minGradeAB)
				setNoHonorRoll();
			else if(gradeInt < minGradeA)
				noGradesBelowMinGradeA = false;
		} else { // non-numeric grade
			switch(grade) {
				case "FA":
					setNoHonorRoll();
					break;
				case "":
				case "_":
					grade = "No Grade!";
					break;
				default:
					grade = "Unhandled grade code: " + grade;
			}
		}
		courses.add(new HonorRollEntryCourse(name, grade));
	}
	
	public class HonorRollEntryCourse {
		public final String NAME;
		public final String GRADE;
		
		private HonorRollEntryCourse(String name, String grade) {
			NAME = name;
			GRADE = grade;
		}

		@Override
		public String toString() {
			return new StringBuilder(20).append(NAME).append(" - (").append(GRADE).append(")").toString();
		}
	}
}
