package edu.cb.songlist.mariscal.juan;

import java.util.List;

import edu.jenks.dist.cb.songlist.AbstractMusicDownloads;
import edu.jenks.dist.cb.songlist.DownloadInfo;

public class MusicDownloads extends AbstractMusicDownloads {

	@Override
	public DownloadInfo getDownloadInfo(String song) {
		// TODO Auto-generated method stub
		
		
		List<DownloadInfo> a = this.getDownloadList();
		DownloadInfo b = null;
		for(int n = 0; n < a.size() && b == null; n++){
			DownloadInfo di = a.get(n);
			if(di.getTitle().equals(song)){
				b = a.get(n);
				
			}
		}
		
		return  b;
	}

	@Override
	public void updateDownloads(List<String> list) {
		// TODO Auto-generated method stub
		
		List<DownloadInfo> a = this.getDownloadList();
		for(int n = 0; n < list.size(); n++){
			String t = list.get(n);
			DownloadInfo di = new DownloadInfo(t);
			di.setTimesDownloaded(1);
			if(getDownloadInfo(t) != null){
				
				int in = findIndex(di.getTitle());
				a.get(in).setTimesDownloaded(di.getTimesDownloaded() + a.get(in).getTimesDownloaded());  
			}
			else{
				a.add(di);
			}
		}
	}
	private int findIndex(String a){
		int index = -1;
		
		for(int n = 0; n < this.getDownloadList().size(); n++){
			if(this.getDownloadList().get(n).getTitle().equals(a)){
				index = n;
			}
		}
		return index;
	}
	
	}

