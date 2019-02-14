package edu.jenks.scrape.course;

import java.io.IOException;
import java.util.List;
import com.gargoylesoftware.htmlunit.html.*;
import edu.jenks.scrape.Scraper;

public class SectionScraper extends Scraper {
	
	private static final String TASK_ADD_COTEACHER = "TaskAddCoteacher";
	private static final String TASK_UPDATE_LEAD_TEACHER = "TaskUpdateLeadTeacher";
	private static final String TASK_UPDATE_ROOM = "TaskUpdateRoom";
	
	/*** fields to set before running: ***/
	private static final String TASK_UPDATE_ROOM_TO_UPDATE = "307"; // if not empty, only update this room
	private static final String TASK = TASK_UPDATE_ROOM;
	private static final String CURRENT_LEAD_TEACHER = "Whisenant, Billy J";
	private static final String NEW_DATA = "303";
	
	private static final SectionScraper INSTANCE = new SectionScraper();
	private static final byte TEACHER_SECTION_TABLE_COL_INDEX_TERM = 1;
	private static final byte TEACHER_SECTION_TABLE_COL_INDEX_SECTION_NUMBER = 4;

	public SectionScraper() {}

	public static void main(String[] args) throws IOException {
		LOGGER.info("Begin sections scrape.");
		try {
			HtmlPage curPage = INSTANCE.authenticatePowerSchoolAdmin();
			LOGGER.info("Authenticated");
			INSTANCE.selectTerm(curPage);
			INSTANCE.updateSections(curPage);
			INSTANCE.signOut(curPage);
		} catch(Throwable t) {
			LOGGER.severe(t.getMessage());
			t.printStackTrace();
		} finally {
			INSTANCE.close();
		}
		LOGGER.info("End sections scrape.");
	}
	
	private boolean updateRoom(HtmlPage sectionPage) throws IOException {
		final String room = NEW_DATA;
		DomNodeList<DomElement> tables = sectionPage.getElementsByTagName("table");
		HtmlTable contentTable = null;
		for(int index = tables.size() - 1; index >= 0 && contentTable == null; index--) {
			HtmlTable table = (HtmlTable)tables.get(index);
			if("linkDescList".equals(table.getAttribute("class")))
				contentTable = table;
		}
		HtmlTableBody tableBody = contentTable.getBodies().get(0);
		List<HtmlTableRow> rows = tableBody.getRows();
		//LOGGER.fine(rows.size() + " rows found.");
		boolean roomUpdated = false;
		for(int index = rows.size() - 1; index >= 0 && !roomUpdated; index--) {
			List<HtmlTableCell> cells = rows.get(index).getCells();
			if(cells != null && cells.size() > 1) {
				String cellText = cells.get(0).getTextContent();
				//LOGGER.fine("Cell value: " + cellText);
				if("Room".equals(cellText)) {
					HtmlInput input = (HtmlInput)cells.get(1).getChildElements().iterator().next();
					String currentRoom = input.getValueAttribute();
					LOGGER.info("Room input field found with value " + currentRoom);
					if(!room.equals(currentRoom) && (TASK_UPDATE_ROOM_TO_UPDATE.length() == 0 || TASK_UPDATE_ROOM_TO_UPDATE.equals(currentRoom))) {
						input.setValueAttribute(room);
						LOGGER.info("Room updated to  " + room);
						roomUpdated = true;
					} else
						index = -1;
				}
			}
		}
		return roomUpdated ? submitPage(sectionPage) : false;
	}
	
	private boolean submitPage(HtmlPage sectionPage) throws IOException {
		HtmlButton submitButton = (HtmlButton)sectionPage.getElementById("btnSubmit");
		sectionPage = submitButton.click();
		DomElement confirmElement = sectionPage.getElementById("feedback-confirm");
		return confirmElement != null;
	}
	
	public void updateSections(HtmlPage curPage) throws IOException {
		updateSections(curPage, null);
	}
	
