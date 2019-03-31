package jenks2018.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class FileHelper {

	/**
	 * @return List where each element is a row
	 * @throws IOException
	 */
	public static String[] loadInput(String directory, String fileName) {
		List<String> input = new ArrayList<String>(500);
		Path path = FileSystems.getDefault().getPath(directory, fileName);
	    BufferedReader reader = null;
	    try {
			reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String line = null;
		    while((line = reader.readLine()) != null)
		    	input.add(line);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return input.toArray(new String[input.size()]);
	}
}
