package edu.jenks.scrape.gpa;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;
import com.gargoylesoftware.htmlunit.html.*;
import edu.jenks.scrape.Scraper;
import edu.jenks.scrape.data.gpa.*;

public class ClassRankScraper extends Scraper {
	
	private static final float GPA_DELTA = 0.0005F;
	private static final float EARNED_CREDIT_DELTA = 0.05F;
	private static final ClassRankScraper INSTANCE = new ClassRankScraper();
	
	private final GpaPersister PERSISTENCE_INTERFACE =  GpaPersister.getInstance(LOGGER);
	private final Properties CLASS_RANKING_REPORT_PROPERTIES;
	private final Properties STORED_GRADE_PROPERTIES;
	private final Properties HISTORICAL_GRADES_PROPERTIES;
	
	public ClassRankScraper() {
		CLASS_RANKING_REPORT_PROPERTIES = new Properties();
		STORED_GRADE_PROPERTIES = new Properties();
		HISTORICAL_GRADES_PROPERTIES = new Properties();
		final String classRankingReportPropertiesPath = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolPages\\ClassRankingReport.properties";
		final String storeGradePropertiesPath = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolPages\\StoredGrade.properties";
		final String historicalGradesPropertiesPath = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolPages\\HistoricalGrades.properties";
		try {
			CLASS_RANKING_REPORT_PROPERTIES.load(new FileInputStream(classRankingReportPropertiesPath));
			STORED_GRADE_PROPERTIES.load(new FileInputStream(storeGradePropertiesPath));
			HISTORICAL_GRADES_PROPERTIES.load(new FileInputStream(historicalGradesPropertiesPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		LOGGER.log(Level.INFO, "Begin class rank scrape.");
		try {
			HtmlPage curPage = INSTANCE.authenticatePowerSchoolAdmin();
			/*List<Student> topSeniors = INSTANCE.scrapeTopSeniorsAndPersist(curPage);
			INSTANCE.scrapeAndPersistHistoricalGrades(curPage, topSeniors);*/
			INSTANCE.calculateAndPersistGpaFromPersistedHistoricalGrades();
			List<Student> studentsWithGpaMismatch = INSTANCE.reportGpaVerifcationErrors();
			INSTANCE.reportCoursesWithGpaMismatch(curPage, studentsWithGpaMismatch);
			INSTANCE.signOut(curPage);
		} catch(Throwable t) {
			LOGGER.log(Level.SEVERE, t.getMessage());
			t.printStackTrace();
		} finally {
			INSTANCE.close();
		}
		LOGGER.log(Level.INFO, "End class rank scrape.");
	}
	
	private byte scrapeStoredGrades(Course hgCourse, Course calcCourse) throws IOException {
		byte gpaMismatches = 0;
		List<Grade> hgGrades = hgCourse.getGrades();
		List<Grade> calcGrades = calcCourse.getGrades();
		final int numGrades = hgGrades.size();
		for(int hgIndex = numGrades - 1; hgIndex >= 0; hgIndex--) {
			Grade hgGrade = hgGrades.get(hgIndex);
			Grade calcGrade = calcGrades.get(calcGrades.indexOf(hgGrade));
			HtmlPage storedGradesPage = hgGrade.getGradeAnchor().click();
			HtmlInput gpaPointsInput = (HtmlInput)storedGradesPage.getElementById(STORED_GRADE_PROPERTIES.getProperty("GPA_POINTS_INPUT_ID"));
			calcGrade.setGpaStoredGrade(Float.parseFloat(gpaPointsInput.getValueAttribute()));
			HtmlInput earnedCreditHoursInput = (HtmlInput)storedGradesPage.getElementById(STORED_GRADE_PROPERTIES.getProperty("EARNED_CREDIT_HOURS_INPUT_ID"));
			calcGrade.setEarnedCreditStoredGrade(Float.parseFloat(earnedCreditHoursInput.getValueAttribute()));
			float expectedEarnedCreditFromCourseNumber = calcExpectedEarnedCredit(calcCourse.getCourseNumber()) / numGrades;
			if(Math.abs(calcGrade.getGpaWeightedCalc() - calcGrade.getGpaStoredGrade()) > GPA_DELTA ||
					Math.abs(calcGrade.getEarnedCreditCalc() - calcGrade.getEarnedCreditStoredGrade()) > EARNED_CREDIT_DELTA ||
					expectedEarnedCreditFromCourseNumber != calcGrade.getEarnedCreditStoredGrade()) {
				logStudent(calcCourse.getStudent().getFullName(), buildGpaMismatchMessage(calcCourse, calcGrade, expectedEarnedCreditFromCourseNumber));
				gpaMismatches++;
			}
		}
		return gpaMismatches;
	}
	
	private float calcExpectedEarnedCredit(String courseNumber) {
		char earnedCreditCode = courseNumber.charAt(courseNumber.length() - 1);
		switch(earnedCreditCode) {
		case 'W': return 1;
		case 'H': return .5F;
		case 'D': return 2;
		default: return 0;
		}
	}
	
	private void reportCoursesWithGpaMismatch(HtmlPage curPage, List<Student> students) throws IOException {
		if(PERSISTENCE_INTERFACE.connect()) {
			LOGGER.info("Connected to DB");
			for(int sIndex = students.size() - 1; sIndex >= 0; sIndex--) {
				Student student = students.get(sIndex);
				List<Course> hgCourses = scrapeHistoricalGrades(curPage, student);
				List<Course> calcCourses = PERSISTENCE_INTERFACE.getCourses(student);
				byte gpaMismatches = 0;
				//LOGGER.info("Begin GPA comparison for " + student.getFullName());
				for(int hgIndex = hgCourses.size() - 1; hgIndex >= 0; hgIndex--) {
					Course hgCourse = hgCourses.get(hgIndex);
					if(hgCourse.getEarnedCredit() > 0) {
						Course calcCourse = calcCourses.get(calcCourses.indexOf(hgCourse));
						calcCourse.setGrades(PERSISTENCE_INTERFACE.getGrades(calcCourse));
						gpaMismatches += scrapeStoredGrades(hgCourse, calcCourse);
					}
				}
				logStudent(student.getFullName(), "Compared " + hgCourses.size() + " course GPAs; found " + gpaMismatches + " GPA mismatches.");
			}
		} else {
			handleDatabaseConnectionFailure();
		}
	}
	
	private String buildGpaMismatchMessage(Course course, Grade grade, float expectedEarnedCreditFromCourseNumber) {
		StringBuilder sb = new StringBuilder(200);
		sb.append("GPA mismatch for ").append(course.getStudent().getFullName()).append("; ");
		sb.append(course.getYearTerm()).append(" - ").append(course.getCourseName()).append("; ");
		sb.append("calculated GPA: ").append(grade.getGpaWeightedCalc()).append("; ");
		sb.append("stored grades GPA: ").append(grade.getGpaStoredGrade()).append("; ");
		sb.append("calculated earned credit: ").append(grade.getEarnedCreditCalc()).append("; ");
		sb.append("stored grades earned credit: ").append(grade.getEarnedCreditStoredGrade()).append("; ");
		sb.append("earned credit from course number: ").append(expectedEarnedCreditFromCourseNumber);
		return sb.toString();
	}
	
	private List<Student> reportGpaVerifcationErrors() throws IOException {
		List<Student> students = null;
		if(PERSISTENCE_INTERFACE.connect()) {
			LOGGER.info("Connected to DB");
			students = PERSISTENCE_INTERFACE.selectStudentsWithGpaMismatch();
			LOGGER.info("GPA mismatch for " + students.size() + " students...");
			for(int index = students.size() - 1; index >= 0; index--) {
				Student student = students.get(index);
				logStudent(student.getFullName(), "GPA mismatch found... rank: " + student.getRank() + "; PS gpa: " + student.getGpaPowerSchool() + "; HG gpa: " + student.getGpaHistoricalGrades());
			}
		} else {
			handleDatabaseConnectionFailure();
		}
		return students;
	}
	
	private void calculateAndPersistGpaFromPersistedHistoricalGrades() throws IOException {
		if(PERSISTENCE_INTERFACE.connect()) {
			LOGGER.info("Connected to DB");
			List<Student> students = calculateGpaFromHistoricalGrades();
			PERSISTENCE_INTERFACE.updateStudentGpa(students);
			LOGGER.info("GPA persisted from historical grades for " + students.size() + " students");
		} else {
			handleDatabaseConnectionFailure();
		}
	}
	
	private void scrapeAndPersistHistoricalGrades(HtmlPage curPage, List<Student> students) throws IOException {
		if(PERSISTENCE_INTERFACE.connect()) {
			LOGGER.info("Connected to DB");
			for(int index = students.size() - 1; index >= 0; index--) {
				List<Course> courses = INSTANCE.scrapeHistoricalGrades(curPage, students.get(index));
				PERSISTENCE_INTERFACE.addCourses(courses);
			}
			LOGGER.info("Historical grades persisted for " + students.size() + " students");
		} else {
			handleDatabaseConnectionFailure();
		}
	}
	
	private List<Student> scrapeTopSeniorsAndPersist(HtmlPage curPage) throws IOException {
		List<Student> students = scrapeTopSeniors(curPage);
		LOGGER.info("Top seniors scraped from class rank.");
		if(PERSISTENCE_INTERFACE.connect()) {
			LOGGER.info("Connected to DB");
			persistClassRankFromPowerSchool(students);
			LOGGER.info("Class rank data persisted for " + students.size() + " students");
		} else {
			handleDatabaseConnectionFailure();
		}
		return students;
	}
	
	private List<Student> calculateGpaFromHistoricalGrades() {
		List<Student> students = PERSISTENCE_INTERFACE.getStudents();
		for(int sIndex = students.size() - 1; sIndex >= 0; sIndex--) {
			Student student = students.get(sIndex);
			float totalGpaPoints = 0;
			float totalEarnedCredits = 0;
			List<Course> courses = PERSISTENCE_INTERFACE.getCourses(student);
			for(int cIndex = courses.size() - 1; cIndex >= 0; cIndex--) {
				Course course = courses.get(cIndex);
				float fullEarnedCredit = course.getEarnedCredit();
				if(fullEarnedCredit > 0) {
					totalEarnedCredits += fullEarnedCredit;
					List<Grade> grades = PERSISTENCE_INTERFACE.getGrades(course);
					int numberGrades = grades.size();
					/*if(numberGrades != 1)
						logStudent(student.getFullName(), numberGrades + " grades for course " + course.getCourseName());*/
					float earnedCredit = fullEarnedCredit / numberGrades;
					for(int gIndex = numberGrades - 1; gIndex >= 0; gIndex--) {
						Grade grade = grades.get(gIndex);
						float weightedGpa = weightGpaWithYear(grade.getGrade(), course.getCourseNumber(), course.getYearTerm());
						grade.setGpaWeightedCalc(weightedGpa);
						grade.setEarnedCreditCalc(earnedCredit);
						float gpaPoints = earnedCredit * weightedGpa;
						totalGpaPoints += gpaPoints;
						PERSISTENCE_INTERFACE.updateGradesGpaWeightedCalc(grade, student, course);
					}
				}
			}
			student.setGpaHistoricalGrades(totalGpaPoints / totalEarnedCredits);
		}
		return students;
	}
	
	private float weightGpaWithYear(float grade, String courseNumber, String yearTerm) {
		float weightedGpa = 0;
		int hyphenIndex = yearTerm.indexOf('-');
		byte lastYear = Byte.parseByte(yearTerm.substring(hyphenIndex + 1, hyphenIndex + 3));
		boolean newScale = lastYear >= 17;
		if(newScale)
			weightedGpa = .1F + .1F * (grade - 51);
		else
			weightedGpa = .125F + .125F * (grade - 62);
		if(weightedGpa > 0) {
			if(courseNumber.indexOf("HW") >= 0 || courseNumber.indexOf("HH") >= 0 || courseNumber.indexOf("HD") >= 0) // honors
				weightedGpa += .5F;
			else if(courseNumber.indexOf("AW") >= 0 || courseNumber.indexOf("EW") >= 0) // AP or dual-credit
				weightedGpa += 1;
		} else
			weightedGpa = 0;
		return weightedGpa;
	}
	
	private List<Course> scrapeHistoricalGrades(HtmlPage curPage, Student student) throws IOException {
		curPage = searchStudent(student.getStudentId()); // will be on students or throw exception
		curPage = requestHistoricalGrades(curPage);
		DomElement element = (HtmlElement)curPage.getElementById(HISTORICAL_GRADES_PROPERTIES.getProperty("CONTENT_MAIN_ID"));
		DomNodeList<HtmlElement> courseDataRows = element.getElementsByTagName(HISTORICAL_GRADES_PROPERTIES.getProperty("ROW_TAG"));
		List<Course> courses = new ArrayList<>(50);
		for(int index = courseDataRows.size() - 1; index > 0; index--) {
			Course course = processCourseData(courseDataRows.get(index), student);
			if(course != null)
				courses.add(course);
		}
		return courses;
	}
	
	private Course processCourseData(HtmlElement tableRow, Student student) {
		Course course = null;
		DomNodeList<HtmlElement> tableData = tableRow.getElementsByTagName(HISTORICAL_GRADES_PROPERTIES.getProperty("CELL_TAG"));
		float earnedCredit = Float.parseFloat(tableData.get(4).getTextContent());
		course = new Course(student);
		course.setYearTerm(tableData.get(0).getTextContent());
		course.setCourseNumber(tableData.get(2).getTextContent());
		course.setCourseName(tableData.get(3).getTextContent());
		course.setEarnedCredit(earnedCredit);
		DomNodeList<HtmlElement> gradeAnchors = tableData.get(5).getElementsByTagName(HISTORICAL_GRADES_PROPERTIES.getProperty("GRADE_ANCHOR_TAG"));
		for(int index = gradeAnchors.size() - 1; index >= 0; index--) {
			HtmlElement gradeAnchor = gradeAnchors.get(index);
			course.addGrade(gradeAnchor);
		}
		return course;
	}
	
	private HtmlPage requestHistoricalGrades(HtmlPage curPage) throws IOException {
		String curUrl = curPage.getUrl().toString();
		String historicalGradesUrl = curUrl.replaceFirst("home", "previousgrades");
		curPage = WEB_CLIENT.getPage(historicalGradesUrl);
		return curPage;
	}
	
	private HtmlPage searchStudent(long studentId) throws IOException {
		final String expectedPathComponent = "students";
		HtmlPage curPage = null;
		for(byte waitSeconds = DEFAULT_WAIT_SECONDS, multiplier = 2, attempts = 0; attempts < 3 && (curPage == null || curPage.getUrl().toString().indexOf(expectedPathComponent) < 0); waitSeconds *= multiplier, attempts++) {
			curPage = WEB_CLIENT.getPage("https://powerschool.gwd50.org/admin/home.html");
			System.out.println("Wait " + waitSeconds + " seconds for AngularJS on landing page: " + curPage.getUrl());
			WEB_CLIENT.waitForBackgroundJavaScriptStartingBefore(waitSeconds * 1000);
			System.out.println("End wait for AngularJS on landing page");
			HtmlTextInput searchInput = (HtmlTextInput)curPage.getElementById("studentSearchInput");
			searchInput.setValueAttribute("student_number=" + studentId);
			HtmlButton searchButton = (HtmlButton)curPage.getElementById("searchButton");
			curPage = searchButton.click();
		}
		if(curPage.getUrl().toString().indexOf(expectedPathComponent) < 0)
			throw new IOException("Student page not loaded");
		return curPage;
	}
	
	private List<Student> scrapeTopSeniors(HtmlPage curPage) throws IOException {
		curPage = clickAnchorByID(curPage, CLASS_RANKING_REPORT_PROPERTIES.getProperty("SYS_REPORTS_ANCHOR_ID"));
		curPage = clickAnchorByText(curPage, CLASS_RANKING_REPORT_PROPERTIES.getProperty("CLASS_RANKING_ANCHOR_TEXT"));
		curPage = fillClassRankTable(curPage, CLASS_RANKING_REPORT_PROPERTIES.getProperty("MIN_GPA_ATTRIBUTE"), CLASS_RANKING_REPORT_PROPERTIES.getProperty("MAX_GPA_ATTRIBUTE"));
		List<Student> students = processGpaData(curPage);
		return students;
	}
	
	private void persistClassRankFromPowerSchool(List<Student> students) {
		PERSISTENCE_INTERFACE.clearStudents();
		LOGGER.info("Student data cleared");
		PERSISTENCE_INTERFACE.addStudents(students);
		LOGGER.info("Students data added");
	}
	
	private Student processClassRankRow(HtmlTableRow studentData) {
		List<HtmlTableCell> cells = studentData.getCells();
		Student student = new Student();
		String rank = cells.get(0).getTextContent();
		student.setRank(Short.parseShort(rank.substring(0, rank.length() - 1)));
		student.setStudentId(Long.parseLong(cells.get(1).getTextContent()));
		String[] names = cells.get(2).getTextContent().split(",");
		student.setLastName(names[0]);
		student.setFirstName(names[1]);
		student.setGpaPowerSchool(Float.parseFloat(cells.get(3).getTextContent()));
		return student;
	}
	
	private List<Student> processGpaData(HtmlPage curPage) {
		HtmlTableBody tableBody = (HtmlTableBody)curPage.getElementsByTagName("tbody").get(0);
		List<HtmlTableRow> tableRows = tableBody.getRows();
		List<Student> students = new ArrayList<>(40);
		for(int index = 1; index < tableRows.size(); index ++)
			students.add(processClassRankRow(tableRows.get(index)));
		return students;
	}
	
	private HtmlPage fillClassRankTable(HtmlPage curPage, String minGPA, String maxGPA) throws IOException {
		String gradeLevelName = CLASS_RANKING_REPORT_PROPERTIES.getProperty("GRADE_LEVEL_NAME");
		HtmlSelect gradeSelect = curPage.getElementByName(gradeLevelName);
		gradeSelect.setSelectedAttribute(CLASS_RANKING_REPORT_PROPERTIES.getProperty("GRADE_LEVEL_ATTRIBUTE"), true);
		HtmlInput mingpaInput = curPage.getElementByName(CLASS_RANKING_REPORT_PROPERTIES.getProperty("MIN_GPA_NAME"));
		mingpaInput.setValueAttribute(minGPA);
		HtmlInput maxgpaInput = curPage.getElementByName(CLASS_RANKING_REPORT_PROPERTIES.getProperty("MAX_GPA_NAME"));
		maxgpaInput.setValueAttribute(maxGPA);
		HtmlButton submitBtn = (HtmlButton)curPage.getElementById(CLASS_RANKING_REPORT_PROPERTIES.getProperty("SUBMIT_BUTTON_ID"));
		return submitBtn.click();
	}
	
	protected void close() {
		super.close();
		PERSISTENCE_INTERFACE.disconnect();
		LOGGER.info("DB connections closed.");
	}
	
	/* search student list
	curPage = clickAnchorByID(curPage, "grade_12");
	WEB_CLIENT.waitForBackgroundJavaScriptStartingBefore(60 * 1000);
	List<HtmlAnchor> studentAnchors = curPage.getByXPath("//a[starts-with(@id, 'selection_lnk')]");
	System.out.println(studentAnchors.size() == 0 ? "No matching student anchors found" : studentAnchors.get(0));
	*/
}