	private void verifyTeacherSectionTableColumns(HtmlTableRow header) throws IOException {
		final String message = "Teacher Section Table column verification failure";
		assertTrue("Term".equals(header.getCell(TEACHER_SECTION_TABLE_COL_INDEX_TERM).getTextContent()), message);
		assertTrue("Sec #".equals(header.getCell(TEACHER_SECTION_TABLE_COL_INDEX_SECTION_NUMBER).getTextContent()), message);
		LOGGER.info("Teacher section table columns verified");
	}
	
	private boolean updateLeadTeacher(HtmlPage sectionPage) throws IOException {
		HtmlTableRow sectionLeadRow = (HtmlTableRow)sectionPage.getElementById("ownerGridController_1");
		//sectionLeadRow.setAttribute("aria-selected", "true");
		//sectionLeadRow.setAttribute("editable", "1");
		sectionPage = sectionLeadRow.click();
		HtmlSelect sectionLeadSelect = null;
		for(byte waitSeconds = DEFAULT_WAIT_SECONDS, multiplier = 2, attempts = 0; attempts < DEFAULT_JS_ATTEMPTS && sectionLeadSelect == null; waitSeconds *= multiplier, attempts++) {
			System.out.println("Wait " + waitSeconds + " seconds for AngularJS on updateLeadTeacher: " + sectionPage.getUrl());
			WEB_CLIENT.waitForBackgroundJavaScriptStartingBefore(waitSeconds * 1000);
			System.out.println("End wait for AngularJS on updateLeadTeacher");
			sectionLeadSelect = (HtmlSelect)sectionPage.getElementById("ownerGridController_1_staff");
		}
		sectionLeadSelect.setSelectedAttribute(sectionLeadSelect.getOptionByText(NEW_DATA), true);
		return submitPage(sectionPage);
	}
	
	private boolean addCoTeacherToSection(HtmlPage sectionPage) throws IOException {
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
		selectStaff.setSelectedAttribute(selectStaff.getOptionByText(NEW_DATA), true);
		HtmlSelect selectRole = (HtmlSelect)sectionPage.getElementById("staffGridController_1_role");
		selectRole.setSelectedAttribute(selectRole.getOptionByText("Co-teacher"), true);
		HtmlInput inputAllocation = (HtmlInput)sectionPage.getElementById("staffGridController_1_allocation");
		inputAllocation.setValueAttribute("100");
		/*HtmlInput inputStartDate = (HtmlInput)sectionPage.getElementByName("startDate");
		LOGGER.fine("Start date: " + inputStartDate);
		HtmlInput inputEndDate = (HtmlInput)sectionPage.getElementByName("endDate");
		LOGGER.fine("End date: " + inputEndDate);*/
		return submitPage(sectionPage);
	}
	
	public void updateSections(HtmlPage curPage, String term) throws IOException {
		curPage = WEB_CLIENT.getPage("https://powerschool.gwd50.org/admin/teacherschedules/menu.html");
		curPage = INSTANCE.clickAnchorByText(curPage, CURRENT_LEAD_TEACHER);
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
				//TODO verify lead teacher before processing section
				LOGGER.info("Process section " + anchor.getTextContent());
				if(anchor.getNextElementSibling() == null) { //TODO fix for coteach sections
					boolean taskSuccess = false;
					switch(TASK) {
					case TASK_ADD_COTEACHER:
						taskSuccess = addCoTeacherToSection(anchor.click());
						break;
					case TASK_UPDATE_LEAD_TEACHER:
						taskSuccess = updateLeadTeacher(anchor.click());
						break;
					case TASK_UPDATE_ROOM:
						taskSuccess = updateRoom(anchor.click());
						break;
					default:
						LOGGER.severe("Task " + TASK + " not supported!");
					}
					if(taskSuccess)
						sectionsUpdated++;
				}
			}
		}
		LOGGER.info("Terms matching " + term + ": " + termMatches + " out of " + rows.size() + " total rows.");
		LOGGER.info(sectionsUpdated + " sections updated");
	}

}
