package edu.cb.songlist.patterson.andrew;

import java.util.*;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
	}
	@Override
	public DownloadInfo getDownloadInfo(String title){
		List<DownloadInfo> dl = getDownloadList();
		for (int index=0; index < dl.size(); index++){
			String name= dl.get(index).getTitle();
			if(name.equals(title)){
				return dl.get(index);
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		for(int index= 0; index < titles.size(); index++){
			String title= titles.get(index);
			DownloadInfo ud= getDownloadInfo(title);
			if(ud==null){
				getDownloadList().add(new DownloadInfo(title));
			}else{
				ud.incrementTimesDownloaded();
			}
		}
		
	}

}
