package edu.jenks.google.drive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class CopyFilesFromProperties {
	
	public static void main(String[] args) {
		System.out.println("Begin");
		Properties props = new Properties();
		try {
			FileReader fr = new FileReader("googleDrive.properties");
			props.load(fr);
			fr.close();
			String turninDir = props.getProperty("google.drive.turnin");
			File turninDirFile = new File(turninDir);
			File[] studentDirs = getStudentDirectories(turninDirFile, props);
			for(int index = studentDirs.length - 1; index >= 0; index--)
				copyStudentFiles(studentDirs[index], props);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("End");
	}
	
	private static File[] getStudentDirectories(File parentFile, Properties props) {
		final String STUDENTDIR_SUFFIX = props.getProperty("google.drive.studentdir.suffix");
		FilenameFilter textFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(STUDENTDIR_SUFFIX);
			}
			
		};
		return parentFile.listFiles(textFilter);
	}
	
	private static void copyStudentFiles(File parentDir, Properties props) throws IOException {
		final String FILE_NAME = props.getProperty("filename");
		String source = parentDir.getPath() + "\\" + FILE_NAME;
		File sourceFile = new File(source);
		if(sourceFile.exists()) {
			System.out.println(source);
			String dest = props.getProperty("eclipse.student.python.sourcecode") + 
					props.getProperty("eclipse.package") + getDestPackage(source, props);
			System.out.println(dest);
			File destFile = new File(dest);
			if(!destFile.exists())
				destFile.mkdirs();
			dest += FILE_NAME;
			destFile = new File(dest);
			System.out.println(dest);
			if(!destFile.exists())
				destFile.createNewFile();
			Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			sourceFile.delete();
		}
	}
	
	private static String getDestPackage(String source, Properties props) {
		String marker = props.getProperty("google.drive.studentdir.suffix");
		String[] sourceDirs = source.split("\\\\");
		String studentName = null;
		for(int index = sourceDirs.length - 1; index >= 0 && studentName == null; index--) {
			String sourceDir = sourceDirs[index];
			int markerIndex = sourceDir.indexOf(marker);
			if(markerIndex > 0)
				studentName = sourceDir.substring(0, markerIndex);
		}
		return buildDestPackage(studentName);
	}
	
	private static String buildDestPackage(String fullName) {
		String packageName = null;
		if(fullName != null) {
			int firstNameIndex = -1;
			for(int charIndex = fullName.length() - 1; charIndex >= 0 && firstNameIndex < 0; charIndex--) {
				if(Character.isUpperCase(fullName.charAt(charIndex)))
					firstNameIndex = charIndex;
			}
			if(firstNameIndex > 0)
				packageName = fullName.substring(0, firstNameIndex) + "/" + fullName.substring(firstNameIndex) + "/";
		}
		return packageName.toLowerCase();
	}

}
