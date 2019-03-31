package com.jenks.ps.io;

import static java.lang.System.out;
import java.io.*;
import java.time.MonthDay;
import java.util.*;
import org.jdom2.*;
import com.jenks.data.RestrictionValue;
import com.opencsv.*;
import edu.jenks.xml.JDOMHelper;

public class ExportFileReader {
	
	private static final String COLUMN_LOCATION_ATTRIBUTE = "col-location";
	private static enum NameTypes {LastCommaFirst, Last, First};
	private static enum ScoreTypes {Points, Percent, PerAssignment};
	private static enum MergeOptions {All, OneToOne, Map};
	
	public static void main(String[] args) throws JDOMException, IOException {
		out.println("Begin");
		new ExportFileReader("resources\\ka-ps-map.xml");
		out.println("End");
	}
	
	private List<String[]> records;
	private Map<String, Integer> headerLabelColumnMap;
	private final Element ROOT;
	private boolean useColumnHeader;
	private int nameLastCommaFirstCol;
	private NameTypes nameType;
	private int scoreCol;
	private ScoreTypes scoreType;
	private int scorePointsAvailableCol;
	private int dueDateCol;
	private MonthDay startDate, endDate;
	private final Map<Integer, RestrictionValue> RESTRICT_COLS = new HashMap<>(3);
	private int assignmentCol;
	private MergeOptions mergeOption;
	private final Map<String, List<String>> IMPORT_FILE_ASSIGNMENT_NAMES_MAP = new HashMap<>(5);

	public ExportFileReader(String exportImportMapPath) throws JDOMException, IOException {
		Document document = JDOMHelper.buildDocument(exportImportMapPath);
		ROOT = document.getRootElement();
		initCSVReader();
		initHeader();
		initName();
		initScore();
		initScorePointsAvailable();
		initRestrictions();
		initAssignments();
	}
	
	private void initAssignments() {
		Element assignmentsElement = ROOT.getChild("assignments");
		assignmentCol = getColNumber(assignmentsElement.getAttributeValue(COLUMN_LOCATION_ATTRIBUTE));
		switch(assignmentsElement.getAttributeValue("merge")) {
		case "all":
			mergeOption = MergeOptions.All;
			List<String> list = new ArrayList<>();
			list.add("ALL");
			IMPORT_FILE_ASSIGNMENT_NAMES_MAP.put(assignmentsElement.getChild("ps-import").getAttributeValue("file-location"), list);
			break;
		default:
			throw new IllegalStateException("Unsupported assignment merge option");
		}
	}
	
	private void initDateRestriction(Element dueDateElement) {
		dueDateCol = getColNumber(dueDateElement.getAttributeValue(COLUMN_LOCATION_ATTRIBUTE));
		if(dueDateCol >= 0) {
			Element startDateElement = dueDateElement.getChild("start-date");
			startDate = createDate(startDateElement);
			Element endDateElement = dueDateElement.getChild("end-date");
			endDate = createDate(endDateElement);
		}
	}
	
	private MonthDay createDate(Element dateElement) {
		MonthDay date = null;
		if(dateElement != null) {
			String day = dateElement.getAttributeValue("day");
			String month = dateElement.getAttributeValue("month");
			date = MonthDay.of(Integer.parseInt(month), Integer.parseInt(day));
		}
		return date;
	}
	
	private void initRestrictions() {
		Element restrictElement = ROOT.getChild("restrict");
		initDateRestriction(restrictElement.getChild("due-date"));
		List<Element> restrictions = restrictElement.getChildren("restrict-col-value");
		int restrictionsSize = restrictions.size();
		for(int index = restrictionsSize - 1; index >= 0; index--) {
			Element restriction = restrictions.get(index);
			int restrictCol = getColNumber(restriction.getAttributeValue(COLUMN_LOCATION_ATTRIBUTE));
			RestrictionValue restrictionValue = new RestrictionValue();
			RESTRICT_COLS.put(restrictCol, restrictionValue);
			List<Element> restrictionValueElements = restriction.getChildren("restrict-value");
			for(Element restrictionValueElement : restrictionValueElements) {
				boolean matches = "yes".equals(restrictionValueElement.getAttributeValue("matches"));
				restrictionValue.addValue(restrictionValueElement.getText(), matches);
			}
		}
	}
	
	private void initScorePointsAvailable() {
		Element scorePointsAvailableElement = ROOT.getChild("score-points-available");
		scorePointsAvailableCol = getColNumber(scorePointsAvailableElement.getAttributeValue(COLUMN_LOCATION_ATTRIBUTE));
	}
	
	private void initScore() {
		Element scoreElement = ROOT.getChild("score-col");
		scoreCol = getColNumber(scoreElement.getAttributeValue(COLUMN_LOCATION_ATTRIBUTE));
		switch(scoreElement.getAttributeValue("score-type")) {
		case "points":
			scoreType = ScoreTypes.Points;
			break;
		default:
			throw new IllegalStateException("Unsupported score format");
		}
	}
	
	private void initName() {
		Element nameElement = ROOT.getChild("name");
		switch(nameElement.getAttributeValue("type")) {
		case "last-comma-first":
			nameType = NameTypes.LastCommaFirst;
			nameLastCommaFirstCol = getColNumber(nameElement.getAttributeValue(COLUMN_LOCATION_ATTRIBUTE));
			break;
		default:
			throw new IllegalStateException("Unsupported name format");
		}
	}
	
	private void initHeader() {
		useColumnHeader = "header".equals(ROOT.getAttributeValue("column"));
		if(useColumnHeader) {
			String[] header = records.get(0);
			headerLabelColumnMap = new HashMap<>(10);
			for(int index = header.length - 1; index >= 0; index--)
				headerLabelColumnMap.put(header[index], index);
		} else
			throw new IllegalStateException("Only use column header is supported");
	}
	
	private void initCSVReader() throws IOException {
		final CSVParser parser = new CSVParserBuilder().withSeparator(',').withQuoteChar('"').withIgnoreQuotations(false).build();
		final CSVReader exportCSV = new CSVReaderBuilder(new FileReader(ROOT.getAttributeValue("file-location"))).withCSVParser(parser).build();
		records = exportCSV.readAll();
		exportCSV.close();
	}
	
	private int getColNumber(String colLocationValue) {
		return useColumnHeader ? headerLabelColumnMap.get(colLocationValue) : Integer.parseInt(colLocationValue);
	}
	
	/**
	 * @return the records
	 */
	public List<String[]> getRecords() {
		return records;
	}

	/**
	 * @return the useColumnHeader
	 */
	public boolean isUseColumnHeader() {
		return useColumnHeader;
	}

	/**
	 * @return the dueDateCol
	 */
	public int getDueDateCol() {
		return dueDateCol;
	}

	/**
	 * @return the endDate
	 */
	public MonthDay getEndDate() {
		return endDate;
	}
	
	
}
