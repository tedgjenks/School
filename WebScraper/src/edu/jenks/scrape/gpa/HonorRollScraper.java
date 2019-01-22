package edu.jenks.scrape.gpa;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import com.gargoylesoftware.htmlunit.html.*;
import com.opencsv.CSVWriter;

import edu.jenks.scrap.util.SystemInfo;
import edu.jenks.scrape.Scraper;
import edu.jenks.scrape.data.gpa.HonorRollEntry;
import edu.jenks.scrape.data.gpa.HonorRollEntry.HonorRollEntryCourse;
import edu.jenks.scrape.data.gpa.Student;
import edu.jenks.util.MathUtil;

import static java.lang.System.out;

public class HonorRollScraper extends AbstractClassRankScraper {
	
	private static final HonorRollScraper INSTANCE = new HonorRollScraper();
	
	private final Properties HONOR_ROLL_PROPERTIES;
	private final Set<String> VALID_TERMS = new HashSet<>(5);
	private final CSVWriter A_HR_WRITER, AB_HR_WRITER, NOT_HR_WRITER;
	public final String QUERY_TERM, QUERY_SEMESTER, QUERY_YEAR = "Y1", QUERY_FINAL = "F1";
	
	public HonorRollScraper() {
		final String historicalGradesPropertiesPath = SystemInfo.INSTANCE.RESOURCES_PATH + "PowerSchoolPages/HistoricalGrades.properties";
		final String honorRollPropertiesPath = SystemInfo.INSTANCE.RESOURCES_PATH + "HonorRoll.properties";
		HONOR_ROLL_PROPERTIES = new Properties();
		try {
			HISTORICAL_GRADES_PROPERTIES.load(new FileInputStream(historicalGradesPropertiesPath));
			HONOR_ROLL_PROPERTIES.load(new FileInputStream(honorRollPropertiesPath));
			QUERY_TERM = HONOR_ROLL_PROPERTIES.getProperty("TERM");
			QUERY_SEMESTER = HONOR_ROLL_PROPERTIES.getProperty("SEMESTER");
			VALID_TERMS.add("YR");
			VALID_TERMS.add(QUERY_TERM);
			VALID_TERMS.add(QUERY_SEMESTER);
			HonorRollEntry.setMinGradeA(Integer.parseInt(HONOR_ROLL_PROPERTIES.getProperty("A_MIN_GRADE")));
			HonorRollEntry.setMinGradeAB(Integer.parseInt(HONOR_ROLL_PROPERTIES.getProperty("AB_MIN_GRADE")));
			A_HR_WRITER = new CSVWriter(new FileWriter(SystemInfo.INSTANCE.LOGGING_PATH + getGradeLevel() + "_A_HonorRoll.csv", false));
			AB_HR_WRITER = new CSVWriter(new FileWriter(SystemInfo.INSTANCE.LOGGING_PATH + getGradeLevel() + "_AB_HonorRoll.csv", false));
			NOT_HR_WRITER = new CSVWriter(new FileWriter(SystemInfo.INSTANCE.LOGGING_PATH + getGradeLevel() + "_NotAB_HonorRoll.csv", false));
		} catch(IOException e) {
			e.printStackTrace(System.err);
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void close() {
		super.close();
		try {
			A_HR_WRITER.close();
			AB_HR_WRITER.close();
			NOT_HR_WRITER.close();
		} catch (IOException e) {
			LOGGER.severe(e.getMessage());
		}
	}

	private Student processClassRankRow(HtmlTableRow studentData, byte gradeLevel) {
		List<HtmlTableCell> cells = studentData.getCells();
		Student student = new Student();
		student.setStudentId(Long.parseLong(cells.get(1).getTextContent()));
		String[] names = cells.get(2).getTextContent().split(",");
		student.setLastName(names[0]);
		student.setFirstName(names[1]);
		student.setGradeLevel(gradeLevel);
		return student;
	}
	
	public List<Student> recordStudentsFromClassRankPage(HtmlPage classRankPage) {
		List<HtmlTableRow> rows = getClassRankRows(classRankPage);
		List<Student> students = new ArrayList<>(40);
		final byte gradeLevel = Byte.parseByte(getGradeLevel());
		for(int index = 1; index < rows.size(); index ++) {
			students.add(processClassRankRow(rows.get(index), gradeLevel));
		}
		return students;
	}
	
	public HonorRollEntry parseHistoricalGrades(HtmlPage hgPage, Student student) {
		HonorRollEntry hrEntry = new HonorRollEntry();
		DomElement element = (HtmlElement)hgPage.getElementById(getHgContentMainId());
		DomNodeList<HtmlElement> courseDataRows = element.getElementsByTagName(getHgRowTag());
		Map<String, Integer> headerIndexes = mapHeaderIndexes(courseDataRows.get(0));
		for(int index = courseDataRows.size() - 1; index > 0; index--) {
			HtmlElement tableRow = courseDataRows.get(index);
			DomNodeList<HtmlElement> tableCells = tableRow.getElementsByTagName(getHgCellTag());
			String yearTerm = tableCells.get(headerIndexes.get(HG_TH_YEAR_TERM_CONTENT)).getTextContent();
			if(yearTerm.startsWith(HONOR_ROLL_PROPERTIES.getProperty("YEAR")) && VALID_TERMS.contains(yearTerm.substring(yearTerm.length() - 2))) {
				String courseName = tableCells.get(headerIndexes.get(HG_TH_COURSE_CONTENT)).getTextContent();
				String grade = discoverGrade(QUERY_TERM, headerIndexes, tableCells);
				hrEntry.addCourse(courseName, grade);
			}
		}
		return hrEntry;
	}
	
	private String discoverGrade(String term, Map<String, Integer> headerIndexes, DomNodeList<HtmlElement> tableCells) {
		Integer headerIndex = null;
		while((headerIndex = headerIndexes.get(term)) == null) { // check for missing terms
			term = getParentTerm(term);
		}
		DomNodeList<HtmlElement> gradeAnchors = tableCells.get(headerIndex).getElementsByTagName(getHgGradeAnchorTag());
		String grade;
		switch(gradeAnchors.size()) {
			case 0: // look at other grades
				grade = !QUERY_FINAL.equals(term) ? discoverGrade(getParentTerm(term), headerIndexes, tableCells) : "";
				break;
			case 1:
				grade = parseGradeAnchorTextContent(term, gradeAnchors.get(0).getTextContent(), headerIndexes, tableCells);
				break;
			default: // more than 1
				grade = handleMultipleGrades(gradeAnchors);
		}
		return grade;
	}
	
	private String parseGradeAnchorTextContent(String term, String textContent, Map<String, Integer> headerIndexes, DomNodeList<HtmlElement> tableCells) {
		String grade = textContent;
		if(!MathUtil.isIntegerNumber(textContent) && !QUERY_FINAL.equals(term)) {
			switch(textContent) {
				case "":
				case "_":
					grade = discoverGrade(getParentTerm(term), headerIndexes, tableCells);
					break;
			}
		}
		return grade;
	}
	
	private String handleMultipleGrades(DomNodeList<HtmlElement> gradeAnchors) {
		List<Integer> grades = new ArrayList<>(2);
		boolean numericGrades = true;
		for(int index = gradeAnchors.size() - 1; index >= 0 && numericGrades; index--) {
			String grade = gradeAnchors.get(index).getTextContent();
			if(MathUtil.isIntegerNumber(grade))
				grades.add(Integer.parseInt(grade));
			else
				numericGrades = false;
		}
		String grade;
		if(numericGrades) {
			double sum = 0;
			for(Integer gradeNum : grades)
				sum += gradeNum;
			sum /= grades.size();
			grade = String.valueOf((int)Math.round(sum));
		} else {
			StringBuilder gradeEntries = new StringBuilder(10);
			for(int index = gradeAnchors.size() - 1; index >= 0; index--)
				gradeEntries.append(gradeAnchors.get(index).getTextContent()).append(" ");
			grade = gradeEntries.toString();
		}
		return grade;
	}
	
	private String getParentTerm(String term) {
		char firstChar = term.charAt(0);
		switch(firstChar) {
			case 'Q':
				return QUERY_SEMESTER;
			case 'S':
				return QUERY_YEAR;
			case 'Y':
				return QUERY_FINAL;
			default:
				throw new IllegalArgumentException("Unknown parent term for " + term);
		}
	}
	
	public void writeHonorRollEntry(Student student, HonorRollEntry hrEntry) {
		CSVWriter writer = hrEntry.isOnHonorRollA() ? A_HR_WRITER : (hrEntry.isOnHonorRollAB() ? AB_HR_WRITER : NOT_HR_WRITER);
		List<HonorRollEntryCourse> hrCourses = hrEntry.getCourses();
		final byte nameCols = 2;
		int arrayLength = nameCols + hrCourses.size();
		String[] row = new String[arrayLength];
		row[0] = student.getLastName();
		row[1] = student.getFirstName();
		for(int index = nameCols; index < row.length; index++)
			row[index] = hrCourses.get(index - nameCols).toString();
		writer.writeNext(row);
	}
	
	public static void main(String[] args) throws IOException {
		final long startTime = System.currentTimeMillis();
		LOGGER.log(Level.INFO, "Begin class rank scrape.");
		Student logStudent = null;
		int count = 1;
		try {
			HtmlPage curPage = INSTANCE.authenticatePowerSchoolAdmin();
			curPage = INSTANCE.navigateToClassRank(curPage);
			curPage = INSTANCE.searchClassRank(curPage);
			List<Student> students = INSTANCE.recordStudentsFromClassRankPage(curPage);
			int totalStudents = students.size();
			LOGGER.info(count + " students to process from class rank.");
			for(Student student : students) {
				try {
					out.println("Process student " + count + " of " + totalStudents);
					logStudent = student;
					curPage = INSTANCE.searchStudent(student);
					curPage = INSTANCE.requestHistoricalGrades(curPage);
					HonorRollEntry hrEntry = INSTANCE.parseHistoricalGrades(curPage, student);
					INSTANCE.writeHonorRollEntry(student, hrEntry);
					if(count % 10 == 0)
						Scraper.reportEstimatedRemainingMinutes(startTime, count, totalStudents);
					count++;
				} catch(Exception e) {
					LOGGER.warning("Exception on student: " + (logStudent != null ? logStudent.getFullName() : "null student"));
					LOGGER.warning(e.getMessage());
					e.printStackTrace();
				}
			}
			INSTANCE.signOut(curPage);
		} catch(Throwable t) {
			LOGGER.severe("Error on student " + (logStudent != null ? logStudent.getFullName() : "null student"));
			LOGGER.severe(t.getMessage());
			t.printStackTrace();
		} finally {
			LOGGER.info(count + " students processed before close.");
			INSTANCE.close();
		}
		LOGGER.log(Level.INFO, "Total time (minutes): " + (System.currentTimeMillis() - startTime) / 1000 / 60);
		LOGGER.log(Level.INFO, "End class rank scrape.");
	}
}
