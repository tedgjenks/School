package edu.cb.songlist.carter.noah;

import java.util.List;

import edu.jenks.dist.cb.songlist.*;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		super();
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> curDownloadInfo = getDownloadList();
		for(int i = 0; i < curDownloadInfo.size();i++){
			String placeholder = curDownloadInfo.get(i).getTitle();
				if (placeholder.equals(title)){
					return curDownloadInfo.get(i);
				}
		}
			return null;
	}

	@Override
	public void updateDownloads(List<String> titles) {
		for (String title:titles){
			DownloadInfo titleinfo = getDownloadInfo(title);
			if (titleinfo == null){
				getDownloadList().add(new DownloadInfo(title));
			}else{
				titleinfo.incrementTimesDownloaded();
			}
		}
		
	}

}
