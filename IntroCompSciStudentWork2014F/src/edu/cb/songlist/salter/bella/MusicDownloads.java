package edu.cb.songlist.salter.bella;
import edu.jenks.dist.cb.songlist.*;
import java.util.*;
public class MusicDownloads extends AbstractMusicDownloads
{
    public static void main(String[] args) {
       System.out.println("Begin test of MusicDownloads");
       /** begin test of getDownloadInfo */
        
       /** begin test of upDateDownloads*/
        
       System.out.println("End test of MusicDownloads with no errors");
    }
    public MusicDownloads() {
    }
    public DownloadInfo getDownloadInfo(String title) {
        for(DownloadInfo info : getDownloadList()) {
            if(info.getTitle().equals(title)) {
                return info;
            }
        }
        return null;
    }
    public void updateDownloads(List<String> titles) {
        boolean inList = false;
        for(String title : titles) {
            inList = false;
            for(int i = 0; i < getDownloadList().size(); i++) {
                if(getDownloadList().get(i).getTitle().equals(title)) {
                    getDownloadList().get(i).incrementTimesDownloaded();
                    inList = true;
                }
            }
            if(!inList) {
                getDownloadList().add(new DownloadInfo(title));
            }
        }
    }
}
