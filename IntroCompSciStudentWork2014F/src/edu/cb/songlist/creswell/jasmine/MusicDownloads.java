package edu.cb.songlist.creswell.jasmine;
import edu.jenks.dist.cb.songlist.*;
import java.util.*;

public class MusicDownloads extends AbstractMusicDownloads
{
  
  public MusicDownloads() {
     super(); 
  }
  public DownloadInfo getDownloadInfo(String title) {
      for (DownloadInfo ele : getDownloadList()) {
          if (ele.getTitle().equals(title)) {
              return ele;
           }
        }
      return null;
  }
  public void updateDownloads(List<String> titles) {
      boolean found = false;
      for (String newSong : titles) {
          found=false;
          for (DownloadInfo song: getDownloadList()) {
              if (newSong.equals(song.getTitle())) {
                  song.incrementTimesDownloaded();
                  found=true;
               }
           }
           if (found==false) {
               getDownloadList().add(new DownloadInfo(newSong));
           }
        }
           
                  
      
  }
}
