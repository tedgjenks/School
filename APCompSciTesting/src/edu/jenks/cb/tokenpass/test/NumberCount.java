/**
 * 
 */
package edu.jenks.cb.tokenpass.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ted
 *
 */
class NumberCount {
	
	private final Map<Integer, Integer> NUMBER_COUNT;

	/**
	 * 
	 */
	NumberCount(int capacity, int min, int max) {
		NUMBER_COUNT = new HashMap<Integer, Integer>(capacity);
		for(int index = max; index >= min; index--)
			NUMBER_COUNT.put(index, 0);
	}
	
	void tallyNumber(int number) {
		NUMBER_COUNT.put(number, NUMBER_COUNT.get(number) + 1);
	}
	
	int getCount(int number) {
		return NUMBER_COUNT.get(number);
	}

}
