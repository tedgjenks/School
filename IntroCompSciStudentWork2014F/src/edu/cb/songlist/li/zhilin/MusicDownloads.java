package edu.cb.songlist.li.zhilin;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
	}

	@Override
	public DownloadInfo getDownloadInfo(String Name) {
		for(int index = 0; index < (getDownloadList().size()); index++){
			if(Name.equals(getDownloadList().get(index).getTitle())){
				return getDownloadList().get(index);
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> arg0) {
		
		
	}
	
}
