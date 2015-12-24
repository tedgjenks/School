package edu.cb.songlist.slimmer.ben;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads
extends AbstractMusicDownloads {

	@Override
	public DownloadInfo getDownloadInfo(String songname) {
		int indexfound=-1;
		for(int i=0;i<this.getDownloadList().size();i++){
			if(this.getDownloadList().get(i).getTitle().equals(songname))
				indexfound=i;
		}
		if(indexfound!=-1){
			return this.getDownloadList().get(indexfound);
		}
		else
			return null;
	}

	@Override
	public void updateDownloads(List<String> newsongs) {
		while(newsongs.size()>0){
			String currentsong=newsongs.get(0);
			if(this.getDownloadInfo(currentsong)==null){
				this.getDownloadList().add(new DownloadInfo(currentsong));
				newsongs.remove(0);
			}
			else{
				DownloadInfo update= this.getDownloadInfo(currentsong);
				int spot= this.getDownloadList().indexOf(update);
				update.incrementTimesDownloaded();
				this.getDownloadList().set(spot, update);
				newsongs.remove(0);
			}
		}
		
	}

}
