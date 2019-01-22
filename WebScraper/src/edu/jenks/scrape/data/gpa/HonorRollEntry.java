package edu.jenks.scrape.data.gpa;

import java.util.*;

import edu.jenks.util.MathUtil;

public class HonorRollEntry {
	private static int minGrade;
	
	public static void setMinGrade(int minGradeArg) {
		minGrade = minGradeArg;
	}
	
	private boolean onHonorRoll = true;
	private List<HonorRollEntryCourse> courses = new ArrayList<>(4);
	
	public HonorRollEntry() {
		if(minGrade == 0)
			throw new IllegalStateException("minGrade not initialized");
	}
	
	public boolean isOnHonorRoll() {
		return onHonorRoll;
	}
	
	public List<HonorRollEntryCourse> getCourses() {
		return courses;
	}
	
	public void addCourse(String name, String grade) {
		if(MathUtil.isIntegerNumber(grade)) { // numeric grade
			if(Integer.parseInt(grade) < minGrade)
				onHonorRoll = false;
		} else { // non-numeric grade
			switch(grade) {
				case "FA":
					onHonorRoll = false;
					break;
				case "":
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
			return new StringBuilder(20).append(NAME).append("(").append(GRADE).append(")").toString();
		}
	}
}
