package edu.cb.songlist.collier.serenity;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {
	
	public MusicDownloads(){
		
	}
	@Override
	public DownloadInfo getDownloadInfo(String title) {
		String DownloadInfo= new String(title);
		getDownloadInfo(title);
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		int title=0;
		
		for (int count=0; title<count; count++){
			Object getDownloadInfo=null;
			if(getDownloadInfo!=null&&title>count){
				count++;
			}
			else {
				getDownloadList().add((DownloadInfo) titles);
			}
				
			
		}
			
		
	}

}
