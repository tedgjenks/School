package edu.jenks.scrape.course;

import java.io.IOException;
import java.util.List;
import com.gargoylesoftware.htmlunit.html.*;
import edu.jenks.scrape.Scraper;

public class SectionScraper extends Scraper {
	
	private static final SectionScraper INSTANCE = new SectionScraper();
	private static final byte TEACHER_SECTION_TABLE_COL_INDEX_TERM = 1;
	private static final byte TEACHER_SECTION_TABLE_COL_INDEX_SECTION_NUMBER = 4;

	public SectionScraper() {}

	public static void main(String[] args) throws IOException {
		LOGGER.info("Begin sections scrape.");
		try {
			HtmlPage curPage = INSTANCE.authenticatePowerSchoolAdmin();
			LOGGER.info("Authenticated");
			INSTANCE.addCoTeacherToAllSections(curPage, "Pfancook, David R", "St. Jean, Paul Michael", "S2");
			INSTANCE.signOut(curPage);
		} catch(Throwable t) {
			LOGGER.severe(t.getMessage());
			t.printStackTrace();
		} finally {
			INSTANCE.close();
		}
		LOGGER.info("End sections scrape.");
	}
	
	public void addCoTeacherToAllSections(HtmlPage curPage, String leadTeacher, String coTeacher) throws IOException {
		addCoTeacherToAllSections(curPage, leadTeacher, coTeacher, null);
	}
	
	private void verifyTeacherSectionTableColumns(HtmlTableRow header) throws IOException {
		final String message = "Teacher Section Table column verification failure";
		assertTrue("Term".equals(header.getCell(TEACHER_SECTION_TABLE_COL_INDEX_TERM).getTextContent()), message);
		assertTrue("Sec #".equals(header.getCell(TEACHER_SECTION_TABLE_COL_INDEX_SECTION_NUMBER).getTextContent()), message);
		LOGGER.info("Teacher section table columns verified");
	}
	
	private boolean addCoTeacherToSection(HtmlPage sectionPage, String coTeacher) throws IOException {
		HtmlSelect selectStaff = null;
		for(byte waitSeconds = DEFAULT_WAIT_SECONDS, multiplier = 2, attempts = 0; attempts < DEFAULT_JS_ATTEMPTS && selectStaff == null; waitSeconds *= multiplier, attempts++) {
			System.out.println("Wait " + waitSeconds + " seconds for AngularJS on add coteacher: " + sectionPage.getUrl());
			WEB_CLIENT.waitForBackgroundJavaScriptStartingBefore(waitSeconds * 1000);
			System.out.println("End wait for AngularJS on add coteacher");
			HtmlButton addCoTeacherButton = (HtmlButton)sectionPage.getElementById("addStaffRowButton");	
			sectionPage = addCoTeacherButton.click();
			selectStaff = (HtmlSelect)sectionPage.getElementById("staffGridController_1_staff");
			if(selectStaff == null && attempts + 1 < DEFAULT_JS_ATTEMPTS) {
				sectionPage = (HtmlPage)sectionPage.refresh();
			}
		}
		selectStaff.setSelectedAttribute(selectStaff.getOptionByText(coTeacher), true);
		HtmlSelect selectRole = (HtmlSelect)sectionPage.getElementById("staffGridController_1_role");
		selectRole.setSelectedAttribute(selectRole.getOptionByText("Co-teacher"), true);
		HtmlInput inputAllocation = (HtmlInput)sectionPage.getElementById("staffGridController_1_allocation");
		inputAllocation.setValueAttribute("100");
		/*HtmlInput inputStartDate = (HtmlInput)sectionPage.getElementByName("startDate");
		LOGGER.fine("Start date: " + inputStartDate);
		HtmlInput inputEndDate = (HtmlInput)sectionPage.getElementByName("endDate");
		LOGGER.fine("End date: " + inputEndDate);*/
		HtmlButton submitButton = (HtmlButton)sectionPage.getElementById("btnSubmit");
		sectionPage = submitButton.click();
		DomElement confirmElement = sectionPage.getElementById("feedback-confirm");
		return confirmElement != null;
	}
	
	public void addCoTeacherToAllSections(HtmlPage curPage, String leadTeacher, String coTeacher, String term) throws IOException {
		curPage = WEB_CLIENT.getPage("https://powerschool.gwd50.org/admin/teacherschedules/menu.html");
		curPage = INSTANCE.clickAnchorByText(curPage, leadTeacher);
		System.out.println(curPage.getUrl());
		HtmlTable table = (HtmlTable)curPage.getElementById("teacherSectionTable");
		List<HtmlTableRow> rows = table.getRows();
		verifyTeacherSectionTableColumns(rows.get(0));
		short sectionsUpdated = 0;
		short termMatches = 0;
		for(int rowIndex = rows.size() - 2; rowIndex > 0; rowIndex--) {
			HtmlTableRow tableRow = rows.get(rowIndex);
			HtmlTableCell tableCell = tableRow.getCell(TEACHER_SECTION_TABLE_COL_INDEX_TERM);
			String termContent = tableCell.getTextContent();
			if(term == null || term.equals(termContent)) {
				termMatches++;
				HtmlTableCell sectionCell = tableRow.getCell(TEACHER_SECTION_TABLE_COL_INDEX_SECTION_NUMBER);
				HtmlAnchor anchor = (HtmlAnchor)sectionCell.getFirstElementChild();
				if(anchor.getNextElementSibling() == null && addCoTeacherToSection(anchor.click(), coTeacher))
					sectionsUpdated++;
			}
		}
		LOGGER.info("Terms matching " + term + ": " + termMatches + " out of " + rows.size() + " total rows.");
		LOGGER.info(sectionsUpdated + " sections updated");
	}

}
