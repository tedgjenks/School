// TODO leaderboard, timestamps
package edu.jenks.test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.jdom2.*;

import edu.jenks.google.drive.Student;
import edu.jenks.xml.JDOMHelper;

/**
 * @author tedgj
 *
 */
public class HighScoreRecorder {
	private static final HighScoreRecorder INSTANCE = new HighScoreRecorder();
	private static final String PROJECT_ELEMENT = "project";
	private static final String NAME_ELEMENT = "name";
	private static final String STUDENTS_ELEMENT = "students";
	private static final String STUDENT_ELEMENT = "student";
	private static final String LAST_NAME_ELEMENT = "last-name";
	private static final String FIRST_NAME_ELEMENT = "first-name";
	private static final String HIGH_SCORE_ELEMENT = "high-score";
	private static final String DATE_TIME_ELEMENT = "date-time";
	private static final String FILE_PATH = "testing/high-scores.xml";
	
	public static HighScoreRecorder getInstance() {
		return INSTANCE;
	}
	
	
	private final Document DOCUMENT;
	private final ProjectComparator PROJECT_COMPARATOR = new ProjectComparator();
	private final StudentComparator STUDENT_COMPARATOR = new StudentComparator();
	
	private HighScoreRecorder() {
		Document temp = null;
		try {
			temp = JDOMHelper.buildDocument(FILE_PATH);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DOCUMENT = temp;
		}
	}
	
