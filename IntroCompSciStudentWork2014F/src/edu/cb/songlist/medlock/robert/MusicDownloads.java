package edu.cb.songlist.medlock.robert;

import java.util.*;
import edu.jenks.dist.cb.songlist.*;




public class MusicDownloads extends AbstractMusicDownloads {
	public MusicDownloads() {
	}
	
	
	
	
	@Override
	public DownloadInfo getDownloadInfo(String title) {
		for(int index = 0; index < getDownloadList().size(); index++){
			if( getDownloadList().get(index).getTitle().equals(title)){
				return getDownloadList().get(index);
			} 
		}return null;
	}
	
	
	@Override
	public void updateDownloads(List<String> titles) {
		for(int index = 0; index < titles.size(); index++){
			if (getDownloadInfo(titles.get(index))==null){
				getDownloadList().add(new DownloadInfo(titles.get(index)));
				getDownloadInfo(titles.get(index)).setTimesDownloaded(0);
			} getDownloadInfo(titles.get(index)).incrementTimesDownloaded();
		}
		
	}
	
}