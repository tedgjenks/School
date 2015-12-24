package edu.cb.songlist.hines.jonathan;

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
		DownloadInfo d = new DownloadInfo(title);
		for(int i = 0; i < getDownloadList().size(); i++)
		{
			if(!d.getTitle().equals(title))
			{
				d = null;
			}
		}		
	return d;
	}

	@Override
	public void updateDownloads(List<String> downLoadList)
	{
		//downLoadList.getDownloadInfo
		
	}
	
}
