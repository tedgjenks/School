package edu.jenks.scrape.data.app;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import com.opencsv.CSVReader;
import edu.jenks.scrape.data.Assignment;

public class KaCsvParser extends AbstractCsvParser {
	
	private static final String HEADER_ASSIGNMENT_NAME = "Assignment Name";
	private static final String HEADER_STUDENT_NAME = "Student Name";
	private static final String HEADER_BEST_SCORE = "Score Best Ever";
	private static final String HEADER_DUE_DATE = "Due Date";
	private static final Map<String, Integer> MONTHS = new HashMap<>(24);
	private static final String KA_PROPERTIES_FILEPATH = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\KhanAcademy.properties";
	private static final String STUDENT_NUMBERS_PROPERTIES_FILEPATH = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolPages\\Alg2StudentNumbers.properties";
	
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
	
	public static KaCsvParser initParser() throws IOException {
		KaCsvParser kaReader =  new KaCsvParser();
		kaReader.loadExportFileProps();
		kaReader.populateStudentNumbers(STUDENT_NUMBERS_PROPERTIES_FILEPATH);
		kaReader.loadAssignments();
		return kaReader;
	}
	
	public static void main(String[] args) {
		System.out.println("Begin");
		KaCsvParser kaReader = null;
		try {
			kaReader = initParser();
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
	
	private LocalDate dueDateAfter, dueDateBefore;
	private final Map<String, Assignment> ASSIGNMENTS = new HashMap<>(100);
	private final Map<String, Integer> HEADER_INDECES = new HashMap<>(8);

	public KaCsvParser() {
		HEADER_INDECES.put(HEADER_ASSIGNMENT_NAME, null);
		HEADER_INDECES.put(HEADER_STUDENT_NAME, null);
		HEADER_INDECES.put(HEADER_BEST_SCORE, null);
		HEADER_INDECES.put(HEADER_DUE_DATE, null);
	}
	
	@Override
	protected void loadExportFileProps() throws IOException {
		EXPORT_FILE_PROPS.load(new FileInputStream(KA_PROPERTIES_FILEPATH));
		Reader csvFile = new FileReader(EXPORT_FILE_PROPS.getProperty("CSV_FILEPATH") + EXPORT_FILE_PROPS.getProperty("CSV_EXPORT_FILE"));
		int year = Integer.parseInt(EXPORT_FILE_PROPS.getProperty("DUE_YEAR"));
		int monthAfter = Integer.parseInt(EXPORT_FILE_PROPS.getProperty("DUE_MONTH_AFTER"));
		int dayAfter = Integer.parseInt(EXPORT_FILE_PROPS.getProperty("DUE_DAY_AFTER"));
		dueDateAfter = LocalDate.of(year, monthAfter, dayAfter);
		int monthBefore = Integer.parseInt(EXPORT_FILE_PROPS.getProperty("DUE_MONTH_BEFORE"));
		int dayBefore = Integer.parseInt(EXPORT_FILE_PROPS.getProperty("DUE_DAY_BEFORE"));
		dueDateBefore = LocalDate.of(year, monthBefore, dayBefore);
		csvReader = new CSVReader(csvFile);
	}
	
	public void generateImportFiles() throws IOException {
		Iterator<String> assignmentNames = ASSIGNMENTS.keySet().iterator();
		while(assignmentNames.hasNext())
			generateImportFile(ASSIGNMENTS.get(assignmentNames.next()));
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
		Iterator<String[]> records = csvReader.iterator();
		if(records.hasNext())
			loadHeaderIndeces(records.next());
		while(records.hasNext()) {
			String[] record = records.next();
			int dueDateIndex = HEADER_INDECES.get(HEADER_DUE_DATE);
			if(dueDateIndex < record.length) {
				String dueDate = record[dueDateIndex];
				if(processRecord(dueDate)) {
					String assignmentName = record[HEADER_INDECES.get(HEADER_ASSIGNMENT_NAME)];
					if(!ASSIGNMENTS.containsKey(assignmentName))
						ASSIGNMENTS.put(assignmentName, new Assignment(assignmentName));
					Assignment assignment = ASSIGNMENTS.get(assignmentName);
					String studentName = record[HEADER_INDECES.get(HEADER_STUDENT_NAME)];
					//studentName = studentName.replaceAll("'", "");
					if(!"null".equals(studentName)) {
						String grade = record[HEADER_INDECES.get(HEADER_BEST_SCORE)];
						String section = STUDENT_SECTION_MAP.get(studentName);
						if(section != null)
							assignment.addAssignment(Byte.parseByte(section), studentName, (grade == null || grade.length() == 0) ? 0 : Byte.parseByte(grade));
						else
							System.out.println(studentName + " does not have a section");
						processedCount++;
					}
				}
			} else
				System.out.println("Stub record length " + record.length + ": " + Arrays.toString(record));
			totalCount++;
		}
		System.out.println("KA records meeting due date: " + processedCount + " out of " + totalCount);
	}
	
	private boolean processRecord(String recordDueDate) {
		return testDateBefore(recordDueDate) && testDateAfter(recordDueDate);
	}
	
	private boolean testDateBefore(String recordDueDate) {
		boolean meetsDueDate = false;
		String monthDay = recordDueDate.split(",")[0];
		String month = monthDay.substring(0, 3);
		if(dueDateBefore.getMonthValue() > MONTHS.get(month))
			meetsDueDate = true;
		else if(dueDateBefore.getMonthValue() == MONTHS.get(month)) {
			String day = String.valueOf(monthDay.charAt(4));
			char second = monthDay.charAt(5);
			if(Character.isDigit(second))
				day += second;
			if(dueDateBefore.getDayOfMonth() >= Integer.parseInt(day))
				meetsDueDate = true;
		}
		return meetsDueDate;
	}
	
	private boolean testDateAfter(String recordDueDate) {
		boolean meetsDueDate = false;
		String monthDay = recordDueDate.split(",")[0];
		String month = monthDay.substring(0, 3);
		if(dueDateAfter.getMonthValue() < MONTHS.get(month))
			meetsDueDate = true;
		else if(dueDateAfter.getMonthValue() == MONTHS.get(month)) {
			String day = String.valueOf(monthDay.charAt(4));
			char second = monthDay.charAt(5);
			if(Character.isDigit(second))
				day += second;
			if(dueDateAfter.getDayOfMonth() <= Integer.parseInt(day))
				meetsDueDate = true;
		}
		return meetsDueDate;
	}
	
	public void close() throws IOException {
		if(csvReader != null) {
			csvReader.close();
		}
	}
}
