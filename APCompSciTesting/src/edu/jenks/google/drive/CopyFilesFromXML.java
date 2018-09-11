package edu.jenks.google.drive;

import java.io.*;
import java.util.*;

import org.jdom2.*;

import edu.jenks.test.TestRunner;
import edu.jenks.xml.JDOMHelper;
import static java.lang.System.out;

public class CopyFilesFromXML {
	
	private static String targetEclipseRoot;
	private static String targetMojiRoot;
	private static File sourceRoot;
	private static String turninDirSuffix;
	private static Document document;
	private static boolean testMode = false;

	public static void main(String[] args) {
		try {
			TestRunner.processCommandLineArgs(args);
			document = JDOMHelper.buildDocument(TestRunner.xmlFilePath);
			Element rootElement = document.getRootElement();
			testMode = Boolean.parseBoolean(rootElement.getAttributeValue("test-mode"));
			targetEclipseRoot = rootElement.getChildText("eclipse-student-root");
			targetMojiRoot = rootElement.getChildText("moji-root");
			//out.println(targetEclipseRoot);
			Element googleDriveElement = rootElement.getChild("google-drive");
			sourceRoot = new File(googleDriveElement.getChildText("turnin-root"));
			turninDirSuffix = googleDriveElement.getChildText("turnin-dir-suffix");
			File[] studentDirs = CopyFileHelper.getStudentDirectories(sourceRoot, turninDirSuffix);
			if(studentDirs != null) {
				processProjects(rootElement.getChildren("project"), studentDirs);
				JDOMHelper.updateXml(document, TestRunner.xmlFilePath);
			} else
				out.println("No student directories for " + sourceRoot + " and " + turninDirSuffix);
		} catch (JDOMException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void handleCopies(String studentDirPath, String fileName) {
		int beginFileExt = fileName.lastIndexOf('.');
		if(beginFileExt > 0) {
			String fileNameNoExtension = fileName.substring(0, beginFileExt);
			CopyFileHelper.handleCopies(studentDirPath, fileNameNoExtension);
		}
	}
	
	private static void processProjects(List<Element> projects, File[] studentDirs) throws IOException, DataConversionException {
		int totalStudentProjects = 0;
		int projectsProcessed = 0;
		for(Element project : projects) {
			if(!project.getAttribute(TestRunner.PROJECT_ACTIVE_ATTRIBUTE).getBooleanValue())
				continue;
			final String PROJECT_NAME = project.getChildText(TestRunner.PROJECT_NAME_TAG);
			final String MOJI_ROOT = targetMojiRoot + PROJECT_NAME + "/";
			final String PACKAGE_ROOT = project.getChildText("package-root");
			//out.println(PACKAGE_ROOT);
			List<Element> files = project.getChild("files").getChildren("file");
			Set<Student> studentsToProcess = new HashSet<Student>();
			for(Element file : files) {
				String fileName = file.getText();
				for(int index = studentDirs.length - 1; index >= 0; index--) {
					String studentDirPath = studentDirs[index].getPath();
					handleCopies(studentDirPath, fileName);
					String source = studentDirPath + "\\" + fileName;
					//out.println("Suffix: " + turninDirSuffix);
					Student student = CopyFileHelper.getStudent(source, turninDirSuffix);
					String generalDest = targetEclipseRoot + PACKAGE_ROOT;
					String packageDest = new StringBuilder(50).append(generalDest).append(student.getLastName()).append("/").append(student.getFirstName()).append("/").toString().toLowerCase();
					//out.println("source: " + source);
					//out.println("fileName: " + fileName);
					if(CopyFileHelper.copyStudentFilesFromTurnIn(source, fileName, packageDest, false)) {
						studentsToProcess.add(student);
						if(!testMode) {
							File directory = CopyFileHelper.makeDestinationDirectories(packageDest);
							CopyFileHelper.deleteLogs(directory, PROJECT_NAME);
						}
						String mossDest = new StringBuilder(50).append(MOJI_ROOT).append(student.getLastName()).append(student.getFirstName()).append("/").toString();
						CopyFileHelper.copyStudentFilesFromTurnIn(source, fileName, mossDest, true);
						out.println("Copied " + fileName + " for " + student);
					}
				}
			}
			for(Student student : studentsToProcess) {
				out.println("Add element for " + student);
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
			int studentsToProcessSize = studentsToProcess.size();
			totalStudentProjects += studentsToProcessSize;
			out.println(studentsToProcessSize + " students processed for project " + project.getChildText(TestRunner.PROJECT_NAME_TAG));
			out.println();
			projectsProcessed++;
		}
		out.println(projectsProcessed + " projects processed out of " + projects.size() + " defined.");
		out.println(totalStudentProjects + " total students processed.");
		if(totalStudentProjects > 0)
			out.println("Refresh student project folder!");
	}
}
