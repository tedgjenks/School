/**
 * 
 */
package edu.jenks.dist.cb.songlist;

/**
 * For each download, a <code>DownloadInfo</code> object stores a song's title and the number of times it has been downloaded. <br/>
 * There should be exactly one <code>DownloadInfo</code> for each unique song title.
 * @author JenksT
 *
 */
public class DownloadInfo {

	private String title;
	private int timesDownloaded;

	/** Creates a new instance with the given unique title and sets the  
	 *  number of times downloaded to 1. 
	 *  @param title the unique title of the downloaded song 
	 */ 
	public DownloadInfo(String title) {
		this.title = title;
		timesDownloaded = 1;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Increment the number times downloaded by 1
	 */
	public void incrementTimesDownloaded() {
		timesDownloaded++;
	}

	/**
	 * @return the number of times downloaded
	 */
	public int getTimesDownloaded() {
		return timesDownloaded;
	}

	/**
	 * @param timesDownloaded
	 */
	public void setTimesDownloaded(int timesDownloaded) {
		this.timesDownloaded = timesDownloaded;
	}
}
