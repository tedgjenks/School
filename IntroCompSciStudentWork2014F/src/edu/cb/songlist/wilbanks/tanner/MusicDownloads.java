package edu.cb.songlist.wilbanks.tanner;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	public MusicDownloads() {
		
	}

	@Override
	public DownloadInfo getDownloadInfo(String selectedSong) {
		DownloadInfo song;
		//for (int Index = 0; Index < SongList)
		return null;
	}

	@Override
	public void updateDownloads(List<String> songTitles) {
		String selectedSong;
		for (int index = 0; index<songTitles.size();index++){
			selectedSong = songTitles.get(index);
			if (null == getDownloadInfo(selectedSong)){
				
			}
		}

	}

}
