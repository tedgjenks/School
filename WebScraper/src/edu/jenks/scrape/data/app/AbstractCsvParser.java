package edu.jenks.scrape.data.app;

import java.io.*;
import java.util.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import edu.jenks.scrape.data.*;
import edu.jenks.scrape.util.SystemInfo;

public abstract class AbstractCsvParser {
	private static final String SECTION_KEY = "sections";
	protected final Properties EXPORT_FILE_PROPS = new Properties();
	protected final Map<String, String> STUDENT_NAME_NUMBER_MAP = new HashMap<String, String>(38);
	protected final Map<String, String> STUDENT_SECTION_MAP = new HashMap<String, String>(38);
	protected CSVReader csvReader;
	protected String[] sections;
	
	protected abstract void loadExportFileProps() throws IOException;
	
	protected void populateStudentNumbers(String studentNumbersPropertiesFilepath) throws IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(studentNumbersPropertiesFilepath));
		sections = props.getProperty(SECTION_KEY).split(",");
		Iterator<Object> names = props.keySet().iterator();
		while(names.hasNext()) {
			String key = names.next().toString();
			if(!SECTION_KEY.equals(key)) {
				StudentSection ss = new StudentSection(key);
				STUDENT_SECTION_MAP.put(ss.STUDENT_NAME, ss.SECTION);
				STUDENT_NAME_NUMBER_MAP.put(ss.STUDENT_NAME, props.getProperty(key));
			}
		}
	}

	protected void generateImportFile(Assignment assignment) throws IOException {
		Map<String, CSVWriter> SECTION_WRITER_MAP = new HashMap<>(4);
		for(String section : sections) {
			File file = createFile(section + assignment.getName());
			CSVWriter writer = new CSVWriter(new FileWriter(file));
			writer.writeNext(new String[] {"STUDENT NUMBER", "STUDENT NAME", "SCORE"});
			SECTION_WRITER_MAP.put(section, writer);
		}
		Iterator<AssignmentGrade> grades = assignment.getAssignmentGrades();
		List<AssignmentGrade> gradesList = new ArrayList<>(50);
		while(grades.hasNext())
			gradesList.add(grades.next());
		Collections.sort(gradesList);
		for(AssignmentGrade grade : gradesList) {
			String name = grade.getStudentName();
			String number = STUDENT_NAME_NUMBER_MAP.get(name);
			if(number != null) {
				CSVWriter writer = SECTION_WRITER_MAP.get(String.valueOf(grade.getSection()));
				writer.writeNext(new String[] {number, name, String.valueOf(grade.getBestScore())});
			}
		}
		for(CSVWriter writer : SECTION_WRITER_MAP.values())
			writer.close();
	}
	
	private File createFile(String assignmentName) throws IOException {
		String fileName = assignmentName.replace(':', '-');
		fileName = fileName.replace('?', ' ');
		String path = SystemInfo.INSTANCE.LOGGING_PATH + EXPORT_FILE_PROPS.getProperty("CSV_FILEPATH") + fileName + ".csv";
		System.out.println(path);
		File file = new File(path);
		file.createNewFile();
		return file;
	}
}
