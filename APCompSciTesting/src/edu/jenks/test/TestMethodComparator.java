package edu.jenks.test;

import java.lang.reflect.Method;
import java.util.Comparator;

public class TestMethodComparator implements Comparator<Method> {

	@Override
	public int compare(Method arg0, Method arg1) {
		if(arg0 == null || arg1 == null)
			return 0;
		String arg0Name = arg0.getName(), arg1Name = arg1.getName();
		if(!arg0Name.startsWith("test") || !arg1Name.startsWith("test"))
			return 0;
		return arg0Name.compareTo(arg1Name);
	}
}
