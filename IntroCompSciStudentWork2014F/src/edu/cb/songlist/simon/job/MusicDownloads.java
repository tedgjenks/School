package edu.cb.songlist.simon.job;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	int songlist=-1;
	@Override
	public DownloadInfo getDownloadInfo(String title) {
		// TODO Auto-generated method stub
		for(int i=0;i<this.getDownloadList().size(); i++) {
			if(this.getDownloadList().get(i).getTitle().equals(title))
				songlist=i;
		}
		if(songlist!=-1){
			return this.getDownloadList().get(songlist);
		}
		else 
			return null;
	}
	@Override
	public void updateDownloads(List<String> titles) {
		// TODO Auto-generated method stub
		
	}

}
