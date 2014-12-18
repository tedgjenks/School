package edu.cb.songlist.white.zackery;

import java.util.*;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;


public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO AUTO-generated constructor stub
	}
	 @Override
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> ca = getDownloadList();
		for(int index = 0; index < ca.size(); index ++){
			String name = ca.get(index).getTitle();
			if(name.equals(title)){
				return ca.get(index);
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		// TODO Auto-generated method stub
		List<DownloadInfo> dl = getDownloadList();
		for (String title:titles){
			DownloadInfo songInfo = getDownloadInfo(title);
			if(songInfo == null){
				dl.add(new DownloadInfo(title));
			}else{
				songInfo.incrementTimesDownloaded();
			}
		}
		
	}

}


