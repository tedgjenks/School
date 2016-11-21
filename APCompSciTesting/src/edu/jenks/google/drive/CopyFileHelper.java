package edu.jenks.google.drive;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

import edu.jenks.util.FileUtil;

public class CopyFileHelper {
	
	public static File[] getStudentDirectories(File parentFile, final String studentDirSuffix) {
		FilenameFilter textFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(studentDirSuffix);
			}
			
		};
		return parentFile.listFiles(textFilter);
	}
	
	public static void makeDirectory(String path) {
		File filePath = new File(path);
		if(!filePath.exists())
			filePath.mkdir();
	}
	
	/*public static File makeDestinationDirectoriesPackage(String generalDest, Student student) {
		String dest = generalDest + (student.getLastName() + "/" + student.getFirstName() + "/").toLowerCase();
		File destFile = new File(dest);
		if(!destFile.exists())
			destFile.mkdirs();
		return destFile;
	}*/
	
	/*public static File makeDestinationDirectoriesMoss(String generalDest, Student student) {
		String dest = generalDest + (student.getLastName() + "/" + student.getFirstName() + "/").toLowerCase();
		File destFile = new File(dest);
		if(!destFile.exists())
			destFile.mkdirs();
		return destFile;
	}*/
	
	public static File makeDestinationDirectories(String dest) {
		File destFile = new File(dest);
		if(!destFile.exists())
			destFile.mkdirs();
		return destFile;
	}
	
	public static void deleteLogs(File directory, String projectName) {
		Pattern pattern = Pattern.compile(projectName + "Feedback" + ".*" + "\\.log(\\.lck)?");
		File[] logFiles = FileUtil.listFiles(directory, pattern);
		for(File logFile : logFiles)
			logFile.delete();
	}
	
	public static boolean copyStudentFilesFromTurnIn(String source, final String fileName, String dest, boolean delete) throws IOException {
		File sourceFile = new File(source);
		boolean sourceFileExists = sourceFile.exists();
		if(sourceFileExists) {
			File destDir = makeDestinationDirectories(dest);
			//String dest = destDir.getAbsolutePath() + "/";
			//System.out.println("destDir: " + dest);
			dest += fileName;
			//System.out.println("destDir: " + dest);
			File destFile = new File(dest);
			if(!destFile.exists())
				destFile.createNewFile();
			Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			if(delete)
				sourceFile.delete();
			//System.out.println("Copied " + fileName + " for " + student);
		}
		return sourceFileExists;
	}
	
	public static void copyFeedbackLogFromEclipse(File source, File target) throws IOException {
		Files.copy(source.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
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
