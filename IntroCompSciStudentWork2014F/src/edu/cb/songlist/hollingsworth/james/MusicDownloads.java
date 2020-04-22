package edu.cb.songlist.hollingsworth.james;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public DownloadInfo getDownloadInfo(String title) {
		for(DownloadInfo tmp : getDownloadList()) {
			if(tmp.getTitle().equals(title)) return tmp;
		}
		return null;
	}

	public void updateDownloads(List<String> titles) {
		for(DownloadInfo di : getDownloadList()) {
			for(int i = 0; i < titles.size();) {
				if(di.getTitle().equals(titles.get(i))) {
					di.incrementTimesDownloaded();
					titles.remove(i);
				}
				else i++;
			}
		}
		for(int t = 0; t < titles.size(); t++) {
			DownloadInfo di = new DownloadInfo(titles.get(t));
			for(int i = t + 1; i < titles.size();) {
				if(di.getTitle().equals(titles.get(i))) {
					di.incrementTimesDownloaded();
					titles.remove(i);
				}
				else i++;
			}
			getDownloadList().add(di);
		}
	}
	
	public static void main(String[] args) {
		MusicDownloads m = new MusicDownloads();
		ArrayList<String> tmp = new ArrayList<String>();
		tmp.add("Hello");
		tmp.add("Hell");
		tmp.add("Hello");
		m.updateDownloads(tmp);
		System.out.println(m.getDownloadList());
	}

}
