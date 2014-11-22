package edu.jenks.google.drive;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyFileHelper {

	private static String buildDestPackage(Student student) {
		return (student.getLastName() + "/" + student.getFirstName() + "/").toLowerCase();
	}
	
	public static File[] getStudentDirectories(File parentFile, final String studentDirSuffix) {
		FilenameFilter textFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(studentDirSuffix);
			}
			
		};
		return parentFile.listFiles(textFilter);
	}
	
	public static boolean copyStudentFiles(String source, final String fileName, String generalDest, Student student) throws IOException {
		//String source = parentDirPath + "\\" + fileName;
		File sourceFile = new File(source);
		boolean sourceFileExists = sourceFile.exists();
		if(sourceFileExists) {
			System.out.println(source);
			String dest = generalDest + buildDestPackage(student);
			System.out.println(dest);
			File destFile = new File(dest);
			if(!destFile.exists())
				destFile.mkdirs();
			dest += fileName;
			destFile = new File(dest);
			System.out.println(dest);
			if(!destFile.exists())
				destFile.createNewFile();
			Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			//TODO uncomment sourceFile.delete();
		}
		return sourceFileExists;
	}
	
	public static Student getStudent(String source, String gdStudentDirSuffix) {
		Student student = null;
		String[] sourceDirs = source.split("\\\\");
		String studentLastFirst = null;
		for(int index = sourceDirs.length - 1; index >= 0 && studentLastFirst == null; index--) {
			String sourceDir = sourceDirs[index];
			int markerIndex = sourceDir.indexOf(gdStudentDirSuffix);
			if(markerIndex > 0)
				studentLastFirst = sourceDir.substring(0, markerIndex);
		}
		if(studentLastFirst != null) {
			int firstNameIndex = -1;
			for(int charIndex = studentLastFirst.length() - 1; charIndex >= 0 && firstNameIndex < 0; charIndex--) {
				if(Character.isUpperCase(studentLastFirst.charAt(charIndex)))
					firstNameIndex = charIndex;
			}
			if(firstNameIndex > 0)
				student = new Student(studentLastFirst.substring(firstNameIndex), studentLastFirst.substring(0, firstNameIndex));
		}
		return student;
	}
}
