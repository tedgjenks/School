package edu.jenks.search.test;

import java.util.logging.Level;

import edu.jenks.search.dist.SearchResults;
import edu.jenks.search.dist.Searcher;
import edu.jenks.test.Testable;
import edu.jenks.util.MathUtil;
import edu.jenks.util.ReflectionUtil;

public class SearchesTest extends Testable {
	
	private static final SearchesTest SINGLETON = new SearchesTest();
	private static final double RELATIVE_DELTA = .001;
	
	private Searcher searcher;
	
	public void test1Constructor() {
		searcher = createInstance();
		if(continueTesting) {
			totalPoints++;
			logger.log(Level.FINE, "Pass - instance created.");
		}
	}
	
	public void testSequentialSearch() {
		int arraySize = 1000;
		Integer[] ints = createIntegerArray(arraySize);
		
		int elementToFind = -1;
		SearchResults sr = searcher.sequentialSearch(ints, elementToFind);
		verifySequentialSearchResults("element not in array", sr, false, arraySize, arraySize, elementToFind);
		
		elementToFind = 0;
		sr = searcher.sequentialSearch(ints, elementToFind);
		verifySequentialSearchResults("find head", sr, true, elementToFind + 1, arraySize, elementToFind);
		
		elementToFind = arraySize - 1;
		sr = searcher.sequentialSearch(ints, elementToFind);
		verifySequentialSearchResults("find tail", sr, true, elementToFind + 1, arraySize, elementToFind);
		
		elementToFind = arraySize / 2;
		sr = searcher.sequentialSearch(ints, elementToFind);
		verifySequentialSearchResults("find middle", sr, true, elementToFind + 1, arraySize, elementToFind);
	}
	
	public void testBinarySearch() {
		int arraySize = 1;
		Integer[] ints = createIntegerArray(arraySize);
		int elementToFind = -1;
		SearchResults sr = searcher.binarySearch(ints, elementToFind);
		verifyBinarySearchResults("element not in small array", sr, false, arraySize, elementToFind);
		
		arraySize = 1000;
		ints = createIntegerArray(arraySize);
		elementToFind = -1;
		sr = searcher.binarySearch(ints, elementToFind);
		verifyBinarySearchResults("element not in large array", sr, false, arraySize, elementToFind);
		
		elementToFind = 0;
		String testDescription = "find head";
		sr = searcher.binarySearch(ints, elementToFind);
		if(!MathUtil.equals(calculateBinaryMax(arraySize), sr.getComparisons(), 1))
			logger.log(Level.INFO, "Fail - " + testDescription + " - comparisons");
		else
			verifyBinarySearchResults(testDescription, sr, true, arraySize, elementToFind);
		
		testDescription = "find tail";
		elementToFind = arraySize - 1;
		sr = searcher.binarySearch(ints, elementToFind);
		if(!MathUtil.equals(calculateBinaryMax(arraySize), sr.getComparisons(), 1))
			logger.log(Level.INFO, "Fail - " + testDescription + " - comparisons");
		else
			verifyBinarySearchResults(testDescription, sr, true, arraySize, elementToFind);
		
		elementToFind = arraySize / 3;
		sr = searcher.binarySearch(ints, elementToFind);
		verifyBinarySearchResults("find lower third", sr, true, arraySize, elementToFind);
		
		elementToFind = arraySize * 2 / 3;
		sr = searcher.binarySearch(ints, elementToFind);
		verifyBinarySearchResults("find upper third", sr, true, arraySize, elementToFind);
		
		String[] stringArray = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
		sr = searcher.binarySearch(stringArray, "i");
		verifyBinarySearchResults("find late element", sr, true, stringArray.length, 8);
	}
	
	private int calculateBinaryMax(int size) {
		return 1 + (int)Math.floor(Math.log(size) / Math.log(2));
	}
	
	private boolean verifyBinarySearchResults(String testDescription, SearchResults sr, boolean expFound, int expDataStructureSize, int expIndex) {
		int comparisonsMax = calculateBinaryMax(expDataStructureSize);
		double expRatio = sr.getComparisons() / (double)expDataStructureSize;
		boolean validSearchResults = verifyCommonSearchResults(sr, expFound, expDataStructureSize, expIndex)
				&& sr.getComparisons() <= comparisonsMax && MathUtil.equalsRelative(sr.compareToSizeRatio(), expRatio, RELATIVE_DELTA);
		processResults(testDescription, validSearchResults);
		return validSearchResults;
	}
	
	private boolean verifySequentialSearchResults(String testDescription, SearchResults sr, boolean expFound, int expComparisons, int expDataStructureSize, int expIndex) {
		double expRatio = expComparisons / (double)expDataStructureSize;
		boolean validSearchResults = verifyCommonSearchResults(sr, expFound, expDataStructureSize, expIndex)
				&& expComparisons == sr.getComparisons() && MathUtil.equalsRelative(sr.compareToSizeRatio(), expRatio, RELATIVE_DELTA);
		processResults(testDescription, validSearchResults);
		return validSearchResults;
	}
	
	private void processResults(String testDescription, boolean validSearchResults) {
		if(validSearchResults) {
			totalPoints++;
			logger.log(Level.FINE, "Pass - " + testDescription);
		} else
			logger.log(Level.INFO, "Fail - " + testDescription);
	}
	
	private boolean verifyCommonSearchResults(SearchResults sr, boolean expFound, int expDataStructureSize, int expIndex) {
		return sr != null && expFound == sr.isElementFound() && expIndex == sr.getDataStructureIndex() && expDataStructureSize == sr.getDataStructureSize();
	}
	
	private Integer[] createIntegerArray(int size) {
		Integer[] ints = new Integer[size];
		for(int index = size - 1; index >= 0; index--)
			ints[index] = index;
		return ints;
	}
	
	private Searcher createInstance() {
		Searcher searcher = null;
		try {
			searcher = (Searcher)ReflectionUtil.newInstance(studentPackage + ".Searches");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException e) {
			logger.log(Level.WARNING, "Fail - object creation failed; abort testing: " + e.getMessage());
			continueTesting = false;
		}
		return searcher;
	}

	@Override
	public String getLogFilePath() {
		return LOG_FILE_PATH_START + "search/test/log.txt";
	}

	@Override
	public Testable getSingleton() {
		return SINGLETON;
	}

	public static void main(String[] args) {
		SINGLETON.execute();
	}

}
