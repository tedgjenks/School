package edu.jenks.scrape.data.gpa;

import com.gargoylesoftware.htmlunit.html.HtmlElement;

public class Grade {
	private long gradesId;
	private byte grade;
	private float gpaWeightedCalc;
	private float earnedCreditCalc;
	private float gpaStoredGrade;
	private float earnedCreditStoredGrade;
	private HtmlElement gradeAnchor;
	
	public Grade() {}
	
	public float getEarnedCreditCalc() {
		return earnedCreditCalc;
	}

	public void setEarnedCreditCalc(float earnedCreditCalc) {
		this.earnedCreditCalc = earnedCreditCalc;
	}
	
	@Override
	public boolean equals(Object obj) {
		Grade argGrade = (Grade)obj;
		return grade == argGrade.getGrade();
	}
	
	public HtmlElement getGradeAnchor() {
		return gradeAnchor;
	}

	public void setGradeAnchor(HtmlElement gradeAnchor) {
		this.gradeAnchor = gradeAnchor;
	}

	public Grade(byte grade) {
		this.grade = grade;
	}
	
	public long getGradesId() {
		return gradesId;
	}
	public void setGradesId(long gradesId) {
		this.gradesId = gradesId;
	}
	public byte getGrade() {
		return grade;
	}
	public void setGrade(byte grade) {
		this.grade = grade;
	}
	public float getGpaWeightedCalc() {
		return gpaWeightedCalc;
	}
	public void setGpaWeightedCalc(float gpaWeightedCalc) {
		this.gpaWeightedCalc = gpaWeightedCalc;
	}
	public float getGpaStoredGrade() {
		return gpaStoredGrade;
	}
	public void setGpaStoredGrade(float gpaStoredGrade) {
		this.gpaStoredGrade = gpaStoredGrade;
	}
	public float getEarnedCreditStoredGrade() {
		return earnedCreditStoredGrade;
	}
	public void setEarnedCreditStoredGrade(float earnedCreditStoredGrade) {
		this.earnedCreditStoredGrade = earnedCreditStoredGrade;
	}
}
