package edu.jenks.scrape.gpa;

import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import com.gargoylesoftware.htmlunit.html.*;
import edu.jenks.scrape.Scraper;
import static java.lang.System.out;

public class HonorRollScraper extends Scraper {
	
	private static final HonorRollScraper INSTANCE = new HonorRollScraper();
	
	private final Properties HISTORICAL_GRADES_PROPERTIES;
	private final Properties HOME_PROPERTIES;
	private final Properties HONOR_ROLL_PROPERTIES;
	private final String HOME_URL;
	
	public HonorRollScraper() {
		final String historicalGradesPropertiesPath = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolPages\\HistoricalGrades.properties";
		HISTORICAL_GRADES_PROPERTIES = new Properties();
		final String homePropertiesPath = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolPages\\Home.properties";
		HOME_PROPERTIES = new Properties();
		final String honorRollPropertiesPath = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\HonorRoll.properties";
		HONOR_ROLL_PROPERTIES = new Properties();
		try {
			HISTORICAL_GRADES_PROPERTIES.load(new FileInputStream(historicalGradesPropertiesPath));
			HOME_PROPERTIES.load(new FileInputStream(homePropertiesPath));
			HONOR_ROLL_PROPERTIES.load(new FileInputStream(honorRollPropertiesPath));
		} catch(IOException e) {
			e.printStackTrace(System.err);
		}
		HOME_URL = HOME_PROPERTIES.getProperty("URL");
	}
	
	private void processStudentRows(DomNodeList<HtmlElement> tableRows) {
		for(HtmlElement tableRow : tableRows) {
			HtmlElement tableData = tableRow.getElementsByTagName("td").get(0);
			out.println(tableData.asText());
		}
	}
	
	private void processGrades() throws IOException {
		String[] gradeLevels = HONOR_ROLL_PROPERTIES.getProperty("GRADE_LEVELS").split(",");
		for(String gradeLevel : gradeLevels) {
			HtmlPage curPage = WEB_CLIENT.getPage(HOME_URL);
			out.println("Process grade " + gradeLevel);
			String anchorID = HOME_PROPERTIES.getProperty(gradeLevel + "_GRADE_STUDENTS_ID");
			curPage = curPage.getElementById(anchorID).click();
			int waitSeconds = 5;
			WEB_CLIENT.waitForBackgroundJavaScriptStartingBefore(waitSeconds * 1000);
			DomElement divElement = curPage.getElementById(HOME_PROPERTIES.getProperty("DIV_STUDENT_SELECT_ID"));
			HtmlElement tbody = divElement.getElementsByTagName("tbody").get(0);
			processStudentRows(tbody.getElementsByTagName("tr"));
		}
	}

	public static void main(String[] args) {
		final long startTime = System.currentTimeMillis();
		LOGGER.log(Level.INFO, "Begin honor roll scrape.");
		try {
			HtmlPage curPage = INSTANCE.authenticatePowerSchoolAdmin();
			if(curPage.getUrl().toString().indexOf("home.html") < 0)
				System.out.println("PROBABLE LOGIN FAILURE!");
			INSTANCE.processGrades();
			INSTANCE.signOut(curPage);
		} catch(Throwable t) {
			LOGGER.log(Level.SEVERE, t.getMessage());
			t.printStackTrace();
		} finally {
			INSTANCE.close();
		}
		LOGGER.log(Level.INFO, "Total time (minutes): " + (System.currentTimeMillis() - startTime) / 1000 / 60);
		LOGGER.log(Level.INFO, "End honor roll scrape.");
	}

}