	public void alphabetizeProjectsAndStudents() {
		try {
			Element root = DOCUMENT.getRootElement();
			List<Element> projects = root.getChildren(PROJECT_ELEMENT);
			projects.sort(PROJECT_COMPARATOR);
			for(int index = projects.size() - 1; index >= 0; index--) {
				Element studentsE = projects.get(index).getChild(STUDENTS_ELEMENT);
				List<Element> students = studentsE.getChildren(STUDENT_ELEMENT);
				students.sort(STUDENT_COMPARATOR);
			}
			JDOMHelper.updateXml(DOCUMENT, FILE_PATH);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
	
	public synchronized LeaderBoard recordScore(Student student, String projectName, String score) {
		try {
			Element root = DOCUMENT.getRootElement();
			Element projectE = getProjectByName(projectName, root);
			Element studentE = getStudent(projectE, student);
			byte scoreB = Byte.parseByte(score);
			updateScore(studentE, scoreB);
			JDOMHelper.updateXml(DOCUMENT, FILE_PATH);
			return new LeaderBoard(student, scoreB, projectE, studentE);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			return null;
		}
	}
	
	private void updateScore(Element studentE, byte score) {
		Element hs = studentE.getChild(HIGH_SCORE_ELEMENT);
		byte curScore = Byte.parseByte(hs.getText());
		if(score > curScore) {
			hs.setText(String.valueOf(score));
			Element dt = studentE.getChild(DATE_TIME_ELEMENT);
			dt.setText(getDateTime());
		}
	}
	
	private Element getStudent(Element projectE, Student student) {
		Element studentE = null;
		List<Element> students = projectE.getChild(STUDENTS_ELEMENT).getChildren(STUDENT_ELEMENT);
		for(int index = students.size() - 1; index >= 0 && studentE == null; index--) {
			Element studentTemp = students.get(index);
			Element lastName = studentTemp.getChild(LAST_NAME_ELEMENT);
			Element firstName = studentTemp.getChild(FIRST_NAME_ELEMENT);
			if(lastName.getText().contentEquals(student.getLastName()) && firstName.getText().contentEquals(student.getFirstName()))
				studentE = studentTemp;
		}
		if(studentE == null) {
			Element studentsE = projectE.getChild(STUDENTS_ELEMENT);
			studentE = new Element(STUDENT_ELEMENT);
			studentsE.addContent(studentE);
			Element ln = new Element(LAST_NAME_ELEMENT);
			studentE.addContent(ln);
			ln.setText(student.getLastName());
			Element fn = new Element(FIRST_NAME_ELEMENT);
			studentE.addContent(fn);
			fn.setText(student.getFirstName());
			Element hs = new Element(HIGH_SCORE_ELEMENT);
			studentE.addContent(hs);
			hs.setText("0");
			Element dt = new Element(DATE_TIME_ELEMENT);
			studentE.addContent(dt);
			dt.setText(getDateTime());
		}
		return studentE;
	}
	
	private String getDateTime() {
		return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
	}
	
	private Element getProjectByName(String projectName, Element root) {
		Element projectE = null;
		List<Element> projects = root.getChildren(PROJECT_ELEMENT);
		for(int index = projects.size() - 1; index >= 0 && projectE == null; index--) {
			Element temp = projects.get(index);
			Element nameE = temp.getChild(NAME_ELEMENT);
			if(nameE.getText().contentEquals(projectName))
				projectE = temp;
		}
		if(projectE == null) {
			projectE = new Element(PROJECT_ELEMENT);
			root.addContent(projectE);
			Element nameE = new Element(NAME_ELEMENT);
			projectE.addContent(nameE);
			nameE.setText(projectName);
			Element students = new Element(STUDENTS_ELEMENT);
			projectE.addContent(students);
		}
		return projectE;
	}
	
	private class ProjectComparator implements Comparator<Element> {

		@Override
		public int compare(Element o1, Element o2) {
			return o1.getChild(NAME_ELEMENT).getText().compareToIgnoreCase(o2.getChild(NAME_ELEMENT).getText());
		}
	}
	
	private class StudentComparator implements Comparator<Element> {

		@Override
		public int compare(Element o1, Element o2) {
			String fullName1 = o1.getChild(LAST_NAME_ELEMENT).getText() + o1.getChild(FIRST_NAME_ELEMENT).getText();
			String fullName2 = o2.getChild(LAST_NAME_ELEMENT).getText() + o2.getChild(FIRST_NAME_ELEMENT).getText();
			return fullName1.compareTo(fullName2);
		}
		
	}
	
	static class LeaderBoard {
		final List<Student> LEADING_STUDENTS = new ArrayList<>(5);
		final int POINT_DEFICIT;
		
		LeaderBoard(Student student, byte score, Element projectE, Element curStudentE) {
			int smallestDeficit = Integer.MAX_VALUE;
			List<Element> students = projectE.getChild(STUDENTS_ELEMENT).getChildren(STUDENT_ELEMENT);
			for(Element studentE : students) {
				byte hs = Byte.parseByte(studentE.getChildText(HIGH_SCORE_ELEMENT));
				if(hs > score) {
					LEADING_STUDENTS.add(new Student(studentE.getChildText(FIRST_NAME_ELEMENT), studentE.getChildText(LAST_NAME_ELEMENT)));
					int thisDeficit = hs - score;
					if(thisDeficit < smallestDeficit)
						smallestDeficit = thisDeficit;
				} else if(hs == score) {
					Student otherStudent = new Student(studentE.getChildText(FIRST_NAME_ELEMENT), studentE.getChildText(LAST_NAME_ELEMENT));
					if(!student.equals(otherStudent)) {
						String otherDateTime = studentE.getChildText(DATE_TIME_ELEMENT).trim();
						if(otherDateTime.length() > 0) {
							LocalDateTime curStudentTime = LocalDateTime.parse(curStudentE.getChildText(DATE_TIME_ELEMENT));
							LocalDateTime otherStudentTime = LocalDateTime.parse(otherDateTime);
							if(curStudentTime.isAfter(otherStudentTime)) {
								LEADING_STUDENTS.add(otherStudent);
								smallestDeficit = 0;
							}
						}
					}
				}
			}
			POINT_DEFICIT = smallestDeficit == Integer.MAX_VALUE ? 0 : smallestDeficit;
		}
	}
}
