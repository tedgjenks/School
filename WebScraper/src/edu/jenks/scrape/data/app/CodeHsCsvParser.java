package edu.jenks.scrape.data.app;

import java.io.*;
import java.util.*;
import edu.jenks.scrape.data.*;
import edu.jenks.util.MathUtil;

import static java.lang.System.out;

public class CodeHsCsvParser extends AbstractCsvParser {
	
	//private static final String CODEHS_PROPERTIES_FILEPATH = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\CodeHS.properties";
	//private static final String STUDENT_NUMBERS_PROPERTIES_FILEPATH = "C:\\Users\\Jenks\\git\\School\\WebScraper\\resources\\PowerSchoolPages\\CSPStudentNumbers.properties";
	private static final byte EXPORT_FILE_FIRST_STUDENT_RECORD = 4;
	
	public static CodeHsCsvParser initParser() throws IOException {
		CodeHsCsvParser codeHsReader =  new CodeHsCsvParser();
		codeHsReader.loadExportFileProps();
		//codeHsReader.populateStudentNumbers(STUDENT_NUMBERS_PROPERTIES_FILEPATH);
		return codeHsReader;
	}

	public static void main(String[] args) {
		out.println("Begin");
		CodeHsCsvParser parser = null;
		try {
			parser = initParser();
			parser.parseExportFile();
			parser.generateImportFiles();
			out.println("End without error.");
		} catch(IOException e) {
			e.printStackTrace(System.err);
		} finally {
			if(parser != null)
				parser.closeResources();
		}
	}
	
	private final Map<Integer, Assignment> COLINDEX_ASSIGNMENT_MAP = new HashMap<Integer, Assignment>(40);
	
	public CodeHsCsvParser() throws IOException {
		super(null); 
	}
	
	@Override
	protected void loadExportFileProps() throws IOException {
		/*EXPORT_FILE_PROPS.load(new FileInputStream(CODEHS_PROPERTIES_FILEPATH));
		Reader reader = new FileReader(EXPORT_FILE_PROPS.getProperty("CSV_FILEPATH") + EXPORT_FILE_PROPS.getProperty("CSV_EXPORT_FILE"));
		csvReader = new CSVReader(reader);*/
	}
	
	private void closeResources() {
		try {
			if(csvReader != null)
				csvReader.close();
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}
	
	private void initAssignments(String[] assignmentCols) {
		for(int index = assignmentCols.length - 1; index > 0; index--)
			COLINDEX_ASSIGNMENT_MAP.put(index, new Assignment(assignmentCols[index]));
	}
	
	public void parseExportFile() throws IOException {
		List<String[]> records = csvReader.readAll();
		initAssignments(records.get(0));
		for(int rowIndex = records.size() - 1; rowIndex >= EXPORT_FILE_FIRST_STUDENT_RECORD; rowIndex--) {
			String[] record = records.get(rowIndex);
			String studentName = record[0];
			for(int colIndex = record.length - 1; colIndex > 0; colIndex--) {
				String gradeData = record[colIndex];
				Assignment assignment = COLINDEX_ASSIGNMENT_MAP.get(colIndex);
				assignment.addAssignment((byte)1, studentName, MathUtil.isRealNumber(gradeData) ? (byte)Double.parseDouble(gradeData) : 0);
			}
		}
	}
	
	public void generateImportFiles() throws IOException {
		/*Iterator<Assignment> assignments = COLINDEX_ASSIGNMENT_MAP.values().iterator();
		while(assignments.hasNext())
			generateImportFile(assignments.next());*/
	}
}
