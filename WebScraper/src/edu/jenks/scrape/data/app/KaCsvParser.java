package edu.jenks.scrape.data.app;

import static java.lang.System.out;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import com.opencsv.CSVReader;
import edu.jenks.scrape.data.Assignment;
import edu.jenks.scrape.data.AssignmentGrade;

public class KaCsvParser extends AbstractCsvParser {
	
	private static final String HEADER_ASSIGNMENT_NAME = "Assignment Name";
	private static final String HEADER_STUDENT_NAME = "Student Name";
	private static final String HEADER_BEST_SCORE = "Score Best Ever";
	private static final String HEADER_DUE_DATE = "Due Date";
	private static final String HEADER_POINTS_POSSIBLE = "Points Possible";
	private static final String HEADER_ASSIGNMENT_TYPE = "Assignment Type";
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
		//kaReader.debugStudent("Hall, Braden");
		return kaReader;
	}
	
	public void debugStudent(String studentName) {
		int totalPoints = 0;
		for(String key : ASSIGNMENTS.keySet()) {
			out.print(key + ": ");
			Assignment a = ASSIGNMENTS.get(key);
			Iterator<AssignmentGrade> iter = a.getAssignmentGrades();
			while(iter.hasNext()) {
				AssignmentGrade grade = iter.next();
				if(grade.getStudentName().equals(studentName)) {
					out.println(grade.getBestScore());
					totalPoints += grade.getBestScore();
					break;
				}
			}
		}
		out.println("Total for " + studentName + ": " + totalPoints);
	}
	
	public static void main(String[] args) {
		System.out.println("Begin");
		KaCsvParser kaReader = null;
		try {
			kaReader = initParser();
			kaReader.mergeAssignments();
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
	private final Set<String> ASSIGNMENT_TYPES = new HashSet<>(4);
	private boolean combineAssignments = false;

	public KaCsvParser() {
		HEADER_INDECES.put(HEADER_ASSIGNMENT_NAME, null);
		HEADER_INDECES.put(HEADER_STUDENT_NAME, null);
		HEADER_INDECES.put(HEADER_BEST_SCORE, null);
		HEADER_INDECES.put(HEADER_DUE_DATE, null);
		HEADER_INDECES.put(HEADER_POINTS_POSSIBLE, null);
		HEADER_INDECES.put(HEADER_ASSIGNMENT_TYPE, null);
	}
	
	public void mergeAssignments() {
		if(combineAssignments) {
			Assignment combinedAssignment = new Assignment(EXPORT_FILE_PROPS.getProperty("COMBINED_NAME"));
			Map<String, AssignmentGrade> studentGradeMap = new HashMap<>(80);
			int totalPoints = 0;
			Iterator<String> assignmentNames = ASSIGNMENTS.keySet().iterator();
			while(assignmentNames.hasNext()) {
				Assignment oldAssignment = ASSIGNMENTS.get(assignmentNames.next());
				int pointsPossible = oldAssignment.getPointsPossible();
				out.println("Processing " + oldAssignment.getName() + " worth " + pointsPossible + " points.");
				totalPoints += pointsPossible;
				Iterator<AssignmentGrade> grades = oldAssignment.getAssignmentGrades();
				while(grades.hasNext()) {
					AssignmentGrade oldGrade = grades.next();
					String studentName = oldGrade.getStudentName();
					if(!studentGradeMap.containsKey(studentName))
						studentGradeMap.put(studentName, new AssignmentGrade(oldGrade.getSection(), studentName));
					AssignmentGrade newGrade = studentGradeMap.get(studentName);
					newGrade.increaseScore(oldGrade.getBestScore());
				}
			}
			Iterator<AssignmentGrade> newGrades = studentGradeMap.values().iterator();
			while(newGrades.hasNext()) {
				AssignmentGrade newGrade = newGrades.next();
				combinedAssignment.addAssignment(newGrade.getSection(), newGrade.getStudentName(), newGrade.getBestScore());
			}
			ASSIGNMENTS.clear();
			ASSIGNMENTS.put(combinedAssignment.getName(), combinedAssignment);
			out.println("Total points after merge = " + totalPoints);
		}
	}
	
	@Override
	protected void loadExportFileProps() throws IOException {
		EXPORT_FILE_PROPS.load(new FileInputStream(KA_PROPERTIES_FILEPATH));
		String[] assignmentTypes = EXPORT_FILE_PROPS.getProperty("ASSIGNMENT_TYPES").split(",");
		for(String type : assignmentTypes)
			ASSIGNMENT_TYPES.add(type);
		String combineValue = EXPORT_FILE_PROPS.getProperty("COMBINE");
		combineAssignments = combineValue != null && Boolean.parseBoolean(combineValue);
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
			if(record.length <= 1)
				continue;
			final String studentName = record[HEADER_INDECES.get(HEADER_STUDENT_NAME)].trim();
			if(studentName.length() == 0 || "null".equals(studentName))
				continue;
			int assignmentTypeIndex = HEADER_INDECES.get(HEADER_ASSIGNMENT_TYPE);
			int dueDateIndex = HEADER_INDECES.get(HEADER_DUE_DATE);
			if(dueDateIndex < record.length) {
				String dueDate = record[dueDateIndex];
				String assignmentType = record[assignmentTypeIndex];
				if(processRecord(dueDate) && (ASSIGNMENT_TYPES.isEmpty() || ASSIGNMENT_TYPES.contains(assignmentType))) {
					String assignmentName = record[HEADER_INDECES.get(HEADER_ASSIGNMENT_NAME)];
					if(!ASSIGNMENTS.containsKey(assignmentName))
						ASSIGNMENTS.put(assignmentName, new Assignment(assignmentName));
					Assignment assignment = ASSIGNMENTS.get(assignmentName);
					if(assignment.getPointsPossible() == 0) {
						String pointsPossible = record[HEADER_INDECES.get(HEADER_POINTS_POSSIBLE)];
						if(pointsPossible != null && pointsPossible.length() > 0)
							assignment.setPointsPossible(Integer.parseInt(pointsPossible));
					}
					String grade = record[HEADER_INDECES.get(HEADER_BEST_SCORE)];
					String section = STUDENT_SECTION_MAP.get(studentName);
					if(section != null) {
						int score = (grade == null || grade.length() == 0) ? 0 : Byte.parseByte(grade);
						if(score > assignment.getPointsPossible()) {
							System.err.println("Score of " + score + " for " + studentName + " on assignment " + assignmentName);
							score = assignment.getPointsPossible();
						}
						assignment.addAssignment(Byte.parseByte(section), studentName, score);
					}else
						System.out.println(studentName + " does not have a section");
					processedCount++;
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
