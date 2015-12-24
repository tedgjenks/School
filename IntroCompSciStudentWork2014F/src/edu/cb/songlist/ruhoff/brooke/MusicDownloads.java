package edu.cb.songlist.ruhoff.brooke;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {
	public MusicDownloads(){
		
	}
	
	@Override
	public DownloadInfo getDownloadInfo(String title) {
		for(int count=0; count<getDownloadList().size(); count++){
			if(title.equals(getDownloadList().get(count).getTitle())){
				return getDownloadList().get(count);
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		for(int count=0; count<titles.size(); count++){
			if(getDownloadInfo(titles.get(count))!=null){
				getDownloadInfo(titles.get(count)).incrementTimesDownloaded();
			}
			else getDownloadList().add(getDownloadInfo(titles.get(count)));
		}
	}
	
}