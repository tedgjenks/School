package edu.jenks.google.drive;

import java.io.*;
import java.util.*;

import org.jdom2.*;

import edu.jenks.test.TestRunner;
import edu.jenks.xml.JDOMHelper;

public class CopyFilesFromXML {
	
	private static String targetRoot;
	private static File sourceRoot;
	private static String turninDirSuffix;
	private static Document document;
	

	public static void main(String[] args) {
		try {
			document = JDOMHelper.buildDocument(TestRunner.XML_FILE_PATH);
			Element rootElement = document.getRootElement();
			targetRoot = rootElement.getChildText("eclipse-student-root");
			//System.out.println(targetRoot);
			Element googleDriveElement = rootElement.getChild("google-drive");
			sourceRoot = new File(googleDriveElement.getChildText("turnin-root"));
			turninDirSuffix = googleDriveElement.getChildText("turnin-dir-suffix");
			File[] studentDirs = CopyFileHelper.getStudentDirectories(sourceRoot, turninDirSuffix);
			processProjects(rootElement.getChildren("project"), studentDirs);
			JDOMHelper.updateXml(document, TestRunner.XML_FILE_PATH);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void processProjects(List<Element> projects, File[] studentDirs) throws IOException {
		for(Element project : projects) {
			String packageRoot = project.getChildText("package-root");
			//System.out.println(packageRoot);
			List<Element> files = project.getChild("files").getChildren("file");
			Set<Student> studentsToProcess = new HashSet<Student>();
			for(Element file : files) {
				String fileName = file.getText();
				for(int index = studentDirs.length - 1; index >= 0; index--) {
					//System.out.println(studentDirs[index].getName() + ", " + fileName + ", " + targetRoot + packageRoot + ", " + turninDirSuffix);
					String source = studentDirs[index].getPath() + "\\" + fileName;
					Student student = CopyFileHelper.getStudent(source, turninDirSuffix);
					if(CopyFileHelper.copyStudentFiles(source, fileName, targetRoot + packageRoot, student))
						studentsToProcess.add(student);
				}
			}
			for(Student student : studentsToProcess) {
				System.out.println("Add element for " + student);
				Element studentsElement = project.getChild("students");
				Element firstNameElement = new Element("first-name");
				firstNameElement.setText(student.getFirstName());
				Element lastNameElement = new Element("last-name");
				lastNameElement.setText(student.getLastName());
				Element studentElement = new Element("student");
				studentElement.addContent(lastNameElement);
				studentElement.addContent(firstNameElement);
				studentsElement.addContent(studentElement);
			}
		}
	}
}
