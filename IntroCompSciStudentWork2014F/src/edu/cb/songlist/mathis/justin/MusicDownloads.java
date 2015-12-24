package edu.cb.songlist.mathis.justin;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> a = this.getDownloadList();
		for(int i = 0; i < a.size(); i++){
			if (a.get(i).getTitle().equals(title))
				return a.get(i);
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> songtitles) {
		for(int i = 0; i < songtitles.size(); i++){
			this.sort(songtitles.get(i));
		}


	}
	public void sort(String title){
		List<DownloadInfo> a = this.getDownloadList();
		for(int i = 0; i < a.size(); i++){
			if (this.getDownloadInfo(title)==null){
				DownloadInfo NewTitle = new DownloadInfo(title);
				NewTitle.setTimesDownloaded(NewTitle.getTimesDownloaded()-1);
				a.add(NewTitle);
				
			}
			else if (a.get(i).getTitle().equals(title)){
				this.getDownloadInfo(title).incrementTimesDownloaded();
			}
				
		}
	}

}
