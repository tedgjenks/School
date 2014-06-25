package edu.jenks.search.dist;

/**
 * Encapsulates information about a search.
 * 
 * @author Ted Jenks
 *
 */
public class SearchResults {

	private boolean elementFound;
	private int comparisons;
	private int dataStructureSize;
	private int dataStructureIndex = -1;
	
	public int getDataStructureIndex() {
		return dataStructureIndex;
	}

	public void setDataStructureIndex(int dataStructureIndex) {
		this.dataStructureIndex = dataStructureIndex;
	}

	public void setDataStructureSize(int dataStructureSize) {
		this.dataStructureSize = dataStructureSize;
	}

	public SearchResults() {}
	
	public SearchResults(int dataStructureSize) {
		this.dataStructureSize = dataStructureSize;
	}
	
	/**
	 * @return the size of the data structure that was searched
	 */
	public int getDataStructureSize() {
		return dataStructureSize;
	}

	/**
	 * @return true if the element was found, otherwise false
	 */
	public boolean isElementFound() {
		return elementFound;
	}
	
	public void setElementFound(boolean elementFound) {
		this.elementFound = elementFound;
	}
	
	/**
	 * @return the number of comparisons performed by the search algorithm
	 */
	public int getComparisons() {
		return comparisons;
	}
	
	public void setComparisons(int comparisons) {
		this.comparisons = comparisons;
	}
	
	/**
	 * @return the ratio of comparisons to the data structure size
	 */
	public double compareToSizeRatio() {
		return comparisons / (double)dataStructureSize;
	}
}
