package edu.jenks.scrape.report;
import java.util.*;

import edu.jenks.scrape.data.gpa.Student;
import edu.jenks.util.MathUtil;
public class PLTW_ByGradeWithAVID {
	private static final Set<String> PLTW_COURSES = new HashSet<>();
	
	private final Set<Student> PLTW_STUDENTS = new HashSet<>();
	
	static {
		PLTW_COURSES.add("PLTW");
		PLTW_COURSES.add("Biomedical");
		PLTW_COURSES.add("Human Body");
		PLTW_COURSES.add("Interventions");
	}
	
	private boolean isCourseAVID(String courseName) {
		return courseName.contains("AVID");
	}
	
	private boolean isCoursePLTW(String courseName) {
		Iterator<String> iter = PLTW_COURSES.iterator();
		while(iter.hasNext()) {
			if(courseName.contains(iter.next()))
				return true;
		}
		return false;
	}
	
	public void addIfPLTW(Student student, String courseName, String grade) {
		if(isCourseAVID(courseName))
			student.setAvid(true);
		else if(isCoursePLTW(courseName) && MathUtil.isIntegerNumber(grade) && Integer.parseInt(grade) >= 60) {
			PLTW_STUDENTS.add(student);
		}
	}
	
	public int countStudentsPLTW() {
		return PLTW_STUDENTS.size();
	}
	
	public int countStudentsPLTW_AVID() {
		int count = 0;
		Iterator<Student> iter = PLTW_STUDENTS.iterator();
		while(iter.hasNext()) {
			if(iter.next().isAvid())
				count++;
		}
		return count;
	}
}
