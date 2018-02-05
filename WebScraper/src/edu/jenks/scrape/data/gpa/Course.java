package edu.jenks.scrape.data.gpa;

import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

import edu.jenks.util.MathUtil;

public class Course {
	private long courseId;
	private Student student;
	private String yearTerm;
	private String courseNumber;
	private String courseName;
	private float earnedCredit;
	private List<Grade> grades = new ArrayList<>(50);
	
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

	@Override
	public boolean equals(Object obj) {
		Course argCourse = (Course)obj;
		return yearTerm.equals(argCourse.getYearTerm()) && courseNumber.equals(argCourse.getCourseNumber());
	}
	
	public Course(Student student) {
		this.student = student;
	}
	
	public List<Grade> getGrades() {
		return grades;
	}
	
	public void addGrade(HtmlElement gradeAnchor) {
		String grade = gradeAnchor.getTextContent();
		byte gradeB = MathUtil.isRealNumber(grade) ? Byte.parseByte(grade) : 0;
		Grade gradeO = new Grade(gradeB);
		gradeO.setGradeAnchor(gradeAnchor);
		grades.add(gradeO);
	}
	
	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getYearTerm() {
		return yearTerm;
	}

	public void setYearTerm(String yearTerm) {
		this.yearTerm = yearTerm;
	}

	public String getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(String courseNumber) {
		this.courseNumber = courseNumber;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public float getEarnedCredit() {
		return earnedCredit;
	}

	public void setEarnedCredit(float earnedCredit) {
		this.earnedCredit = earnedCredit;
	}
}
