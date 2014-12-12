package edu.cb.songlist.guareschi.marco;
import java.util.*;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub	
	}
	@Override
	public DownloadInfo getDownloadInfo (String title) {
		List<DownloadInfo> downloadList = getDownloadList();
		for(int i = 0; i < downloadList.size(); i++) {
			String name = downloadList.get (i).getTitle();
			if (name.equals(title)) {
				return downloadList.get(i);	
			}
			
		}
		
		return null;
	}

	
	@Override
	public void updateDownloads (List<String> titles) {
		for(String title : titles) {
			DownloadInfo info = getDownloadInfo (title);
			if (info != null) {
				info.incrementTimesDownloaded();
			}else{
				getDownloadList().add(new DownloadInfo(title));
			}
		}
	}
}