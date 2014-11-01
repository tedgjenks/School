/**
 * 
 */
package edu.frstats.jenks.dist;

import java.util.List;

/**
 * Creates and maintains student score information.
 * 
 * @author Ted Jenks
 *
 */
public interface StatsRecorder {
	
	/**
	 * Records a score in the database, keeping the database in increasing score order.<br/>
	 * If no other ScoreInfo object represents score,<br/>
	 * a new ScoreInfo object representing score is added to the database;<br/>
	 * otherwise, the frequency in the ScoreInfo object representing score is incremented.<br/>
	 * @param score a score to be recorded in the list
	 * @return true if a new ScoreInfo object representing score was added to the list; false otherwise
	 */
	public boolean record(int score);
	
	/**
	 * Records all score in stuScores in the database, keeping the database in increasing score order.
	 * @param stuScores
	 */
	public void recordScores(int[] stuScores);
	
	/**
	 * @return the list of ScoreInfo objects (this should never be null; an empty list should be returned if no scores have been recorded)
	 */
	public List<ScoreInfo> getScoreList();
}
