package edu.jenks.scrape.data;

public class StudentPsData {
	public final byte SECTION;
	public final String STUDENT_NAME;
	public final long ID;
	
	public StudentPsData(byte section, String studentName, long id) {
		SECTION = section;
		STUDENT_NAME = studentName;
		ID = id;
	}
}
