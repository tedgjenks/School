package edu.cb.songlist.clark.jessica;

import java.util.List;


import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;


import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		// TODO Auto-generated method stub
		for(int index = 0; index < getDownloadList().size(); index++){
			if( getDownloadList().get(index).getTitle().equals(title)){
				return getDownloadList().get(index);
			}	
				
		}
		return null;
	}

	

	@Override
	public void updateDownloads(List<String> titles) {
		// TODO Auto-generated method stub
		for(int index = 0; index < titles.size(); index++){
			
			if (getDownloadInfo(titles.get(index))==null){
				getDownloadList().add(new DownloadInfo(titles.get(index)));
				getDownloadInfo(titles.get(index)).setTimesDownloaded(0);
			
			}
			getDownloadInfo(titles.get(index)).incrementTimesDownloaded();
		}
		

	}

}
