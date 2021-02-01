package edu.jenks.scrape.data.app;

import java.io.*;
import java.util.*;
import com.opencsv.CSVReader;
import edu.jenks.scrape.data.StudentPsData;
import edu.jenks.util.MathUtil;

/**
 * Read any export file from PS and generate a file with section, student name, student ID
 * 
 * @author tedgj
 *
 */
public class PsCsvParser {
	private final CSVReader CSV_READER;
	private final Map<String, StudentPsData> STUDENT_SECTION_MAP = new HashMap<>(100);
	private final Set<String> SECTIONS = new HashSet<>(6);

	// file name pst_<course code>_<section>.csv
	public PsCsvParser(String fileName) throws IOException {
		int endIndex = fileName.indexOf(".csv");
		int beginIndex = fileName.lastIndexOf('_') + 1;
		final String section = fileName.substring(beginIndex, endIndex);
		SECTIONS.add(section);
		CSV_READER = new CSVReader(new FileReader(fileName));
		List<String[]> records = CSV_READER.readAll();
		for(String[] record : records) {
			String studentNum = record[0];
			if(MathUtil.isIntegerNumber(studentNum)) {
				String studentName = record[1];
				STUDENT_SECTION_MAP.put(studentName, new StudentPsData(section, studentName, Long.parseLong(studentNum)));
			}
		}
		//new StudentSection(section, studentName)
		CSV_READER.close();
	}
	
	public StudentPsData getStudentPsData(String studentName) {
		return STUDENT_SECTION_MAP.get(studentName);
	}
	
	public Set<String> getSections() {
		return SECTIONS;
	}
}
