package edu.cb.songlist.page.javin;
import java.util.List;

import edu.jenks.dist.cb.songlist.*;
public class MusicDownloads extends AbstractMusicDownloads {

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		for(DownloadInfo a : getDownloadList()) {
			if(title.contentEquals(a.getTitle()))return a;
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		// TODO Auto-generated method stub
		for(int i = 0; i < titles.size(); i++) {
			for(DownloadInfo a: getDownloadList()) {
				if(a.getTitle().equals(titles.get(i))){
					a.setTimesDownloaded(a.getTimesDownloaded() + 1);
					titles.remove(i);
					i--;
				}
			}
		}
		for(String b : titles) {
			getDownloadList().add(new DownloadInfo(b));
		}
	}

}
