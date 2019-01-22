package edu.jenks.scrape.gpa;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import com.gargoylesoftware.htmlunit.html.*;
import com.opencsv.CSVWriter;

import edu.jenks.scrap.util.SystemInfo;
import edu.jenks.scrape.data.gpa.HonorRollEntry;
import edu.jenks.scrape.data.gpa.HonorRollEntry.HonorRollEntryCourse;
import edu.jenks.scrape.data.gpa.Student;
import edu.jenks.util.MathUtil;

import static java.lang.System.out;

public class HonorRollScraper extends AbstractClassRankScraper {
	
	private static final HonorRollScraper INSTANCE = new HonorRollScraper();
	
	private final Properties HISTORICAL_GRADES_PROPERTIES;
	private final String QUERY_TERM, QUERY_SEMESTER, QUERY_YEAR = "Y1", QUERY_FINAL = "F1";
	private final int TABLE_CELL_INDEX_COURSE_NAME, TABLE_CELL_INDEX_TERM, TABLE_CELL_INDEX_SEMESTER, TABLE_CELL_INDEX_YEAR, TABLE_CELL_INDEX_FINAL;
	private final Properties HONOR_ROLL_PROPERTIES;
	private final Set<String> VALID_TERMS = new HashSet<>(5);
	private final Map<String, Integer> TABLE_CELL_INDEX_TERMS_MAP = new HashMap<>(5);
	private final CSVWriter HR_WRITER, NOT_HR_WRITER;
	
