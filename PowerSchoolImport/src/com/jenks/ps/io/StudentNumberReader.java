package com.jenks.ps.io;

import static java.lang.System.out;
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import org.jdom2.*;
import com.jenks.data.Student;
import com.opencsv.*;
import edu.jenks.xml.JDOMHelper;

public class StudentNumberReader {
	
	private static final Pattern DIGITS_PATTERN = Pattern.compile("\\d+");

	public static void main(String[] args) throws IOException, JDOMException {
		out.println("Begin");
		String path = "C:\\Users\\Jenks\\Documents\\temp\\ka\\studentNumbers.csv";
		/*List<Student> students = new StudentNumberReader().readStudents(path);
		System.out.println(students);*/
		new StudentNumberReader().addStudentNamesAndNumbers(path, "resources\\students-power-school.xml");
		out.println("End");
	}
	
	public void addStudentNamesAndNumbers(String psExportFilePath, String psStudentNumberFilePath) throws JDOMException, IOException {
		List<Student> students = readStudents(psExportFilePath);
		Document document = JDOMHelper.buildDocument(psStudentNumberFilePath);
		Element root = document.getRootElement();
		for(Student student : students) {
			Element studentElement = new Element("student");
			root.addContent(studentElement);
			Element nameElement = new Element("name");
			nameElement.setText(student.getNameLastCommaFirst());
			studentElement.addContent(nameElement);
			Element studentNumberElement = new Element("student-number");
			studentNumberElement.setText(String.valueOf(student.getNumber()));
			studentElement.addContent(studentNumberElement);
		}
		JDOMHelper.updateXml(document, psStudentNumberFilePath);
	}

	private List<Student> readStudents(String psExportFilePath) throws IOException {
		final byte numCol = 0, nameCol = 1;
		FileReader fileReader = new FileReader(psExportFilePath);
		List<Student> students = new ArrayList<>(50);
		final CSVParser parser = new CSVParserBuilder().withSeparator(',').withQuoteChar('"').withIgnoreQuotations(false).build();
		final CSVReader reader = new CSVReaderBuilder(fileReader).withCSVParser(parser).build();
		Iterator<String[]> lines = reader.iterator();
		while(lines.hasNext()) {
			String[] cols = lines.next();
			String studentNumber = cols[numCol];
			if(DIGITS_PATTERN.matcher(studentNumber).matches()) {
				Student student = new Student(cols[nameCol], Long.parseLong(studentNumber));
				students.add(student);
			}
		}
		reader.close();
		Collections.sort(students);
		return students;
	}
}
