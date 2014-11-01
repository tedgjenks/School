package edu.jenks.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

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
}
