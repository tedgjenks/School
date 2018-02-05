package edu.jenks.scrape.data.ka;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class KaCsvReader {
	
	private static final String HEADER_ASSIGNMENT_NAME = "Assignment Name";
	private static final String HEADER_STUDENT_NAME = "Student Name";
	private static final String HEADER_BEST_SCORE = "Score Best Ever";
	private static final String HEADER_DUE_DATE = "Due Date";
	private static final Map<String, Integer> MONTHS = new HashMap<>(24);
	private static final String KA_PROPERTIES_FILEPATH = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\KhanAcademy.properties";
	private static final String STUDENT_NUMBERS_PROPERTIES_FILEPATH = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolPages\\StudentNumbers.properties";
	private static final Properties KA_PROPS = new Properties();
	
	static {
		MONTHS.put("Jan", 1);
		MONTHS.put("Feb", 2);
		MONTHS.put("Mar", 3);
		MONTHS.put("Apr", 4);
		MONTHS.put("May", 5);
		MONTHS.put("Jun", 6);
		MONTHS.put("Jul", 7);
		MONTHS.put("Aug", 8);
		MONTHS.put("Sep", 9);
		MONTHS.put("Oct", 10);
		MONTHS.put("Nov", 11);
		MONTHS.put("Dec", 12);
	}
	
	public static KaCsvReader initKaReader() throws IOException {
		KA_PROPS.load(new FileInputStream(KA_PROPERTIES_FILEPATH));
		Reader reader = new FileReader(KA_PROPS.getProperty("CSV_FILEPATH") + KA_PROPS.getProperty("CSV_EXPORT_FILE"));
		int year = Integer.parseInt(KA_PROPS.getProperty("DUE_YEAR"));
		int month = Integer.parseInt(KA_PROPS.getProperty("DUE_MONTH"));
		int day = Integer.parseInt(KA_PROPS.getProperty("DUE_DAY"));
		LocalDate date = LocalDate.of(year, month, day);
		KaCsvReader kaReader =  new KaCsvReader(date, reader);
		kaReader.loadAssignments();
		return kaReader;
	}
	
	public static void main(String[] args) {
		System.out.println("Begin");
		KaCsvReader kaReader = null;
		try {
			kaReader = initKaReader();
			kaReader.generateImportFiles();
			System.out.println("End without exception!");
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(kaReader != null) {
				try {
					kaReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private final LocalDate DUE_DATE;
	private final Map<String, Assignment> ASSIGNMENTS = new HashMap<>(100);
	private final Map<String, Integer> HEADER_INDECES = new HashMap<>(8);
	private final Map<String, String> STUDENT_NAME_NUMBER_MAP = new HashMap<String, String>(38);
	private final CSVReader CSV_READER;

	public KaCsvReader(LocalDate dueDate, Reader csvFile) {
		DUE_DATE = dueDate;
		CSV_READER = new CSVReader(csvFile);
		HEADER_INDECES.put(HEADER_ASSIGNMENT_NAME, null);
		HEADER_INDECES.put(HEADER_STUDENT_NAME, null);
		HEADER_INDECES.put(HEADER_BEST_SCORE, null);
		HEADER_INDECES.put(HEADER_DUE_DATE, null);
	}
	
	public void populateStudentNumbers() throws IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(STUDENT_NUMBERS_PROPERTIES_FILEPATH));
		Iterator<Object> names = props.keySet().iterator();
		while(names.hasNext()) {
			String name = names.next().toString();
			STUDENT_NAME_NUMBER_MAP.put(name, props.getProperty(name));
		}
	}
	
	public void generateImportFiles() throws IOException {
		populateStudentNumbers();
		Iterator<String> assignmentNames = ASSIGNMENTS.keySet().iterator();
		while(assignmentNames.hasNext())
			generateImportFile(ASSIGNMENTS.get(assignmentNames.next()));
	}
	
	private void generateImportFile(Assignment assignment) throws IOException {
		File file = createFile(assignment.getKaName());
		CSVWriter writer = new CSVWriter(new FileWriter(file));
		writer.writeNext(new String[] {"STUDENT NUMBER", "STUDENT NAME", "SCORE"});
		List<AssignmentGrade> grades = assignment.getAssignmentGrades();
		for(AssignmentGrade grade : grades) {
			String name = grade.getKaStudentName();
			String number = STUDENT_NAME_NUMBER_MAP.get(name);
			if(number != null)
				writer.writeNext(new String[] {number, name, String.valueOf(grade.getBestScore())});
		}
		writer.close();
	}
	
	private File createFile(String assignmentName) throws IOException {
		String fileName = assignmentName.replace(':', '-');
		File file = new File(KA_PROPS.getProperty("CSV_FILEPATH") + fileName + ".csv");
		file.createNewFile();
		return file;
	}
	
	public Map<String, Assignment> getAssignmentRecords() {
		return ASSIGNMENTS;
	}
	
	private void loadHeaderIndeces(String[] headerRow) {
		for(int index = headerRow.length - 1; index >= 0; index--) {
			String headerValue = headerRow[index];
			if(HEADER_INDECES.containsKey(headerValue))
				HEADER_INDECES.put(headerValue, index);
		}
	}
	
	public void loadAssignments() {
		int totalCount = 0, processedCount = 0;
		Iterator<String[]> records = CSV_READER.iterator();
		if(records.hasNext())
			loadHeaderIndeces(records.next());
		while(records.hasNext()) {
			String[] record = records.next();
			String dueDate = record[HEADER_INDECES.get(HEADER_DUE_DATE)];
			if(processRecord(dueDate)) {
				String assignmentName = record[HEADER_INDECES.get(HEADER_ASSIGNMENT_NAME)];
				if(!ASSIGNMENTS.containsKey(assignmentName))
					ASSIGNMENTS.put(assignmentName, new Assignment(assignmentName));
				Assignment assignment = ASSIGNMENTS.get(assignmentName);
				String studentName = record[HEADER_INDECES.get(HEADER_STUDENT_NAME)];
				if(!"null".equals(studentName)) {
					String grade = record[HEADER_INDECES.get(HEADER_BEST_SCORE)];
					assignment.addAssignment(studentName, (grade == null || grade.length() == 0) ? 0 : Byte.parseByte(grade));
					processedCount++;
				}
			}
			totalCount++;
		}
		System.out.println("KA records meeting due date: " + processedCount + " out of " + totalCount);
	}
	
	private boolean processRecord(String recordDueDate) {
		boolean meetsDueDate = false;
		String monthDay = recordDueDate.split(",")[0];
		String month = monthDay.substring(0, 3);
		if(DUE_DATE.getMonthValue() < MONTHS.get(month))
			meetsDueDate = true;
		else if(DUE_DATE.getMonthValue() == MONTHS.get(month)) {
			String day = String.valueOf(monthDay.charAt(4));
			char second = monthDay.charAt(5);
			if(Character.isDigit(second))
				day += second;
			if(DUE_DATE.getDayOfMonth() <= Integer.parseInt(day))
				meetsDueDate = true;
		}
		return meetsDueDate;
	}
	
	public void close() throws IOException {
		if(CSV_READER != null) {
			CSV_READER.close();
		}
	}

}
