package edu.cb.songlist.higginbotham.andrew;


import java.util.List;
import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;


public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public DownloadInfo getDownloadInfo(String title) {
		List<DownloadInfo> list = getDownloadList();
		if(title !=null)
		for(int index = 0;index < list.size() - 1;index++ )
			if((list.get(index).getTitle()).equals(title))
				return list.get(index);
		return null;
	}

	
	@Override
	public void updateDownloads(List<String> songTitles) {
		List<DownloadInfo> list = getDownloadList();
		for(int index = 0; index < songTitles.size() - 1; index++)
			if(getDownloadInfo(songTitles.get(index)) != null)
				list.get(index).incrementTimesDownloaded();
			else{
				DownloadInfo newSong = new DownloadInfo(songTitles.get(index));
				list.add(list.size() +1, newSong);
			}
		
	}

}
