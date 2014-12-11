package edu.cb.songlist.tran.duc;

import java.util.*;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> downloadList = new ArrayList<DownloadInfo>(getDownloadList());
		DownloadInfo returnedInfo = null;
		for(int index = 0; index < downloadList.size(); index++){
			if(title.equals(downloadList.get(index).getTitle())){
				returnedInfo = downloadList.get(index);
			}
		}
		return returnedInfo;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		
	}

}
