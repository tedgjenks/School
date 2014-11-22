package edu.jenks.google.drive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CopyFilesFromProperties {
	
	public static void main(String[] args) {
		System.out.println("Begin");
		Properties props = new Properties();
		try {
			FileReader fr = new FileReader("googleDrive.properties");
			props.load(fr);
			fr.close();
			File turninDirFile = new File(props.getProperty("google.drive.turnin"));
			File[] studentDirs = CopyFileHelper.getStudentDirectories(turninDirFile, props.getProperty("google.drive.studentdir.suffix"));
			for(int index = studentDirs.length - 1; index >= 0; index--) {
				String fileName = props.getProperty("filename");
				String source = studentDirs[index].getPath() + "\\" + fileName;
				Student student = CopyFileHelper.getStudent(source, props.getProperty("google.drive.studentdir.suffix"));
				CopyFileHelper.copyStudentFilesFromTurnIn(source, fileName, props.getProperty("eclipse.student.python.sourcecode") + props.getProperty("eclipse.package"), student);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("End");
	}
}
