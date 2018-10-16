/**
 * 
 */
package edu.jenks.scrape.course;

import java.util.*;
import java.io.*;
import com.gargoylesoftware.htmlunit.html.*;
import com.opencsv.CSVWriter;
import edu.jenks.scrape.Scraper;

/**
 * @author Jenks
 *
 */
public class CourseEnrollmentScraper extends Scraper {

	private static final CourseEnrollmentScraper INSTANCE = new CourseEnrollmentScraper();
	private static final String END_OF_COURSE = "\n" + "(";
	private static final String[] ROOMS_TO_EXCLUDE = {"GHS", "EHS", "PTC", "VS", "FRTC", "LAN", "OC", "OFF Campus", "LANDER"};
	private static final String[] COURSES_TO_EXCLUDE = {"GSSM", "Alignment"};
	private static final String SCHOOL = "Greenwood High School";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info("Begin course enrollment scrape.");
		try {
			if(INSTANCE.CSV_WRITER != null) {
				HtmlPage curPage = INSTANCE.authenticatePowerSchoolAdmin();
				LOGGER.info("Authenticated");
				//INSTANCE.selectTerm(curPage);
				if(SCHOOL != null)
					INSTANCE.selectSchool(curPage, SCHOOL);
				curPage = INSTANCE.clickAnchorByID(curPage, "navMasterSched");
				LOGGER.info("On master schedule.");
				INSTANCE.parseMasterSchedule(curPage);
				INSTANCE.signOut(curPage);
			} else
				LOGGER.severe("Writer not created");
		} catch(Throwable t) {
			LOGGER.severe(t.getMessage());
			t.printStackTrace();
		} finally {
			INSTANCE.close();
		}
		LOGGER.info("End course enrollment scrape.");
	}
	
	private final CSVWriter CSV_WRITER;
	
	public CourseEnrollmentScraper() {
		CSVWriter temp = null;
		try {
			temp = createCsvWriter();
		} catch(IOException e) {
			LOGGER.severe(e.getMessage());
			e.printStackTrace(System.err);
		} finally {
			CSV_WRITER = temp;
		}
	}
	
	private void parseMasterSchedule(HtmlPage curPage) {
		CSV_WRITER.writeNext(new String[] {"Course", "Enrollment", "Sections"});
		HtmlTableBody tableBody = (HtmlTableBody)curPage.getElementsByTagName("tbody").get(0);
		List<HtmlTableRow> sectionRows = tableBody.getRows();
		for(int rowIndex = sectionRows.size() - 1; rowIndex > 0; rowIndex--) {
			//int rowIndex = sectionRows.size() - 9;
			HtmlTableRow sectionRow = (HtmlTableRow)sectionRows.get(rowIndex);
			processSectionRow(sectionRow);
		}
	}
	
	private void processSectionRow(HtmlTableRow sectionRow) {
		String course = parseCourseCell(sectionRow.getCell(0));
		System.out.println("Process course " + course);
		boolean inExcludedCourses = false;
		for(int index = COURSES_TO_EXCLUDE.length - 1; index >= 0 && !inExcludedCourses; index--)
			inExcludedCourses = course.contains(COURSES_TO_EXCLUDE[index]);
		if(!inExcludedCourses) {
			Set<String> sectionNumbers = new HashSet<>(6);
			int enrollment = 0;
			for(int cellIndex = 12 + 2 - 1; cellIndex >= 2; cellIndex--) {
				HtmlTableCell cell = sectionRow.getCell(cellIndex);
				DomNodeList<HtmlElement> htmlAnchorList = cell.getElementsByTagName("a");
				if(htmlAnchorList.size() > 0) {
					enrollment += processPeriod(htmlAnchorList, sectionNumbers);
				}
			}
			if(enrollment > 0)
				CSV_WRITER.writeNext(new String[] {course, String.valueOf(enrollment), String.valueOf(sectionNumbers.size())});
		}
	}
	
	private int processPeriod(DomNodeList<HtmlElement> htmlAnchorList, Set<String> sectionNumbers) {
		int enrollment = 0;
		for(int indexSectionStart = 0; indexSectionStart < htmlAnchorList.size(); indexSectionStart++) {
			HtmlAnchor anchor = (HtmlAnchor)htmlAnchorList.get(indexSectionStart);
			if(inHref(anchor, "sections")) {
				String sectionNumber = anchor.getTextContent();
				if(!sectionNumbers.contains(sectionNumber)) {
					HtmlAnchor enrollmentAnchor = (HtmlAnchor)htmlAnchorList.get(++indexSectionStart);
					if(!matchRoom(enrollmentAnchor, "GHS")) {
						enrollment += Integer.parseInt(enrollmentAnchor.getTextContent());
						sectionNumbers.add(sectionNumber);
					}
				}
			}
		}
		return enrollment;
	}
	
	private boolean matchRoom(HtmlAnchor enrollmentAnchor, String room) {
		DomNode nextSibling = enrollmentAnchor.getNextSibling();
		String text = nextSibling.getTextContent();
		//System.out.println(text);
		while(!text.contains("Room") && nextSibling != null) {
			nextSibling = nextSibling.getNextSibling();
			if(nextSibling != null)
				text = nextSibling.getTextContent();
		}
		boolean inExcludedList = false;
		if(nextSibling != null) {
			for(int index = ROOMS_TO_EXCLUDE.length - 1; index >= 0 && !inExcludedList; index--) {
				inExcludedList = text.contains(ROOMS_TO_EXCLUDE[index]);
			}
		}
		return inExcludedList;
	}
	
	private boolean inHref(HtmlAnchor anchor, String match) {
		String href = anchor.getHrefAttribute();
		return href != null && href.contains(match);
	}
	
	private String parseCourseCell(HtmlTableCell courseCell) {
		String text = courseCell.getTextContent();
		int endIndex = text.indexOf(END_OF_COURSE);
		return text.substring(0, endIndex);
	}
	
	private CSVWriter createCsvWriter() throws IOException {
		File file = new File(LOG_PATH + "SectionReport.csv");
		file.createNewFile();
		return new CSVWriter(new FileWriter(file));
	}

	/* (non-Javadoc)
	 * @see edu.jenks.scrape.Scraper#close()
	 */
	@Override
	protected void close() {
		super.close();
		try {
			CSV_WRITER.close();
		} catch(IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
	

}
