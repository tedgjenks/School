package edu.cb.songlist.patterson.andrew;

import java.util.*;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
	}

	public DownloadInfo getDownloadInfo(String title){
		List<DownloadInfo> dl = getDownloadList();
		for (int index=0; index<dl.size();){
			String name= dl.get(index).getTitle();
			if(name.equals(title)){
				return dl.get(index);
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		
		
	}

}
