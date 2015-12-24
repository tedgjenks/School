package edu.cb.songlist.ball.daniel;

import java.util.ArrayList;
import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads 
{
	
	public MusicDownloads() 
	{
		
	}

	@Override
	public DownloadInfo getDownloadInfo(String title)
	{
		List<DownloadInfo> playlist = getDownloadList();
		boolean found = false;
		for (int i = 0; i < playlist.size() && found == false; i++)
		{
			String comp = playlist.get(i).getTitle();
			if (comp.equals(title))
			{
				found = true;
				return getDownloadList().get(i);
			}
		}
		return null;
	}

	@Override
	public void updateDownloads(List<String> titles) 
	{
		for (int i = 0; i < titles.size(); i++)
		{
			String song = titles.get(i);
			if (getDownloadInfo(song) == null)
			{
				DownloadInfo add = new DownloadInfo(song);
				getDownloadList().add(add);
			}
			else
			{
				getDownloadInfo(song).incrementTimesDownloaded();
			}
		}
		/*
		int timesFound = 0;
		String newSong = ("");
			for (int i = 0; i < titles.size(); i++)
			{
				newSong = titles.get(i);
				for (int z = 0; z < getDownloadList().size(); z++)
				{
					String oldSong = getDownloadList().get(z).getTitle();
					if (newSong.equals(oldSong))
					{
						getDownloadList().get(z).incrementTimesDownloaded();
						timesFound++;
					}
				}
				if (timesFound == 0)
				{
					DownloadInfo add = new DownloadInfo(newSong);
					getDownloadList().add(add);
				}
			}
		}*/
	}
}

