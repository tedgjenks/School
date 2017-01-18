package edu.jenks.util;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class FileUtil {

	public static Iterator<String> retrieveLinesFromFile(String path) throws IOException {
		return retrieveLinesFromFile(path, null, null);
	}
	
	public static Iterator<String> retrieveLinesFromFile(String path, String comment) throws IOException {
		return retrieveLinesFromFile(path, comment, null);
	}
	
	public static Iterator<String> retrieveLinesFromFile(String path, String comment, String sentinel) throws IOException {
		File packageList = new File(path);
		Scanner scanner = new Scanner(packageList);
		List<String> packages = new LinkedList<String>();
		boolean sentinelReached = false;
		while(scanner.hasNextLine() && !sentinelReached) {
			String line = scanner.nextLine().trim();
			if(line.startsWith(sentinel))
				sentinelReached = true;
			else if(line.length() > 0 && (comment == null || !line.startsWith(comment)))
				packages.add(line);
		}
		scanner.close();
		return packages.iterator();
	}
	
	public static boolean renameFile(File currentFile, File newFile) {
		boolean success = false;
		if(currentFile != null && newFile != null && currentFile.exists())
			success = currentFile.renameTo(newFile);
		return success;
	}
	
	public static File[] listFiles(String directory, final Pattern pattern) {
		File fileDir = new File(directory);
		return listFiles(fileDir, pattern);
	}
	
	public static File[] listFiles(File directory, final Pattern pattern) {
		FilenameFilter textFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				Matcher matcher = pattern.matcher(name);
				return  matcher.matches();
			}
			
		};
		return directory.listFiles(textFilter);
	}
}
