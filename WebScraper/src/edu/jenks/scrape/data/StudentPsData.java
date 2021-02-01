package edu.jenks.scrape.data;

public class StudentPsData {
	public final String SECTION;
	public final String STUDENT_NAME;
	public final long ID;
	
	public StudentPsData(String section, String studentName, long id) {
		SECTION = section;
		STUDENT_NAME = studentName;
		ID = id;
	}
}
