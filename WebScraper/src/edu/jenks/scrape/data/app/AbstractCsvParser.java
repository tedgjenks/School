package edu.jenks.scrape.data.app;

import java.io.*;
import java.util.*;
import org.jdom2.*;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import edu.jenks.scrape.data.*;

public abstract class AbstractCsvParser {
	protected final PsCsvParser STUDENT_PS_DATA;
	protected CSVReader csvReader;
	
	protected AbstractCsvParser(String psExportFilePath) throws IOException {
		STUDENT_PS_DATA = new PsCsvParser(psExportFilePath);
	}
	
	protected abstract void loadExportFileProps() throws IOException, JDOMException;

	protected void generateImportFile(Assignment assignment, String importFilePath) throws IOException {
		Map<Byte, CSVWriter> SECTION_WRITER_MAP = new HashMap<>(4);
		for(Byte section : STUDENT_PS_DATA.getSections()) {
			File file = createFile(importFilePath, section + assignment.getName());
			CSVWriter writer = new CSVWriter(new FileWriter(file));
			writer.writeNext(new String[] {"STUDENT NUMBER", "STUDENT NAME", "SCORE"});
			SECTION_WRITER_MAP.put(section, writer);
		}
		Iterator<AssignmentGrade> grades = assignment.getAssignmentGrades();
		List<AssignmentGrade> gradesList = new ArrayList<>(50);
		while(grades.hasNext())
			gradesList.add(grades.next());
		Collections.sort(gradesList);
		for(AssignmentGrade grade : gradesList) {
			String name = grade.getStudentName();
			String number = String.valueOf(STUDENT_PS_DATA.getStudentPsData(name).ID);
			CSVWriter writer = SECTION_WRITER_MAP.get(grade.getSection());
			writer.writeNext(new String[] {number, name, String.valueOf(grade.getBestScore())});
		}
		for(CSVWriter writer : SECTION_WRITER_MAP.values())
			writer.close();
	}
	
	private File createFile(String path, String assignmentName) throws IOException {
		String fileName = assignmentName.replaceAll("[\\?':]", "");
		String fullPath = path + fileName + ".csv";
		System.out.println(fullPath);
		File file = new File(fullPath);
		file.createNewFile();
		return file;
	}
}
