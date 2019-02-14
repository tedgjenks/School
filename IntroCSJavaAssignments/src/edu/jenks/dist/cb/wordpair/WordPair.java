package edu.jenks.dist.cb.wordpair;

/**
 * @author Jenks
 *
 */
public class WordPair {

	private String first, last;
	
	/**
	 * @param first
	 * @param last
	 */
	public WordPair(String first, String last) {
		this.first = first;
		this.last = last;
	}

	/**
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		WordPair arg = (WordPair)obj;
		return first.equals(arg.first) && last.equals(arg.last);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return new StringBuilder(20).append("(").append(first).append(", ").append(last).append(")").toString();
	}
}
