package edu.cb.songlist.deloach.hunter;

import java.util.*;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		
	}
	
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> downloadList=getDownloadList();
		for(int index=0; index < downloadList.size(); index++) {
			String name = downloadList.get(index).getTitle();
			if (name.equals(title)) {
				return downloadList.get(index);
			}
		}
		return null;
			
	}
		
	
	public void updateDownloads (List<String> arg0) {
		
	}
}
