package edu.jenks.scrape.data;

public class StudentSection {
	public final String SECTION;
	public final String STUDENT_NAME;
	
	public StudentSection(String key) {
		SECTION = key.substring(0, 1);
		STUDENT_NAME = key.substring(1);
	}
}
