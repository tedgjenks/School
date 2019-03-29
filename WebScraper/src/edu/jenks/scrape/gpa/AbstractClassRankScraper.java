package edu.jenks.scrape.gpa;

//import static java.lang.System.out;
import java.io.*;
import java.util.*;
import com.gargoylesoftware.htmlunit.html.*;

import edu.jenks.scrape.Scraper;
import edu.jenks.scrape.data.gpa.Student;
import edu.jenks.scrape.util.SystemInfo;

public class AbstractClassRankScraper extends Scraper {
	public final Properties CLASS_RANKING_REPORT_PROPERTIES;
	public final Properties HISTORICAL_GRADES_PROPERTIES;
	public final Properties HOME_PROPERTIES;
	public final String HG_TH_YEAR_TERM_CONTENT = "Year/Term", HG_TH_GRADE_LEVEL_CONTENT = "Grd Lvl",
			HG_TH_COURSE_CONTENT = "Course", HG_TH_EARNED_CREDIT_CONTENT = "Earned Credit";
		
	public AbstractClassRankScraper() {
		CLASS_RANKING_REPORT_PROPERTIES = new Properties();
		HISTORICAL_GRADES_PROPERTIES = new Properties();
		HOME_PROPERTIES = new Properties();
		final String classRankingReportPropertiesPath = SystemInfo.INSTANCE.RESOURCES_PATH + "PowerSchoolPages/ClassRankingReport.properties";
		final String historicalGradesPropertiesPath = SystemInfo.INSTANCE.RESOURCES_PATH + "PowerSchoolPages/HistoricalGrades.properties";
		final String homePropertiesPath = SystemInfo.INSTANCE.RESOURCES_PATH + "PowerSchoolPages/Home.properties";
		try {
			CLASS_RANKING_REPORT_PROPERTIES.load(new FileInputStream(classRankingReportPropertiesPath));
			HISTORICAL_GRADES_PROPERTIES.load(new FileInputStream(historicalGradesPropertiesPath));
			HOME_PROPERTIES.load(new FileInputStream(homePropertiesPath));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String getHomePageUrl() {
		return HOME_PROPERTIES.getProperty("URL");
	}
	
	public String getHgContentMainId() {
		return HISTORICAL_GRADES_PROPERTIES.getProperty("CONTENT_MAIN_ID");
	}
	
	public String getHgRowTag() {
		return HISTORICAL_GRADES_PROPERTIES.getProperty("ROW_TAG");
	}
	
	public String getHgCellTag() {
		return HISTORICAL_GRADES_PROPERTIES.getProperty("CELL_TAG");
	}
	
	public String getHgGradeAnchorTag() {
		return HISTORICAL_GRADES_PROPERTIES.getProperty("GRADE_ANCHOR_TAG");
	}
	
	public Map<String, Integer> mapHeaderIndexes(HtmlElement headerRow) {
		Map<String, Integer> map = new HashMap<>(20);
		DomNodeList<HtmlElement> headerCells = headerRow.getElementsByTagName("th");
		for(int index = headerCells.size() - 1; index >= 0; index--)
			map.put(headerCells.get(index).getTextContent(), index);
		return map;
	}
	
	public HtmlPage searchStudent(Student student) throws IOException {
		final String expectedPathComponent = "students";
		HtmlPage curPage = null;
		for(byte waitSeconds = DEFAULT_WAIT_SECONDS, multiplier = 2, attempts = 0; attempts < 4 && (curPage == null || curPage.getUrl().toString().indexOf(expectedPathComponent) < 0); waitSeconds *= multiplier, attempts++) {
			curPage = WEB_CLIENT.getPage(getHomePageUrl());
			waitForJavascript(waitSeconds, "search by student ID");
			HtmlTextInput searchInput = (HtmlTextInput)curPage.getElementById("studentSearchInput");
			searchInput.setValueAttribute("student_number=" + student.getStudentId());
			HtmlButton searchButton = (HtmlButton)curPage.getElementById("searchButton");
			curPage = searchButton.click();
		}
		if(curPage.getUrl().toString().indexOf(expectedPathComponent) < 0) {
			logStudent(student.getFullName(), "Student page not loaded from searchStudent");
			curPage = null;
		}
		return curPage;
	}
	
	public String getGradeLevel() {
		return CLASS_RANKING_REPORT_PROPERTIES.getProperty("GRADE_LEVEL_ATTRIBUTE");
	}
	
	public HtmlPage navigateToClassRank(HtmlPage homePage) throws IOException {
		HtmlPage nextPage = clickAnchorByID(homePage, CLASS_RANKING_REPORT_PROPERTIES.getProperty("SYS_REPORTS_ANCHOR_ID"));
		nextPage = clickAnchorByText(nextPage, CLASS_RANKING_REPORT_PROPERTIES.getProperty("CLASS_RANKING_ANCHOR_TEXT"));
		return nextPage;
	}
	
	public HtmlPage searchClassRank(HtmlPage classRankPage) throws IOException {
		String gradeLevel = getGradeLevel();
		String gradeLevelName = CLASS_RANKING_REPORT_PROPERTIES.getProperty("GRADE_LEVEL_NAME");
		HtmlSelect gradeSelect = classRankPage.getElementByName(gradeLevelName);
		gradeSelect.setSelectedAttribute(gradeLevel, true);
		HtmlInput mingpaInput = classRankPage.getElementByName(CLASS_RANKING_REPORT_PROPERTIES.getProperty("MIN_GPA_NAME"));
		mingpaInput.setValueAttribute(CLASS_RANKING_REPORT_PROPERTIES.getProperty("MIN_GPA_ATTRIBUTE"));
		HtmlInput maxgpaInput = classRankPage.getElementByName(CLASS_RANKING_REPORT_PROPERTIES.getProperty("MAX_GPA_NAME"));
		maxgpaInput.setValueAttribute(CLASS_RANKING_REPORT_PROPERTIES.getProperty("MAX_GPA_ATTRIBUTE"));
		HtmlButton submitBtn = (HtmlButton)classRankPage.getElementById(CLASS_RANKING_REPORT_PROPERTIES.getProperty("SUBMIT_BUTTON_ID"));
		return submitBtn.click();
	}
	
	public List<HtmlTableRow> getClassRankRows(HtmlPage classRankPage) {
		HtmlTableBody tableBody = (HtmlTableBody)classRankPage.getElementsByTagName("tbody").get(0);
		return tableBody.getRows();
	}
	
	public HtmlPage requestHistoricalGrades(HtmlPage curPage) throws IOException {
		String curUrl = curPage.getUrl().toString();
		String historicalGradesUrl = curUrl.replaceFirst("home", "previousgrades");
		curPage = WEB_CLIENT.getPage(historicalGradesUrl);
		return curPage;
	}
}
