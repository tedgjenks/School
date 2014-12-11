/**
 * 
 */
package edu.cb.songlist.jenks.ted;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

/**
 * @author JenksT
 *
 */
public class MusicDownloads extends AbstractMusicDownloads {

	/**
	 * 
	 */
	public MusicDownloads() {
		super();
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.songlist.AbstractMusicDownloads#getDownloadInfo(java.lang.String)
	 */
	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> downloadList = getDownloadList();
		DownloadInfo downloadInfo = null;
		for(int index = downloadList.size() - 1; index >=0 && downloadInfo == null; index--) {
			DownloadInfo curDownloadInfo = downloadList.get(index);
			if(title.equals(curDownloadInfo.getTitle()))
				downloadInfo = curDownloadInfo;
		}
		return downloadInfo;
	}

	/* (non-Javadoc)
	 * @see edu.jenks.dist.cb.songlist.AbstractMusicDownloads#updateDownloads(java.util.List)
	 */
	@Override
	public void updateDownloads(List<String> titles) {
		for(String title : titles) {
			DownloadInfo downloadInfo = getDownloadInfo(title);
			if(downloadInfo == null)
				getDownloadList().add(new DownloadInfo(title));
			else
				downloadInfo.incrementTimesDownloaded();
		}
	}

}
