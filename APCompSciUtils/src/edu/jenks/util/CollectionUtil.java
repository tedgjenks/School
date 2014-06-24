package edu.jenks.util;

import java.util.Collection;

public class CollectionUtil {

	public static int count(Collection<?> c, Object o) {
		int count = 0;
		if(c != null && o != null) {
			for(Object element : c) {
				if(o.equals(element))
					count++;
			}
		}
		return count;
	}
}
