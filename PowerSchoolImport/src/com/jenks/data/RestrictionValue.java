package com.jenks.data;

import java.util.*;

public class RestrictionValue {
	private Map<String, Boolean> values = new HashMap<>(5);
	
	public void addValue(String value, boolean matches) {
		values.put(value, matches);
	}
	
	public boolean passRestriction(String value) {
		return values.containsKey(value) && values.get(value);
	}
}