	public HonorRollScraper() {
		final String historicalGradesPropertiesPath = SystemInfo.INSTANCE.RESOURCES_PATH + "PowerSchoolPages/HistoricalGrades.properties";
		HISTORICAL_GRADES_PROPERTIES = new Properties();
		final String honorRollPropertiesPath = SystemInfo.INSTANCE.RESOURCES_PATH + "HonorRoll.properties";
		HONOR_ROLL_PROPERTIES = new Properties();
		try {
			HISTORICAL_GRADES_PROPERTIES.load(new FileInputStream(historicalGradesPropertiesPath));
			HONOR_ROLL_PROPERTIES.load(new FileInputStream(honorRollPropertiesPath));
			TABLE_CELL_INDEX_COURSE_NAME = Integer.parseInt(HISTORICAL_GRADES_PROPERTIES.getProperty("COURSE_NAME_INDEX"));
			QUERY_TERM = HONOR_ROLL_PROPERTIES.getProperty("TERM");
			final String indexSuffix = "_INDEX";
			TABLE_CELL_INDEX_TERM = Integer.parseInt(HISTORICAL_GRADES_PROPERTIES.getProperty(QUERY_TERM + indexSuffix));
			TABLE_CELL_INDEX_TERMS_MAP.put(QUERY_TERM, TABLE_CELL_INDEX_TERM);
			QUERY_SEMESTER = HONOR_ROLL_PROPERTIES.getProperty("SEMESTER");
			TABLE_CELL_INDEX_SEMESTER = Integer.parseInt(HISTORICAL_GRADES_PROPERTIES.getProperty(QUERY_SEMESTER + indexSuffix));
			TABLE_CELL_INDEX_TERMS_MAP.put(QUERY_SEMESTER, TABLE_CELL_INDEX_SEMESTER);
			TABLE_CELL_INDEX_YEAR = Integer.parseInt(HISTORICAL_GRADES_PROPERTIES.getProperty(QUERY_YEAR + indexSuffix));
			TABLE_CELL_INDEX_TERMS_MAP.put(QUERY_YEAR, TABLE_CELL_INDEX_YEAR);
			TABLE_CELL_INDEX_FINAL = Integer.parseInt(HISTORICAL_GRADES_PROPERTIES.getProperty(QUERY_FINAL + indexSuffix));
			TABLE_CELL_INDEX_TERMS_MAP.put(QUERY_FINAL, TABLE_CELL_INDEX_FINAL);
			VALID_TERMS.add("YR");
			VALID_TERMS.add(QUERY_TERM);
			VALID_TERMS.add(QUERY_SEMESTER);
			HonorRollEntry.setMinGrade(Integer.parseInt(HONOR_ROLL_PROPERTIES.getProperty("AB_MIN_GRADE")));
			HR_WRITER = new CSVWriter(new FileWriter(SystemInfo.INSTANCE.LOGGING_PATH + getGradeLevel() + "_AB_HonorRoll.csv", false));
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
			HR_WRITER.close();
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
		for(int index = courseDataRows.size() - 1; index > 0; index--) {
			HtmlElement tableRow = courseDataRows.get(index);
			DomNodeList<HtmlElement> tableCells = tableRow.getElementsByTagName(getHgCellTag());
			String yearTerm = tableCells.get(0).getTextContent();
			if(yearTerm.startsWith(HONOR_ROLL_PROPERTIES.getProperty("YEAR")) && VALID_TERMS.contains(yearTerm.substring(yearTerm.length() - 2))) {
				String courseName = tableCells.get(TABLE_CELL_INDEX_COURSE_NAME).getTextContent();
				String grade = discoverGrade(QUERY_TERM, tableCells);
				hrEntry.addCourse(courseName, grade);
			}
		}
		return hrEntry;
	}
	
	private String discoverGrade(String term, DomNodeList<HtmlElement> tableCells) {
		DomNodeList<HtmlElement> gradeAnchors = tableCells.get(TABLE_CELL_INDEX_TERMS_MAP.get(term)).getElementsByTagName(getHgGradeAnchorTag());
		String grade;
		switch(gradeAnchors.size()) {
			case 0: // look at other grades
				if(!QUERY_FINAL.equals(term))
					grade = discoverGrade(getParentTerm(term), tableCells);
				else
					grade = "";
				break;
			case 1:
				grade = parseGradeAnchorTextContent(term, gradeAnchors.get(0).getTextContent(), tableCells);
				break;
			default: // more than 1
				grade = handleMultipleGrades(gradeAnchors);
		}
		return grade;
	}
	
	private String parseGradeAnchorTextContent(String term, String textContent, DomNodeList<HtmlElement> tableCells) {
		String grade = textContent;
		if(!MathUtil.isIntegerNumber(textContent) && !QUERY_FINAL.equals(term)) {
			switch(textContent) {
				case "_":
					grade = discoverGrade(getParentTerm(term), tableCells);
					break;
				default:
					LOGGER.warning("Unrecognized grade code: " + grade);
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
		CSVWriter writer = hrEntry.isOnHonorRoll() ? HR_WRITER : NOT_HR_WRITER;
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
		try {
			HtmlPage curPage = INSTANCE.authenticatePowerSchoolAdmin();
			curPage = INSTANCE.navigateToClassRank(curPage);
			curPage = INSTANCE.searchClassRank(curPage);
			List<Student> students = INSTANCE.recordStudentsFromClassRankPage(curPage);
			int count = 1, totalStudents = students.size();
			for(Student student : students) {
				try {
					out.println("Process student " + count + " of " + totalStudents);
					logStudent = student;
					curPage = INSTANCE.searchStudent(student);
					curPage = INSTANCE.requestHistoricalGrades(curPage);
					HonorRollEntry hrEntry = INSTANCE.parseHistoricalGrades(curPage, student);
					INSTANCE.writeHonorRollEntry(student, hrEntry);
					count++;
				} catch(Exception e) {
					//TODO Young, William Edward Peter
					LOGGER.warning("Exception on student" + (logStudent != null ? logStudent.getFullName() : "null student"));
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
			INSTANCE.close();
		}
		LOGGER.log(Level.INFO, "Total time (minutes): " + (System.currentTimeMillis() - startTime) / 1000 / 60);
		LOGGER.log(Level.INFO, "End class rank scrape.");
	}
}
