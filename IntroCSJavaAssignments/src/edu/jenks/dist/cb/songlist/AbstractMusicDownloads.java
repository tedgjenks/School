/**
 * 
 */
package edu.jenks.dist.cb.songlist;

import java.util.*;

/**
 * @author JenksT
 *
 */
public abstract class AbstractMusicDownloads {
	
	private List<DownloadInfo> downloadList;

	/**
	 * Creates the list of downloaded information. 
	 */
	public AbstractMusicDownloads() {
		downloadList = new ArrayList<DownloadInfo>();
	}
	
	/** 
	 * Returns a reference to the <code>DownloadInfo</code> object with the requested title if it exists. <br/>  
	 * <b>Precondition</b>: <code>title</code> is not <code>null</code><br/>
	 * <b>Postcondition</b>:   
	 *   - no changes were made to <code>downloadList</code>.  
	 *  @param title the requested title  
	 *  @return a reference to the <code>DownloadInfo</code> object with the  
	 *          title that matches the parameter <code>title</code> if it exists in the list; 
	 *          null otherwise. 
	 */ 
	public abstract DownloadInfo getDownloadInfo(String title);
	
	/** 
	 * Updates downloadList with information from titles. <br/>
	 *  <b>Postcondition</b>:<br/>   
	 *   - <code>downloadList</code> can be accessed with <code>getDownloadList()</code><br/>
	 *   - there are no duplicate titles in <code>downloadList</code>. <br/>
	 *   - no entries were removed from <code>downloadList</code>. <br/> 
	 *   - all songs in <code>titles</code> are represented in <code>downloadList</code>.  <br/>
	 *   - for each existing entry in <code>downloadList</code>, the download count is increased by <br/> 
	 *       the number of times its title appeared in titles.  <br/>
	 *   - the order of the existing entries in <code>downloadList</code> is not changed.  <br/>
	 *   - the first time an object with a title from titles is added to <code>downloadList</code>, it  <br/>
	 *       is added to the end of the list.  <br/>
	 *   - new entries in <code>downloadList</code> appear in the same order  <br/>
	 *       in which they first appear in titles.   <br/>
	 *   - for each new entry in <code>downloadList</code>, the download count is equal to  <br/>
	 *       the number of times its title appeared in titles.
	 * @param titles a list of song titles 
	 */ 
	public abstract void updateDownloads(List<String> titles);

	/**
	 * @return the download list
	 */
	public List<DownloadInfo> getDownloadList() {
		return downloadList;
	}

	/**
	 * @param downloadList
	 */
	public void setDownloadList(List<DownloadInfo> downloadList) {
		this.downloadList = downloadList;
	}

}
