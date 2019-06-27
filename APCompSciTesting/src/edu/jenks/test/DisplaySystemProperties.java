package edu.jenks.test;
import static java.lang.System.out;
public class DisplaySystemProperties {

	public static void main(String[] args) {
		String key = "user.dir";
		out.println(key + "=" + System.getProperty(key));
		key = "java.ext.dirs";
		out.println(key + "=" + System.getProperty(key));
		key = "java.home";
		out.println(key + "=" + System.getProperty(key));
	}

}
