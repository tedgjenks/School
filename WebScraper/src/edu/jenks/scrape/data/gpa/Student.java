package edu.jenks.scrape.data.gpa;

public class Student {

	private long studentId;
	private String firstName;
	private String lastName;
	private byte gradeLevel;
	private short rank;
	private float gpaPowerSchool;
	private float gpaHistoricalGrades;
	private boolean avid;
	
	public boolean isAvid() {
		return avid;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof Student))
			return false;
		else if (studentId != 0) {
			return studentId == ((Student)obj).studentId;
		} else {
			Student student = ((Student)obj);
			return lastName.equals(student.lastName) && firstName.equals(student.firstName);
		}
	}

	public void setAvid(boolean avid) {
		this.avid = avid;
	}

	public String getFullName() {
		return new StringBuilder(50).append(getLastName()).append(", ").append(getFirstName()).toString();
	}
	
	public float getGpaHistoricalGrades() {
		return gpaHistoricalGrades;
	}
	public void setGpaHistoricalGrades(float gpaHistoricalGrades) {
		this.gpaHistoricalGrades = gpaHistoricalGrades;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public byte getGradeLevel() {
		return gradeLevel;
	}
	public void setGradeLevel(byte gradeLevel) {
		this.gradeLevel = gradeLevel;
	}
	public short getRank() {
		return rank;
	}
	public void setRank(short rank) {
		this.rank = rank;
	}
	public float getGpaPowerSchool() {
		return gpaPowerSchool;
	}
	public void setGpaPowerSchool(float gpaPowerSchool) {
		this.gpaPowerSchool = gpaPowerSchool;
	}
}
