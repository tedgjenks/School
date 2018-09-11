package edu.jenks.scrape.gradebook;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.javascript.host.event.KeyboardEvent;
import edu.jenks.scrape.Scraper;
import edu.jenks.scrape.data.Assignment;
import edu.jenks.scrape.data.app.KaCsvParser;

public class GradebookScraper extends Scraper {
	
	private static final String KA_PROPERTIES_FILEPATH = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\KhanAcademy.properties";
	private static final String GRADEBOOK_PROPERTIES_FILEPATH = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolPages\\Gradebook.properties";
	private static final GradebookScraper INSTANCE = new GradebookScraper();
	
	private KaCsvParser kaReader;
	
	public GradebookScraper() {}

	public static void main(String[] args) {
		LOGGER.info("Begin gradebook scrape.");
		try {
			INSTANCE.initKaReader();
			HtmlPage curPage = INSTANCE.authenticatePowerSchoolTeacher();
			INSTANCE.waitForJavascript(60, "Gradebook Homepage");
			INSTANCE.importAssignments(curPage);
			INSTANCE.signOut(curPage);
		} catch(Throwable t) {
			LOGGER.severe(t.getMessage());
			t.printStackTrace();
		} finally {
			INSTANCE.close();
		}
		LOGGER.info("End gradebook scrape.");
	}
	
	private void importAssignment(HtmlPage curPage, Assignment assignment) throws IOException {
		
	}
	
	public void importAssignments(HtmlPage curPage) throws IOException {
		Properties gradebookProperties = new Properties();
		gradebookProperties.load(new FileInputStream(GRADEBOOK_PROPERTIES_FILEPATH));
		HtmlAnchor anchor = (HtmlAnchor)curPage.getElementById(gradebookProperties.getProperty("Algebra2GradebookAnchorId"));
		curPage = anchor.click();
		waitForJavascript(60, "Algebra 2 gradebook");
		String rowsPerPageInputId = gradebookProperties.getProperty("RowsPerPageInputId");
		System.out.println(rowsPerPageInputId);
		HtmlInput rowsPerPageInput = (HtmlInput)curPage.getElementById(rowsPerPageInputId);
		rowsPerPageInput.setValueAttribute(gradebookProperties.getProperty("RowsPerPageValue"));
		curPage = (HtmlPage)rowsPerPageInput.type(KeyboardEvent.DOM_VK_RETURN);
		Map<String, Assignment> assignmentRecords = kaReader.getAssignmentRecords();
		Iterator<String> assignmentNames = assignmentRecords.keySet().iterator();
		while(assignmentNames.hasNext()) {
			Assignment assignment = assignmentRecords.get(assignmentNames.next());
			importAssignment(curPage, assignment);
		}
	}
	
	public void initKaReader() throws IOException {
		Properties kaProps = new Properties();
		kaProps.load(new FileInputStream(KA_PROPERTIES_FILEPATH));
		//Reader reader = new FileReader(kaProps.getProperty("CSV_FILEPATH"));
		//int year = Integer.parseInt(kaProps.getProperty("DUE_YEAR"));
		//int month = Integer.parseInt(kaProps.getProperty("DUE_MONTH"));
		//int day = Integer.parseInt(kaProps.getProperty("DUE_DAY"));
		//LocalDate date = LocalDate.of(year, month, day);
		kaReader =  new KaCsvParser();
		kaReader.loadAssignments();
	}
	
	protected void close() {
		super.close();
		try {
			if(kaReader != null) {
				kaReader.close();
				LOGGER.info("CSVReader closed");
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
