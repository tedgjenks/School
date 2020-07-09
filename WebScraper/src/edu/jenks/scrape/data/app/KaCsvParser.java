package edu.jenks.scrape.data.app;

import static java.lang.System.out;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

import org.jdom2.*;

import com.opencsv.CSVReader;
import edu.jenks.scrape.data.*;
import edu.jenks.scrape.data.app.scoring.KaScoring;
import edu.jenks.scrape.util.SystemInfo;
import edu.jenks.xml.*;

public class KaCsvParser extends AbstractCsvParser {
	
	private static final Map<String, Integer> MONTHS = new HashMap<>(24);
	private static final String KA_RULES_FILEPATH = SystemInfo.INSTANCE.RESOURCES_PATH + "game_dev_ka_rules.xml";
	private static final Set<String> UNRECOGNIZED_STUDENT_NAMES = new HashSet<>(5);
	
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
	
	public static KaCsvParser initParser() throws IOException, JDOMException {
		KaCsvParser kaReader =  new KaCsvParser();
		kaReader.loadExportFileProps();
		kaReader.loadAssignments();
		//kaReader.debugStudent("Hall, Braden");
		return kaReader;
	}
	
	public static void main(String[] args) {
		System.out.println("Begin");
		KaCsvParser kaReader = null;
		try {
			kaReader = initParser();
			//kaReader.mergeAssignments();
			kaReader.generateImportFiles();
			System.out.println("End without exception!");
		} catch(IOException | JDOMException e) {
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
	public final Map<String, Integer> HEADER_INDECES = new HashMap<>(8); // header name to row index
	private final Set<String> ASSIGNMENT_TYPES = new HashSet<>(4);
	private String psPath;
	private String assignmentNameColLabel, studentNameColLabel, dueDateColLabel, assignmentTypeColLabel;
	//private boolean combineAssignments = false;

	public KaCsvParser() throws IOException {
		super(SystemInfo.INSTANCE.LOGGING_PATH + "ps/pst_gd_1.csv"); // TODO allow for multiple files
		/*HEADER_INDECES.put(HEADER_ASSIGNMENT_NAME, null);
		HEADER_INDECES.put(HEADER_STUDENT_NAME, null);
		HEADER_INDECES.put(HEADER_BEST_SCORE, null);
		HEADER_INDECES.put(HEADER_DUE_DATE, null);
		HEADER_INDECES.put(HEADER_POINTS_POSSIBLE, null);
		HEADER_INDECES.put(HEADER_ASSIGNMENT_TYPE, null);
		HEADER_INDECES.put(HEADER_SCORE_AT_DUE_DATE, null);*/
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
	
	/*public void mergeAssignments() {
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
	}*/
	
	@Override
	protected void loadExportFileProps() throws IOException, JDOMException {
		Document document = JDOMHelper.buildDocument(KA_RULES_FILEPATH);
		Element rootElement = document.getRootElement();
		List<Element> rules = rootElement.getChild("rules").getChildren("rule");
		for(Element rule : rules)
			ASSIGNMENT_TYPES.add(rule.getAttributeValue("assignment-type"));
		loadDates(rootElement);
		Element relativePaths = rootElement.getChild("relative-paths");
		psPath = relativePaths.getAttributeValue("ps-path");
		String exportFile = SystemInfo.INSTANCE.LOGGING_PATH + relativePaths.getAttributeValue("ka-path");
		out.println("Export file: " + exportFile);
		Reader csvFile = new FileReader(exportFile);
		csvReader = new CSVReader(csvFile);
		Element headers = rootElement.getChild("headers");
		assignmentNameColLabel = headers.getAttributeValue("assignment-name");
		studentNameColLabel = headers.getAttributeValue("student-name");
		assignmentTypeColLabel = headers.getAttributeValue("assignment-type");
		KaScoring.init(rootElement, this);
	}
	
	private void loadDates(Element rootElement) { // TODO implement dates
		Element datesE = rootElement.getChild("dates");
		if(datesE != null) {
			dueDateColLabel = datesE.getAttributeValue("header-due-date");
			List<Element> dates = datesE.getChildren("date");
			for(Element dateE : dates) {
				int year = Integer.parseInt(dateE.getAttributeValue("date-year"));
				int month = Integer.parseInt(dateE.getAttributeValue("date-month"));
				int day = Integer.parseInt(dateE.getAttributeValue("date-day"));
				if("end".contentEquals(dateE.getAttributeValue("date-bound")))
					dueDateBefore = LocalDate.of(year, month, day);
				else
					dueDateAfter = LocalDate.of(year, month, day);
			}
		}
	}
	
	public void generateImportFiles() throws IOException {
		Iterator<String> assignmentNames = ASSIGNMENTS.keySet().iterator();
		while(assignmentNames.hasNext())
			generateImportFile(ASSIGNMENTS.get(assignmentNames.next()), SystemInfo.INSTANCE.LOGGING_PATH + psPath);
	}
	
	public Map<String, Assignment> getAssignmentRecords() {
		return ASSIGNMENTS;
	}
	
	private void loadHeaderIndeces(String[] headerRow) {
		for(int index = headerRow.length - 1; index >= 0; index--) {
			String value = headerRow[index].replaceAll("[^A-z\\s]", "");
			HEADER_INDECES.put(value, index);
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
			final String studentName = record[HEADER_INDECES.get(studentNameColLabel)].trim();
			if(studentName.length() == 0 || "null".equals(studentName))
				continue;
			StudentPsData studentPsData = STUDENT_PS_DATA.getStudentPsData(studentName);
			if(studentPsData == null) {
				UNRECOGNIZED_STUDENT_NAMES.add(studentName);
				continue;
			}
			int assignmentTypeIndex = HEADER_INDECES.get(assignmentTypeColLabel);
				String dueDate = null;
				if(dueDateColLabel != null)
					dueDate = record[HEADER_INDECES.get(dueDateColLabel)];
				String assignmentType = record[assignmentTypeIndex];
				if(processRecord(dueDate) && (ASSIGNMENT_TYPES.isEmpty() || ASSIGNMENT_TYPES.contains(assignmentType))) {
					String assignmentName = record[HEADER_INDECES.get(assignmentNameColLabel)];
					if(!ASSIGNMENTS.containsKey(assignmentName))
						ASSIGNMENTS.put(assignmentName, new Assignment(assignmentName));
					Assignment assignment = ASSIGNMENTS.get(assignmentName);
					KaScoring scoring = KaScoring.getInstance(assignmentType);
					byte section = studentPsData.SECTION;
					double score = scoring.getScore(record);
					assignment.addAssignment(section, studentName, score);
					processedCount++;
				}
			totalCount++;
		}
		System.out.println("KA records meeting due date: " + processedCount + " out of " + totalCount);
		for(String name : UNRECOGNIZED_STUDENT_NAMES)
			System.out.println(name + " not processed");
	}
	
	private boolean processRecord(String recordDueDate) {
		return recordDueDate == null || (testDateBefore(recordDueDate) && testDateAfter(recordDueDate));
	}
	
	private boolean testDateBefore(String recordDueDate) {
		if(dueDateBefore == null)
			return true;
		boolean meetsDueDate = false;
		String monthDay = recordDueDate.split(",")[0];
		final byte expectedLength = 3;
		if(monthDay.length() < expectedLength)
			return false;
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
		if(dueDateAfter == null)
			return true;
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
	
	/*public LocalDate getDueDateAfter() {
		return dueDateAfter;
	}

	public LocalDate getDueDateBefore() {
		return dueDateBefore;
	}

	public String getAssignmentNameColLabel() {
		return assignmentNameColLabel;
	}

	public String getStudentNameColLabel() {
		return studentNameColLabel;
	}

	public String getAssignmentTypeColLabel() {
		return assignmentTypeColLabel;
	}

	public String getCompletionDateLabel() {
		return completionDateLabel;
	}*/
}
